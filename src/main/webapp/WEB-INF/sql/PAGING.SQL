SELECT  
    	    BNO
    	  , MENU_ID 
    	  , TITLE    	  
    	  , WRITER
    	  , TO_CHAR(REGDATE, 'YYYY-MM-DD') REGDATE
    	  , HIT
    	 FROM  
           BOARD
          WHERE  MENU_ID = 'MENU01' 	    
         ORDER BY  BNO DESC
         OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY 
         
         
  -- 생성한 테이블
CREATE TABLE TEST_EXAMPLE_TB (
    seq_no NUMBER NOT NULL,
    data1 VARCHAR2(100),
    data2 VARCHAR2(100)
);

---- PL/SQL LOOP, FOR문 ----
DECLARE
 vn_data1 VARCHAR2(100) := 'test1_';
 vn_data2 VARCHAR2(100) := 'test2_';
BEGIN
	 FOR i IN 4..1000
	 LOOP
	 INSERT INTO BOARD
	 VALUES
	 (i, 'MENU01', vn_data1 || i, vn_data2 || i, 'BBB', SYSDATE, 0);
	END LOOP;
    COMMIT;
END;
/
