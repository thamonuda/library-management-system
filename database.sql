
CREATE DATABASE library;
USE library;


CREATE TABLE Member(
     	MembID VARCHAR(6) NOT NULL,
	MembName VARCHAR(30) NOT NULL,
	DOB DATE,
	MembAddress VARCHAR(30),
	PRIMARY KEY (MembID)
);

CREATE TABLE Categorie (
    CategID VARCHAR(6) NOT NULL,
    Categories VARCHAR(30) NOT NULL,
    PRIMARY KEY (CategID)
);

CREATE TABLE Book(
	BookID VARCHAR(6) NOT NULL,
        BookName VARCHAR(30) NOT NULL,
	CategID VARCHAR(50) NOT NULL,
        BookCount INT(5) NOT NULL,
	Author VARCHAR(30),
        PRIMARY KEY (BookID),
        FOREIGN KEY(CategID) REFERENCES Categorie(CategID) 
);



-- Create BorrowDetail table
CREATE TABLE BorrowDetail(
    BorrowID VARCHAR(6) NOT NULL,
    BookID VARCHAR(6) NOT NULL,
    MembID VARCHAR(6) NOT NULL,
    CategID VARCHAR(6) NOT NULL,
    IssuedDate DATE,
    DueDate DATE,
    PRIMARY KEY (BorrowID),
    FOREIGN KEY(BookID) REFERENCES Book(BookID),
    FOREIGN KEY(MembID) REFERENCES Member(MembID), 
    FOREIGN KEY(CategID) REFERENCES Categorie(CategID)
);

-- Create ReturnedDetail table
CREATE TABLE ReturnedDetail(
    BorrowID VARCHAR(6) NOT NULL,
    BookID VARCHAR(6) NOT NULL,
    MembID VARCHAR(6) NOT NULL,
    DueDate DATE,
    ReturnedDate DATE,
    LateDays INT(5),
    PRIMARY KEY (BorrowID),
    FOREIGN KEY(BookID) REFERENCES Book(BookID),
    FOREIGN KEY(MembID) REFERENCES Member(MembID)
);



-- Create the trigger to set DueDate and calculate LateDays before insert
DELIMITER //

CREATE TRIGGER before_insert_returned_detail
BEFORE INSERT ON ReturnedDetail
FOR EACH ROW
BEGIN
    DECLARE bd_due_date DATE;

    -- Get the DueDate from BorrowDetail
    SELECT DueDate INTO bd_due_date
    FROM BorrowDetail
    WHERE BorrowID = NEW.BorrowID;

    -- Set the DueDate in the new row and calculate LateDays
    SET NEW.DueDate = bd_due_date;
    SET NEW.LateDays = DATEDIFF(NEW.ReturnedDate, bd_due_date);
END; //

DELIMITER ;





INSERT INTO ReturnedDetail (BorrowID, BookID, MembID, ReturnedDate)
VALUES ('R001', 'B001', 'M001','2024-07-26');


INSERT INTO BorrowDetail
VALUES('R001','B001','M001','C006','2024-7-13','2024-7-24');



INSERT INTO Book
VALUES('B001','Madol Doova','C006','5','Martin Wickramasinghe');
INSERT INTO Book
VALUES('B002','Music Book','C001','2','Dave Grohl');
INSERT INTO Book
VALUES('B003','Graphic Science','C002','3','Darryl Cunningham');
INSERT INTO Book
VALUES('B004','Bhagawat Gita','C003','8','Veda Vyas');
INSERT INTO Book
VALUES('B005','The Art','C004','4','Davin Chalers');
INSERT INTO Book
VALUES('B006','Narnia','C005','7','C.S.Lewis');
INSERT INTO Book
VALUES('B007','Hackers','C007','5','Steven levy');



INSERT INTO Member
VALUES('M001','Nimal','1981-2-6','No.20,Walana,Panadura');
INSERT INTO Member
VALUES('M002','Kamal','2000-2-6','No.10,Paratta,Panadura');
INSERT INTO Member
VALUES('M003','Gunapala','2005-7-6','No.25,Ginigama,Panadura');
INSERT INTO Member
VALUES('M004','Amarapala','1999-5-1','No.55,Padukka,Panadura');
INSERT INTO Member
VALUES('M005','Jinapala','2001-1-6','No.33,Nagoda,Panadura');
INSERT INTO Member
VALUES('M006','Amarawatta','2000-5-8','No.44,Wadduwa,Panadura');
INSERT INTO Member
VALUES('M007','Samarapala','2008-2-6','No.45,Pamunuwa,Panadura');
INSERT INTO Member
VALUES('M008','Navidu','2004-4-6','No.4,Pinwatta,Panadura');

INSERT INTO Categorie
VALUES('C001','Music');
INSERT INTO Categorie
VALUES('C002','Science');
INSERT INTO Categorie
VALUES('C003','History');
INSERT INTO Categorie
VALUES('C004','Art');
INSERT INTO Categorie
VALUES('C005','Religion');
INSERT INTO Categorie
VALUES('C006','Novel');
INSERT INTO Categorie
VALUES('C007','Technology');







