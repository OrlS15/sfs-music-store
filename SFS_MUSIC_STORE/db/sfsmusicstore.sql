DROP DATABASE IF EXISTS sfsmusicstore;
create database sfsmusicstore;
use sfsmusicstore;

create table UTENTE (
	id int auto_increment NOT NULL,
    nome varchar(50) NOT NULL,
    cognome varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
    password varchar(255) NOT NULL,
    PRIMARY KEY(id)
);

create table ADMIN(
	id int NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES UTENTE(id)
);

create table ORDINE(
	id int auto_increment NOT NULL,
    id_utente int NOT NULL,
    data timestamp default current_timestamp,
    indirizzo varchar(50) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_utente) REFERENCES UTENTE(id)
);

create table INFO_PRODOTTO(
	id int auto_increment NOT NULL,
    nome varchar(50) NOT NULL,
    descrizione text NOT NULL,
    tipo varchar(50) NOT NULL,
    immagine mediumblob NOT NULL,
    PRIMARY KEY(id)
);

create table ORDER_ITEM(
	id_ordine int NOT NULL,
    id_info_prodotto int NOT NULL,
    quantita int NOT NULL,
    prezzo double NOT NULL,
    PRIMARY KEY(id_ordine, id_info_prodotto),
	FOREIGN KEY(id_ordine) REFERENCES ORDINE(id),
    FOREIGN KEY(id_info_prodotto) REFERENCES INFO_PRODOTTO(id)
);

create table PROD_IN_VENDITA(
	id_info_prodotto int NOT NULL,
    prezzo double NOT NULL,
    quantita int NOT NULL,
    condizione varchar(9) NOT NULL default "nuovo",
    PRIMARY KEY(id_info_prodotto),
    FOREIGN KEY(id_info_prodotto) REFERENCES INFO_PRODOTTO(id)
);

create table RICHIESTA_VENDITA(
	id int auto_increment NOT NULL,
    id_info_prodotto int NOT NULL,
    stato varchar(20) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_info_prodotto) REFERENCES INFO_PRODOTTO(id)
);

create table CONTROLLATA_DA(
	id_richiesta_vendita int NOT NULL,
    id_admin int NOT NULL,
    PRIMARY KEY(id_richiesta_vendita,id_admin),
    FOREIGN KEY(id_richiesta_vendita) REFERENCES RICHIESTA_VENDITA(id),
    FOREIGN KEY(id_admin) REFERENCES ADMIN(id)
);

INSERT INTO UTENTE (nome, cognome, email, password)
VALUES ("admin", "admin", "admin@admin", "c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec");

INSERT INTO ADMIN VALUES (1);