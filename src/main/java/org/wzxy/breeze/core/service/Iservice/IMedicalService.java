package org.wzxy.breeze.core.service.Iservice;

import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.po.medical;
import org.wzxy.breeze.core.model.vo.Page;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface IMedicalService {

    List<medical> getAllMedical();

    Page<medical> findMedicalByPage(int nowPage, int pageSize);

    HandleResult addMedical(medical m) ;

    HandleResult updateMedical(medical m) ;

    medical findMedicalById(int medicalId);

    HandleResult deleteMedicalById(int medicalId);

}
