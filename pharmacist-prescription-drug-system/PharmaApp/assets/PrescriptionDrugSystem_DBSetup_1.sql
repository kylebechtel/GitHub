create table address
(ID int(6) NOT NULL,  
 StreetNumberName VARCHAR(50), City VARCHAR(20), StateAbbreviation VARCHAR(2), PostalCode int(5),
 PRIMARY KEY USING BTREE(ID));

create table doctor
(ID int(6) NOT NULL, FirstName VARCHAR(25), LastSurname VARCHAR(25),
 addressID int(6), TelephoneNumber VARCHAR(10), Email VARCHAR(30),
 Specialization VARCHAR(25),
 PRIMARY KEY USING BTREE(ID),
 FOREIGN KEY (addressID) REFERENCES address(ID));
 
 create table drug
(ID int(6) NOT NULL, Name VARCHAR(25), CompanyName VARCHAR(25), addressID int(6),
 CompanyTelephoneNumber VARCHAR(10), CompanyContact VARCHAR(50), SideEffects VARCHAR(200),
 PRIMARY KEY USING BTREE(ID),
 FOREIGN KEY (addressID) REFERENCES address(ID));
 
 create table insuranceco
(ID int(6) NOT NULL, Name VARCHAR(25), addressID int(6), TelephoneNumber VARCHAR(10), Email VARCHAR(30),
 Contact VARCHAR(50), ContactPhone VARCHAR(10),
 PRIMARY KEY USING BTREE(ID),
 FOREIGN KEY (addressID) REFERENCES address(ID));
 
 create table patient
(ID int(6) NOT NULL, FirstName VARCHAR(25), LastSurname VARCHAR(25),
 Birthdate datetime, Gender VARCHAR(1), addressID int(6), 
 TelephoneNumber VARCHAR(10), Email VARCHAR(30), Contact VARCHAR(50), ContactPhone VARCHAR(10),
 PRIMARY KEY USING BTREE(ID),
 FOREIGN KEY (addressID) REFERENCES address(ID));
 
 create table pharmacist
(ID int(6) NOT NULL, FirstName VARCHAR(25), LastSurname VARCHAR(25),
 addressID int(6), TelephoneNumber VARCHAR(10), Email VARCHAR(30),
 FirstLicensed datetime, StatesLicensed VARCHAR(50),
 PRIMARY KEY USING BTREE(ID),
 FOREIGN KEY (addressID) REFERENCES address(ID));
 
 create table prescription
(ID int(6) NOT NULL, patientID int(6), drugID int(6),
 Dosage VARCHAR(25), OrderDate datetime, Amount int(6), RefillAmount int(6),
 OrderFulfillDate datetime, PatientPickupDate datetime, doctorID int(6),  pharmacistID int(6),
 PRIMARY KEY USING BTREE(ID),
 FOREIGN KEY (patientID) REFERENCES patient(ID),
 FOREIGN KEY (drugID) REFERENCES drug(ID),
 FOREIGN KEY (doctorID) REFERENCES doctor(ID),
 FOREIGN KEY (pharmacistID) REFERENCES pharmacist(ID));
 
 
INSERT INTO `address` (`ID`,`StreetNumberName`,`City`,`StateAbbreviation`,`PostalCode`) VALUES (1,'123 Venture Way','Someplace','CA',12345);
INSERT INTO `address` (`ID`,`StreetNumberName`,`City`,`StateAbbreviation`,`PostalCode`) VALUES (2,'323 Some Street','Nowhere','GA',30033);
INSERT INTO `address` (`ID`,`StreetNumberName`,`City`,`StateAbbreviation`,`PostalCode`) VALUES (3,'666 Evil Drive','Inferno','FL',66666);
INSERT INTO `address` (`ID`,`StreetNumberName`,`City`,`StateAbbreviation`,`PostalCode`) VALUES (4,'1337 Street','Pwned','TN',56424);
INSERT INTO `address` (`ID`,`StreetNumberName`,`City`,`StateAbbreviation`,`PostalCode`) VALUES (5,'555 Main Street','Atlanta','GA',30302);
INSERT INTO `address` (`ID`,`StreetNumberName`,`City`,`StateAbbreviation`,`PostalCode`) VALUES (6,'234 Sunny Lane','Atlanta','GA',30303);

INSERT INTO `doctor` (`ID`,`FirstName`,`LastSurname`,`addressID`,`TelephoneNumber`,`Email`,`Specialization`) VALUES (1,'Ernesto','Guevara',2,'4041234567','eguevara@webmd.com','Internal Medicine');
INSERT INTO `doctor` (`ID`,`FirstName`,`LastSurname`,`addressID`,`TelephoneNumber`,`Email`,`Specialization`) VALUES (2,'Quentin','Ball',4,'6781234567','Qball@icup.com','Family Medicine');
INSERT INTO `doctor` (`ID`,`FirstName`,`LastSurname`,`addressID`,`TelephoneNumber`,`Email`,`Specialization`) VALUES (3,'Paul','Entmann',5,'7701234567','paul.entmann@hospital.org','Anasthesiology');

INSERT INTO `drug` (`ID`,`Name`,`CompanyName`,`addressID`,`CompanyTelephoneNumber`,`CompanyContact`,`SideEffects`) VALUES (1,'WeightWeight Gain 4000','Beefcake Industries',4,'4567891425','Matt Stone','Getting Totally Ripped');
INSERT INTO `drug` (`ID`,`Name`,`CompanyName`,`addressID`,`CompanyTelephoneNumber`,`CompanyContact`,`SideEffects`) VALUES (2,'Ritalin','Behaivior Pharm',2,'5551234678','Trey Parker','Calming Down');
INSERT INTO `drug` (`ID`,`Name`,`CompanyName`,`addressID`,`CompanyTelephoneNumber`,`CompanyContact`,`SideEffects`) VALUES (3,'Ritalout','Behaivior Pharm',2,'5551234678','Trey Parker','Waking Up');
 
INSERT INTO `insuranceco` (`ID`,`Name`,`addressID`,`TelephoneNumber`,`Email`,`Contact`,`ContactPhone`) VALUES (1,'Medicare',2,'2025658989','Support@Medicare.gov','Uncle Sam','6521234848');
INSERT INTO `insuranceco` (`ID`,`Name`,`addressID`,`TelephoneNumber`,`Email`,`Contact`,`ContactPhone`) VALUES (2,'BCBS',5,'2225556666','CustomerCare@bcbs.com','Noh Wan','4448883333');

INSERT INTO `patient` (`ID`,`FirstName`,`LastSurname`,`Birthdate`,`Gender`,`addressID`,`TelephoneNumber`,`Email`,`Contact`,`ContactPhone`) VALUES (1,'Rusty','Venture','1970-12-25 00:00:00','M',1,'1234567890','rustyrocks@hotmail.com','Jonas Venture','4567891230');
INSERT INTO `patient` (`ID`,`FirstName`,`LastSurname`,`Birthdate`,`Gender`,`addressID`,`TelephoneNumber`,`Email`,`Contact`,`ContactPhone`) VALUES (2,'Broc','Samson','1965-08-15 00:00:00','M',1,'7778889999','bsamson@osi.gov',null,null);
INSERT INTO `patient` (`ID`,`FirstName`,`LastSurname`,`Birthdate`,`Gender`,`addressID`,`TelephoneNumber`,`Email`,`Contact`,`ContactPhone`) VALUES (3,'Roy','Brisby','1954-01-12 00:00:00','M',3,'6665554444','busybee@isp.com','Mandalay','7178289696');
INSERT INTO `patient` (`ID`,`FirstName`,`LastSurname`,`Birthdate`,`Gender`,`addressID`,`TelephoneNumber`,`Email`,`Contact`,`ContactPhone`) VALUES (4,'Triana','Orpheus','1994-10-31 00:00:00','F',1,'1112223333',null,'Byron Orpheus','1314647979');
INSERT INTO `patient` (`ID`,`FirstName`,`LastSurname`,`Birthdate`,`Gender`,`addressID`,`TelephoneNumber`,`Email`,`Contact`,`ContactPhone`) VALUES (5,'Phantom','Limb','1966-06-06 00:00:00','M',4,'3336669999','jsmith@aol.com','Sheila','4546564646');
INSERT INTO `patient` (`ID`,`FirstName`,`LastSurname`,`Birthdate`,`Gender`,`addressID`,`TelephoneNumber`,`Email`,`Contact`,`ContactPhone`) VALUES (6,'Sally','Impossible','1981-05-11 00:00:00','F',5,'2225558888','nowyouseeme@impossiblelabs.org','Richard Impossible','8587479393');

INSERT INTO `pharmacist` (`ID`,`FirstName`,`LastSurname`,`addressID`,`TelephoneNumber`,`Email`,`FirstLicensed`,`StatesLicensed`) VALUES (1,'Mort','Goldman',6,'6465852525','mgoldman@pharm.com','1985-05-10 00:00:00','NY');
INSERT INTO `pharmacist` (`ID`,`FirstName`,`LastSurname`,`addressID`,`TelephoneNumber`,`Email`,`FirstLicensed`,`StatesLicensed`) VALUES (2,'Sum','Guy',3,'3336664444','sumemail@address.com','1978-08-20 00:00:00','GA');

INSERT INTO `prescription` (`ID`,`patientID`,`drugID`,`Dosage`,`OrderDate`,`Amount`,`RefillAmount`,`OrderFulfillDate`,`PatientPickupDate`,`doctorID`,`pharmacistID`) VALUES (1,1,1,'300mg','2014-01-21 00:00:00',90,2,'2014-01-21 00:00:00','2014-01-22 00:00:00',1,1);
INSERT INTO `prescription` (`ID`,`patientID`,`drugID`,`Dosage`,`OrderDate`,`Amount`,`RefillAmount`,`OrderFulfillDate`,`PatientPickupDate`,`doctorID`,`pharmacistID`) VALUES (2,3,2,'10mg','2014-02-04 00:00:00',30,4,'2014-02-07 00:00:00','2014-02-07 00:00:00',2,2);
INSERT INTO `prescription` (`ID`,`patientID`,`drugID`,`Dosage`,`OrderDate`,`Amount`,`RefillAmount`,`OrderFulfillDate`,`PatientPickupDate`,`doctorID`,`pharmacistID`) VALUES (3,4,2,'10mg','2014-03-03 00:00:00',30,3,'2014-03-03 00:00:00','2014-03-07 00:00:00',2,1);
INSERT INTO `prescription` (`ID`,`patientID`,`drugID`,`Dosage`,`OrderDate`,`Amount`,`RefillAmount`,`OrderFulfillDate`,`PatientPickupDate`,`doctorID`,`pharmacistID`) VALUES (4,4,3,'5mg','2014-03-08 00:00:00',30,3,'2014-03-08 00:00:00','2014-03-08 00:00:00',3,2);