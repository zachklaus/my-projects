DROP TABLE IF EXISTS author,
                     book,
                     publisher,
                     member,
                     publisher_phone,
                     author_phone,
                     borrowed_by,
                     written_by;

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
  FOREIGN KEY (pubID) REFERENCES publisher (pubID) ON DELETE CASCADE,
  PRIMARY KEY (ISBN)
);

CREATE TABLE member (
  memberID     INT             NOT NULL,
  last_name    VARCHAR(50)     NOT NULL,
  first_name   VARCHAR(50)     NOT NULL,
  DOB          DATE            NOT NULL,
  PRIMARY KEY (memberID)
);

CREATE TABLE publisher_phone (
  pubID        INT             NOT NULL,
  pNumber      VARCHAR(50)     NOT NULL,
  type         CHAR(1)     NOT NULL,
  FOREIGN KEY (pubID) REFERENCES publisher (pubID) ON DELETE CASCADE,
  PRIMARY KEY (pubID, pNumber)
);

CREATE TABLE author_phone (
  author_ID    INT             NOT NULL,
  pNumber      VARCHAR(50)     NOT NULL,
  type         CHAR(1)     NOT NULL,
  FOREIGN KEY (author_ID) REFERENCES author (author_ID) ON DELETE CASCADE,
  PRIMARY KEY (author_ID, pNumber)
);

CREATE TABLE borrowed_by (
  memberID        INT             NOT NULL,
  ISBN            VARCHAR(50)     NOT NULL,
  checkout_date   DATE            NOT NULL,
  checkin_date    DATE,
  FOREIGN KEY (memberID) REFERENCES member (memberID) ON DELETE CASCADE,
  FOREIGN KEY (ISBN) REFERENCES book (ISBN) ON DELETE CASCADE,
  PRIMARY KEY (memberID, ISBN, checkout_date)
);

CREATE TABLE written_by (
  ISBN            VARCHAR(50)    NOT NULL,
  author_ID       INT            NOT NULL,
  FOREIGN KEY (ISBN) REFERENCES book (ISBN) ON DELETE CASCADE,
  FOREIGN KEY (author_ID) REFERENCES author (author_ID) ON DELETE CASCADE,
  PRIMARY KEY (ISBN, author_ID)
);
