package io.github.easy.esign.struct.seal.req;

import io.github.easy.esign.struct.common.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrgAuthorizedSealReq extends PageInfo {

    String orgId;

    String authorizerOrgId;
}
