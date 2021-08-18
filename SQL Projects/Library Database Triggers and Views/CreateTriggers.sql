DROP TRIGGER IF EXISTS add_author;
DROP TRIGGER IF EXISTS add_book_to_shelf;
DROP TRIGGER IF EXISTS delete_book_from_shelf;
DROP TRIGGER IF EXISTS modify_number_copies;

DELIMITER #

CREATE TRIGGER add_author AFTER INSERT ON author
FOR EACH ROW
BEGIN
  INSERT INTO audit (table_name, action, date_and_time) VALUES ('author', 'insert', NOW());
END#

DELIMITER ;


DELIMITER #

CREATE TRIGGER add_book_to_shelf AFTER INSERT ON stored_on
FOR EACH ROW
BEGIN
  INSERT INTO audit (table_name, action, date_and_time) VALUES ('stored_on', 'insert', NOW());
END#

DELIMITER ;


DELIMITER #

CREATE TRIGGER delete_book_from_shelf AFTER DELETE ON stored_on
FOR EACH ROW
BEGIN
  INSERT INTO audit (table_name, action, date_and_time) VALUES ('stored_on', 'delete', NOW());
END#

DELIMITER ;


DELIMITER #

CREATE TRIGGER modify_number_copies AFTER UPDATE ON stored_on
FOR EACH ROW
BEGIN
  IF !(NEW.total_copies <=> OLD.total_copies) THEN
    INSERT INTO audit (table_name, action, date_and_time) VALUES ('stored_on', 'update', NOW());
  END IF;
END#

DELIMITER ;
