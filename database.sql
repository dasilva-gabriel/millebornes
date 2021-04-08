CREATE TABLE `teams` (
  `color` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `leader` varchar(126) DEFAULT NULL,
  `coins` int(64) NOT NULL DEFAULT '0',
  `distance` int(64) NOT NULL DEFAULT '0',
  `go` tinyint(1) NOT NULL DEFAULT '0',
  `limited` tinyint(1) NOT NULL DEFAULT '0',
  `problem1` tinyint(1) NOT NULL DEFAULT '0',
  `problem2` tinyint(1) NOT NULL DEFAULT '0',
  `problem3` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `teams`
  ADD PRIMARY KEY (`color`);
<<<<<<< HEAD
COMMIT;
=======
COMMIT;
>>>>>>> 642589c9cf2d9d40acad64933133554db0d8ebdb
