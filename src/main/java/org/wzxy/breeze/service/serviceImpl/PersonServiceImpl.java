package org.wzxy.breeze.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.familyMapper;
import org.wzxy.breeze.mapper.paymentMapper;
import org.wzxy.breeze.mapper.personMapper;
import org.wzxy.breeze.model.dto.FamilyDto;
import org.wzxy.breeze.model.dto.PersonDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.family;
import org.wzxy.breeze.model.po.person;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.service.Iservice.IPersonService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    protected List<PersonDto> personDtoList;
    @Resource
    private paymentMapper payDao;
    @Autowired
    protected Page<PersonDto> personPage;
    @Resource
    private familyMapper familyDao;
    @Resource
    private personMapper personDao;
    @Autowired
    private HandleResult handle ;
    private int exist=-1;

    @Override
    @Cacheable(value = "personZone" , key = "'findPersonByPage'+#famicode+','+#nowPage+','+#pageSize")
    public Page<PersonDto> findPersonByPage(int famicode, int nowPage, int pageSize) {
        List<person> personsByPage = personDao.getPersonsByPage(famicode, nowPage * pageSize, pageSize);
        personDtoList.clear();
        for (person p:
                personsByPage) {
            personDtoList.add(new PersonDto(p));
        }
        personPage.setDatas(personDtoList);
        personPage.setNowPage(nowPage+1);
        personPage.setDataTotalCount(personDao.getTotalCount(famicode));
        personPage.setPageSize(pageSize);
        personPage.setPageTotalCount(personPage.getPageTotalCount());
        return personPage;


    }

    @Override
    @CacheEvict(cacheNames = "personZone",allEntries = true)
    public HandleResult addPerson(PersonDto personDto) {
        exist=personDao.isExist(personDto.getPerscode());
        //户主、配偶家庭中只能有一位
        //同时修改家庭中的户主姓名和人数
        if (exist==0){
            if("户主".equals(personDto.getRelation())||"配偶".equals(personDto.getRelation())){
                exist= personDao.isHolderOrPartnerExist(personDto.getFamicode(), personDto.getRelation());

                if(exist==1){
                    handle.setStatus(ResponseCode.FAIL.getCode());
                    handle.setMessage("新增失败，一个家庭中只能有一个户主或配偶!");
                }else {
                    if("户主".equals(personDto.getRelation()) ){

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        personDto.setBirthday(dateFormat.format(personDto.getBirthTime()));
                        person person = new person(personDto);

                        if(personDao.addPerson(person)){
                            handle.setStatus(ResponseCode.OK.getCode());
                            handle.setMessage("新增家庭成员成功!");
                        }else{
                            handle.setStatus(ResponseCode.FAIL.getCode());
                            handle.setMessage("新增家庭成员失败!");
                        }

                        family familyByFamicode = familyDao.findFamilyByFamicode(personDto.getFamicode());
                        familyByFamicode.setHolderName(personDto.getPersname());
                        familyByFamicode.setPerscode(person.getPerscode());
                        familyDao.updateFamily(familyByFamicode);

                        return handle;
                    }else{
                        //新增吧
                        this.add(personDto);
                    }

                }

            }else{
                //新增吧
                this.add(personDto);
            }
            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("新增失败，家庭成员已存在!");
            return handle;
        }
    }


    @Override
    @CacheEvict(cacheNames = "personZone",allEntries = true)
    public HandleResult deletePersonById(int perscode) {
        exist=personDao.isExist(perscode);
        if(exist==1){
            person personById = personDao.findPersonById(perscode);
            String relation =personById.getRelation();
            family familyByPerscode = familyDao.findFamilyByFamicode(personById.getFamicode());
            //家庭人口数-1
            ///如果是户主还需删除家庭档案中户主的信息
            if("户主".equals(relation)){
                familyByPerscode.setPerscode(0);
                familyByPerscode.setHolderName("暂无信息");
            }

            if(personDao.deletePerson(perscode)){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("删除家庭成员成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("删除家庭成员失败!");
            }

            int popuNum = familyByPerscode.getPopuNum();
            familyByPerscode.setPopuNum(--popuNum);
            familyDao.updateFamily(familyByPerscode);

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("删除失败，家庭成员不存在!");
            return handle;
        }


    }

    @Override
    @CacheEvict(cacheNames = "personZone",allEntries = true)
    public HandleResult updatePerson(PersonDto personDto) {

        exist=personDao.isExist(personDto.getPerscode());
        //户主、配偶家庭中只能有一位
        //同时修改家庭中的户主姓名
        if (exist==1){
            if("户主".equals(personDto.getRelation())||"配偶".equals(personDto.getRelation())){
                exist= personDao.isHolderOrPartnerExist(personDto.getFamicode(), personDto.getRelation());
                String relation = personDao.findPersonById(personDto.getPerscode()).getRelation();

                if(exist==1 && !( "户主".equals(relation)||"配偶".equals(relation) ) ){
                    handle.setStatus(ResponseCode.FAIL.getCode());
                    handle.setMessage("更新失败，一个家庭中只能有一个户主或配偶!");
                }else {
                    if("户主".equals(personDto.getRelation()) ){
                        if(personDto.getBirthTime()!=null){
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            personDto.setBirthday(dateFormat.format(personDto.getBirthTime()));
                        }
                        if( personDao.updatePerson(new person(personDto))){
                            handle.setStatus(ResponseCode.OK.getCode());
                            handle.setMessage("更新家庭成员成功!");
                        }else{
                            handle.setStatus(ResponseCode.FAIL.getCode());
                            handle.setMessage("更新家庭成员失败!");
                        }
                        family familyByFamicode = familyDao.findFamilyByFamicode(personDto.getFamicode());
                        familyByFamicode.setHolderName(personDto.getPersname());
                        familyDao.updateFamily(familyByFamicode);

                        handle.setStatus(ResponseCode.OK.getCode());
                        handle.setMessage("更新家庭成员成功!");
                        return handle;
                    }else{
                        //更新
                        this.update(personDto);
                    }

                }

            }else{
                //更新
                this.update(personDto);
            }
            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("更新失败，家庭成员不存在!");
            return handle;
        }


    }

    @Override
    @Cacheable(value = "personZone" , key = "'findPersonById'+#perscode")
    public PersonDto findPersonById(int perscode)
    {
        person personById = personDao.findPersonById(perscode);
        return new PersonDto(personById );
    }

    @Override
    @Cacheable(value = "personZone" , key = "'findPersonByHolder'+#name+','+#regionId")
    public List<PersonDto> findPersonByHolder(String name,int regionId) {

        List<person> personByHolder = personDao.findPersonByHolder(regionId,name);
        personDtoList.clear();
        for (person p:
                personByHolder) {
            personDtoList.add( new PersonDto(p));
        }

        return personDtoList;
    }

    @Override
    @Cacheable(value = "personZone" , key = "'findPersonByCardID'+#cardID+','+#regionId")
    public List<PersonDto> findPersonByCardID(String cardID, int regionId) {
   /*  系统根据身份证号校验该身份证号是否参合，如果未参合，系统提示未参合信息并退出慢病报销
        功能界面；如果参合，系统自动显示参合农民的基本信息*/
        int pay = payDao.isPay(cardID);
        personDtoList.clear();
            if (pay==0){    //未参合
                return  null;
            }else {
                List<person> personByCardID = personDao.findPersonByCardIDToExpense(regionId, cardID);

                for (person p:
                        personByCardID) {
                    personDtoList.add( new PersonDto(p));
                }
            }

        return personDtoList;
    }


    private  void add(PersonDto personDto){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        personDto.setBirthday(dateFormat.format(personDto.getBirthTime()));
        if( personDao.addPerson( new person(personDto))){
            handle.setStatus(ResponseCode.OK.getCode());
            handle.setMessage("新增家庭成员成功!");
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("新增家庭成员失败!");
        }
        family familyByFamicode = familyDao.findFamilyByFamicode(personDto.getFamicode());
        int popuNum = familyByFamicode.getPopuNum();
        familyByFamicode.setPopuNum(++popuNum);

        familyDao.updateFamily(familyByFamicode);

    }


    private  void update(PersonDto personDto){

        if(personDto.getBirthTime()!=null){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            personDto.setBirthday(dateFormat.format(personDto.getBirthTime()));
        }
        if( personDao.updatePerson(new person(personDto)) ){
            handle.setStatus(ResponseCode.OK.getCode());
            handle.setMessage("更新家庭成员成功!");
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("更新家庭成员失败!");
        }

    }

}
