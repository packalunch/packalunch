DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL DEFAULT '',
  `last_name` varchar(255) NOT NULL DEFAULT '',
  `email` varchar (255) NOT NULL  DEFAULT '',
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
#   UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `customer` VALUES
  (1,'david','gilmour', 'gilmour@email.com' ,'hello st',NULL, 0),
  (2,'roger','waters','waters@email.com' ,'final cut st',NULL, 0),
  (3,'john','locke','locke@email.com' ,'enlightment st',NULL, 0),
  (4,'noam','chumsky','chumsky@email.com' ,'failed state st',NULL, 0);

DROP TABLE IF EXISTS `meal`;
CREATE TABLE `meal` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NULL,
  `date` date NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `ordered_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `version` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `meal` VALUES
  (1,1,'2015-01-05',1,NULL,'2015-01-02 02:27:42', 0 ),
  (2,1,'2015-01-06',1,NULL,'2015-11-02 02:28:02', 0 ),
  (3,3,'2015-01-07',1,NULL,'2015-11-02 02:28:02', 0 );

DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NULL,
  `payment_amount` float(11) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `account_due` float(11) DEFAULT NULL,
  `version` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

INSERT INTO `account` (`id`, `customer_id`, `payment_amount`, `payment_date`, `account_due`, `version`) VALUES
  (1, 1, 10, '2014-12-02', 25, 0),
  (2, 3, 0, null, 30, 0),
  (3, 2, 10, '2014-12-02', 20, 0);


DROP TABLE IF EXISTS `credential`;
CREATE TABLE IF NOT EXISTS `credential` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11)  NULL,
  `role` varchar (20) NULL,
  `sign_in_provider` varchar (20) NULL,
  `salt` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_id` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `credential` (`id`, `customer_id`, `role`, `sign_in_provider`, `salt`, `password`, `version` ) VALUES
  (1, 1, 'ROLE_USER', NULL , '123qweasd', '$2a$10$yWH3GRxFBuOlVuBQGhjqJe3OyvdCYUEnfBjL514x.vubpVh3vYoAG', 0),
  (2, 2, 'ROLE_USER', NULL , '123qweasd', '$2a$10$yWH3GRxFBuOlVuBQGhjqJe3OyvdCYUEnfBjL514x.vubpVh3vYoAG', 0),
  (3, 3, 'ROLE_USER', NULL , '123qweasd', '$2a$10$yWH3GRxFBuOlVuBQGhjqJe3OyvdCYUEnfBjL514x.vubpVh3vYoAG', 0);

DROP TABLE IF EXISTS `UserConnection`;
create table `UserConnection` (
  userId varchar(255) not null,
  providerId varchar(255) not null,
  providerUserId varchar(255),
  rank int not null,
  displayName varchar(255),
  profileUrl varchar(512),
  imageUrl varchar(512),
  accessToken varchar(255) not null,
  secret varchar(255),
  refreshToken varchar(255),
  expireTime bigint,
  primary key (userId, providerId, providerUserId))
  ENGINE=InnoDB DEFAULT CHARSET=utf8;
create unique index UserConnectionRank on UserConnection(userId, providerId, rank);