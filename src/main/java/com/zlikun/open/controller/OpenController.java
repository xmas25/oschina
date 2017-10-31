package com.zlikun.open.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 开放API控制器
 */
@Slf4j
@RestController
public class OpenController {

    @Autowired
    OkHttpClient client ;
    @Autowired
    ObjectMapper objectMapper ;

    @Value("${oschina.client_id}")
    String clientId ;
    @Value("${oschina.client_secret}")
    String clientSecret ;
    @Value("${oschina.redirect_uri}")
    String redirectUri ;
    @Value("${http.header.user_agent}")
    String userAgent ;

    /**
     * 回调请求
     * http://www.oschina.net/openapi/docs/oauth2_authorize
     * @return
     */
    @GetMapping("/callback")
    public Object callback(String code ,String state) {

        // code = Ktv5nM ,state =
        log.info("code = {} ,state = {}" ,code ,state) ;

        if (code == null) {
            // TODO error
        }

        TokenInfo tokenInfo = null ;

        // 获取 access_token 等信息
        try {
            Response response = queryToken(code) ;
            if (!response.isSuccessful()) {
                // TODO error
            }
            // 从响应信息中提取access_token等字段
            String json = response.body().string() ;

            // {"access_token":"6d56535d-36ff-4b08-ab9c-c004c2317b19","refresh_token":"b4ef0883-890f-4277-af11-211b6e7a6ec8","uid":1023355,"token_type":"bearer","expires_in":604799}
            log.info("token response = {}" ,json);

            tokenInfo = objectMapper.readValue(json ,TokenInfo.class) ;
            if (tokenInfo == null || tokenInfo.getAccessToken() == null || tokenInfo.getUid() == null) {
                // TODO error
            }
        } catch (IOException e) {
            log.error("查询accessToken信息出错!" ,e);
            // TODO error
        }

        UserInfo userInfo = null ;

        // 获取授权用户信息
        try {
            Response response = queryUser(tokenInfo.getAccessToken()) ;

            // 从响应信息中提取用户名等字段
            String json = response.body().string() ;

            //
            log.info("user response = {}" ,json);

            userInfo = objectMapper.readValue(json ,UserInfo.class) ;
            if (userInfo == null || userInfo.getId() == null) {
                // TODO error
            }
        } catch (IOException e) {
            log.error("查询授权用户信息出错!" ,e);
            // TODO error
        }

        return userInfo ;
    }

    @Data
    private static final class UserInfo {
        private String id ;
        private String email ;
        private String name ;
        private String gender ;
        private String avatar ;
        private String location ;
        private String url ;

        private String error ;
        private String errorDescription ;
    }

    /**
     * 根据 `access_token` 查询授权用户信息
     * @param accessToken
     * @return
     * @throws IOException
     */
    private Response queryUser(String accessToken) throws IOException {
        // 构造请求参数
        FormBody body = new FormBody.Builder()
                .add("access_token" ,accessToken)
                .add("dataType" ,"json")
                .build() ;

        // 构造请求
        Request request = new Request.Builder()
                .url("https://www.oschina.net/action/openapi/user")
                .addHeader("User-Agent" ,userAgent)
                .post(body)
                .build() ;

        Response response = client.newCall(request).execute() ;

        log.info("user response code = {} ,message = {}" ,response.code() ,response.message());

        return response ;
    }

    @Data
    private static final class TokenInfo {
        private String accessToken ;
        private String refreshToken ;
        private String tokenType ;
        private Long uid ;
        private Long expiresIn ;
    }

    /**
     * 根据 `token` 查询 `access_token` 等信息
     * http://www.oschina.net/openapi/docs/oauth2_token
     * @param code
     * @return
     */
    private Response queryToken(String code) throws IOException {

        // 构造请求参数
        FormBody body = new FormBody.Builder()
                .add("redirect_uri" ,redirectUri)
                .add("client_id" ,clientId)
                .add("client_secret" ,clientSecret)
                .add("grant_type" ,"authorization_code")
                .add("dataType" ,"json")
                .add("code" ,code)
                .build() ;

        // 构造请求
        Request request = new Request.Builder()
                .url("https://www.oschina.net/action/openapi/token")
                .addHeader("User-Agent" ,userAgent)
                .post(body)
                .build() ;

        Response response = client.newCall(request).execute() ;

        log.info("token response code = {} ,message = {}" ,response.code() ,response.message());

        return response ;

    }

}
