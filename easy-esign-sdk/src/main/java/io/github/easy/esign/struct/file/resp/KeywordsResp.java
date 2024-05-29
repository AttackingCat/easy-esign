package io.github.easy.esign.struct.file.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 关键字信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeywordsResp {

    private List<KeywordPositions> keywordPositions;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class KeywordPositions {
        /**
         * 关键字
         */
        private String keyword;
        /**
         * 关键字是否检索到坐标值
         */
        private Boolean searchResult;
        /**
         * 关键字位置信息
         */
        private List<Positions> positions;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Positions {
            /**
             * 关键字所在页码
             */
            private Integer pageNum;
            /**
             * 关键字XY坐标值
             */
            private List<Coordinates> coordinates;

            @Data
            @AllArgsConstructor
            @NoArgsConstructor
            public static class Coordinates {
                /**
                 * X坐标
                 */
                private Double positionX;
                /**
                 * Y坐标
                 */
                private Double positionY;
            }
        }
    }
}
