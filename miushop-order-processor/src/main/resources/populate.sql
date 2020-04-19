INSERT INTO `zerobug-order-processor`.`roles` (`role_id`, `role`) VALUES ('1', 'ADMIN');
INSERT INTO `zerobug-order-processor`.`roles` (`role_id`, `role`) VALUES ('2', 'PURCHASER');
INSERT INTO `zerobug-order-processor`.`roles` (`role_id`, `role`) VALUES ('3', 'STUDENT');

-- username: eden  password: eden
INSERT INTO `zerobug-order-processor`.`user` (`user_id`, `active`, `email`, `firstname`, `lastname`, `password`, `username`) VALUES ('1000', b'1', 'beden@miu.edi', 'Eden', 'Bereda', '$2a$10$WkNF/qiMb4jjjUgSRMPON.JsUnpdK7J2Q5yUy1zPvgH7Mi9JaXkxW', 'eden');
INSERT INTO `zerobug-order-processor`.`user_role` (`user_id`, `role_id`) VALUES ('1000', '1');

INSERT INTO `zerobug-order-processor`.`products` (`product_id`, `product_name`, `product_walmart_link`, `product_category_id`) VALUES ('1', 'Water', 'water.com', '1');
INSERT INTO `zerobug-order-processor`.`products` (`product_id`, `product_name`, `product_walmart_link`, `product_category_id`) VALUES ('2', 'Snacks', 'snacks.com', '1');
INSERT INTO `zerobug-order-processor`.`products` (`product_id`, `product_name`, `product_walmart_link`, `product_category_id`) VALUES ('3', 'Socks', 'socks.com', '2');
INSERT INTO `zerobug-order-processor`.`products` (`product_id`, `product_name`, `product_walmart_link`, `product_category_id`) VALUES ('4', 'T-shirt', 'tshirt.com', '2');
INSERT INTO `zerobug-order-processor`.`products` (`product_id`, `product_name`, `product_walmart_link`, `product_category_id`) VALUES ('5', 'Toothpaste', 'toothpaste.com', '3');
INSERT INTO `zerobug-order-processor`.`products` (`product_id`, `product_name`, `product_walmart_link`, `product_category_id`) VALUES ('6', 'Toilet Pater', 'tpaper.com', '3');


INSERT INTO `zerobug-order-processor`.`categories` (`category_id`, `category_name`) VALUES ('1', 'Food');
INSERT INTO `zerobug-order-processor`.`categories` (`category_id`, `category_name`) VALUES ('2', 'Clothing');
INSERT INTO `zerobug-order-processor`.`categories` (`category_id`, `category_name`) VALUES ('3', 'Toiletries');


INSERT INTO `zerobug-order-processor`.`inventory` (`inventory_id`, `quantity`, `version`, `product_id`) VALUES ('1', '2', '0', '1');
INSERT INTO `zerobug-order-processor`.`inventory` (`inventory_id`, `quantity`, `version`, `product_id`) VALUES ('2', '1', '0', '2');
INSERT INTO `zerobug-order-processor`.`inventory` (`inventory_id`, `quantity`, `version`, `product_id`) VALUES ('3', '0', '0', '3');

INSERT INTO `zerobug-order-processor`.`inventory` (`inventory_id`, `quantity`, `version`, `product_id`) VALUES ('4', '1', '0', '4');
INSERT INTO `zerobug-order-processor`.`inventory` (`inventory_id`, `quantity`, `version`, `product_id`) VALUES ('5', '2', '0', '5');
INSERT INTO `zerobug-order-processor`.`inventory` (`inventory_id`, `quantity`, `version`, `product_id`) VALUES ('6', '3', '0', '6');


