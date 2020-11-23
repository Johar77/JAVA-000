```mysql
CREATE TABLE `t_user_info` (
  `user_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(64) NOT NULL,
  `card_type` tinyint NOT NULL,
  `card_num` varchar(64) NOT NULL,
  `phone_num` varchar(13) NOT NULL,
  `email` varchar(64) NOT NULL,
  `register_time` datetime NOT NULL,
  `birthday` date DEFAULT NULL,
  `membership_level` tinyint NOT NULL,
  `integral` int NOT NULL DEFAULT '0',
  `balance` bigint unsigned NOT NULL DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
```



```mysql
CREATE TABLE `t_user_login` (
  `user_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `login_name` varchar(64) NOT NULL,
  `password` varchar(256) NOT NULL,
  `user_status` tinyint NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户登录表';
```

```mysql
CREATE TABLE `t_user_address` (
  `address_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `recipient` varchar(64) NOT NULL COMMENT '收件人',
  `phone_num` varchar(13) NOT NULL,
  `province` varchar(10) NOT NULL,
  `city` varchar(10) NOT NULL,
  `area` varchar(20) NOT NULL,
  `full_address` varchar(100) NOT NULL COMMENT '详细地址',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收件地址表';
```

```mysql
CREATE TABLE `t_product_category` (
  `category_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) NOT NULL,
  `category_code` varchar(10) NOT NULL,
  `parent_id` bigint unsigned NOT NULL COMMENT '父类id，-1代表根',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品分类表';
```

```mysql
CREATE TABLE `t_product_info` (
  `product_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `product_code` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL,
  `bar_code` varchar(64) NOT NULL,
  `category_id` bigint unsigned NOT NULL,
  `price` bigint unsigned NOT NULL,
  `cost` bigint unsigned NOT NULL COMMENT '成本',
  `publish_status` tinyint NOT NULL COMMENT '上下架状态',
  `audit_status` tinyint NOT NULL COMMENT '审核状态',
  `weight` float DEFAULT NULL,
  `length` float DEFAULT NULL,
  `height` float DEFAULT NULL,
  `with` float DEFAULT NULL,
  `production_date` bigint unsigned NOT NULL,
  `shelf_lift` int NOT NULL,
  `color` tinyint DEFAULT NULL,
  `description` text NOT NULL,
  `indate` bigint unsigned NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品表';
```

```mysql
CREATE TABLE `order_master` (
  `order_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `order_sn` bigint unsigned NOT NULL COMMENT '订单编号',
  `customer_id` bigint unsigned NOT NULL COMMENT '下单人id',
  `user_address_id` bigint unsigned NOT NULL,
  `payment_method` tinyint NOT NULL,
  `order_money` bigint unsigned NOT NULL,
  `district_money` bigint unsigned NOT NULL COMMENT '优惠金额',
  `payment_money` bigint unsigned NOT NULL COMMENT '支付金额',
  `shipping_compony_name` varchar(64) NOT NULL COMMENT '快递公司名字',
  `shipping_sn` varchar(64) NOT NULL COMMENT '快递单号',
  `create_time` datetime NOT NULL COMMENT '下单时间',
  `shipping_time` datetime DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL,
  `receive_time` datetime DEFAULT NULL,
  `order_status` tinyint NOT NULL,
  `order_point` int unsigned NOT NULL DEFAULT '0' COMMENT '订单积分',
  `invoice_header` varchar(100) DEFAULT NULL COMMENT '发票抬头',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单主表';
```

```mysql
CREATE TABLE `t_order_detail` (
  `order_detail_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `order_id` bigint unsigned NOT NULL,
  `product_id` bigint unsigned NOT NULL,
  `product_name` varchar(64) NOT NULL,
  `product_count` int NOT NULL,
  `product_price` bigint unsigned NOT NULL,
  `weight` float DEFAULT NULL,
  `fee_money` bigint unsigned NOT NULL DEFAULT '0' COMMENT '优惠金额',
  PRIMARY KEY (`order_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单详情表';
```

