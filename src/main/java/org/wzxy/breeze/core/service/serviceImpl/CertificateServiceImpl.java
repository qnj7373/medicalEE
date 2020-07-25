package org.wzxy.breeze.core.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wzxy.breeze.core.mapper.certificateMapper;
import org.wzxy.breeze.core.mapper.personMapper;
import org.wzxy.breeze.core.model.dto.certificateDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.po.certificate;
import org.wzxy.breeze.core.model.vo.Page;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.service.Iservice.ICertificateService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Service
public class CertificateServiceImpl implements ICertificateService {

    @Resource
    private certificateMapper  certDao;
    @Resource
    private personMapper personDao;
    @Autowired
    private List<certificateDto> certDtoList;
    @Autowired
    private Page<certificateDto> certPage;
    @Autowired
    private  certificateDto certifiDto;
    @Autowired
    private HandleResult handle ;
    private int exist=-1;

    @Override
    @Cacheable(value = "certificateZone" , key = "'findCertByPage'+#regionId+','+#nowPage+','+#pageSize")
    public Page<certificateDto> findCertByPage(int regionId, int nowPage, int pageSize) {
        List<certificate> certificateByPage = certDao.getCertificateByPage(regionId, nowPage * pageSize, pageSize);
        certDtoList.clear();
        for (certificate c:
                certificateByPage) {
            certDtoList.add(new certificateDto(c));
        }
        certPage.setDatas(certDtoList);
        certPage.setNowPage(nowPage+1);
        certPage.setDataTotalCount(certDao.getTotalCount(regionId));
        certPage.setPageSize(pageSize);
        certPage.setPageTotalCount(certPage.getPageTotalCount());
        return certPage;
    }



    @Override
    @CacheEvict(cacheNames = "certificateZone",allEntries = true)
    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    public HandleResult addCert(certificateDto certDto,int regionId) {

        exist=certDao.isExist(certDto.getCertificateId());
        if (exist==0){
            exist=personDao.cardIDIsInManage(certDto.getCardID(), regionId);
            if(exist==1){

                int count=certDao.isOnlyOne(certDto.getCardID());
                if(count==0){
                    exist=personDao.cardIDIsExist(certDto.getCardID());
                    if (exist==1){
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date endDate = certDto.getEndDate();
                        Date startDate = certDto.getStartDate();
                        certDto.setStartTime(dateFormat.format(startDate));
                        certDto.setEndTime(dateFormat.format(endDate));
                        certDto.setRuralCard(personDao.findPersonByCardID(certDto.getCardID()).getRuralCard());

                        if(certDao.addCertificate(new certificate(certDto)) ){
                            handle.setStatus(ResponseCode.OK.getCode());
                            handle.setMessage("新增慢性病证信息成功!");
                        }else{
                            handle.setStatus(ResponseCode.FAIL.getCode());
                            handle.setMessage("新增慢性病证信息失败!");
                        }

                    }else{
                        handle.setStatus(ResponseCode.FAIL.getCode());
                        handle.setMessage("新增失败，该身份证号不存在!");
                    }
                }else{

                    handle.setStatus(ResponseCode.FAIL.getCode());
                    handle.setMessage("新增失败，此人已拥有慢性病证!");

                }


            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("新增失败，该身份证号不在管辖范围内!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("新增失败，该慢性病证已存在!");
            return handle;
        }
    }

    @Override
    @CacheEvict(cacheNames = "certificateZone",allEntries = true)
    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    public HandleResult deleteCertById(int certificateId) {
        exist=certDao.isExist(certificateId);
        if(exist==1){

            if( certDao.deleteCertificate(certificateId) ){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("删除慢性病证信息成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("新增慢性病证信息失败!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("删除失败，慢性病证信息不存在!");
            return handle;
        }
    }

    @Override
    @CacheEvict(cacheNames = "certificateZone",allEntries = true)
    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    public HandleResult updateCert(certificateDto certDto,int regionId) {


        exist=certDao.isExist(certDto.getCertificateId());
        if(exist==1){
            exist=personDao.cardIDIsInManage(certDto.getCardID(), regionId);
            if(exist==1){
                int count=certDao.isHave(certDto.getCardID());
                if(count==1||count==0){
                    exist=personDao.cardIDIsExist(certDto.getCardID());
                    if (exist==1){
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        if(certDto.getStartDate()!=null){
                            Date startDate = certDto.getStartDate();
                            certDto.setStartTime(dateFormat.format(startDate));
                        }
                        if(certDto.getEndDate()!=null){
                            Date endDate = certDto.getEndDate();
                            certDto.setEndTime(dateFormat.format(endDate));
                        }
                        certDto.setRuralCard(personDao.findPersonByCardID(certDto.getCardID()).getRuralCard());

                        if( certDao.updateCertificate(new certificate(certDto)) ){
                            handle.setStatus(ResponseCode.OK.getCode());
                            handle.setMessage("更新慢性病证信息成功!");
                        }else{
                            handle.setStatus(ResponseCode.FAIL.getCode());
                            handle.setMessage("更新慢性病证信息失败!");
                        }


                    }else{
                        handle.setStatus(ResponseCode.FAIL.getCode());
                        handle.setMessage("更新失败，该身份证号不存在!");
                    }

                }else{
                    handle.setStatus(ResponseCode.FAIL.getCode());
                    handle.setMessage("更新失败，此人已拥有慢性病证!");
                }



            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("更新失败，该身份证号不在管辖范围内!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("更新失败，慢性病证信息不存在!");
            return handle;
        }
    }

    @Override
    @Cacheable(value = "certificateZone" , key = "'findCertById'+#certificateId")
    public certificateDto findCertById(int certificateId) {
        certificate certificateByPeId = certDao.findCertificateById(certificateId);
        certifiDto= new certificateDto(certificateByPeId);
        certifiDto.setRuralCard(personDao.findPersonByCardID(certifiDto.getCardID()).getRuralCard());
        return certifiDto;
    }



}
