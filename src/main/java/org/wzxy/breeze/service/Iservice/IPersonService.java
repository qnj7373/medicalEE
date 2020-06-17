package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.FamilyDto;
import org.wzxy.breeze.model.dto.PersonDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.Page;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface IPersonService {


    Page<PersonDto> findPersonByPage(int famicode, int nowPage, int pageSize);

    HandleResult addPerson(PersonDto personDto) ;

    HandleResult deletePersonById(int perscode);

    HandleResult updatePerson(PersonDto personDto) ;

    PersonDto findPersonById(int perscode);

    List<PersonDto> findPersonByHolder(String name,int regionId);

    List<PersonDto> findPersonByCardID(String cardID,int regionId);

}
