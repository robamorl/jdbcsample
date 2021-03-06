DROP TABLE SPRING_DEV.M_ROLE PURGE;

CREATE TABLE SPRING_DEV.M_ROLE (
    ROLE_ID NUMBER(19,0) PRIMARY KEY,
    ROLE_NAME VARCHAR(100) NOT NULL,
    DESCRIPTION VARCHAR(100) NOT NULL,
    ENTRY_DATE TIMESTAMP,
    ENTRY_USER CHAR(8),
    UPDATE_DATE TIMESTAMP,
    UPDATE_USER CHAR(8),
    DELETE_FLG NUMERIC(1)
    );

 CREATE SEQUENCE SPRING_DEV.ROLE_ID_SEQ
    INCREMENT BY 1
    START WITH 1
    MAXVALUE 99999
    NOCYCLE
    NOCACHE;