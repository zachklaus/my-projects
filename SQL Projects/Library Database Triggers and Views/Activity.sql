INSERT INTO book VALUES('96-42013-10510', 'Title Growing your own Weeds', 2012, 10000);
INSERT INTO stored_on VALUES ('Main', 8, '96-42013-10510', 1);

UPDATE
  stored_on
SET
  total_copies = 8
WHERE
  library_name = 'Main'
  AND ISBN = '96-42103-10907';


DELETE FROM
  author
WHERE
  first_name = 'Grace'
  AND last_name = 'Slick';


INSERT INTO author VALUES (305, 'Adams', 'Commander');


INSERT INTO stored_on VALUES ('South Park', 8, '96-42013-10510', 1);


DELETE FROM
  stored_on
WHERE
  ISBN = '96-42103-11003';


UPDATE
  stored_on
SET
  total_copies = 4
WHERE
  library_name = 'South Park'
  AND ISBN = '96-42103-11604';


INSERT INTO book VALUES ('96-42013-10513', 'Title Growing your own Weeds', 2012, 90000);
INSERT INTO stored_on VALUES ('Main', 8, '96-42013-10513', 1);


Select * from audit;
