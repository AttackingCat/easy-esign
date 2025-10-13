package io.github.easy.esign.struct.file.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUrlReq {
    //文件内容MD5
    private String contentMd5;
    //目标文件的MIME类型 application/pdf
    private String contentType;
    //文件名称
    private String fileName;
    //文件大小
    private long fileSize;
    //是否需要转换成PDF文档，默认值 false。
    private boolean convertToPDF;
    //是否需要转换成HTML文档，默认值 false。
    private boolean convertToHTML;
    //专属云项目ID
    private String dedicatedCloudId;
}
