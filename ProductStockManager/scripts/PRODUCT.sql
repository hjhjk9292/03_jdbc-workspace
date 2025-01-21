--JDBC��������� --��Ŭ���� ProductStockManager

DROP TABLE PRODUCT_IO;
DROP TRIGGER TRG_PRODUCT_IO;
DROP SEQUENCE SEQ_IO_NUM;


-- PRODUCT ���̺��� ��ǰ ������ 
--PRODUCT_IO ���̺��� �ش� ��ǰ ���õ� �����͵��� ���� �ǵ��� �ܷ�Ű �������� �ɼ� �ο� 
--IO_DATE �÷��� �⺻�� SYSDATE, STATUS �÷��� �����δ�
--���԰� Ȥ�� ������� INSERT �� �� �ֵ��� ���� ���� �ο� �� ��

-- Product_IO ���̺� ����
CREATE TABLE PRODUCT_IO (
    IO_NUM NUMBER PRIMARY KEY,
    PRODUCT_ID VARCHAR2(20) REFERENCES PRODUCT(PRODUCT_ID) ON DELETE CASCADE,
    IO_DATE DATE DEFAULT SYSDATE NOT NULL,
    AMOUNT NUMBER NOT NULL,
    STATUS VARCHAR2(10) CHECK (STATUS IN ('�԰�', '���'))
);

-- PRODUCT_IO ���̺� ������ INSERT�� IO_NUM�� ���� SEQUENCE ���� �� �۾�
-- ������ ����
CREATE SEQUENCE SEQ_IO_NUM
START WITH 1
INCREMENT BY 1
NOCACHE;


-- PRODUCT_IO ���̺� �԰� �Ǵ� ��� ���� ������ ���� �� �� ���¿� ���� 
-- PROUDCT ���̺��� STOCK �÷� ���� �ڵ����� �����ǵ��� Ʈ���Ÿ� ���� �� �۾�

-- Ʈ���� ����
--CREATE OR REPLACE TRIGGER TRG_PRODUCT_IO
--AFTER INSERT ON PRODUCT
--FOR EACH ROW
--BEGIN
--    -- ��ǰ�� �԰�� ��� => ������ ����
--    IF :NEW.STATUS = '�԰�' THEN
--        UPDATE PRODUCT
--        SET STOCK = STOCK + :NEW.AMOUNT
--        WHERE PRODUCT_ID = :NEW.PRODUCT_ID;
--    END IF;
--
--    -- ��ǰ�� ���� ��� => ������ ����
--    IF :NEW.STATUS = '���' THEN
--        UPDATE PRODUCT
--        SET STOCK = STOCK - :NEW.AMOUNT
--        WHERE PRODUCT_ID = :NEW.PRODUCT_ID;
--    END IF;
--END;
--/



CREATE OR REPLACE TRIGGER TRG_PRODUCT_IO
BEFORE INSERT OR UPDATE ON Product_IO
FOR EACH ROW
DECLARE
   v_stock NUMBER;
BEGIN
   IF :NEW.STATUS = '���' THEN
      SELECT STOCK INTO v_stock FROM Product WHERE PRODUCT_ID = :NEW.PRODUCT_ID;
--      IF v_stock < :NEW.AMOUNT THEN
--         RAISE_APPLICATION_ERROR(-20001, '��� �����Ͽ� ��� �Ұ����մϴ�.');
--      END IF;
      UPDATE Product SET STOCK = STOCK - :NEW.AMOUNT WHERE PRODUCT_ID = :NEW.PRODUCT_ID;
   ELSIF :NEW.STATUS = '�԰�' THEN
      UPDATE Product SET STOCK = STOCK + :NEW.AMOUNT WHERE PRODUCT_ID = :NEW.PRODUCT_ID;
   END IF;
END;
/


-- ���� ������ ����
INSERT INTO PRODUCT_IO VALUES (SEQ_IO_NUM.NEXTVAL, 'nb_ss7', SYSDATE, 30, '�԰�');
INSERT INTO PRODUCT_IO VALUES (SEQ_IO_NUM.NEXTVAL, 'nb_ss7', SYSDATE, 5, '���');
INSERT INTO PRODUCT_IO VALUES (SEQ_IO_NUM.NEXTVAL, 'pc_ibm', SYSDATE, 20, '�԰�');



SELECT * FROM PRODUCT;
SELECT * FROM PRODUCT_IO;

commit;


-- ����� ��ü ��ȸ
		SELECT IO_NUM
			 , PRODUCT_ID
			 , P_NAME
			 , IO_DATE
			 , AMOUNT
			 , STATUS
		FROM
			   PRODUCT_IO
		JOIN   PRODUCT USING(PRODUCT_ID);
        
-- �԰� ��ȸ / ��� ��ȸ WHERE STATUS = '�԰�';  '���';
		SELECT IO_NUM
			 , PRODUCT_ID
			 , P_NAME
			 , IO_DATE
			 , AMOUNT
			 , STATUS
		  FROM PRODUCT_IO
		  JOIN PRODUCT USING(PRODUCT_ID)
	     WHERE STATUS = '�԰�';
        