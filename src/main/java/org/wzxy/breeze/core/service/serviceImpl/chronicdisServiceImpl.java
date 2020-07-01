package org.wzxy.breeze.core.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.core.mapper.chronicdisMapper;
import org.wzxy.breeze.core.model.dto.chronicdisDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.po.chronicdis;
import org.wzxy.breeze.core.model.vo.Page;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.service.Iservice.IChronicdisService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Service
public class chronicdisServiceImpl  implements IChronicdisService {

    @Resource
    private chronicdisMapper   chronicdisDao;

    @Autowired
    private List<chronicdisDto>  chronicdisDtoList;

    @Autowired
    private Page<chronicdisDto>   chronicdisPage;
    @Autowired
    private HandleResult handle ;
    private int exist=-1;


    @Override
    @Cacheable(value = "chronicdisZone" , key = "'getAllChronicdis'")
    public List<chronicdis> getAllChronicdis() {
        return chronicdisDao.getAllChronicdis();
    }

    @Override
    @Cacheable(value = "chronicdisZone" , key = "'findChronicdisByPage'+#nowPage+','+#pageSize")
    public Page<chronicdisDto> findChronicdisByPage(int nowPage, int pageSize) {

        List<chronicdis> chronicdisByPage = chronicdisDao.getChronicdisByPage(nowPage * pageSize, pageSize);
        chronicdisDtoList.clear();
        for (chronicdis c:
        chronicdisByPage) {
            chronicdisDtoList.add(new chronicdisDto(c)) ;
        }
        chronicdisPage.setDatas(chronicdisDtoList);
        chronicdisPage.setNowPage(nowPage+1);
        chronicdisPage.setDataTotalCount(chronicdisDao.getTotalCount());
        chronicdisPage.setPageSize(pageSize);
        chronicdisPage.setPageTotalCount(chronicdisPage.getPageTotalCount());
        return chronicdisPage;
    }

    @Override
    @CacheEvict(cacheNames = "chronicdisZone",allEntries = true)
    public HandleResult addChronicdis(chronicdisDto chrDto) {
        exist=chronicdisDao.isExist(chrDto.getIllcode());
        if(exist==0){

            if(  chronicdisDao.addChronicdis(new chronicdis(chrDto)) ){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("新增慢性病成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("新增慢性病失败!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("新增失败，慢性病已存在!");
            return handle;
        }

    }

    @Override
    @CacheEvict(cacheNames = "chronicdisZone",allEntries = true)
    public HandleResult updateChronicdis(chronicdisDto chrDto) {
        exist=chronicdisDao.isExist(chrDto.getIllcode());
        if(exist==1){

            if( chronicdisDao.updateChronicdis(new chronicdis(chrDto)) ){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("更新慢性病成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("更新慢性病失败!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("更新失败，慢性病不存在!");
            return handle;
        }
    }

    @Override
    @Cacheable(value = "chronicdisZone" , key = "'findChronicdisById'+#illcode")
    public chronicdisDto findChronicdisById(int illcode) {
        return  new chronicdisDto(chronicdisDao.findChronicdisById(illcode));
    }

    @Override
    @CacheEvict(cacheNames = "chronicdisZone",allEntries = true)
    public HandleResult deleteChronicdisById(int illcode) {
        exist=chronicdisDao.isExist(illcode);
        if(exist==1){

            if( chronicdisDao.deleteChronicdis(illcode) ){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("删除慢性病成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("删除慢性病失败!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("删除失败，慢性病不存在!");
            return handle;
        }
    }
}
