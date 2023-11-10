package metaint.replanet.rest.campaign.service;

import metaint.replanet.rest.campaign.controller.CampaignController;
import metaint.replanet.rest.campaign.dto.CampaignDescriptionDTO;
import metaint.replanet.rest.campaign.entity.CampaignDescription;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.assertj.core.util.DateUtil.now;

@SpringBootTest
public class CampaignServiceTest {

    @Autowired
    private CampaignService campaignService;

    @DisplayName("캠페인 진행중 전체 조회 테스트")
    @Test
    public void campaignFindAllTest() {
        //when
        List<CampaignDescription> findCampaignList = campaignService.findCampaignList();
        //then
        Assertions.assertNotNull(findCampaignList);
        findCampaignList.forEach(System.out::println);
    }

    @DisplayName("캠페인 종료 전체 조회 테스트")
    @Test
    public void campaignDoneFindAllTest() {
        //when
        List<CampaignDescription> findCampaignList = campaignService.findCampaignListDone();
        //then
        Assertions.assertNotNull(findCampaignList);
        findCampaignList.forEach(System.out::println);
    }

    @DisplayName("캠페인 상세 조회 테스트")
    @Test
    public void campaignFindByIdTest() {
        //given
        int charityCode = 1;

        //when
        CampaignDescription findCampaign = campaignService.findCampaign(charityCode);

        //then
        Assertions.assertNotNull(findCampaign);
        System.out.println(findCampaign);
    }

    @DisplayName("캠페인 조건 조회 종료 임박 순")
    @Test
    public void campaignFindSortTest() {
        //given
        Date currentDate = now();

        //when
        List<CampaignDescription> findCampaignSort = campaignService.findCampaignSort(currentDate);

        //then
        Assertions.assertNotNull(findCampaignSort);

        findCampaignSort.forEach(System.out::println);

    }
}
