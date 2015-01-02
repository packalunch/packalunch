DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL DEFAULT '',
  `last_name` varchar(255) NOT NULL DEFAULT '',
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

INSERT INTO `customer` VALUES (1,'david','gilmoure','hello st',NULL,0),(2,'roger','waters','final cut st',NULL,0),(3,'john','locke','enlightment st',NULL,0);

DROP TABLE IF EXISTS `meal`;
CREATE TABLE `meal` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `ordered_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `meal` VALUES (1,2,'2014-11-05',1,NULL,'2014-11-02 02:27:42',0 ),(2,3,'2014-11-06',1,NULL,'2014-11-02 02:28:02', 0 );

drop table if exists `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `payment_amount` float(11) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `account_due` float(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

INSERT INTO `account` (`id`, `customer_id`, `payment_amount`, `payment_date`, `account_due`, `version`) VALUES
  (1, 1, 10, '2014-12-02', 25, 0),
  (2, 3, 0, null, 30, 0),
  (3, 2, 10, '2014-12-02', 20, 0);