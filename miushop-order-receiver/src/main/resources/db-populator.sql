

INSERT INTO `zerobug-order-receiver`.`products` (`product_id`, `product_name`, `product_walmart_link`, `product_category_id`) VALUES ('1', 'Water', 'water.com', '1');
INSERT INTO `zerobug-order-receiver`.`products` (`product_id`, `product_name`, `product_walmart_link`, `product_category_id`) VALUES ('2', 'Snacks', 'snacks.com', '1');
INSERT INTO `zerobug-order-receiver`.`products` (`product_id`, `product_name`, `product_walmart_link`, `product_category_id`) VALUES ('3', 'Socks', 'socks.com', '2');
INSERT INTO `zerobug-order-receiver`.`products` (`product_id`, `product_name`, `product_walmart_link`, `product_category_id`) VALUES ('4', 'T-shirt', 'tshirt.com', '2');
INSERT INTO `zerobug-order-receiver`.`products` (`product_id`, `product_name`, `product_walmart_link`, `product_category_id`) VALUES ('5', 'Toothpaste', 'toothpaste.com', '3');
INSERT INTO `zerobug-order-receiver`.`products` (`product_id`, `product_name`, `product_walmart_link`, `product_category_id`) VALUES ('6', 'Toilet Pater', 'tpaper.com', '3');


INSERT INTO `zerobug-order-receiver`.`cart_items` (`cartitem_id`, `price`, `quantity`, `product_id`) VALUES ('1', '10', '2', '1');
INSERT INTO `zerobug-order-receiver`.`cart_items` (`cartitem_id`, `price`, `quantity`, `product_id`) VALUES ('2', '20', '3', '3');
INSERT INTO `zerobug-order-receiver`.`cart_items` (`cartitem_id`, `price`, `quantity`, `product_id`) VALUES ('3', '15', '1', '6');


INSERT INTO `zerobug-order-receiver`.`cart` (`cartid`, `total_price`, `user_id`) VALUES ('1', '45', '1');


INSERT INTO `zerobug-order-receiver`.`cart_item_table` (`cart_cartid`, `item_list_cartitem_id`) VALUES ('1', '1');
INSERT INTO `zerobug-order-receiver`.`cart_item_table` (`cart_cartid`, `item_list_cartitem_id`) VALUES ('1', '2');
INSERT INTO `zerobug-order-receiver`.`cart_item_table` (`cart_cartid`, `item_list_cartitem_id`) VALUES ('1', '3');



INSERT INTO `zerobug-order-receiver`.`roles` (`id`, `name`) VALUES ('1', 'ROLE_USER');
INSERT INTO `zerobug-order-receiver`.`roles` (`id`, `name`) VALUES ('2', 'ROLE_PURCHASER');
INSERT INTO `zerobug-order-receiver`.`roles` (`id`, `name`) VALUES ('3', 'ROLE_ADMIN');


INSERT INTO `zerobug-order-receiver`.`categories` (`category_id`, `category_name`) VALUES ('1', 'Food');
INSERT INTO `zerobug-order-receiver`.`categories` (`category_id`, `category_name`) VALUES ('2', 'Clothing');
INSERT INTO `zerobug-order-receiver`.`categories` (`category_id`, `category_name`) VALUES ('3', 'Toiletries');


