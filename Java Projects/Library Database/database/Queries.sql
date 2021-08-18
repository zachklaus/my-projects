SELECT * FROM borrowed_by;

SELECT m.memberID, m.last_name, m.first_name, b.title
FROM book b
     JOIN borrowed_by bb ON bb.ISBN = b.ISBN
     JOIN member m ON m.memberID = bb.memberID
WHERE bb.checkin_date IS NULL;
