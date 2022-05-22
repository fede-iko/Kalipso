CREATE DATABASE literature_game;

CREATE TABLE sentence (
    id_sentence SMALLINT NOT NULL AUTO_INCREMENT,
    sentence_text varchar(100),
    n_guessed SMALLINT DEFAULT 0,
    n_total SMALLINT DEFAULT 0,
    PRIMARY KEY(id_sentence)
    );

INSERT INTO sentence (sentence_text) VALUES
('Ei fu'), 
('Tanto gentile e'), 
('Nel mezzo del cammin di nostra vita'), 
('La donzelletta vien dalla campagna'), 
('Sempre caro mi fu'), 
('Tu non altro che il canto'), 
('La nebbia a gl’irti colli'), 
('Si sta come d’autunno'), 
('Silvia, rimembri ancora'), 
('Gemmea l’aria, il sole così chiaro');

CREATE TABLE answer (
    id_sentence SMALLINT NOT NULL,
    answer_text varchar(100),
	isCorrect BOOLEAN,
	PRIMARY KEY(id_sentence, answer_text),
    FOREIGN KEY (id_sentence) REFERENCES sentence(id_sentence)
    );

INSERT INTO answer (id_sentence, answer_text, isCorrect) VALUES
(1, 'siccome immobile', 1), 
(2, 'tanto onesta pare', 1), 
(3, 'mi ritrovai per una selva oscura', 1), 
(4, 'in sul calar del sole', 1), 
(5, 'quest’ermo colle', 1), 
(6, 'avrai del figlio', 1), 
(7, 'Piovigginando sale', 1), 
(8, 'sugli alberi le foglie', 1), 
(9, 'Quel tempo della tua vita mortale', 1), 
(10, 'che tu ricerchi gli albicocchi in fiore', 1);