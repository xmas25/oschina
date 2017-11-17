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
 * http://www.oschina.net/openapi/docs/news_detail
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/11/17 21:51
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsDetailTest {

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
                .add("id" ,"90682")
                .build() ;

        // 构造请求
        Request request = new Request.Builder()
                .url("https://www.oschina.net/action/openapi/news_detail")
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
    "author": "同一种调调",
    "id": 90682,
    "authorid": 811064,
    "title": "uiw 1.2.17 发布，基于 React 16 的组件库",
    "body": "<style type='text/css'>pre {white-space:pre-wrap;word-wrap:break-word;}</style><p><strong>uiw&nbsp;</strong>1.2.17 发布， 高品质的UI工具包，React 16+的组件库。</p> \n<p><img src=\"https://static.oschina.net/uploads/space/2017/1117/195411_G2Yu_2720166.png\"></p> \n<p><strong>更新内容：</strong><br></p> \n<ul> \n <li><p>修复没有代码检测文件匹配*.css。&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2F57128871bd84b87ec5a85d422b5be8d740394df4\" target=\"_blank\" rel=\"nofollow\">5712887</a></p></li> \n <li><p>添加 .editorconfig 文件.&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2Fd82dabfd804dd28aecec63fa21428c1e30b90186\" target=\"_blank\" rel=\"nofollow\">d82dabf</a></p></li> \n <li><p>给测试添加钩子包，触发precommit脚本代码检测&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2F2edf9a280ce3aaf6bf0786ad2cfea6b437032f43\" target=\"_blank\" rel=\"nofollow\">2edf9a2</a></p></li> \n <li><p>alert组件样式格式化修复警告&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2F0d9e780c5e00b51d8f31fd48ea0566b13e7d6442\" target=\"_blank\" rel=\"nofollow\">0d9e780</a></p></li> \n <li><p>添加代码风格测试&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2Fe7fe79aeae82c4c017340fade19311494390252a\" target=\"_blank\" rel=\"nofollow\">e7fe79a</a></p></li> \n <li><p>组件Layout Col 添加测试(<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fpull%2F17\" target=\"_blank\" rel=\"nofollow\">#17</a>) :&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fkooff88\" target=\"_blank\" rel=\"nofollow\">@kooff88</a>&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2F47a616f458e563c298bb5cf0d358b163b725bbe6\" target=\"_blank\" rel=\"nofollow\">47a616f</a></p></li> \n <li><p>Affix和Breadcrumb添加测试用例 (<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fpull%2F18\" target=\"_blank\" rel=\"nofollow\">#18</a>) :&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2FXing-He\" target=\"_blank\" rel=\"nofollow\">@xing.he</a>&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2F18e123beb489639ef17ff63edd857caeb27607a0\" target=\"_blank\" rel=\"nofollow\">18e123b</a></p></li> \n <li><p>Tree组件添加文档。&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2Fc459fe748d61c172f74f397c416e6d83b22a8d5d\" target=\"_blank\" rel=\"nofollow\">c459fe7</a></p></li> \n <li><p>添加新的组件Tree。&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2Fb10f140f0bf90a589c041d0cf0cdd3908907bdeb\" target=\"_blank\" rel=\"nofollow\">b10f140</a></p></li> \n <li><p>添加ayout Row 测试用例 (<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fpull%2F15\" target=\"_blank\" rel=\"nofollow\">#15</a>) :&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fkooff88\" target=\"_blank\" rel=\"nofollow\">@kooff88</a>&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2Fdd98efc817c7dfa169e1ff3933bd9c5d348521c2\" target=\"_blank\" rel=\"nofollow\">dd98efc</a></p></li> \n <li><p>添加Layout文档藐视错误 (<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fpull%2F13\" target=\"_blank\" rel=\"nofollow\">#13</a>) :&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fkooff88\" target=\"_blank\" rel=\"nofollow\">@kooff88</a>&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2Fa8aabc4d4b7241202526c8e0c7820fb8a2943510\" target=\"_blank\" rel=\"nofollow\">a8aabc4</a></p></li> \n <li><p>提取公共房方法scrollTop&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2F6468a2f9877d38d378c7c694e8e95c6e8537d7de\" target=\"_blank\" rel=\"nofollow\">6468a2f</a></p></li> \n <li><p>从doc中删除多余的propsstatus并添加测试用例。 :&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2FXing-He\" target=\"_blank\" rel=\"nofollow\">@xing.he</a>&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2F4dfeaae0d32c7718f07f65dfe9d22d3814095a4a\" target=\"_blank\" rel=\"nofollow\">4dfeaae</a></p></li> \n <li><p>InputNumber 组件添加默认值 (<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fpull%2F11\" target=\"_blank\" rel=\"nofollow\">#11</a>) :&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fkooff88\" target=\"_blank\" rel=\"nofollow\">@kooff88</a>&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2F3de17409b4ae6ad59d568ba456db1d63240e77c8\" target=\"_blank\" rel=\"nofollow\">3de1740</a></p></li> \n <li><p>Affix组件修复scrollTop为0的问题。&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2F699a175742134393fe31d2b00b8e972ca5cdbf38\" target=\"_blank\" rel=\"nofollow\">699a175</a></p></li> \n <li><p>组件Avatar添加测试用例 :&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2FXing-He\" target=\"_blank\" rel=\"nofollow\">@xing.he</a>&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2Fb24b5febf62747e80000b2163312597c927101de\" target=\"_blank\" rel=\"nofollow\">b24b5fe</a></p></li> \n <li><p>组件input添加测试用例(<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fpull%2F9\" target=\"_blank\" rel=\"nofollow\">#9</a>)&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2F3eb889ba9b05c45a3dd3979cf19da0cee3abd91a\" target=\"_blank\" rel=\"nofollow\">3eb889b</a>&nbsp;(<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fpull%2F8\" target=\"_blank\" rel=\"nofollow\">#8</a>) :&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fkooff88\" target=\"_blank\" rel=\"nofollow\">@kooff88</a>&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2F12e9c8df2359cf6dc2c1dd035e54cb9fdd0e9c5c\" target=\"_blank\" rel=\"nofollow\">12e9c8d</a></p></li> \n <li><p>改变文档例子的书写方式&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2F4815a8739e33c519804791f483e52d56dfae9a0e\" target=\"_blank\" rel=\"nofollow\">4815a87</a></p></li> \n <li><p>更新 uiw-iconfont图标字体&nbsp;<a href=\"https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fuiw-react%2Fuiw%2Fcommit%2Fdc76f409fb9a0a3c4615054c0a8288b2edfb48f0\" target=\"_blank\" rel=\"nofollow\">dc76f40</a></p></li> \n</ul> \n<p>新增图标： environment, eye, folder, man, user-add, user-delete, usergroup-delete, woman, icons.</p>",
    "pubDate": "2017-11-17 19:32:31",
    "favorite": 0,
    "url": "https://www.oschina.net/news/90682/uiw-1-2-17-released",
    "relativies": [
        {
            "title": "React 16.1.1 发布，构建用户界面的 JavaScript 库",
            "url": "https://www.oschina.net/news/90549/react-16-1-1"
        },
        {
            "title": "uiw-iconfont v1.2.4 发布，开源图标字体",
            "url": "https://www.oschina.net/news/90541/uiw-iconfont-1-2-4-released"
        },
        {
            "title": "uiw 1.2.16 发布，基于 React 16 的组件库",
            "url": "https://www.oschina.net/news/90489/uiw-1-2-16"
        },
        {
            "title": "React 16.1.0 发布，开源的 JavaScript 库",
            "url": "https://www.oschina.net/news/90435/react-16-1-0-release"
        },
        {
            "title": "uiw 1.2.15 发布，基于 React 16 的组件库",
            "url": "https://www.oschina.net/news/90420/uiw-1-2-15"
        },
        {
            "title": "uiw 1.2.14 发布，基于 React 16 的组件库",
            "url": "https://www.oschina.net/news/90355/uiw-1-2-14"
        },
        {
            "title": "uiw 1.2.13 发布，基于 React 16 的组件库",
            "url": "https://www.oschina.net/news/90288/uiw-1-2-13"
        },
        {
            "title": "React 16.1.0-beta 发布，开源的 JavaScript 库",
            "url": "https://www.oschina.net/news/90236/react-16-1-0-beta"
        },
        {
            "title": "uiw 1.2.12 发布，基于 React 16 的组件库",
            "url": "https://www.oschina.net/news/90197/uiw-1-2-12"
        },
        {
            "title": "uiw v1.2.10 发布， 基于 React 的 UI 组件库",
            "url": "https://www.oschina.net/news/90119/uiw-1-2-10"
        }
    ],
    "commentCount": 0,
    "notice": {
        "referCount": 0,
        "replyCount": 0,
        "msgCount": 0,
        "fansCount": 0
    }
}
--------------------------------------------------------------------------------------------------------------- */