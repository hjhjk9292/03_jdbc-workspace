-- pstmt ���
-- ��Ŭ���� 02_JDBC_PreparedStatement
-- ����Ŭ JDBC���������

-- 3��. ȸ�� ���̵�� �˻� ��û �� ����� sql��
SELECT * FROM MEMBER WHERE USERID = ?;

-- 4��. ȸ�� �̸����� �˻� ��û �� ����� sql��
SELECT * FROM MEMBER WHERE USERNAME LIKE '%?%'; -- �̷��� ���� �� ���� �� �ɱ�?
--> ����(�� �� ����� ������ ������ ����� ����)

-- 5��. ȸ�� ���� ���� ��û �� ����� sql��
UPDATE MEMBER SET USERPWD = ?, EMAIL = ?, PHONE =?, ADDRESS = ? WHERE USERID = ?;

-- 6��. ȸ�� Ż�� ��û �� ����� sql��
DELETE FROM MEMBER WHERE USERID = ?;



SELECT * FROM MEMBER;