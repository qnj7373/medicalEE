package org.wzxy.breeze.core.service.Iservice;

import org.wzxy.breeze.core.model.dto.FamilyDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.vo.Page;

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
