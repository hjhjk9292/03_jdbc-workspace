DROP TABLE BOOK;
CREATE TABLE BOOK;
SELECT * FROM BOOK;

CREATE TABLE BOOK(
    BNO NUMBER PRIMARY KEY, -- 컬럼 레벨 방식
    TITLE VARCHAR2(50), 
    AUTHOR VARCHAR2(20),
    PUBLISHER VARCHAR2(30),
    PRICE NUMBER, 
    DESCRIPTION VARCHAR2(100)
    ,YEAR DATE --출간년도 
    ,MONTH DATE --출간월 
    );

-- 책번호는 sequence로

INSERT INTO BOOK VALUES(100, '잃어버린 성훈이를 찾아서', '고영훈 박사', '나무 출판사', 8500, '『시연스』, 『르 몽드』 선정 20세기 최고의 책', NULL, NULL);
INSERT INTO BOOK VALUES(101, '준서의 자서전', '김준서가 옮김', '빛나는 현지출판사', 8500, '영화배우 서동진이 추천한 젊음에 바치는 영혼의 자서전', NULL, NULL);
INSERT INTO BOOK VALUES(102, '손희찬 과학동아', '편집부 신현정 선생', '리얼 사이언스', 8500, '용훈이네 유튜브에 소개된 과학만화 잡지', 2024, 3);
INSERT INTO BOOK VALUES(103, '지은이의 코딩스쿨', '코딩천재 이지은', '코딩하는 동네', 9900, '제 13회 호석이가 주는 문학상 수상작', NULL, NULL);
INSERT INTO BOOK VALUES(104, '창용이의 여행일기', '창용짱', '시연21', 3800, '영화감독 정회윤이 집필한 영화 전문 잡지', 2024, 4);
INSERT INTO BOOK VALUES(105, '현수의 데일리룩', '인플루언서 주현수', '우리네 책방', 12000, '핫가이 한재희의 샤라웃을 받은 잡지', 2024,11);
INSERT INTO BOOK VALUES(106, '차은우의 미라클모닝', '차은우', '존잘 출판사', 6300, '회윤이도 2번 성공한 미라클모닝', NULL, NULL);
INSERT INTO BOOK VALUES(107, '현정이의 백만가지 요리레시피', '현정 신 지음', '우리반 출판사', 19900, '학원에서 밥해먹고 사는 일상, NULL, NULL');



CREATE TABLE MAGAZINE
AS SELECT * FROM BOOK;

DROP TABLE MAGAZINE;
CREATE TABLE MAGAZINE;
SELECT * FROM MAGAZINE;

CREATE TABLE MAGAZINE(
    BNO NUMBER , -- 컬럼 레벨 방식
    TITLE VARCHAR2(30), 
    AUTHOR VARCHAR2(20) NOT NULL,
    PUBLISHER VARCHAR2(20) NOT NULL,
    PRICE NUMBER, 
    DESCRIPTION VARCHAR2(30)
    );