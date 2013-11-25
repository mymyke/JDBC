CREATE TABLE app.klassen(
	K_ID VARCHAR(10) NOT NULL CONSTRAINT PK_klassen PRIMARY KEY,
	K_Bez VARCHAR(50),
	K_S_Klaspr INTEGER,
	K_S_Klasprstv INTEGER,
	K_L_Klavst VARCHAR(5)
 );
--  
ALTER TABLE app.klassen ADD CONSTRAINT P_UC_klassensprecher UNIQUE (K_S_Klaspr);
ALTER TABLE app.klassen ADD CONSTRAINT P_UC_klassensprecherstellvertreter UNIQUE (K_S_Klasprstv);

CREATE TABLE app.schueler(
	--S_SCHNR SMALLINT  NOT NULL CONSTRAINT PK_schueler PRIMARY KEY,
	S_SCHNR INTEGER NOT NULL CONSTRAINT PK_schueler PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	S_Name VARCHAR(50),
	S_Vorname VARCHAR(50),
	S_Gebdat DATE,
	S_Adresse VARCHAR(50),
	S_K_Klasse VARCHAR(10),
	
	CONSTRAINT FK_schueler_klassen FOREIGN KEY(S_K_Klasse) REFERENCES app.klassen (K_ID)
); 

ALTER TABLE app.klassen ADD CONSTRAINT FK_klassen_schueler FOREIGN KEY(K_S_Klaspr) REFERENCES app.schueler (S_SCHNR);
ALTER TABLE app.klassen ADD CONSTRAINT FK_klassen_schueler1 FOREIGN KEY(K_S_Klasprstv) REFERENCES app.schueler (S_SCHNR);



INSERT INTO app.klassen VALUES 
	('1chif','HIF', null, null, null),
	('2chif','HIF', null, null, null);

INSERT INTO app.schueler(S_Name, S_Vorname, S_Gebdat, S_Adresse, S_K_Klasse) VALUES 
	('S1nach', 'S1vor', '1991-10-20', 'wien 1', '1chif'),
	('S2nach', 'S2vor', '1992-10-20', 'wien 2', '1chif'),
	('S3nach', 'S3vor', '1993-10-20', 'wien 3', '1chif'),
	('S4nach', 'S2vor', '1994-10-20', 'wien 4', '2chif');
	