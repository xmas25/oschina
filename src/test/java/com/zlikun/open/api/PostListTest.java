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
 * http://www.oschina.net/openapi/docs/post_list
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/11/17 21:45
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostListTest {

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
                .add("catalog" ,"1")        // 类别ID 1-问答 2-分享 3-IT杂烩(综合) 4-站务 100-职业生涯 0-所有
//                .add("tag" ,"")             // 帖子相关标签
                .add("pageIndex" ,"1")      // 分页页码，从1开始
                .add("pageSize" ,"3")       // 分页大小，默认：20条
                .build() ;

        // 构造请求
        Request request = new Request.Builder()
                .url("https://www.oschina.net/action/openapi/post_list")
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
    "post_list": [
        {
            "answerCount": 25,
            "answer": {
                "name": "我是短发控",
                "time": "2017-11-16 13:01:44"
            },
            "author": "局长",
            "id": 2269719,
            "viewCount": 2704,
            "title": "高手问答第 177 期 —— iView 作者带来的 Vue.js 实战分享",
            "portrait": "https://static.oschina.net/uploads/user/1360/2720166_50.jpg?t=1470892376000",
            "authorid": 2720166,
            "pubDate": "2017-11-14 19:46:12"
        },
        {
            "answerCount": 1,
            "answer": {
                "name": "dqk1985",
                "time": "2017-11-13 22:08:18"
            },
            "author": "qwerttaa",
            "id": 2269448,
            "viewCount": 1278,
            "title": "【招募中】智能对话Chatbot服务搭建",
            "portrait": "https://www.oschina.net/img/portrait.gif",
            "authorid": 3341527,
            "pubDate": "2017-11-09 15:10:56"
        },
        {
            "answerCount": 7,
            "answer": {
                "name": "菜鸟游戏程序员",
                "time": "2017-11-17 17:05:32"
            },
            "author": "宋庆离",
            "id": 2269047,
            "viewCount": 2144,
            "title": "进击的程序员！4周带你玩转人工智能、区块链、物联网",
            "portrait": "https://static.oschina.net/uploads/user/1331/2663968_50.png?t=1469078818000",
            "authorid": 2663968,
            "pubDate": "2017-11-02 14:24:12"
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