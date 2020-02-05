CREATE TABLE VOTING_SESSION
  (
    ID NUMBER(10) NOT NULL,
    AGENDA_ID INTEGER NOT NULL,
    START_VOTING_SESSION TIMESTAMP NOT NULL,
    FINISH_VOTING_SESSION TIMESTAMP NOT NULL,
    OPENED NUMBER(1) DEFAULT 1 NOT NULL,

    CONSTRAINT "VOTING_SESSION_PK" PRIMARY KEY ("ID") ENABLE,
    CONSTRAINT VOTING_SESSION_AGENDA_FK
        FOREIGN KEY ( AGENDA_ID ) REFERENCES AGENDA ( ID )
  );