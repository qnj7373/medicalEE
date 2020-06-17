package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.FamilyDto;
import org.wzxy.breeze.model.dto.MenuDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.Page;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface IFamilyService {

    Page<FamilyDto> findFamilyByPage(int  regionId,int nowPage, int pageSize);

    HandleResult addFamily(FamilyDto familyDto) ;

    HandleResult deleteFamilyById(int  famicode);

    HandleResult updateFamily(FamilyDto familyDto) ;

    FamilyDto findFamilyById(int  famicode);

}
