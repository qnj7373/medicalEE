package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.OrganizationDto;
import org.wzxy.breeze.model.dto.RegionDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.region;
import org.wzxy.breeze.model.vo.Page;

import java.util.List;
import java.util.Map;

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
