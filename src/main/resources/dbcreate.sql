PRAGMA foreign_keys;
PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS AdministratorGroup (
id integer PRIMARY KEY,
groupName TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Administrator (
id integer PRIMARY KEY,
administratorName text NOT NULL,
administratorGroupId integer NOT NULL,
FOREIGN KEY (administratorGroupId)
REFERENCES AdministratorGroup (id)
ON UPDATE CASCADE
ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Article (
id integer PRIMARY KEY,
author text NOT NULL,
authorDate text NOT NULL,
title text NOT NULL,
description text NOT NULL,
content text NOT NULL);

CREATE TABLE IF NOT EXISTS Artist (
id INTEGER PRIMARY KEY,
artistName TEXT
);

CREATE TABLE IF NOT EXISTS Blog (
id integer PRIMARY KEY,
author text NOT NULL,
authorDate text NOT NULL,
title text NOT NULL,
description text NOT NULL,
content text NOT NULL);

CREATE TABLE IF NOT EXISTS Chat (
id INTEGER PRIMARY KEY,
userIdFrom INTEGER NOT NULL,
userIdTo INTEGER NOT NULL,
dateTime TEXT NOT NULL,
priority INTEGER,
subject TEXT,
message TEXT
);

CREATE TABLE IF NOT EXISTS Configuration (
id INTEGER PRIMARY KEY,
messages TEXT NOT NULL,
views INTEGER NOT NULL,
shares INTEGER NOT NULL,
users INTEGER NOT NULL,
dateTime TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Social (
id INTEGER PRIMARY KEY,
postname TEXT NOT NULL,
content TEXT NOT NULL,
reviewed INTEGER NOT NULL,
published TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Event (
id integer PRIMARY KEY,
title text NOT NULL,
startDate text NOT NULL,
endDate text NOT NULL,
location text NOT NULL,
description text NOT NULL);

CREATE TABLE IF NOT EXISTS EventAdministrator (
id integer PRIMARY KEY,
administratorName text NOT NULL,
title text NOT NULL,
subTitle text NOT NULL,
contactInfo text NOT NULL,
eventId integer,
CONSTRAINT fk_Event
FOREIGN KEY (eventId)
REFERENCES Event(id)
ON DELETE CASCADE);

CREATE TABLE IF NOT EXISTS EventAdvertisement (
id integer PRIMARY KEY,
title text NOT NULL,
subTitle text NOT NULL,
adImagePath text NOT NULL,
contactInfo text NOT NULL,
eventId integer,
CONSTRAINT fk_Event
FOREIGN KEY (eventId)
REFERENCES Event(id)
ON DELETE CASCADE);

CREATE TABLE IF NOT EXISTS ArtificialIntelligence(
id integer PRIMARY KEY,
title text NOT NULL,
description text NOT NULL,
algorithmPath text,
dataSourcePath text,
dataTargetPath text);

CREATE TABLE IF NOT EXISTS MachineLearning(
id integer PRIMARY KEY,
title text NOT NULL,
description text NOT NULL,
algorithmPath text,
dataSourcePath text,
dataTargetPath text);


CREATE TABLE IF NOT EXISTS Subscription (
id integer PRIMARY KEY,
subscriptionPlan text NOT NULL,
publisher text NOT NULL,
topic text NOT NULL);

CREATE TABLE IF NOT EXISTS Journal (
id integer PRIMARY KEY,
journal text NOT NULL);

CREATE TABLE IF NOT EXISTS Subscriber (
id integer PRIMARY KEY,
subscriber text NOT NULL);

CREATE TABLE IF NOT EXISTS Course
( id INTEGER PRIMARY KEY AUTOINCREMENT,
courseName VARCHAR);

CREATE TABLE IF NOT EXISTS Student
( id INTEGER PRIMARY KEY AUTOINCREMENT,
lastName VARCHAR NOT NULL,
firstName VARCHAR,
courseId INTEGER,
CONSTRAINT fk_Course
FOREIGN KEY (courseId)
REFERENCES Course(id)
ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Lecture(
instructor TEXT,
lectureName TEXT,
lecturePoster BINARY,
PRIMARY KEY(instructor,lectureName)
);

CREATE TABLE IF NOT EXISTS LectureNote(
id INTEGER,
noteInstructor TEXT,
noteLecture TEXT,
noteText TEXT,
FOREIGN KEY(noteInstructor, noteLecture) 
REFERENCES Lecture(instructor, lectureName)
);

CREATE TABLE IF NOT EXISTS Lecturer(
id INTEGER PRIMARY KEY,
lecturerName TEXT
);

CREATE TABLE IF NOT EXISTS School(
id INTEGER PRIMARY KEY,
schoolName TEXT,
schoolLecturer INTEGER 
REFERENCES Lecturer(id) 
ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS ShopCart (
id INTEGER PRIMARY KEY,
customerId INTEGER,
paymentId INTEGER,
lastModified DATETIME DEFAULT CURRENT_TIMESTAMP,
datePurchased DATETIME DEFAULT CURRENT_TIMESTAMP,
cartStatus INTEGER,
cartDateFinished DATETIME DEFAULT CURRENT_TIMESTAMP,
currency TEXT,
currencyValue FLOAT,
FOREIGN KEY customerId REFERENCES AccountUser(id)
);

CREATE TABLE IF NOT EXISTS CartItem (
id INTEGER PRIMARY KEY,
cartId INTEGER,
itemId INTEGER
FOREIGN KEY cartId REFERENCES ShopCart(id)
FOREIGN KEY itemId REFERENCES ShopItem(id)
);

CREATE TABLE IF NOT EXISTS CartStatus (
id INTEGER PRIMARY KEY
description TEXT);


CREATE TABLE IF NOT EXISTS ShopItem (
id INTEGER PRIMARY KEY,
productId INTEGER,
quantity INTEGER,
FOREIGN KEY productId REFERENCES Product(id)
);

CREATE TABLE IF NOT EXISTS ShopMessage (
id INTEGER PRIMARY KEY,
message TEXT,
email TEXT,
body TEXT,
createdTime DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ShopOrder (
id INTEGER PRIMARY KEY,
customerId INTEGER,
lastModified DATETIME DEFAULT CURRENT_TIMESTAMP,
datePurchased DATETIME DEFAULT CURRENT_TIMESTAMP,
orderAmount FLOAT,
FOREIGN KEY customerId REFERENCES AccountUser(id)
);

CREATE TABLE IF NOT EXISTS ShopOrderCart (
id INTEGER PRIMARY KEY,
orderId INTEGER,
cartId INTEGER,
orderPrice FLOAT NOT NULL DEFAULT 0.00,
orderTax FLOAT NOT NULL DEFAULT 0.00,
FOREIGN KEY orderId REFERENCES ShopOrder(id)
);

CREATE TABLE IF NOT EXISTS ShopOrderStatus (
id INTEGER PRIMARY KEY,
description TEXT
);

CREATE TABLE IF NOT EXISTS ShopPayment (
id INTEGER PRIMARY KEY,
paymentType INTEGER,
ccType TEXT,
ccOwner TEXT,
ccNumber TEXT,
ccExpires TEXT,
lastModified DATETIME DEFAULT CURRENT_TIMESTAMP,
datePurchased DATETIME DEFAULT CURRENT_TIMESTAMP,
orderStatus INTEGER NOT NULL DEFAULT 0,
orderDateFinished DATETIME DEFAULT CURRENT_TIMESTAMP,
currency TEXT,
currencyValue FLOAT,
FOREIGN KEY paymentType REFERENCES ShopPaymentType(id)
);

CREATE TABLE IF NOT EXISTS ShopPaymentType (
id INTEGER PRIMARY KEY,
paymentTypeDescription TEXT
);

CREATE TABLE IF NOT EXISTS ShopProduct(
id INTEGER PRIMARY KEY,
description TEXT,
price FLOAT
);

CREATE TABLE IF NOT EXISTS HrGroup (
id integer PRIMARY KEY,
groupName TEXT NOT NULL
);



CREATE TABLE IF NOT EXISTS HrClient (
id integer PRIMARY KEY,
clientFirstName text NOT NULL,
clientLastName text NOT NULL,
clientSpecialty text NOT NULL,
clientContact text NOT NULL,
hrGroupId integer NOT NULL,
FOREIGN KEY (hrGroupId)
REFERENCES HrGroup (id)
ON UPDATE CASCADE
ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS HrEmployer (
id integer PRIMARY KEY,
employerName text NOT NULL,
employerContact text NOT NULL,
employerContactType text NOT NULL,
employerContactInfo text NOT NULL,
hrGroupId integer NOT NULL,
FOREIGN KEY (hrGroupId)
REFERENCES HrGroup (id)
ON UPDATE CASCADE
ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Researcher (
id integer PRIMARY KEY,
researcherFirstName TEXT NOT NULL,
researcherLastName TEXT NOT NULL,
researcherDegree TEXT NOT NULL,
researcherMajor TEXT NOT NULL,
researcherInstitution TEXT NOT NULL,
researcherSpecialty TEXT NOT NULL,
);

CREATE TABLE IF NOT EXISTS Review (
id integer PRIMARY KEY,
author text NOT NULL,
authorDate text NOT NULL,
title text NOT NULL,
description text NOT NULL,
content text NOT NULL);

CREATE TABLE IF NOT EXISTS Topic (
id integer PRIMARY KEY,
topicName text NOT NULL,
topicSubject text NOT NULL,
topicDescription text NOT NULL,
researcherId integer NOT NULL,
FOREIGN KEY (researcherId)
REFERENCES Researcher (id)
ON UPDATE CASCADE
ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Project (
id integer PRIMARY KEY,
projectName text NOT NULL,
projectSubject text NOT NULL,
projectDescription text NOT NULL,
researcherId integer NOT NULL,
FOREIGN KEY (researcherId)
REFERENCES Researcher (id)
ON UPDATE CASCADE
ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS AccountUser (
id integer PRIMARY KEY,
username TEXT NOT NULL,
firstName TEXT NOT NULL,
lastName TEXT NOT NULL,
email TEXT NOT NULL,
phoneNumber TEXT,
address1 TEXT,
address2 TEXT,
city TEXT,
statecode TEXT,
zipcode TEXT,
businessName TEXT,
websiteName TEXT,
profileImagePath TEXT)

CREATE TABLE IF NOT EXISTS AccountUserDetails (
id integer PRIMARY KEY,
content1 TEXT NOT NULL,
content2 TEXT NOT NULL,
content3 TEXT NOT NULL,      
accountUserId integer NOT NULL,
FOREIGN KEY (accountUserId)
REFERENCES AccountUser (id)
ON UPDATE CASCADE
ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Video(
id integer PRIMARY KEY,
artist TEXT,
videoName TEXT,
videoPath TEXT);

CREATE TABLE IF NOT EXISTS Game (
id integer PRIMARY KEY,
title text NOT NULL,
highScore integer NOT NULL,
created datetime DEFAULT CURRENT_TIMESTAMP,
lastUpdated datetime DEFAULT CURRENT_TIMESTAMP);

