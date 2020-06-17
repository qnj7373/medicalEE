package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.MenuDto;
import org.wzxy.breeze.model.dto.chronicdisDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.chronicdis;
import org.wzxy.breeze.model.po.menu;
import org.wzxy.breeze.model.vo.Page;

import java.util.List;
import java.util.Map;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface IChronicdisService {


    List<chronicdis> getAllChronicdis();

    Page<chronicdisDto> findChronicdisByPage(int nowPage, int pageSize);

    HandleResult addChronicdis(chronicdisDto chrDto) ;

    HandleResult updateChronicdis(chronicdisDto chrDto) ;

    chronicdisDto findChronicdisById(int illcode);

    HandleResult deleteChronicdisById(int illcode);

}
