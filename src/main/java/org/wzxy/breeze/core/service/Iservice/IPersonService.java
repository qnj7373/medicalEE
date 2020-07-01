package org.wzxy.breeze.core.service.Iservice;

import org.wzxy.breeze.core.model.dto.PersonDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.vo.Page;

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
