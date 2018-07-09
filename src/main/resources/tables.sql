CREATE TABLE `books` (
  `isbn` varchar(17) NOT NULL,
  `title` varchar(120) NOT NULL,
  `author` varchar(40) NOT NULL,
  `available` tinyint(4) NOT NULL,
  PRIMARY KEY (`isbn`)
);


CREATE TABLE `members` (
  `card_Id` int(6) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(40) NOT NULL,
  `address` varchar(80) NOT NULL,
  `phone` varchar(9) NOT NULL,
  `date_registered` date NOT NULL,
  PRIMARY KEY (`card_Id`)
);

CREATE TABLE `loaned_books` (
  `loan_Id` int(6) NOT NULL AUTO_INCREMENT,
  `isbn` varchar(17) NOT NULL,
  `card_Id` int(6) NOT NULL,
  `date_out` date NOT NULL,
  `date_due` date NOT NULL,
  PRIMARY KEY (`loan_Id`),
  KEY `FK5b8x3eosngky8htxoc95721id` (`isbn`),
  KEY `FKmhdabwodngj4gwo5jfs9iudgx` (`card_Id`),
  CONSTRAINT `FK5b8x3eosngky8htxoc95721id` FOREIGN KEY (`isbn`) REFERENCES `books` (`isbn`),
  CONSTRAINT `FKmhdabwodngj4gwo5jfs9iudgx` FOREIGN KEY (`card_Id`) REFERENCES `members` (`card_Id`),
  CONSTRAINT `loaned_books_ibfk_1` FOREIGN KEY (`card_Id`) REFERENCES `members` (`card_Id`),
  CONSTRAINT `loaned_books_ibfk_2` FOREIGN KEY (`isbn`) REFERENCES `books` (`isbn`)
);

CREATE TABLE `library_accounts` (
  `username` varchar(20) NOT NULL,
  `password` varchar(60) NOT NULL,
  PRIMARY KEY (`username`)
);

INSERT INTO Library_Accounts(username,password)VALUES
('admin','$2a$10$N4ucdaTk7yRterw.3QXbp.EYqqJbiP.2E1z8RDVtY4h4kx0h1DtYK'),
('novak','$2a$10$JJi5EOUNHyth7ntg4U.IRec0.1n/.9f3imHz25StR/81iG6EcJohy');

INSERT INTO Members(card_Id,full_name,address,phone,date_registered)VALUES
(1,'Novak Saljic','Bulevar Djindjica 24','604444272','2018-06-22'),
(2,'Gojko Puska','Milosa Krajice 5','694521114','2017-02-09'),
(3,'Kojot Paric','Kovaceviceva 96','652335991','2018-06-22'),
(4,'Pavle Garas','Kraljeva 18','621112956','2017-12-02'),
(5,'Marko Skljaric','Pajkiceva Budza 1','652335991','2017-10-12');

INSERT INTO Books(isbn,title,author,available)VALUES
('414-5-215156-56-1','Good Bad Ugly',"Example Author",true,'A05'),
('978-1-60309-050-6','To Kill a Mockingbird',"Harper Lee",true,'A01'),
('978-1-60309-266-1','Pride and Prejudice',"Jane Austen",true,'A03'),
('978-1-891830-17-4','Alices Adventures in Wonderland & Through the Looking-Glass',"Lewis Carroll",true,'A05');



DELIMITER $$
CREATE TRIGGER return_book BEFORE DELETE ON Loaned_Books FOR EACH ROW BEGIN
UPDATE Books SET available=1 WHERE isbn=OLD.isbn;
END $$
DELIMITER ; $$


DELIMITER $$
CREATE TRIGGER loan_book AFTER INSERT ON Loaned_Books FOR EACH ROW BEGIN
UPDATE Books SET available=0 WHERE isbn=NEW.isbn;
END $$
DELIMITER ; $$