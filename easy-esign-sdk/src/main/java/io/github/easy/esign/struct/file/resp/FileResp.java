package io.github.easy.esign.struct.file.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileResp {
    //文件ID
    private String fileId;
    //文件下载地址（有效期为60分钟，过期后可以重新调用接口获取新的下载地址）
    private String fileDownloadUrl;
    //文件名称
    private String fileName;
    //文件大小
    private int fileSize;
    //文件状态
    //0 - 文件未上传
    //1 - 文件上传中
    //2 - 文件上传已完成 或 文件已转换（HTML）
    //3 - 文件上传失败
    //4 - 文件等待转换（PDF）
    //5 - 文件已转换（PDF）
    //6 - 加水印中
    //7 - 加水印完毕
    //8 - 文件转化中（PDF）
    //9 - 文件转换失败（PDF）
    //10 - 文件等待转换（HTML）
    //11 - 文件转换中（HTML）
    //12 - 文件转换失败（HTML）
    private int fileStatus;
    //pdf文件总页数
    private int fileTotalPageCount;
    //首页宽度，单位：像素（px）
    private float pageWidth;
    //首页高度，单位：像素（px）
    private float pageHeight;
}
