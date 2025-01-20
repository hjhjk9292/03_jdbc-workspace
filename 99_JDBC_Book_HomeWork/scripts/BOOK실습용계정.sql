-- 계정 생성 및 권한 부여
CREATE USER BOOK IDENTIFIED BY BOOK;
GRANT CONNECT, RESOURCE TO BOOK;

DROP SEQUENCE BNO_SEQ;
DROP TABLE BOOK;
-- 기존 테이블 삭제
DROP TABLE BOOK CASCADE CONSTRAINTS;

-- BOOK 테이블 생성 (bNo을 VARCHAR2로 변경)
CREATE TABLE BOOK (
    BNO VARCHAR2(10) PRIMARY KEY,
    TITLE VARCHAR2(50), 
    AUTHOR VARCHAR2(50),
    PUBLISHER VARCHAR2(50),
    PRICE NUMBER, 
    DESCRIPTION VARCHAR2(500)
);

---- 시퀀스 생성
--CREATE SEQUENCE SEQ_BNO START WITH 100 INCREMENT BY 1;

-- INSERT 구문 예시 (시퀀스를 문자열로 변환하여 사용)
--INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION)
--VALUES (TO_CHAR(BNO_SEQ.NEXTVAL), '책 제목', '작가 이름', '출판사', 15000, '설명');


-- 데이터 삽입 (책번호는 시퀀스 사용)
INSERT INTO BOOK VALUES('100', '잃어버린 성훈이를 찾아서', '고영훈 박사', '나무 출판사', 8500, '『시연스』, 『르 몽드』 선정 20세기 최고의 책');
INSERT INTO BOOK VALUES('101', '준서의 자서전', '김준서가 옮김', '빛나는 현지출판사', 8500, '영화배우 서동진이 추천한 젊음에 바치는 영혼의 자서전');
INSERT INTO BOOK VALUES('102', '손희찬 과학동아', '편집부 신현정 선생', '리얼 사이언스', 8500, '용훈이네 유튜브에 소개된 과학만화 잡지');
INSERT INTO BOOK VALUES('103', '지은이의 코딩스쿨', '코딩천재 이지은', '코딩하는 동네', 9900, '제 13회 호석이가 주는 문학상 수상작');
INSERT INTO BOOK VALUES('104', '창용이의 여행일기', '창용짱', '시연21', 3800, '영화감독 정회윤이 집필한 영화 전문 잡지');
INSERT INTO BOOK VALUES('105', '현수의 데일리룩', '인플루언서 주현수', '우리네 책방', 12000, '핫가이 한재희의 샤라웃을 받은 잡지');
INSERT INTO BOOK VALUES('106', '차은우의 미라클모닝', '차은우', '존잘 출판사', 6300, '회윤이도 2번 성공한 미라클모닝');
INSERT INTO BOOK VALUES('107', '현정이의 백만가지 요리레시피', '현정 신 지음', '우리반 출판사', 19900, '학원에서 밥해먹고 사는 일상');


SELECT * FROM BOOK;
SELECT * FROM MAGAZINE;

DROP TABLE MAGAZINE;

--CREATE TABLE MAGAZINE (
--    BNO VARCHAR2(10) PRIMARY KEY,
--    TITLE VARCHAR2(100),
--    AUTHOR VARCHAR2(50),
--    PUBLISHER VARCHAR2(50),
--    PRICE NUMBER,
--    DESCRIPTION VARCHAR2(500),
--    YEAR NUMBER,
--    MONTH NUMBER,
--    CONSTRAINT FK_MAGAZINE_BOOK FOREIGN KEY (BNO) REFERENCES BOOK(BNO)
--);

-- INSERT 구문 예시
--INSERT INTO MAGAZINE (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION, YEAR, MONTH)
--VALUES (TO_CHAR(SEQ_BNO.NEXTVAL), '잡지 제목', '작가 이름', '출판사', 20000, '잡지 설명', 2024, 1);

    
 --MAGAZINE 테이블 생성 (BOOK 상속)
CREATE TABLE MAGAZINE (
    BNO VARCHAR2(10) PRIMARY KEY,
    TITLE VARCHAR2(50),
    AUTHOR VARCHAR2(50),
    PUBLISHER VARCHAR2(50),
    PRICE NUMBER,
    DESCRIPTION VARCHAR2(100),
    YEAR NUMBER,
    MONTH NUMBER,
    CONSTRAINT FK_MAGAZINE_BOOK FOREIGN KEY (BNO) REFERENCES BOOK(BNO)
);

-- MAGAZINE 데이터 삽입 (시퀀스 사용)
--INSERT INTO MAGAZINE VALUES(BNO_SEQ.NEXTVAL, '손희찬 과학동아', '편집부 신현정 선생', '리얼 사이언스', 8500, '용훈이네 유튜브에 소개된 과학만화 잡지', 2024, 3);
--INSERT INTO MAGAZINE VALUES(BNO_SEQ.NEXTVAL, '창용이의 여행일기', '창용짱', '시연21', 3800, '영화감독 정회윤이 집필한 영화 전문 잡지', 2024, 4);
--INSERT INTO MAGAZINE VALUES(BNO_SEQ.NEXTVAL, '현수의 데일리룩', '인플루언서 주현수', '우리네 책방', 12000, '핫가이 한재희의 샤라웃을 받은 잡지', 2024,11);

-- BOOK에 BNO 추가
--INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION) 
--VALUES ('102', '손희찬 과학동아', '편집부 신현정 선생', '리얼 사이언스', 8500, '용훈이네 유튜브에 소개된 과학만화 잡지');

-- MAGAZINE에 동일한 BNO 사용
INSERT INTO MAGAZINE (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION, YEAR, MONTH) 
VALUES ('102', '손희찬 과학동아', '편집부 신현정 선생', '리얼 사이언스', 8500, '용훈이네 유튜브에 소개된 과학만화 잡지', 2024, 3);

-- BOOK에 BNO 추가
--INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION) 
--VALUES ('104', '창용이의 여행일기', '창용짱', '시연21', 3800, '영화감독 정회윤이 집필한 영화 전문 잡지');

-- MAGAZINE에 동일한 BNO 사용
INSERT INTO MAGAZINE (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION, YEAR, MONTH) 
VALUES ('104', '창용이의 여행일기', '창용짱', '시연21', 3800, '영화감독 정회윤이 집필한 영화 전문 잡지', 2024, 4);

-- BOOK에 BNO 추가
--INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION) 
--VALUES ('105', '현수의 데일리룩', '인플루언서 주현수', '우리네 책방', 12000, '핫가이 한재희의 샤라웃을 받은 잡지');

-- MAGAZINE에 동일한 BNO 사용
INSERT INTO MAGAZINE (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION, YEAR, MONTH) 
VALUES ('105', '현수의 데일리룩', '인플루언서 주현수', '우리네 책방', 12000, '핫가이 한재희의 샤라웃을 받은 잡지', 2024, 11);

COMMIT;