package org.wzxy.breeze.core.service.Iservice;

import org.wzxy.breeze.core.model.dto.RegionDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.po.region;
import org.wzxy.breeze.core.model.vo.Page;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface IRegionService {

    List<region> getAllRegions();

    List<region> getOwnRegions(int regionId);

    Page<RegionDto> findRegionByPage(int nowPage, int pageSize);

    HandleResult addRegion(RegionDto regionDto) ;

    HandleResult updateRegion(RegionDto regionDto) ;

    HandleResult deleteRegionById(int regionId);

    RegionDto queryRegionById(int  regionId) ;



}
