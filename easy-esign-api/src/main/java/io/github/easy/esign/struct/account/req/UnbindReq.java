package io.github.easy.esign.struct.account.req;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnbindReq {
    /*
     * 账号
     */
    private String account;
    /**
     * 是否隐藏顶部导航栏，true 表示隐藏，false 表示不隐藏
     */
    private boolean hideTopBar;
    /**
     * 解绑成功后的重定向地址
     */
    private String redirectUrl;
    /**
     * 自定义业务编号，用于标识本次解绑任务
     */
    private String customBizNum;
}
