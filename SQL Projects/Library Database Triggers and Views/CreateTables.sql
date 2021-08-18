DROP TABLE IF EXISTS audit,
                     borrowed_by,
                     member,
                     publisher_phone,
                     author_phone,
                     stored_on,
                     written_by,
                     author,
                     book,
                     phone,
                     publisher,
                     shelf,
                     library;

CREATE TABLE author (
  author_ID    INT             NOT NULL,
  last_name    VARCHAR(50)     NOT NULL,
  first_name   VARCHAR(50)     NOT NULL,
  PRIMARY KEY (author_ID)
);

CREATE TABLE publisher (
  pubID        INT             NOT NULL,
  pub_name     VARCHAR(50)     NOT NULL,
  PRIMARY KEY (pubID)
);

CREATE TABLE book (
  ISBN            VARCHAR(50)     NOT NULL,
  title           VARCHAR(50)     NOT NULL,
  year_published  INT             NOT NULL,
  pubID           INT             NOT NULL,
  FOREIGN KEY (pubID) REFERENCES publisher (pubID) ON DELETE RESTRICT,
  PRIMARY KEY (ISBN)
);

CREATE TABLE member (
  memberID     INT             NOT NULL,
  last_name    VARCHAR(50)     NOT NULL,
  first_name   VARCHAR(50)     NOT NULL,
  DOB          DATE            NOT NULL,
  gender       CHAR(1)         NOT NULL,
  PRIMARY KEY (memberID)
);

CREATE TABLE phone (
	pNumber		   CHAR(20)        NOT NULL,
	type		     CHAR(1)         NOT NULL,
	PRIMARY KEY	(pNumber)
);

CREATE TABLE author_phone (
	author_ID 	INT		NOT NULL,
	pNumber		CHAR(20)	NOT NULL,
	FOREIGN KEY (author_ID) REFERENCES author (author_ID) ON DELETE RESTRICT,
	FOREIGN KEY (pNumber) REFERENCES phone (pNumber) ON DELETE RESTRICT,
	PRIMARY KEY (author_ID, pNumber)
);

CREATE TABLE publisher_phone (
	pubID 	  INT		    NOT NULL,
	pNumber		CHAR(20)	NOT NULL,
	FOREIGN KEY (pubID) REFERENCES publisher (pubID) ON DELETE RESTRICT,
	FOREIGN KEY (pNumber) REFERENCES phone (pNumber) ON DELETE RESTRICT,
	PRIMARY KEY (pubID, pNumber)
);

CREATE TABLE borrowed_by (
  memberID        INT             NOT NULL,
  ISBN            VARCHAR(50)     NOT NULL,
  checkout_date   DATE            NOT NULL,
  checkin_date    DATE,
  FOREIGN KEY (memberID) REFERENCES member (memberID) ON DELETE RESTRICT,
  FOREIGN KEY (ISBN) REFERENCES book (ISBN) ON DELETE RESTRICT,
  PRIMARY KEY (memberID, ISBN, checkout_date)
);

CREATE TABLE written_by (
  ISBN            VARCHAR(50)    NOT NULL,
  author_ID       INT            NOT NULL,
  FOREIGN KEY (ISBN) REFERENCES book (ISBN) ON DELETE RESTRICT,
  FOREIGN KEY (author_ID) REFERENCES author (author_ID) ON DELETE RESTRICT,
  PRIMARY KEY (ISBN, author_ID)
);

CREATE TABLE library (
  name  VARCHAR(50)   NOT NULL,
  street  VARCHAR(50)   NOT NULL,
  city  VARCHAR(50)   NOT NULL,
  state   VARCHAR(50) NOT NULL,
  PRIMARY KEY (name)
);

CREATE TABLE shelf (
  library_name  VARCHAR(50) NOT NULL,
  shelf_number INT  NOT NULL,
  floor_number   INT   NOT NULL,
  FOREIGN KEY (library_name) REFERENCES library (name) ON DELETE RESTRICT,
  PRIMARY KEY (library_name, shelf_number)
);

CREATE TABLE audit (
  record_id   INT   NOT NULL  AUTO_INCREMENT,
  table_name  VARCHAR(50)   NOT NULL,
  action  ENUM('insert', 'update', 'delete')   NOT NULL,
  date_and_time   DATETIME  NOT NULL,
  PRIMARY KEY (record_id)
);

CREATE TABLE stored_on (
  library_name  VARCHAR(50)   NOT NULL,
  shelf_number  INT   NOT NULL,
  ISBN  VARCHAR(50)   NOT NULL,
  total_copies  INT   NOT NULL,
  FOREIGN KEY (library_name, shelf_number) REFERENCES shelf (library_name, shelf_number) ON DELETE RESTRICT,
  FOREIGN KEY (ISBN) REFERENCES book (ISBN) ON DELETE RESTRICT,
  PRIMARY KEY (library_name, shelf_number, ISBN)
);
