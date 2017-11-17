package com.zlikun.open.configure;

import com.sun.tracing.dtrace.ProviderAttributes;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/11/17 21:23
 */
@Data
@Component
@ConfigurationProperties(prefix = "oschina")
public class AppProperties {

    String clientId ;
    String clientSecret ;
    String redirectUri ;

    /** 注意，该值可能会过期，仅用作api包下测试类使用 */
    String accessToken = "d285bd6e-8bb1-4065-91b5-5c430b892048" ;

}
