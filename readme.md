## 项目基于e签宝电子签名SaaS API V3版本进行开发，接入文档参考

***
_特别说明：由于并非所有API都会被经常使用，本项目仅提供通用场景的接口，如有特殊情况请fork一份进行扩展。_
***

https://open.esign.cn/doc/opendoc/apiv3-guide/tfb6gn

### 相关链接

> SaaS API V3版时序图
>
> https://open.esign.cn/doc/opendoc/apiv3-guide/dtsgz1

> SaaS API V3版API列表
>
> https://open.esign.cn/doc/opendoc/apiv3-guide/zkgi4m

> 常见场景对接说明
>
> https://open.esign.cn/doc/opendoc/apiv3-guide/al7kcc

> 错误码
>
>+ 实名认证和授权服务API 3.0
>- https://open.esign.cn/doc/opendoc/codemsg-v3/agi7xuv4yrw1i8f3
>+ 文件和模板服务API V3
>- https://open.esign.cn/doc/opendoc/codemsg-v3/px5yvgqf9glbs7o6
>+ 合同签署服务API V3
>- https://open.esign.cn/doc/opendoc/codemsg-v3/gts2lir7t1hwyndy
>+ 流程模板服务API V3
>- https://open.esign.cn/doc/opendoc/codemsg-v3/uir8r6eorxqxhb5n
>+ 印章服务API 3.0
>- https://open.esign.cn/doc/opendoc/codemsg-v3/gqgb0gwznb2su405
>+ 企业机构成员服务API 3.0
>- https://open.esign.cn/doc/opendoc/codemsg-v3/ckh528ox9a1k9u07

### 使用方式

#### 配置
- 配置文件，springboot中可以自动加载的情况

```yaml
logging:
  level:
    io.github.easy.esign: info #日志级别，推荐info，打印参数地址和返回值

esign-v3:
  print-banner: true #打印banner图
  default-config-name: app1
  auto-explanation: true #默认开启，是否自动解释错误，要求可以访问该域名 tapi.esign.cn
  proxy:
    host-name: 127.0.0.1 #代理地址
    port: 8080
  configs:
    - name: app1
      app-id: #e签宝的appId
      secret: #e钱包的secret
      sandbox: true #沙箱模式
    - name: app2
      app-id: #e签宝的appId
      secret: #e钱包的secret
      sandbox: false #沙箱模式
```

- 自定义配置，项目初始化时调用该方法自行注入

```java
ESignManager.init(new ESignConfigs());
```
#### 调用
- springboot项目

```java

@Autowired
private ESignOrgAuthSrv ESignOrgAuthSrv;

@GetMapping("/info")
public Object demo01(@RequestBody OrgIdentityInfoReq request) {
    return ESignOrgAuthSrv.identityInfo(request);
}
```

- 其他

``` java
ESignOrgAuthSrv signOrgAuthSrv = ESignOrgAuthSrv.getInstance();
ESignResp<OrgIdentityInfoResp> orgIdentityInfoResponseESignResp = signOrgAuthSrv.identityInfo(orgIdentityInfoReq);
```

#### 多APP切换
- 注解式，spring环境

```java
/**
 * 使用该注解切换e签宝app，此处value为配置的app name，非空
 * 默认app则不需要添加，可作用于类和方法上，方法优先级高于类
 * aop原理，aop失效会导致该注解失效
 */
@SwitchESignApp("app1")
```

- 编程式，不建议使用

```java
ESignOrgAuthSrv orgAuthSrv = ESignOrgAuthSrv.getInstance();
ESignManager.switchExecute("app1");
//业务逻辑：
ESignResp<OrgIdentityInfoResp> resp = orgAuthSrv.identityInfo(request);
ESignManager.clearExecute();
```

### 依赖引入（beta）
由于个人精力有限，未覆盖到所有接口的开发和测试。如有需求，请fork后自行开发，欢迎pr，感谢。

gradle: 
> implementation 'io.github.attackingcat:easy-esign-starter:1.0.9'
> 
maven:
```xml
<dependency>
    <groupId>io.github.attackingcat</groupId>
    <artifactId>easy-esign-starter</artifactId>
    <version>1.0.13</version>
</dependency>
```

#### 鸣谢，项目灵感来源于 https://github.com/psoho/fastesign
