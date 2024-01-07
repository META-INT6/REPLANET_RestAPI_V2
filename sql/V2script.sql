/* 
	DROP DATABASE REPLANET;
 */


CREATE DATABASE REPLANET;

/* 스키마 접속 */
USE REPLANET;

/* DDL */


CREATE TABLE `tbl_bookmark`
(
    `bookmark_code`    INTEGER NOT NULL AUTO_INCREMENT COMMENT '북마크코드',
    `member_code`    INTEGER NOT NULL COMMENT '회원코드',
    `campaign_code`    INTEGER NOT NULL COMMENT '캠페인코드',
    PRIMARY KEY ( `bookmark_code` )
) COMMENT = '북마크';


CREATE TABLE `tbl_campaign_description`
(
    `campaign_code`    INTEGER NOT NULL AUTO_INCREMENT COMMENT '캠페인코드',
    `campaign_title`    VARCHAR(500) NOT NULL COMMENT '캠페인제목',
    `campaign_content`    BLOB NOT NULL COMMENT '캠페인내용',
    `start_date`    DATETIME NOT NULL COMMENT '캠페인시작일자',
    `end_date`    DATETIME NOT NULL COMMENT '캠페인마감일자',
    `campaign_category`    VARCHAR(255) NOT NULL COMMENT '캠페인카테고리',
    `current_budget`    INTEGER DEFAULT 0 NOT NULL COMMENT '캠페인현재모금액',
    `goal_budget`    INTEGER NOT NULL COMMENT '캠페인목표액',
    `org_code`    INTEGER NOT NULL COMMENT '기부처코드',
    PRIMARY KEY ( `campaign_code` )
) COMMENT = '캠페인상세내용';



CREATE TABLE `tbl_campaign_desc_file`
(
    `campaign_file_code`    INTEGER NOT NULL AUTO_INCREMENT COMMENT '캠페인첨부파일코드',
    `file_origin_name`    VARCHAR(255) NOT NULL COMMENT '원본파일명',
    `file_save_name`    VARCHAR(255) NOT NULL COMMENT '저장파일명',
    `file_extension`    VARCHAR(255) NOT NULL COMMENT '파일확장자',
    `file_save_path`    VARCHAR(255) NOT NULL COMMENT '저장경로',
    `campaign_code`    INTEGER NOT NULL COMMENT '캠페인코드',
    PRIMARY KEY (`campaign_file_code`),
    KEY `campaign_code` (`campaign_code`),
    CONSTRAINT `tbl_campaign_desc_file` FOREIGN KEY (`campaign_code`) REFERENCES `tbl_campaign_description` (`campaign_code`)
) COMMENT = '캠페인첨부파일';



CREATE TABLE `tbl_campaign_rev_file`
(
    `review_file_code`    INTEGER NOT NULL AUTO_INCREMENT COMMENT '후기첨부파일코드',
    `file_origin_name`    VARCHAR(255) NOT NULL COMMENT '원본파일명',
    `file_origin_path`    VARCHAR(255) NOT NULL COMMENT '원본경로',
    `file_save_name`    VARCHAR(255) NOT NULL COMMENT '저장파일명',
    `file_save_path`    VARCHAR(255) NOT NULL COMMENT '저장경로',
    `file_extension`    VARCHAR(255) NOT NULL COMMENT '파일확장자',
    `review_code`    INTEGER NOT NULL COMMENT '후기코드',
    PRIMARY KEY ( `review_file_code` )
) COMMENT = '후기첨부파일';




CREATE TABLE `tbl_review`
(
    `review_code`    INTEGER NOT NULL AUTO_INCREMENT COMMENT '후기코드',
    `review_title`    VARCHAR(500) NOT NULL COMMENT '후기제목',
    `description`    LONGBLOB NOT NULL COMMENT '후기내용',
    `campaign_code`    INTEGER NOT NULL COMMENT '캠페인코드',
    PRIMARY KEY ( `review_code` )
) COMMENT = '후기게시글';




CREATE TABLE `tbl_chat_bot`
(
    `question_code`    INTEGER NOT NULL AUTO_INCREMENT COMMENT '질문코드',
    `question_content`    VARCHAR(1000) NOT NULL COMMENT '질문내용',
    `answer_content`    VARCHAR(1000) NOT NULL COMMENT '답변내용',
    PRIMARY KEY ( `question_code` )
) COMMENT = '챗봇';




CREATE TABLE `tbl_donation`
(
    `donation_code`    INTEGER NOT NULL AUTO_INCREMENT COMMENT '기부코드',
    `donation_date_time`    DATETIME NOT NULL COMMENT '기부일시',
    `donation_point`    INTEGER NOT NULL COMMENT '사용포인트',
    `member_code`    INTEGER NOT NULL COMMENT '회원코드',
    `campaign_code`    INTEGER NOT NULL COMMENT '캠페인코드',
    PRIMARY KEY ( `donation_code` )
) COMMENT = '기부내역';



CREATE TABLE `tbl_member`
(
    `member_code`    INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원코드',
    `member_email`    VARCHAR(255) NOT NULL COMMENT '이메일',
    `member_name`    VARCHAR(255) NOT NULL COMMENT '이름',
    `password`    VARCHAR(255) NOT NULL COMMENT '비밀번호',
    `phone`    VARCHAR(255) NOT NULL COMMENT '휴대폰번호',
    `join_date`    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '가입일자',
    `member_role`    VARCHAR(255) DEFAULT 'ROLE_USER' NOT NULL COMMENT '유저권한',
    `withdraw`    VARCHAR(1) DEFAULT 'N' NOT NULL COMMENT '탈퇴여부',
    `withdraw_date`    DATETIME DEFAULT NULL COMMENT '탈퇴일자',
    `current_point`    INTEGER DEFAULT 0 NOT NULL COMMENT '보유포인트',
    `privacy_status`    CHAR DEFAULT 'N' NOT NULL COMMENT '개인정보동의여부',
    `resident_num`    VARCHAR(255) COMMENT '주민등록번호',
    `provider` varchar(255) NOT NULL DEFAULT 'REPLANET' COMMENT '가입플랫폼',
    `provider_id` varchar(255) NOT NULL DEFAULT 'REPLANET' COMMENT '가입플랫폼ID',
    `withdraw_reason`    VARCHAR(1000) COMMENT '탈퇴사유',
    `temp_pwd` varchar(255) DEFAULT NULL COMMENT '임시비밀번호',
    PRIMARY KEY ( `member_code` )
) COMMENT = '회원정보';
# 개인정보동의여부, 주민등록번호 추가됨 231124


CREATE TABLE `tbl_pay`
(
    `pay_code`    INTEGER NOT NULL AUTO_INCREMENT COMMENT '결제코드',
    `pay_amount`    INTEGER NOT NULL COMMENT '결제금액',
    `pay_tid`    VARCHAR(255) COMMENT '결제고유번호',
    `donation_code`    INTEGER COMMENT '기부코드',
    PRIMARY KEY ( `pay_code` )
) COMMENT = '결제내역';



CREATE TABLE `tbl_point_exchange`
(
    `exchange_code`    INTEGER NOT NULL AUTO_INCREMENT COMMENT '신청코드',
    `exchange_date`    DATETIME NOT NULL COMMENT '신청일자',
    `title`    VARCHAR(255) NOT NULL COMMENT '신청제목',
    `status`    VARCHAR(255) DEFAULT '대기' NOT NULL COMMENT '상태',
    `processing_date` DATETIME COMMENT '처리일자',
    `return_detail`    VARCHAR(255) COMMENT '반려사유',
    `points`    INTEGER COMMENT '승인포인트',
    `member_code`    INTEGER NOT NULL COMMENT '회원코드',
    PRIMARY KEY ( `exchange_code` )
) COMMENT = '포인트신청';



CREATE TABLE `tbl_point_file`
(
    `file_code`    INTEGER NOT NULL AUTO_INCREMENT COMMENT '확인서파일코드',
    `file_origin_name`    VARCHAR(255) NOT NULL COMMENT '원본파일명',
    `file_path`    VARCHAR(255) NOT NULL COMMENT '저장경로',
    `file_extension`    VARCHAR(255) NOT NULL COMMENT '파일확장자',
    `file_save_name`    VARCHAR(255) NOT NULL COMMENT '저장파일명',
    `application_code`    INTEGER NOT NULL COMMENT '신청코드',
    PRIMARY KEY ( `file_code` )
) COMMENT = '확인서첨부파일';




CREATE TABLE `tbl_review_comment`
(
    `rev_comment_code`    INTEGER ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '댓글코드',
    `rev_comment_content`    VARCHAR(1000) NOT NULL COMMENT '댓글내용',
    `member_code`    VARCHAR(255) NOT NULL COMMENT '작성자',
    `rev_comment_date`    DATETIME NOT NULL COMMENT '댓글작성일자',
    `review_code`    INTEGER NOT NULL COMMENT '후기코드',
    `rev_comment_monitorized` VARCHAR(45) NOT NULL DEFAULT 'N',
    PRIMARY KEY ( `rev_comment_code` )
) COMMENT = '후기댓글';



CREATE TABLE `tbl_org`
(
    `org_code`    INTEGER NOT NULL COMMENT '기부처코드(회원코드)',
    `file_origin_name`    VARCHAR(255) COMMENT '원본파일명',
    `file_save_name`    VARCHAR(255) COMMENT '저장파일명',
    `file_save_path`    VARCHAR(255) COMMENT '파일확장자',
    `file_extension`    VARCHAR(255) COMMENT '저장경로',
    `org_description`    VARCHAR(1000) COMMENT '기부처소개',
    `withdraw_req_date`    DATETIME DEFAULT null COMMENT '탈퇴신청일자',
    `withdraw_reason`    VARCHAR(1000) COMMENT '탈퇴사유',
    PRIMARY KEY ( `org_code` )
) COMMENT = '기부처정보';

-- FK 설정

-- 기부처정보(멤버코드 FK)
ALTER TABLE tbl_org ADD FOREIGN KEY (org_code) REFERENCES tbl_member(member_code);

-- 캠페인상세내용(기부처코드 FK)
ALTER TABLE tbl_campaign_description ADD FOREIGN KEY (org_code) REFERENCES tbl_org(org_code);

-- 기부내역(회원코드, 캠페인코드 FK)
ALTER TABLE tbl_donation ADD FOREIGN KEY (member_code) REFERENCES tbl_member(member_code);
ALTER TABLE tbl_donation ADD FOREIGN KEY (campaign_code) REFERENCES tbl_campaign_description(campaign_code);

-- 결제내역(기부코드 FK)
ALTER TABLE tbl_pay ADD FOREIGN KEY (donation_code) REFERENCES tbl_donation(donation_code);

-- 캠페인첨부파일(캠페인코드 FK)
ALTER TABLE tbl_campaign_desc_file ADD FOREIGN KEY (campaign_code) REFERENCES tbl_campaign_description(campaign_code);

-- 북마크(회원코드 FK, 캠페인코드 FK)
ALTER TABLE tbl_bookmark ADD FOREIGN KEY (member_code) REFERENCES tbl_member(member_code);
ALTER TABLE tbl_bookmark ADD FOREIGN KEY (campaign_code) REFERENCES tbl_campaign_description(campaign_code);

-- 포인트신청(회원코드 FK)
ALTER TABLE tbl_point_exchange ADD FOREIGN KEY (member_code) REFERENCES tbl_member(member_code);

-- 확인서첨부파일(신청코드 FK)
ALTER TABLE tbl_point_file ADD FOREIGN KEY (application_code) REFERENCES tbl_point_exchange(exchange_code);
