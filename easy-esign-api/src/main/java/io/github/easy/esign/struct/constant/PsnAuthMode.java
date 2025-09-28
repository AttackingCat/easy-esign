package io.github.easy.esign.struct.constant;

public enum PsnAuthMode {
    CODE_SMS,//短信验证码
    PSN_FACE_ALIPAY,//支付宝刷脸
    PSN_FACE_TECENT,//腾讯云刷脸
    PSN_FACE_ESIGN,//快捷刷脸
    PSN_FACE_WECHAT,//微信小程序刷脸（仅限微信小程序中使用）
    //以下方式如需使用，请联系交付顾问开通：
    PSN_AUDIO_VIDEO_ALIPAY,//支付宝智能视频认证
    PSN_AUDIO_VIDEO_WECHAT,//微信智能视频认证
}
