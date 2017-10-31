package com.zlikun.open.configure;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfigure {

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient.Builder()
                .connectTimeout(3 , TimeUnit.SECONDS)
                .readTimeout(5 ,TimeUnit.SECONDS)
                .writeTimeout(5 ,TimeUnit.SECONDS)
                .build() ;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper() ;
        // 设置不匹配的字段不报错(忽略即可)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES ,false) ;
        // 下划线命名与驼峰命名相互转换
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return objectMapper ;
    }

}
