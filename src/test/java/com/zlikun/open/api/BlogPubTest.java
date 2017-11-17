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
 * http://www.oschina.net/openapi/docs/blog_pub
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/11/17 21:59
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogPubTest {

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
                .add("title" ,"测试文章-标题")
                .add("content" ,"测试文章-正文")
                // 保存到草稿 是：1 否：0
                .add("save_as_draft" ,"0")
                // 博客分类
                .add("catalog" ,"zlikun")
                // 博客摘要
                .add("abstracts" ,"测试文章-摘要")
                // 博客标签，用逗号隔开
                .add("tags" ,"xxx,yyy,zzz")
                // 系统博客分类
                .add("classification" ,"1575308")
                // 原创：1、转载：4
                .add("type" ,"1")
                // 转载的原文链接
                .add("origin_url" ,"https://zlikun.com/")
                // 公开：0、私有：1
                .add("privacy" ,"1")
                // 允许评论：0、禁止评论：1
                .add("deny_comment" ,"0")
                // 自动生成目录：0、不自动生成目录：1
                .add("auto_content" ,"1")
                // 非置顶：0、置顶：1
                .add("as_top" ,"1")
                .build() ;

        // 构造请求
        Request request = new Request.Builder()
                .url("https://www.oschina.net/action/openapi/blog_pub")
                .addHeader("User-Agent" ,userAgent)
                .post(body)
                .build() ;

        Response response = client.newCall(request).execute() ;

        log.info("user response code = {} ,message = {}" ,response.code() ,response.message());

        assertTrue(response.isSuccessful()) ;

        // {"error_description":"操作成功完成","error":"200"}
        log.info("/--------\n{}\n-----------------------------------------/" ,response.body().string());

    }

}
