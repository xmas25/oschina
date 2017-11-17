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
 * http://www.oschina.net/openapi/docs/blog_list
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/11/17 21:13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogListTest {

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
                .add("pageIndex" ,"1")      // 分页页码，从1开始
                .add("pageSize" ,"3")       // 分页大小，默认：20条
                .build() ;

        // 构造请求
        Request request = new Request.Builder()
                .url("https://www.oschina.net/action/openapi/blog_list")
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
    "bloglist": [
        {
            "author": "beibugulf",
            "id": 1575308,
            "title": "没用的程序设计题-美甲帮笔试题",
            "authorid": 1767631,
            "type": 1,
            "pubDate": "2017-11-17 21:06:55.0",
            "commentCount": 0
        },
        {
            "author": "hblt-j",
            "id": 1575307,
            "title": "SparkSQL运行计划及调优",
            "authorid": 1998220,
            "type": 4,
            "pubDate": "2017-11-17 21:00:27.0",
            "commentCount": 0
        },
        {
            "author": "Friankin",
            "id": 1575305,
            "title": "leetcode -- Maximum Subarray",
            "authorid": 1447519,
            "type": 1,
            "pubDate": "2017-11-17 20:58:57.0",
            "commentCount": 0
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