package io.github.easy.esign.struct.seal.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrgOwnSealResp {
    private String sealId;

    private String sealName;

    private Long sealCreateTime;

    private boolean defaultSealFlag;

    private int sealWidth;

    private int sealHeight;

    private String sealBizType;

    private String sealBizTypeDescription;

    private int sealStyle;

    private int sealStatus;

    private String statusDescription;

    private String rejectReason;

    private String sealImageDownloadUrl;
}
