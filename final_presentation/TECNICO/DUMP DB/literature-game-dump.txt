-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mag 27, 2022 alle 09:44
-- Versione del server: 10.4.21-MariaDB
-- Versione PHP: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: literature_game
--

-- --------------------------------------------------------

--
-- Struttura della tabella `answer`
--
DROP DATABASE IF EXISTS literature_game;
CREATE DATABASE literature_game;
USE literature_game;

CREATE TABLE `answer` (
  `id_answer` int(11) NOT NULL,
  `id_sentence` int(11) NOT NULL,
  `answer_text` text NOT NULL,
  `is_correct` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `answer`
--

INSERT INTO `answer` (`id_answer`, `id_sentence`, `answer_text`, `is_correct`) VALUES
(1, 1, 'siccome immobile', 1),
(2, 1, 'siccome immemore', 0),
(3, 1, 'dato il mortal sospiro', 0),
(4, 1, 'al nunzio sta', 0),
(5, 2, 'tanto onesta pare', 1),
(6, 2, 'quand\'ella altrui saluta', 0),
(7, 2, 'tremando, muta', 0),
(8, 2, 'furba rimane', 0),
(9, 3, 'mi ritrovai per una selva oscura', 1),
(10, 3, 'mi ritrovai perduto nei boschi', 0),
(11, 3, 'mi ritrovai smarrito nelle pianure', 0),
(12, 3, 'mi ritrovai innamorato', 0),
(13, 4, 'in sul calar del sole', 1),
(14, 4, 'passeggiando allegra', 0),
(15, 4, 'verso il tramonto', 0),
(16, 4, 'per i monti lontani', 0),
(17, 5, 'quest\'ermo colle', 1),
(18, 5, 'il mio pargolo', 0),
(19, 5, 'questo natio borgo', 0),
(20, 5, 'questo amore perduto', 0),
(21, 6, 'avrai del figlio', 1),
(22, 6, 'avrai del padre', 0),
(23, 6, 'avrai del fratello', 0),
(24, 6, 'avrai del nipote', 0),
(25, 7, 'piovigginando sale', 1),
(26, 7, 'sotto il maestrale', 0),
(27, 7, 'tristemente cade', 0),
(28, 7, 'nel vespero migrar', 0),
(29, 8, 'sugli alberi le foglie', 1),
(30, 8, 'sul monte la neve', 0),
(31, 8, 'sulla spiaggia le conchiglie', 0),
(32, 8, 'sul rubinetto le goccie', 0),
(33, 9, 'quel tempo della tua vita mortale', 1),
(34, 9, 'il nostro forte amore', 0),
(35, 9, 'il triste autunno', 0),
(36, 9, 'la nostra vita insieme', 0),
(37, 10, 'che tu ricerchi gli albicocchi in fiore', 1),
(38, 10, 'e la giornata splende', 0),
(39, 10, 'la primavera arriva', 0),
(40, 10, 'e le allegre stagioni', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `sentence`
--

CREATE TABLE `sentence` (
  `id_sentence` int(11) NOT NULL,
  `sentence_text` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `sentence`
--

INSERT INTO `sentence` (`id_sentence`, `sentence_text`) VALUES
(1, 'Ei fu'),
(2, 'Tanto gentile e'),
(3, 'Nel mezzo del cammin di nostra vita'),
(4, 'La donzelletta vien dalla campagna'),
(5, 'Sempre caro mi fu'),
(6, 'Tu non altro che il canto'),
(7, 'La nebbia a gl\'irti colli'),
(8, 'Si sta come d\'autunno'),
(9, 'Silvia, rimembri ancora'),
(10, 'Gemmea l\'aria, il sole così chiaro');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`id_answer`),
  ADD KEY `id_domanda` (`id_sentence`);

--
-- Indici per le tabelle `sentence`
--
ALTER TABLE `sentence`
  ADD PRIMARY KEY (`id_sentence`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `answer`
--
ALTER TABLE `answer`
  MODIFY `id_answer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT per la tabella `sentence`
--
ALTER TABLE `sentence`
  MODIFY `id_sentence` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `id_domanda` FOREIGN KEY (`id_sentence`) REFERENCES `sentence` (`id_sentence`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
