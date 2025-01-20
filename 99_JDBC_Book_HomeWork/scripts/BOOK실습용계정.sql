-- ���� ���� �� ���� �ο�
CREATE USER BOOK IDENTIFIED BY BOOK;
GRANT CONNECT, RESOURCE TO BOOK;

DROP SEQUENCE BNO_SEQ;
DROP TABLE BOOK;
-- ���� ���̺� ����
DROP TABLE BOOK CASCADE CONSTRAINTS;

-- BOOK ���̺� ���� (bNo�� VARCHAR2�� ����)
CREATE TABLE BOOK (
    BNO VARCHAR2(10) PRIMARY KEY,
    TITLE VARCHAR2(50), 
    AUTHOR VARCHAR2(50),
    PUBLISHER VARCHAR2(50),
    PRICE NUMBER, 
    DESCRIPTION VARCHAR2(500)
);

---- ������ ����
--CREATE SEQUENCE SEQ_BNO START WITH 100 INCREMENT BY 1;

-- INSERT ���� ���� (�������� ���ڿ��� ��ȯ�Ͽ� ���)
--INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION)
--VALUES (TO_CHAR(BNO_SEQ.NEXTVAL), 'å ����', '�۰� �̸�', '���ǻ�', 15000, '����');


-- ������ ���� (å��ȣ�� ������ ���)
INSERT INTO BOOK VALUES('100', '�Ҿ���� �����̸� ã�Ƽ�', '���� �ڻ�', '���� ���ǻ�', 8500, '���ÿ�����, ���� ���塻 ���� 20���� �ְ��� å');
INSERT INTO BOOK VALUES('101', '�ؼ��� �ڼ���', '���ؼ��� �ű�', '������ �������ǻ�', 8500, '��ȭ��� �������� ��õ�� ������ ��ġ�� ��ȥ�� �ڼ���');
INSERT INTO BOOK VALUES('102', '������ ���е���', '������ ������ ����', '���� ���̾�', 8500, '�����̳� ��Ʃ�꿡 �Ұ��� ���и�ȭ ����');
INSERT INTO BOOK VALUES('103', '�������� �ڵ�����', '�ڵ�õ�� ������', '�ڵ��ϴ� ����', 9900, '�� 13ȸ ȣ���̰� �ִ� ���л� ������');
INSERT INTO BOOK VALUES('104', 'â������ �����ϱ�', 'â��¯', '�ÿ�21', 3800, '��ȭ���� ��ȸ���� ������ ��ȭ ���� ����');
INSERT INTO BOOK VALUES('105', '������ ���ϸ���', '���÷�� ������', '�츮�� å��', 12000, '�ְ��� �������� ������� ���� ����');
INSERT INTO BOOK VALUES('106', '�������� �̶�Ŭ���', '������', '���� ���ǻ�', 6300, 'ȸ���̵� 2�� ������ �̶�Ŭ���');
INSERT INTO BOOK VALUES('107', '�������� �鸸���� �丮������', '���� �� ����', '�츮�� ���ǻ�', 19900, '�п����� ���ظ԰� ��� �ϻ�');


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

-- INSERT ���� ����
--INSERT INTO MAGAZINE (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION, YEAR, MONTH)
--VALUES (TO_CHAR(SEQ_BNO.NEXTVAL), '���� ����', '�۰� �̸�', '���ǻ�', 20000, '���� ����', 2024, 1);

    
 --MAGAZINE ���̺� ���� (BOOK ���)
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

-- MAGAZINE ������ ���� (������ ���)
--INSERT INTO MAGAZINE VALUES(BNO_SEQ.NEXTVAL, '������ ���е���', '������ ������ ����', '���� ���̾�', 8500, '�����̳� ��Ʃ�꿡 �Ұ��� ���и�ȭ ����', 2024, 3);
--INSERT INTO MAGAZINE VALUES(BNO_SEQ.NEXTVAL, 'â������ �����ϱ�', 'â��¯', '�ÿ�21', 3800, '��ȭ���� ��ȸ���� ������ ��ȭ ���� ����', 2024, 4);
--INSERT INTO MAGAZINE VALUES(BNO_SEQ.NEXTVAL, '������ ���ϸ���', '���÷�� ������', '�츮�� å��', 12000, '�ְ��� �������� ������� ���� ����', 2024,11);

-- BOOK�� BNO �߰�
--INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION) 
--VALUES ('102', '������ ���е���', '������ ������ ����', '���� ���̾�', 8500, '�����̳� ��Ʃ�꿡 �Ұ��� ���и�ȭ ����');

-- MAGAZINE�� ������ BNO ���
INSERT INTO MAGAZINE (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION, YEAR, MONTH) 
VALUES ('102', '������ ���е���', '������ ������ ����', '���� ���̾�', 8500, '�����̳� ��Ʃ�꿡 �Ұ��� ���и�ȭ ����', 2024, 3);

-- BOOK�� BNO �߰�
--INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION) 
--VALUES ('104', 'â������ �����ϱ�', 'â��¯', '�ÿ�21', 3800, '��ȭ���� ��ȸ���� ������ ��ȭ ���� ����');

-- MAGAZINE�� ������ BNO ���
INSERT INTO MAGAZINE (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION, YEAR, MONTH) 
VALUES ('104', 'â������ �����ϱ�', 'â��¯', '�ÿ�21', 3800, '��ȭ���� ��ȸ���� ������ ��ȭ ���� ����', 2024, 4);

-- BOOK�� BNO �߰�
--INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION) 
--VALUES ('105', '������ ���ϸ���', '���÷�� ������', '�츮�� å��', 12000, '�ְ��� �������� ������� ���� ����');

-- MAGAZINE�� ������ BNO ���
INSERT INTO MAGAZINE (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION, YEAR, MONTH) 
VALUES ('105', '������ ���ϸ���', '���÷�� ������', '�츮�� å��', 12000, '�ְ��� �������� ������� ���� ����', 2024, 11);

COMMIT;