package org.wzxy.breeze.core.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.wzxy.breeze.common.utils.getUId;
import org.wzxy.breeze.core.mapper.organizationMapper;
import org.wzxy.breeze.core.mapper.userMapper;
import org.wzxy.breeze.core.model.po.User;
import org.wzxy.breeze.core.model.po.organization;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Service
public class getStatusService {

    @Resource
    private userMapper  userDao;
    @Resource
    private organizationMapper organizationDao;


    public int getMyRegionId(){
        organization organById = organizationDao.findOrganById(getAdministrationId());
        if (organById!=null){
            return  organById.getRegionId();
        }else{
            return 0;
        }
    }


    public int getAdministrationId(){
        User u = new User();
        u.setUid(getUId.getid());
        List<User> users = new ArrayList<>();
        users= userDao.findUserByFactor(u);
        if (users!=null){
            return  users.get(0).getAdministrationId();
        }else{
            return 0;
        }
    }


}
