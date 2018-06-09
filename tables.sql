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
