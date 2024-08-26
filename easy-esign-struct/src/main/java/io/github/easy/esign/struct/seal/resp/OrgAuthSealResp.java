package io.github.easy.esign.struct.seal.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrgAuthSealResp {

    private String sealId;

    private String sealName;

    private String sealBizType;

    private int sealStyle;

    private String sealBizTypeDescription;

    private String authorizerOrgId;

    private String authorizerOrgName;

    private String sealAuthBizId;

    private long effectiveTime;

    private long expireTime;

    private String sealImageDownloadUrl;

    private int sealHeight;

    private int sealWidth;
}
