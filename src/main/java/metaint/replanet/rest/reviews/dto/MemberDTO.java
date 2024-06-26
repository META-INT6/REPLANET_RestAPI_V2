package metaint.replanet.rest.reviews.dto;

import lombok.*;
import metaint.replanet.rest.auth.entity.MemberRole;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
    private Long memberCode;
    private String memberEmail;
    private String memberName;
    private String password;
    private String phone;
    private Date joinDate;
    private MemberRole memberRole;
    private String withdraw;
    private Date withdrawDate;
    private int currentPoint;
}