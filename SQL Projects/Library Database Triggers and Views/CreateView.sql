DROP VIEW IF EXISTS book_info;

CREATE SQL SECURITY INVOKER VIEW book_info AS
SELECT
  b.title, GROUP_CONCAT(DISTINCT a.last_name) AS authors, so.shelf_number, s.floor_number, so.library_name
FROM
  book b
  JOIN written_by wb ON wb.ISBN = b.ISBN
  JOIN author a ON a.author_ID = wb.author_ID
  JOIN stored_on so ON so.ISBN = b.ISBN
  JOIN shelf s ON s.library_name = so.library_name
       AND s.shelf_number = so.shelf_number
GROUP BY
  b.title, s.library_name;
