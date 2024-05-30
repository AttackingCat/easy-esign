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
- 配置文件，springboot中可以自动加载的情况
```yaml
esign-v3:
  app-id: #e签宝的appId
  secret: #e钱包的secret
  sandbox: true #沙箱模式
  print-banner: true #打印banner图
  callback-url: #回调地址配置集合，如下
    sign-flow: https://www.123.com/ #这里的sign-flow为自定义key
logging:
  level:
    io.github.easy.esign: info #日志级别，推荐info，打印参数地址和返回值
```

- 配置文件，不用springboot自动加载的需要在resources下创建文件esign-v3.properties
```properties
esign-v3.app-id=
esign-v3.secret=
esign-v3.sandbox=true
esign-v3.print-banner=true
esign-v3.callback-url.sign-flow=https://www.123.com/
```

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
```
ESignOrgAuthSrv signOrgAuthSrv = ESignOrgAuthSrv.getInstance();
ESignResp<OrgIdentityInfoResp> orgIdentityInfoResponseESignResp = signOrgAuthSrv.identityInfo(orgIdentityInfoReq);
```
### 依赖引入（待发布）
> 
> 
#### 鸣谢，项目灵感来源于 https://github.com/psoho/fastesign 因业务需要在其基础上做了大量扩展