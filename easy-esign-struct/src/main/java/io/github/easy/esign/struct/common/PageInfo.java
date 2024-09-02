package io.github.easy.esign.struct.common;

import lombok.Data;

@Data
public class PageInfo {
    /**
     * 当前页码 1
     */
    Integer pageNum = 1;
    /**
     * 每页大小 20
     */
    Integer pageSize = 20;
}
