package org.wzxy.breeze.core.service.Iservice;

import org.wzxy.breeze.core.model.dto.chronicdisDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.po.chronicdis;
import org.wzxy.breeze.core.model.vo.Page;

import java.util.List;

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
