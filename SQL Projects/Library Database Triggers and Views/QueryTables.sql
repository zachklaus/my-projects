SELECT
  *
FROM
  library l
ORDER BY
  l.name;


SELECT
  *
FROM
  shelf s
ORDER BY
  s.library_name, s.floor_number;


SELECT
  *
FROM
  stored_on so
ORDER BY
  so.ISBN;


SELECT
  b.title, so.shelf_number, so.library_name
FROM
  book b
  JOIN stored_on so ON so.ISBN = b.ISBN
  JOIN stored_on soo
WHERE
  so.library_name <> soo.library_name
  AND so.ISBN = soo.ISBN
ORDER BY
  b.title;

  
SELECT
  so.shelf_number, so.library_name, COUNT(so.ISBN) AS number_titles
FROM
  stored_on so
GROUP BY
  so.shelf_number, so.library_name
ORDER BY
  so.library_name, so.shelf_number;
