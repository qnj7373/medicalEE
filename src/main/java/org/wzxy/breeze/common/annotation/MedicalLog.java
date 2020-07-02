package org.wzxy.breeze.common.annotation;

import java.lang.annotation.*;

/**
 * @author 覃能健
 * @create 2020-07
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MedicalLog {

    String description();

}
