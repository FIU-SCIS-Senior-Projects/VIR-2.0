USE `vir`;

--
-- Table structure for table `words`
--

DROP TABLE IF EXISTS `vir`.`word`;
CREATE TABLE `vir`.`word` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(256) NOT NULL,
  `category` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `value_UNIQUE` (`value`) COMMENT 'The value should be unique'
) ENGINE=InnoDB AUTO_INCREMENT=41981 DEFAULT CHARSET=latin1