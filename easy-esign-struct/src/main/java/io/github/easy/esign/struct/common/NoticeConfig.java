package io.github.easy.esign.struct.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoticeConfig {
    private String noticeTypes; // 通知类型，默认不通知（值为""空字符串），允许多种通知方式，请使用英文逗号分隔
}