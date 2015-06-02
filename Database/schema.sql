DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL DEFAULT '',
  `last_name` varchar(255) NOT NULL DEFAULT '',
  `email` varchar (255) NOT NULL  DEFAULT '',
  `userType` varchar (255) NOT NULL  DEFAULT '',
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `created` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL,
  `deleted` timestamp NULL,
  `version` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `credential`;
CREATE TABLE IF NOT EXISTS `credential` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11)  NULL,
  `role` varchar (20) NULL,
  `sign_in_provider` varchar (20) NULL,
  `salt` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `created` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL,
  `deleted` timestamp NULL,
  `version` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `meal`;
CREATE TABLE `meal` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL,
  `date` date NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `ordered_at` timestamp NULL,
  `created` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL,
  `deleted` timestamp NULL,
  `version` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL,
  `payment_amount` float(11) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `account_due` float(11) DEFAULT NULL,
  `created` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL,
  `deleted` timestamp NULL,
  `version` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

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