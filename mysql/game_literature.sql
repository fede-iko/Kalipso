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
-- Database: `game_literature`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `answer`
--

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
(2, 2, 'tanto onesta pare', 1),
(3, 3, 'mi ritrovai per una selva oscura', 1),
(4, 4, 'in sul calar del sole', 1),
(5, 5, 'quest\'ermo colle', 1),
(6, 6, 'avrai del figlio', 1),
(7, 7, 'piovigginando sale', 1),
(8, 8, 'sugli alberi le foglie', 1),
(9, 9, 'quel tempo della tua vita mortale', 1),
(10, 10, 'che tu ricerchi gli albicocchi in fiore', 1);

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
  ADD CONSTRAINT `id_domanda` FOREIGN KEY (`id_sentence`) REFERENCES `sentence` (`id_sentence`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
