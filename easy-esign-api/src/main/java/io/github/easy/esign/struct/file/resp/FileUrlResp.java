package io.github.easy.esign.struct.file.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUrlResp {
    //文件ID
    private String fileId;
    //文件上传地址
    private String fileUploadUrl;
}
