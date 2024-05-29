package io.github.easy.esign.struct;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * e签宝响应
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ESignResp<T> {

    /**
     * 编码
     */
    Integer code;

    /**
     * 消息
     */
    String message;

    /**
     * 类型
     */
    T data;

}
