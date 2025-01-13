DROP TABLE MEMBER;
DROP SEQUENCE SEQ_USERNO;
DROP TABLE TEST;

CREATE TABLE MEMBER(
    USERNO NUMBER PRIMARY KEY,                      -- ȸ����ȣ
    USERID VARCHAR2(15) NOT NULL UNIQUE,            -- ȸ�����̵�
    USERPWD VARCHAR2(15) NOT NULL,                  -- ȸ����й�ȣ
    USERNAME VARCHAR2(20) NOT NULL,                 -- ȸ����
    GENDER CHAR(1) CHECK (GENDER IN ('M', 'F')),    -- ����
    AGE NUMBER,                                     -- ����
    EMAIL VARCHAR2(30),                             -- �̸���
    PHONE CHAR(11),                                 -- ��ȭ��ȣ
    ADDRESS VARCHAR2(100),                          -- �ּ�
    HOBBY VARCHAR2(50),                              -- ���
    ENROLL_DATE DATE DEFAULT SYSDATE NOT NULL       -- ȸ��������
);

CREATE SEQUENCE SEQ_USERNO
NOCACHE;

INSERT INTO MEMBER
VALUES(SEQ_USERNO.NEXTVAL, 'admin', '1234', '������', 'M', 45, 'admin@iei.or.kr', '01012345555', '����', null, '2025-01-02');

SELECT * FROM MEMBER;

INSERT INTO MEMBER
VALUES(SEQ_USERNO.NEXTVAL, 'user01', 'pass01', '������', null, 23, 'user01@iei.or.kr', '01022221111', '�λ�', '���,��ȭ����', '2025-01-10');

COMMIT;

CREATE TABLE TEST(
    TNO NUMBER,
    TNAME VARCHAR2(20),
    TDATE DATE
);















