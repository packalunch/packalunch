INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `address`, `telephone`, `created`, `updated`, `deleted`, `version`) VALUES
  (1,'david','gilmour', 'gilmour@email.com', 'hello st', NULL, '2015-11-02 05:28:02', NULL, NULL, 0),
  (2,'roger','waters','waters@email.com', 'final cut st', NULL, '2015-11-02 05:28:02', NULL, NULL, 0),
  (3,'john','locke','locke@email.com', 'enlightment st', NULL, '2015-11-02 05:28:02', NULL, NULL, 0),
  (4,'noam','chumsky','chumsky@email.com', 'failed state st', NULL, '2015-11-02 05:28:02', NULL, NULL, 0);

INSERT INTO `credential` (`id`, `customer_id`, `role`, `sign_in_provider`, `salt`, `password`, `created`, `updated`, `deleted`, `version`) VALUES
  (1, 1, 'ROLE_USER', NULL , '123qweasd', '$2a$10$yWH3GRxFBuOlVuBQGhjqJe3OyvdCYUEnfBjL514x.vubpVh3vYoAG', '2015-11-02 05:28:02', NULL, NULL, 0),
  (2, 2, 'ROLE_USER', NULL , '123qweasd', '$2a$10$yWH3GRxFBuOlVuBQGhjqJe3OyvdCYUEnfBjL514x.vubpVh3vYoAG', '2015-11-02 05:28:02', NULL, NULL, 0),
  (3, 3, 'ROLE_USER', NULL , '123qweasd', '$2a$10$yWH3GRxFBuOlVuBQGhjqJe3OyvdCYUEnfBjL514x.vubpVh3vYoAG', '2015-11-02 05:28:02', NULL, NULL, 0);

INSERT INTO `meal` (`id`, `user_id`, `date`, `quantity`, `rating`, `ordered_at`, `created`, `updated`, `deleted`, `version`) VALUES
  (1,1,'2015-01-05',1,NULL,'2015-01-02 02:27:42', '2015-11-02 05:28:02', NULL, NULL, 0 ),
  (2,1,'2015-01-06',1,NULL,'2015-11-02 02:28:02', '2015-11-02 05:28:02', NULL, NULL, 0 ),
  (3,3,'2015-01-07',1,NULL,'2015-11-02 02:28:02', '2015-11-02 05:28:02', NULL, NULL, 0 );

INSERT INTO `account` (`id`, `customer_id`, `payment_amount`, `payment_date`, `account_due`, `created`, `updated`, `deleted`, `version`) VALUES
  (1, 1, 10, '2014-12-02', 25, '2015-11-02 05:28:02', NULL, NULL, 0),
  (2, 3, 0, null, 30, '2015-11-02 05:28:02', NULL, NULL, 0),
  (3, 2, 10, '2014-12-02', 20, '2015-11-02 05:28:02', NULL, NULL, 0);
