package metaint.replanet.rest.bookmark.service;

import metaint.replanet.rest.bookmark.dto.BookmarkDTO;
import metaint.replanet.rest.bookmark.dto.BookmarkRegistDTO;
import metaint.replanet.rest.bookmark.entity.Bookmark;
import metaint.replanet.rest.bookmark.repository.BookmarkRepository;
import metaint.replanet.rest.campaign.dto.CampaignDesOrgDTO;
import metaint.replanet.rest.campaign.entity.Campaign;
import metaint.replanet.rest.campaign.entity.CampaignDescription;
import metaint.replanet.rest.point.entity.Member;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookmarkService {

    private BookmarkRepository bookmarkRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public BookmarkService(BookmarkRepository bookmarkRepository, ModelMapper modelMapper) {
        this.bookmarkRepository = bookmarkRepository;
        this.modelMapper = modelMapper;
    }

    // 북마크 등록
    @Transactional
    public Boolean addBookmark(BookmarkRegistDTO bookmarkDTO) {
        int result = 0;
        try {
            Bookmark bookmark = modelMapper.map(bookmarkDTO, Bookmark.class);

            bookmarkRepository.save(bookmark);
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result > 0 ? true : false;
    }

    // 북마크 멤버코드로 조회
    public List<BookmarkDTO> getBookmarkListByMember(String memberCode) {
        int changeCode = Integer.parseInt(memberCode);
        List<Bookmark> bookmarkEntity = bookmarkRepository.findByMemberCodeMemberCode(changeCode);

        return bookmarkEntity.stream()
                .map(bookmarkList -> modelMapper.map(bookmarkList, BookmarkDTO.class))
                .collect(Collectors.toList());
    }

    // 북마크 삭제
    @Transactional
    public Boolean deleteBookmark(String memberCode, String campaignCode) {
        int mCode = Integer.parseInt(memberCode);
        int cCode = Integer.parseInt(campaignCode);

        Member member = new Member(mCode);
        Campaign campaign = new Campaign(cCode);

        //int changeCode = Integer.parseInt(memberCode);
        int result = 0;
        try {
            result = bookmarkRepository.deleteByMemberCodeAndCampaignCode(member,campaign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result > 0 ? false : true;
    }

    // 북마크 전체삭제
    @Transactional
    public Boolean bookmarkDeleteAll(List<Integer> campaignCode) {
        int result = 0;
        try {
            result = bookmarkRepository.deleteAllByIds(campaignCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result > 0 ? false : true;
    }


}

