package io.github.easy.esign.struct.seal.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrgOwnSealsResp {
    private Integer total;
    private List<OrgOwnSealResp> seals;
}
