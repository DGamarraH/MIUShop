INSERT INTO `zerobug-order-processor`.`categories` (`category_id`, `category_name`, `version`) VALUES ('1', 'Food', '0');
INSERT INTO `zerobug-order-processor`.`categories` (`category_id`, `category_name`, `version`) VALUES ('2', 'Clothing', '0');
INSERT INTO `zerobug-order-processor`.`categories` (`category_id`, `category_name`, `version`) VALUES ('3', 'Toiletries', '0');

INSERT INTO `zerobug-order-processor`.`products` (`product_id`, `catalog_enabled`, `product_name`, `product_walmart_link`, `product_category_id`, `version`) VALUES ('1', b'1', 'Water', 'water.com', '1', '0');
INSERT INTO `zerobug-order-processor`.`products` (`product_id`, `catalog_enabled`, `product_name`, `product_walmart_link`, `product_category_id`, `version`) VALUES ('2', b'1', 'Snacks', 'snacks.com', '1', '0');
INSERT INTO `zerobug-order-processor`.`products` (`product_id`, `catalog_enabled`, `product_name`, `product_walmart_link`, `product_category_id`, `version`) VALUES ('3', b'1', 'Socks', 'socks.com', '2', '0');
INSERT INTO `zerobug-order-processor`.`products` (`product_id`, `catalog_enabled`, `product_name`, `product_walmart_link`, `product_category_id`, `version`) VALUES ('4', b'1', 'T-shirt', 'tshirt.com', '2', '0');
INSERT INTO `zerobug-order-processor`.`products` (`product_id`, `catalog_enabled`, `product_name`, `product_walmart_link`, `product_category_id`, `version`) VALUES ('5', b'1', 'Toothpaste', 'toothpaste.com', '3', '0');
INSERT INTO `zerobug-order-processor`.`products` (`product_id`, `catalog_enabled`, `product_name`, `product_walmart_link`, `product_category_id`, `version`) VALUES ('6', b'1', 'Toilet Pater', 'tpaper.com', '3', '0');

INSERT INTO `zerobug-order-processor`.`inventory` (`inventory_id`, `quantity`, `version`, `product_id`) VALUES ('1', '2', '0', '1');
INSERT INTO `zerobug-order-processor`.`inventory` (`inventory_id`, `quantity`, `version`, `product_id`) VALUES ('2', '1', '0', '2');
INSERT INTO `zerobug-order-processor`.`inventory` (`inventory_id`, `quantity`, `version`, `product_id`) VALUES ('3', '0', '0', '3');

INSERT INTO `zerobug-order-processor`.`inventory` (`inventory_id`, `quantity`, `version`, `product_id`) VALUES ('4', '1', '0', '4');
INSERT INTO `zerobug-order-processor`.`inventory` (`inventory_id`, `quantity`, `version`, `product_id`) VALUES ('5', '2', '0', '5');
INSERT INTO `zerobug-order-processor`.`inventory` (`inventory_id`, `quantity`, `version`, `product_id`) VALUES ('6', '3', '0', '6');


-- username: eden  password: eden
INSERT INTO `zerobug-order-processor`.`users` (`user_id`, `email`, `first_name`, `last_name`, `user_credentials_id`) VALUES ('1000', 'beden@miu.edi', 'Eden', 'Bereda', '1');

INSERT INTO `zerobug-order-processor`.`user_credentials` (`user_credentials_id`, `password`, `username`) VALUES ('1', '$2a$10$WkNF/qiMb4jjjUgSRMPON.JsUnpdK7J2Q5yUy1zPvgH7Mi9JaXkxW', 'eden');