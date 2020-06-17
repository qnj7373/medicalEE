package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.chronicdisDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.chronicdis;
import org.wzxy.breeze.model.po.s201_xx;
import org.wzxy.breeze.model.vo.Page;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface IS201_xxService {


    List<s201_xx> getAllS201_xx(String table);

    Page<s201_xx> findS201_xxByPage(String table,int nowPage, int pageSize);

    HandleResult addS201_xx(s201_xx s) ;

    HandleResult updateS201_xx(s201_xx s) ;

    s201_xx findS201_xxById(s201_xx s);

    HandleResult deleteS201_xxById(int id,String table);


}
