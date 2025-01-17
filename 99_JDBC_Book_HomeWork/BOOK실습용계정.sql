DROP TABLE BOOK;
CREATE TABLE BOOK;
SELECT * FROM BOOK;

CREATE TABLE BOOK(
    BNO NUMBER PRIMARY KEY, -- �÷� ���� ���
    TITLE VARCHAR2(50), 
    AUTHOR VARCHAR2(20),
    PUBLISHER VARCHAR2(30),
    PRICE NUMBER, 
    DESCRIPTION VARCHAR2(100)
    ,YEAR DATE --�Ⱓ�⵵ 
    ,MONTH DATE --�Ⱓ�� 
    );

-- å��ȣ�� sequence��

INSERT INTO BOOK VALUES(100, '�Ҿ���� �����̸� ã�Ƽ�', '���� �ڻ�', '���� ���ǻ�', 8500, '���ÿ�����, ���� ���塻 ���� 20���� �ְ��� å', NULL, NULL);
INSERT INTO BOOK VALUES(101, '�ؼ��� �ڼ���', '���ؼ��� �ű�', '������ �������ǻ�', 8500, '��ȭ��� �������� ��õ�� ������ ��ġ�� ��ȥ�� �ڼ���', NULL, NULL);
INSERT INTO BOOK VALUES(102, '������ ���е���', '������ ������ ����', '���� ���̾�', 8500, '�����̳� ��Ʃ�꿡 �Ұ��� ���и�ȭ ����', 2024, 3);
INSERT INTO BOOK VALUES(103, '�������� �ڵ�����', '�ڵ�õ�� ������', '�ڵ��ϴ� ����', 9900, '�� 13ȸ ȣ���̰� �ִ� ���л� ������', NULL, NULL);
INSERT INTO BOOK VALUES(104, 'â������ �����ϱ�', 'â��¯', '�ÿ�21', 3800, '��ȭ���� ��ȸ���� ������ ��ȭ ���� ����', 2024, 4);
INSERT INTO BOOK VALUES(105, '������ ���ϸ���', '���÷�� ������', '�츮�� å��', 12000, '�ְ��� �������� ������� ���� ����', 2024,11);
INSERT INTO BOOK VALUES(106, '�������� �̶�Ŭ���', '������', '���� ���ǻ�', 6300, 'ȸ���̵� 2�� ������ �̶�Ŭ���', NULL, NULL);
INSERT INTO BOOK VALUES(107, '�������� �鸸���� �丮������', '���� �� ����', '�츮�� ���ǻ�', 19900, '�п����� ���ظ԰� ��� �ϻ�, NULL, NULL');



CREATE TABLE MAGAZINE
AS SELECT * FROM BOOK;

DROP TABLE MAGAZINE;
CREATE TABLE MAGAZINE;
SELECT * FROM MAGAZINE;

CREATE TABLE MAGAZINE(
    BNO NUMBER , -- �÷� ���� ���
    TITLE VARCHAR2(30), 
    AUTHOR VARCHAR2(20) NOT NULL,
    PUBLISHER VARCHAR2(20) NOT NULL,
    PRICE NUMBER, 
    DESCRIPTION VARCHAR2(30)
    );