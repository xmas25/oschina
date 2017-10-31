# oschina

开源中国[`OpenAPI`](http://www.oschina.net/openapi/docs)接入

#### 流程
```
1、执行登录请求
https://www.oschina.net/action/oauth2/authorize?response_type=code&client_id={client_id}&redirect_uri={redirect_uri}
2、通过{redirect_uri}请求接收返回的`code`、`state`值
3、通过`code`交换`access_token`等信息
4、通过`access_token`调用资源方提供的API
```