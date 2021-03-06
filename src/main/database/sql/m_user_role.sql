DROP TABLE M_USER_ROLE PURGE;

CREATE TABLE SPRING_DEV.M_USER_ROLE (
    USER_ID NUMBER(19,0),
    ROLE_ID NUMBER(19,0),
    primary key(USER_ID, ROLE_ID),
    DEFAULT_FLG NUMERIC(1),
    ENTRY_DATE TIMESTAMP,
    ENTRY_USER CHAR(8),
    UPDATE_DATE TIMESTAMP,
    UPDATE_USER CHAR(8),
    DELETE_FLG NUMERIC(1)
    );

CREATE SEQUENCE SPRING_DEV.USER_ROLE_ID_SEQ
    INCREMENT BY 1
    START WITH 1
    MAXVALUE 99999
    NOCYCLE
    NOCACHE;