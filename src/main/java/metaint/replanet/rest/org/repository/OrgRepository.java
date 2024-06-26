package metaint.replanet.rest.org.repository;

import metaint.replanet.rest.org.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrgRepository extends JpaRepository<Organization,Integer> {

    @Query(value = "SELECT m.member_email, " +
            "m.member_name, " +
            "m.phone, " +
            "o.org_description, " +
            "o.file_save_name " +
            "FROM tbl_member m " +
            "JOIN tbl_org o ON m.member_code = o.org_code " +
            "WHERE m.member_code = :memberCode"
    , nativeQuery = true)
    List<Object[]> selectOrgInformation(int memberCode);

    @Query(value = "UPDATE Organization " +
                    "SET fileOriginName = 'withdrawal', " +
                        "fileSaveName = 'withdrawal', " +
                        "fileSavePath = 'withdrawal', " +
                        "fileExtension = 'withdrawal' " +
                        "WHERE orgCode = :orgCode")
    @Modifying
    int updateFileDataByOrgCode(int orgCode);
}
