package org.wzxy.breeze.common.factory;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.wzxy.breeze.common.utils.getUId;
        import org.wzxy.breeze.core.model.po.HandleResult;
        import org.wzxy.breeze.core.model.vo.ResponseResult;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class CommonFactory {
    @Bean
    public HandleResult createHandleResult() {
        return new HandleResult();
    }

    @Bean
    public Logger createLogger(){
        return  LoggerFactory.getLogger(getClass());
    }


    @Bean
    public ResponseResult createResponseResult() {
        return new ResponseResult();
    }


}
