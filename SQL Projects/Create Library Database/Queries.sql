SELECT
  *
FROM
  book b
ORDER BY
  b.ISBN;


SELECT
  *
FROM
  member m
ORDER BY
  m.last_name, m.first_name;


SELECT
  *
FROM
  author a
ORDER BY
  a.last_name, a.first_name;


SELECT
  *
FROM
  publisher p
ORDER BY
  p.pub_name;


SELECT
  *
FROM
  publisher_phone pn
ORDER BY
  pn.pNumber;


SELECT
  *
FROM
  author_phone an
ORDER BY
  an.pNumber;
  
  
SELECT
  *
FROM
  borrowed_by;


SELECT
  *
FROM
  written_by;


SELECT
  m.first_name, m.last_name
FROM
  member m
WHERE
  m.last_name REGEXP '^[B]';


SELECT
  b.title
FROM
  book b
  JOIN publisher p ON p.pubID = b.pubID
WHERE
  p.pub_name = 'Coyote Publishing'
ORDER BY
  b.title;


SELECT
  m.first_name, m.last_name, m.memberID, b.title AS Has_Checked_Out
FROM
  member m
  JOIN borrowed_by bb ON bb.memberID = m.memberID
  JOIN book b ON b.ISBN = bb.ISBN
WHERE
  bb.checkin_date IS NULL;


SELECT
  a.first_name, a.last_name, a.author_ID, b.title AS Has_Written
FROM
  author a
  JOIN written_by wb ON wb.author_ID = a.author_ID
  JOIN book b ON b.ISBN = wb.ISBN;


SELECT
  a.first_name, a.last_name, ap.pNumber
FROM
  author a
  JOIN author_phone ap ON ap.author_ID = a.author_ID
  JOIN author_phone app
WHERE
  ap.pNumber = app.pNumber
  AND ap.author_ID <> app.author_ID;
