package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.OrganizationDto;
import org.wzxy.breeze.model.dto.RoleDto;
import org.wzxy.breeze.model.dto.UserDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.menu;
import org.wzxy.breeze.model.po.organization;
import org.wzxy.breeze.model.vo.Page;

import java.util.List;
import java.util.Map;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface IOrganizationService {

    List<organization> getAllOrgans();

    List<Map<String,String>> getTreeOfAdd();

    Page<OrganizationDto> findOrganByPage(int nowPage, int pageSize);

    HandleResult addOrgan(OrganizationDto organDto) ;

    HandleResult updateOrgan(OrganizationDto organDto) ;

    HandleResult deleteOrganById(int Id);

    OrganizationDto queryOrganById(int  administrationId) ;

    List<Map<String,String>> getTreeOfHave(int administrationId);

    List<Map<String,String>> getOrganOfHave(int uid);

}
