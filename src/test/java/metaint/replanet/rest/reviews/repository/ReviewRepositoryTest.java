package metaint.replanet.rest.reviews.repository;

import metaint.replanet.rest.reviews.entity.Review;
import metaint.replanet.rest.reviews.entity.ReviewFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewFileRepository reviewFileRepository;

    @Autowired
    private CampaignReviewRepository campaignReviewRepository;

    @DisplayName("검색어 sorting 테스트")
    @Test
    public void getResultBySearchValue() {

        //given
        String searchFilter = "입니다";

        //when
        List<Review> filteredReviewList = reviewRepository.findFilteredReviews(searchFilter);

        //then
        int expectedSize = 2;
        Assertions.assertEquals(expectedSize, filteredReviewList.size());
    }

    @DisplayName("리뷰 썸네일 찾아오기 테스트")
    @Test
    public void getSpecificReviewThumbnailPath() {
        //given
        Long reviewCode = 54L;

        //when
        ReviewFile reviewFile = reviewFileRepository.findByReviewCode(reviewCode);

        //then
        Assertions.assertNotNull(reviewFile);
    }

    @DisplayName("기존 리뷰 수정하기 테스트")
    @Test
    public void modifyReview() {}
        //given


}
