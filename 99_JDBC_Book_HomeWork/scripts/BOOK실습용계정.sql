-- ���� ������ �� ���̺� ����
DROP SEQUENCE SEQ_BNO;
DROP TABLE MAGAZINE CASCADE CONSTRAINTS;
DROP TABLE BOOK CASCADE CONSTRAINTS;

-- BOOK ���̺� ����
CREATE TABLE BOOK (
    BNO VARCHAR2(10) PRIMARY KEY,
    TITLE VARCHAR2(50), 
    AUTHOR VARCHAR2(50),
    PUBLISHER VARCHAR2(50),
    PRICE NUMBER, 
    DESCRIPTION VARCHAR2(500)
);

-- MAGAZINE ���̺� ���� (BOOK ���)
CREATE TABLE MAGAZINE (
    BNO VARCHAR2(10) PRIMARY KEY,
    TITLE VARCHAR2(50),
    AUTHOR VARCHAR2(50),
    PUBLISHER VARCHAR2(50),
    PRICE NUMBER,
    DESCRIPTION VARCHAR2(500),
    YEAR NUMBER,
    MONTH NUMBER,
    CONSTRAINT FK_MAGAZINE_BOOK FOREIGN KEY (BNO) REFERENCES BOOK(BNO)
);

-- ������ ���� (100������ ����)
CREATE SEQUENCE SEQ_BNO 
START WITH 100 
INCREMENT BY 1;

-- ������ ����: �θ� ���̺� ���� ����
INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION)
VALUES (TO_CHAR(SEQ_BNO.NEXTVAL), '�Ҿ���� �����̸� ã�Ƽ�', '���� �ڻ�', '���� ���ǻ�', 8500, '���ÿ�����, ���� ���塻 ���� 20���� �ְ��� å');
INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION)
VALUES (TO_CHAR(SEQ_BNO.NEXTVAL), '�ؼ��� �ڼ���', '���ؼ��� �ű�', '������ �������ǻ�', 8500, '��ȭ��� �������� ��õ�� ������ ��ġ�� ��ȥ�� �ڼ���');
INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION)

VALUES (TO_CHAR(SEQ_BNO.NEXTVAL), '�������� �ڵ�����', '�ڵ�õ�� ������', '�ڵ��ϴ� ����', 9900, '�� 13ȸ ȣ���̰� �ִ� ���л� ������');
INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION)


VALUES (TO_CHAR(SEQ_BNO.NEXTVAL), '�������� �̶�Ŭ���', '������', '���� ���ǻ�', 6300, 'ȸ���̵� 2�� ������ �̶�Ŭ���');
INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION)
VALUES (TO_CHAR(SEQ_BNO.NEXTVAL), '�������� �鸸���� �丮������', '���� �� ����', '�츮�� ���ǻ�', 19900, '�п����� ���ظ԰� ��� �ϻ�');

-- 102 ������ ����: �θ� ���̺� ���� ����
INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION)
VALUES (TO_CHAR(SEQ_BNO.NEXTVAL), '������ ���е���', '������ ������ ����', '���� ���̾�', 8500, '�����̳� ��Ʃ�꿡 �Ұ��� ���и�ȭ ����');

-- �ڽ� ���̺� ������ BNO 102 ������ ����
INSERT INTO MAGAZINE (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION, YEAR, MONTH)
VALUES (TO_CHAR(SEQ_BNO.CURRVAL), '������ ���е���', '������ ������ ����', '���� ���̾�', 8500, '�����̳� ��Ʃ�꿡 �Ұ��� ���и�ȭ ����', 2024, 3);

-- 104 ������ ����
INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION)
VALUES (TO_CHAR(SEQ_BNO.NEXTVAL), 'â������ �����ϱ�', 'â��¯', '�ÿ�21', 3800, '��ȭ���� ��ȸ���� ������ ��ȭ ���� ����');

INSERT INTO MAGAZINE (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION, YEAR, MONTH)
VALUES (TO_CHAR(SEQ_BNO.CURRVAL), 'â������ �����ϱ�', 'â��¯', '�ÿ�21', 3800, '��ȭ���� ��ȸ���� ������ ��ȭ ���� ����', 2024, 4);

-- 105 ������ ����
INSERT INTO BOOK (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION)
VALUES (TO_CHAR(SEQ_BNO.NEXTVAL), '������ ���ϸ���', '���÷�� ������', '�츮�� å��', 12000, '�ְ��� �������� ������� ���� ����');

INSERT INTO MAGAZINE (BNO, TITLE, AUTHOR, PUBLISHER, PRICE, DESCRIPTION, YEAR, MONTH)
VALUES (TO_CHAR(SEQ_BNO.CURRVAL), '������ ���ϸ���', '���÷�� ������', '�츮�� å��', 12000, '�ְ��� �������� ������� ���� ����', 2024, 11);

-- ������ Ȯ��
SELECT * FROM BOOK;
SELECT * FROM MAGAZINE;

-- ���� ���� ����
COMMIT;
