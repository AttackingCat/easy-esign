package io.github.easy.esign.struct.file.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUpResp {
    //业务码，0表示成功，非0表示异常。
    private int errCode;
    //业务信息
    private String msg;
}
