package metaint.replanet.rest.bookmark.dto;

import lombok.*;
import metaint.replanet.rest.campaign.dto.CampaignDesOrgDTO;
import metaint.replanet.rest.campaign.dto.MemberDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookmarkDTO {

    private int bookmarkCode;
    private MemberDTO memberCode;
    private CampaignDesOrgDTO campaignCode;

    public BookmarkDTO(MemberDTO memberCode, CampaignDesOrgDTO campaignCode) {
        this.memberCode = memberCode;
        this.campaignCode = campaignCode;
    }
}
