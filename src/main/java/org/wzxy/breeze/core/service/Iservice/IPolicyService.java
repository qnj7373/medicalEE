package org.wzxy.breeze.core.service.Iservice;

import org.wzxy.breeze.core.model.dto.policyDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.po.policy;
import org.wzxy.breeze.core.model.vo.Page;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface IPolicyService {


    List<policy> getAllPolicy();

    Page<policyDto> findPolicyByPage(int nowPage, int pageSize);

    HandleResult addPolicy(policyDto pDto) ;

    HandleResult updatePolicy(policyDto pDto) ;

    policyDto findPolicyById(int id);

    HandleResult deletePolicyById(int id);

}
