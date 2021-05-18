Create Table User (
Id integer NOT NULL,
Code VARCHAR(20) not null,
Name VARCHAR(20) not null,
Surname VARCHAR(20) not null,
Password VARCHAR(100) not null,
Email VARCHAR(50) not null,
Phone VARCHAR(20),
Status SmallInt not null);

Create Unique Index UserPK On User(Id);
Create Unique Index UserCodeUIdx On User(Code);
Create Unique Index UserEmailUIdx On User(Email);

Create SEQUENCE SEQ_USERPK;
