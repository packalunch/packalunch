DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id int(11) unsigned NOT NULL AUTO_INCREMENT,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  email varchar (255)  DEFAULT NULL,
  user_type varchar (255) ,
  address varchar(255) DEFAULT NULL,
  telephone varchar(255) DEFAULT NULL,
  created timestamp DEFAULT CURRENT_TIMESTAMP,
  updated timestamp NULL,
  version int(11) DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS credential;
CREATE TABLE IF NOT EXISTS credential (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(11)  NULL,
  role varchar (20) NULL,
  sign_in_provider varchar (20) NULL,
  salt varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  created timestamp DEFAULT CURRENT_TIMESTAMP,
  updated timestamp NULL,
  version int(11) DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE KEY user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS meal;
CREATE TABLE meal (
  id int(11) unsigned NOT NULL AUTO_INCREMENT,
  user_id int(11) NULL,
  date date NOT NULL,
  quantity int(11) DEFAULT NULL,
  rating int(11) DEFAULT NULL,
  ordered_at timestamp NULL,
  created timestamp DEFAULT CURRENT_TIMESTAMP,
  updated timestamp NULL,
  version int(11) DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS account;
CREATE TABLE IF NOT EXISTS account (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(11) NULL,
  payment_amount float(11) DEFAULT NULL,
  payment_date date DEFAULT NULL,
  account_due float(11) DEFAULT NULL,
  created timestamp DEFAULT CURRENT_TIMESTAMP,
  updated timestamp NULL,
  version int(11) DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS supplier;
CREATE TABLE IF NOT EXISTS supplier (
  id int(11) NOT NULL AUTO_INCREMENT,

  title varchar (255) NULL,
  description varchar (255) NULL,
  availabiity varchar (255) NULL,
  cusine varchar (255) NULL,
  payment_method varchar (255) NULL,
  special varchar (255) NULL,
  neighbourhood VARCHAR(255) NULL,
  rating double NULL,

  created timestamp DEFAULT CURRENT_TIMESTAMP,
  updated timestamp NULL,
  version int(11) DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS plan;
CREATE TABLE IF NOT EXISTS plan (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(11) NULL,
  name varchar (255) NULL,
  description varchar (255) NULL,
  unit_price double NULL,
  number_of_meals int (11) NULL,
  isAvailable BOOLEAN NULL ,
  created timestamp DEFAULT CURRENT_TIMESTAMP,
  updated timestamp NULL,
  version int(11) DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS customer_plan;
CREATE TABLE customer_plan
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  plan_id INT NOT NULL,
  credit INT NOT NULL,
  created timestamp DEFAULT CURRENT_TIMESTAMP,
  updated timestamp NULL,
  version int(11) DEFAULT 0
);
CREATE UNIQUE INDEX unique_id ON customer_plan (id);


DROP TABLE IF EXISTS supplier_account;
CREATE TABLE supplier_account (
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  plan_id INT NOT NULL,
  credit INT NOT NULL,
  created timestamp DEFAULT CURRENT_TIMESTAMP,
  updated timestamp NULL,
  version int(11) DEFAULT 0
);
CREATE UNIQUE INDEX unique_id ON supplier_account (id);



DROP TABLE IF EXISTS UserConnection;
create table UserConnection (
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