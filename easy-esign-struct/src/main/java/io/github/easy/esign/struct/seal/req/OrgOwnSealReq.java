package io.github.easy.esign.struct.seal.req;

import io.github.easy.esign.struct.common.PageInfo;
import io.github.easy.esign.struct.constant.SealBizTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrgOwnSealReq extends PageInfo {
    String orgId;

    SealBizTypes sealBizTypes;
}
