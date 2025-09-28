package io.github.easy.esign.struct.file.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeywordsReq {
    //关键字列表，能够通过该值定位到在文件中的位置坐标。
    //补充说明：
    //（1）允许一次查找多个关键字，请使用英文逗号分隔；
    //（2）关键字不支持特殊字符、符号等Adobe无法解析的字符；
    private List<String> keywords;
}
