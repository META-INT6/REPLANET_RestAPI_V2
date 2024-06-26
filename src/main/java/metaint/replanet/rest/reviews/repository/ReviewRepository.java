package metaint.replanet.rest.reviews.repository;

import metaint.replanet.rest.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {


    @Query(value = "select * from tbl_review " +
            "where review_title like %:searchFilter%", nativeQuery = true)
    List<Review> findFilteredReviews(@Param("searchFilter") String searchFilter);

    @Query("SELECT r.reviewCode FROM reviewPkg_entityReview r WHERE r.campaign.campaignCode = :campaignCode")
    Long findReviewCodeByCampaignCode(@Param("campaignCode") Long campaignCode);


    @Query(value = "select * from tbl_campaign_rev_file where review_code = :reviewCode", nativeQuery = true)
    List<Object> findByReviewCode(@Param("reviewCode")Long reviewCode);

    @Modifying
    @Query(value = "delete from tbl_review where review_code = :reviewCode", nativeQuery = true)
    void deleteByReviewCode(@Param("reviewCode") Long reviewCode);

   @Query(value = "SELECT r.*, c.*, o.*\n" +
            "FROM tbl_review r\n" +
            "LEFT JOIN tbl_campaign_description c ON r.campaign_code = c.campaign_code\n" +
            "LEFT JOIN tbl_org o ON c. org_code = o.org_code\n" +
            "ORDER BY r.review_code DESC", nativeQuery = true)
    List<Review> findAllOrderedByReviewCodeDesc();

    @Query(value = "SELECT r.*, c.*, o.*\n" +
            "FROM tbl_review r\n" +
            "LEFT JOIN tbl_campaign_description c ON r.campaign_code = c.campaign_code\n" +
            "LEFT JOIN tbl_org o ON c. org_code = o.org_code\n" +
            "WHERE o.org_code = :memberCode\n" +
            "ORDER BY r.review_code DESC", nativeQuery = true)
    List<Review> findAllOrgReviewsByMemberCode(@Param("memberCode") Long memberCode);


}
