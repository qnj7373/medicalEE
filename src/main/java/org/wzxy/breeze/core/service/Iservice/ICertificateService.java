package org.wzxy.breeze.core.service.Iservice;

import org.wzxy.breeze.core.model.dto.certificateDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.vo.Page;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface ICertificateService {

    Page<certificateDto> findCertByPage(int  regionId, int nowPage, int pageSize);

    HandleResult addCert(certificateDto certDto,int regionId) ;

    HandleResult deleteCertById(int  certificateId);

    HandleResult updateCert(certificateDto certDto,int regionId) ;

    certificateDto findCertById(int  certificateId);






}
