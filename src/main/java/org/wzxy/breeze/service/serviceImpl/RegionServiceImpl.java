package org.wzxy.breeze.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.regionMapper;
import org.wzxy.breeze.model.dto.RegionDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.region;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.service.Iservice.IRegionService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Service
public class RegionServiceImpl  implements IRegionService {
    @Autowired
    private List<RegionDto> regionDtos;
    @Autowired
    private Page<RegionDto> RegionPage;
    @Resource
    private regionMapper regionDao;
    @Autowired
    private HandleResult handle ;
    private int exist=-1;

    @Override
    @Cacheable(value = "regionZone" , key = "'getAllRegions'")
    public List<region> getAllRegions() {
        return regionDao.findAllRegion();
    }

    @Override
    @Cacheable(value = "regionZone" , key = "'regionId'+#regionId")
    public List<region> getOwnRegions(int regionId) {
        return regionDao.findOwnRegions(regionId);
    }

    @Override
    @Cacheable(value = "regionZone" , key = "'findRegionByPage'+#nowPage+','+#pageSize")
    public Page<RegionDto> findRegionByPage(int nowPage, int pageSize) {
        List<region> regionByPage = regionDao.getRegionByPage(nowPage * pageSize, pageSize);
        regionDtos.clear();
        for (region r:
                regionByPage) {
            regionDtos.add( new RegionDto(r));
        }
        RegionPage.setDatas(regionDtos);
        RegionPage.setNowPage(nowPage+1);
        RegionPage.setDataTotalCount(regionDao.getTotalCount());
        RegionPage.setPageSize(pageSize);
        RegionPage.setPageTotalCount(RegionPage.getPageTotalCount());
        return RegionPage;
    }

    @Override
    @CacheEvict(cacheNames = "regionZone",allEntries = true)
    public HandleResult addRegion(RegionDto regionDto) {
        exist= regionDao.isExist(regionDto.getRegionId());
        if (exist==0){
            if ( "0".equals(regionDto.getRegionPid()) ) {
                regionDto.setLevel("1");
            }else{
                region regionById
                        = regionDao.findRegionById(Integer.parseInt(regionDto.getRegionPid()));
                int i = Integer.parseInt(regionById.getLevel());
                i++;
                regionDto.setLevel(String.valueOf(i));
            }
            if(regionDao.addRegion( new region(regionDto))){
                handle.setStatus(ResponseCode.getOkcode());
                handle.setMessage("新增区域成功!");
            }else{
                handle.setStatus(ResponseCode.getFailcode());
                handle.setMessage("新增区域失败!");
            }

            return handle;

        }else{
            handle.setStatus(ResponseCode.getFailcode());
            handle.setMessage("新增失败，区域已存在!");
            return handle;
        }
    }

    @Override
    @CacheEvict(cacheNames = "regionZone",allEntries = true)
    public HandleResult updateRegion(RegionDto regionDto) {
        exist= regionDao.isExist(regionDto.getRegionId());
        if(exist==1){
            if ( "0".equals(regionDto.getRegionPid()) ) {
                regionDto.setLevel("1");
            }else{
                region regionById
                        = regionDao.findRegionById(Integer.parseInt(regionDto.getRegionPid()));
                int i = Integer.parseInt(regionById.getLevel());
                i++;
                regionDto.setLevel(String.valueOf(i));
            }
            if( regionDao.updateRegion( new region(regionDto))){
                handle.setStatus(ResponseCode.getOkcode());
                handle.setMessage("更新区域信息成功!");
            }else{
                handle.setStatus(ResponseCode.getFailcode());
                handle.setMessage("更新区域失败!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.getFailcode());
            handle.setMessage("更新失败，区域不存在!");
            return handle;
        }
    }

    @Override
    @CacheEvict(cacheNames = "regionZone",allEntries = true)
    public HandleResult deleteRegionById(int regionId) {
        exist= regionDao.isExist(regionId);
        if(exist==1){
            if(regionDao.deleteRegion(regionId)){
                handle.setStatus(ResponseCode.getOkcode());
                handle.setMessage("删除区域成功!");
            }else{
                handle.setStatus(ResponseCode.getFailcode());
                handle.setMessage("删除区域失败!");
            }
            return handle;
        }else{
            handle.setStatus(ResponseCode.getFailcode());
            handle.setMessage("删除失败，区域不存在!");
            return handle;
        }
    }

    @Override
    @Cacheable(value = "regionZone" , key = "'queryRegionById'+#regionId")
    public RegionDto queryRegionById(int regionId) {
        region regionById = regionDao.findRegionById(regionId);
        if(regionById!=null) {
            return  new RegionDto(regionById);
        }else {
            return null;
        }
    }
}
