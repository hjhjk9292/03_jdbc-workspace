-- pstmt 사용
-- 이클립스 02_JDBC_PreparedStatement
-- 오라클 JDBC수업용계정

-- 3번. 회원 아이디로 검색 요청 시 실행될 sql문
SELECT * FROM MEMBER WHERE USERID = ?;

-- 4번. 회원 이름으로 검색 요청 시 실행될 sql문
SELECT * FROM MEMBER WHERE USERNAME LIKE '%?%'; -- 이렇게 했을 때 과연 잘 될까?
--> 보완(안 됨 방법이 여러개 있으니 골똘히 생각)

-- 5번. 회원 정보 변경 요청 시 실행될 sql문
UPDATE MEMBER SET USERPWD = ?, EMAIL = ?, PHONE =?, ADDRESS = ? WHERE USERID = ?;

-- 6번. 회원 탈퇴 요청 시 실행될 sql문
DELETE FROM MEMBER WHERE USERID = ?;



SELECT * FROM MEMBER;