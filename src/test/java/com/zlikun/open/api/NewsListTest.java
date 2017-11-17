package com.zlikun.open.api;

import com.zlikun.open.configure.AppProperties;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * http://www.oschina.net/openapi/docs/news_list
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/11/17 21:47
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsListTest {

    @Autowired
    AppProperties properties ;

    @Autowired
    OkHttpClient client ;

    @Value("${http.header.user_agent}")
    String userAgent ;

    @Test
    public void request() throws IOException {

        // 构造请求参数
        FormBody body = new FormBody.Builder()
                .add("access_token" ,properties.getAccessToken())
                .add("dataType" ,"json")
                .add("catalog" ,"1")        // 1-所有|2-综合新闻|3-软件更新
                .add("pageIndex" ,"1")      // 分页页码，从1开始
                .add("pageSize" ,"3")       // 分页大小，默认：20条
                .build() ;

        // 构造请求
        Request request = new Request.Builder()
                .url("https://www.oschina.net/action/openapi/news_list")
                .addHeader("User-Agent" ,userAgent)
                .post(body)
                .build() ;

        Response response = client.newCall(request).execute() ;

        log.info("user response code = {} ,message = {}" ,response.code() ,response.message());

        assertTrue(response.isSuccessful()) ;

        log.info("/--------\n{}\n-----------------------------------------/" ,response.body().string());

    }

}
/* ---------------------------------------------------------------------------------------------------------------
{
    "newslist": [
        {
            "author": "同一种调调",
            "id": 90682,
            "title": "uiw 1.2.17 发布，基于 React 16 的组件库",
            "type": 4,
            "authorid": 811064,
            "pubDate": "2017-11-17 19:32:31",
            "commentCount": 0
        },
        {
            "author": "淡漠悠然",
            "id": 90680,
            "title": "Hibernate Validator 6.0.5 发布，包含重要的 bug 修复",
            "type": 4,
            "authorid": 2305107,
            "pubDate": "2017-11-17 16:27:30",
            "commentCount": 0
        },
        {
            "author": "赫杰辉",
            "id": 90679,
            "title": "携程 DAL 框架 Ctrip Dal 的 Java 客户端 1.13.1 发布",
            "type": 4,
            "authorid": 2915750,
            "pubDate": "2017-11-17 15:40:34",
            "commentCount": 10
        }
    ],
    "notice": {
        "referCount": 0,
        "replyCount": 0,
        "msgCount": 0,
        "fansCount": 0
    }
}
--------------------------------------------------------------------------------------------------------------- */