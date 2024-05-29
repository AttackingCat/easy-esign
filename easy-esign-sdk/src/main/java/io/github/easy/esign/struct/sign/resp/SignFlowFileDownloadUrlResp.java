package io.github.easy.esign.struct.sign.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * https://open.esign.cn/doc/opendoc/pdf-sign3/pvfkwd
 * <p>
 * 获取签署页面链接
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignFlowFileDownloadUrlResp {

    /**
     * 签署短链接（有效期180天）
     */
    List<FileInfo> files;

    /**
     * 签署长链接（永久有效）
     * <p>
     * 【注】支持自定义域名，微信小程序H5内嵌场景需要使用长链接
     */
    List<FileInfo> attachments;

    /**
     * 海外签证书报告下载地址
     */
    String certificateDownloadUrl;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FileInfo {
        //文件ID
        String fileId;
        //文件名称
        String filename;
        //文件下载链接（有效期为60分钟，过期后可以重新调用接口获取新的下载地址）
        String downloadUrl;
    }
}
