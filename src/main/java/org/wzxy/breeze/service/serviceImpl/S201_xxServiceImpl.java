package org.wzxy.breeze.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.s201_xxMapper;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.s201_xx;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.service.Iservice.IS201_xxService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Service
public class S201_xxServiceImpl  implements IS201_xxService {

    @Resource
    private s201_xxMapper s201_xxDao;

    @Autowired
    private List<s201_xx>  s201_xxDtoList;

    @Autowired
    private Page<s201_xx>   s201_xxPage;
    @Autowired
    private HandleResult handle ;
    private int exist=-1;

    @Override
    @Cacheable(value = "s201Zone" , key = "'getAllS201_xx'+#table")
    public List<s201_xx> getAllS201_xx(String table) {
        return s201_xxDao.getAlls201_xx(table);
    }

    @Override
    @Cacheable(value = "s201Zone" , key = "'findS201_xxByPage'+#table+','+#nowPage+','+#pageSize")
    public Page<s201_xx> findS201_xxByPage(String table, int nowPage, int pageSize) {
        s201_xxDtoList=s201_xxDao.getS201_xxByPage(table, nowPage*pageSize, pageSize);

        s201_xxPage.setDatas(s201_xxDtoList);
        s201_xxPage.setNowPage(nowPage+1);
        s201_xxPage.setDataTotalCount(s201_xxDao.getTotalCount(table));
        s201_xxPage.setPageSize(pageSize);
        s201_xxPage.setPageTotalCount(s201_xxPage.getPageTotalCount());
        return s201_xxPage;
    }

    @Override
    @CacheEvict(cacheNames = "s201Zone",allEntries = true)
    public HandleResult addS201_xx(s201_xx s ) {
        exist=s201_xxDao.isExist(s.getId(), s.getTable());
        if(exist==0){

            if(s201_xxDao.adds201_xx(s)){
                handle.setStatus(ResponseCode.getOkcode());
                handle.setMessage("新增成功!");
            }else{
                handle.setStatus(ResponseCode.getFailcode());
                handle.setMessage("新增失败!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.getFailcode());
            handle.setMessage("新增失败，已存在!");
            return handle;
        }
    }

    @Override
    @CacheEvict(cacheNames = "s201Zone",allEntries = true)
    public HandleResult updateS201_xx(s201_xx s) {
        exist=s201_xxDao.isExist(s.getId(), s.getTable());
        if(exist==1){
            if(s201_xxDao.updateS201_xx(s)){
                handle.setStatus(ResponseCode.getOkcode());
                handle.setMessage("更新成功!");
            }else{
                handle.setStatus(ResponseCode.getFailcode());
                handle.setMessage("更新失败!");
            }
            return handle;
        }else{
            handle.setStatus(ResponseCode.getFailcode());
            handle.setMessage("更新失败，不存在!");
            return handle;
        }
    }

    @Override
    public s201_xx findS201_xxById(s201_xx s) {
        return s201_xxDao.finds201_xxById(s.getId(), s.getTable());
    }

    @Override
    @CacheEvict(cacheNames = "s201Zone",allEntries = true)
    public HandleResult deleteS201_xxById(int id, String table) {
        exist=s201_xxDao.isExist(id, table);
        if(exist==1){
            if(s201_xxDao.deleteS201_xx(id, table)){
                handle.setStatus(ResponseCode.getOkcode());
                handle.setStatus(ResponseCode.getOkcode());
                handle.setMessage("删除成功!");
            }else{
                handle.setStatus(ResponseCode.getFailcode());
                handle.setMessage("删除失败!");
            }
            return handle;
        }else{
            handle.setStatus(ResponseCode.getFailcode());
            handle.setMessage("删除失败，不存在!");
            return handle;
        }
    }
}
