# 	**作业说明**

# **Week08 作业题目（周四）：**

**1.（选做）**分析前面作业设计的表，是否可以做垂直拆分。

- 前面设计电商交易表的时候，已经考虑到用户登录是一个频繁的动作，将登录信息单独拆分到t_user_login表
- 可以将用户信息，商品信息，订单信息，拆分成三个库

**2.（必做）**设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github。

这个里面有两个表t_order,t_order_item,分成两个库ds_0,ds_1,每个库16张表：t_order_{0..15},t_order_item_{0..15}，分表规则如下：

```properties
spring.shardingsphere.datasource.names=ds_0,ds_1

spring.shardingsphere.datasource.ds_0.jdbc-url=jdbc:mysql://localhost:3326/commerce_ds_0?serverTimezone=UTC&useSSL=false
spring.shardingsphere.datasource.ds_0.type=com.zaxxer.hikari.HikariDataSource
spring.shardingsphere.datasource.ds_0.driver-class-name=com.mysql.jdbc.Driver
spring.shardingsphere.datasource.ds_0.username=root
spring.shardingsphere.datasource.ds_0.password=root
spring.shardingsphere.datasource.ds_0.max-active=16

spring.shardingsphere.datasource.ds_1.jdbc-url=jdbc:mysql://localhost:3326/commerce_ds_1?serverTimezone=UTC&useSSL=false
spring.shardingsphere.datasource.ds_1.type=com.zaxxer.hikari.HikariDataSource
spring.shardingsphere.datasource.ds_1.driver-class-name=com.mysql.jdbc.Driver
spring.shardingsphere.datasource.ds_1.username=root
spring.shardingsphere.datasource.ds_1.password=root
spring.shardingsphere.datasource.ds_1.max-active=16

spring.shardingsphere.rules.sharding.default-database-strategy.standard.sharding-column=order_id
spring.shardingsphere.rules.sharding.default-database-strategy.standard.sharding-algorithm-name=database_inline
spring.shardingsphere.rules.sharding.binding-tables=t_order,t_order_item

spring.shardingsphere.rules.sharding.tables.t_order.actual-data-nodes=ds_$->{0..1}.t_order_$->{0..15}
spring.shardingsphere.rules.sharding.tables.t_order.table-strategy.standard.sharding-column=order_id
spring.shardingsphere.rules.sharding.tables.t_order.table-strategy.standard.sharding-algorithm-name=t_order_inline

spring.shardingsphere.rules.sharding.tables.t_order.key-generate-strategy.column=order_id
spring.shardingsphere.rules.sharding.tables.t_order.key-generate-strategy.key-generator-name=snowflake

spring.shardingsphere.rules.sharding.tables.t_order_item.actual-data-nodes=ds_$->{0..1}.t_order_item_$->{0..15}
spring.shardingsphere.rules.sharding.tables.t_order_item.table-strategy.standard.sharding-column=order_id
spring.shardingsphere.rules.sharding.tables.t_order_item.table-strategy.standard.sharding-algorithm-name=t_order_item_inline

spring.shardingsphere.rules.sharding.tables.t_order_item.key-generate-strategy.column=order_item_id
spring.shardingsphere.rules.sharding.tables.t_order_item.key-generate-strategy.key-generator-name=snowflake

spring.shardingsphere.rules.sharding.sharding-algorithms.database_inline.type=INLINE
spring.shardingsphere.rules.sharding.sharding-algorithms.database_inline.props.algorithm-expression=ds_$->{((order_id % 32) >> 4) & 1 }
spring.shardingsphere.rules.sharding.sharding-algorithms.t_order_inline.type=INLINE
spring.shardingsphere.rules.sharding.sharding-algorithms.t_order_inline.props.algorithm-expression=t_order_$->{order_id % 16}
spring.shardingsphere.rules.sharding.sharding-algorithms.t_order_item_inline.type=INLINE
spring.shardingsphere.rules.sharding.sharding-algorithms.t_order_item_inline.props.algorithm-expression=t_order_item_$->{order_id % 16}

spring.shardingsphere.rules.sharding.key-generators.snowflake.type=SNOWFLAKE
spring.shardingsphere.rules.sharding.key-generators.snowflake.props.worker-id=123

#打开日志
spring.shardingsphere.props.sql-show=true
# 允许bean覆盖，防止bean和springboot注入冲突
spring.main.allow-bean-definition-overriding=true
```

代码：

```java
@Transactional(rollbackOn = Exception.class)
    public void addMockOrder(){
        int count = 33;
        Random random = new Random();
        for (int i = 1; i < count; i++){
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderId((long) (i));
            orderEntity.setOrderSN(randNum(20));
            int price = random.nextInt(10000) + 10;
            orderEntity.setOrderMoney(randPositiveNumber());
            orderEntity.setAddressId(randPositiveNumber());
            orderEntity.setCustomerId((long)(i & 16));
            orderEntity.setDistrictMoney(orderEntity.getOrderMoney() >>> 3);
            orderEntity.setPaymentMethod(random.nextInt(5));
            orderEntity.setPaymentMoney(orderEntity.getOrderMoney() - orderEntity.getDistrictMoney());

            orderRepository.insert(orderEntity.getOrderId(), orderEntity.getAddressId(), orderEntity.getCustomerId(), orderEntity.getOrderSN());

            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setOrderItemId((long)i);
            orderItemEntity.setOrderId(orderEntity.getOrderId());
            orderItemEntity.setFreeMoney(randPositiveNumber());
            orderItemEntity.setProductCount(1);
            orderItemEntity.setWeight(random.nextFloat());
            orderItemEntity.setProductionName(randCharter(10));
            orderItemEntity.setProductPrice(randPositiveNumber());
            orderItemEntity.setFreeMoney(randPositiveNumber());
            orderItemRepository.insert(orderItemEntity.getOrderItemId(), orderEntity.getOrderId());
        }
    }
```

代码日志：

```
2020-12-12 14:18:28.872  INFO 15112 --- [           main] ShardingSphere-SQL                       : Logic SQL: insert into t_order_item(order_item_id, order_id) values (?, ?)
2020-12-12 14:18:28.872  INFO 15112 --- [           main] ShardingSphere-SQL                       : SQLStatement: MySQLInsertStatement(setAssignment=Optional.empty, onDuplicateKeyColumns=Optional.empty)
2020-12-12 14:18:28.872  INFO 15112 --- [           main] ShardingSphere-SQL                       : Actual SQL: ds_0 ::: insert into t_order_item_15(order_item_id, order_id) values (?, ?) ::: [15, 15]
2020-12-12 14:18:28.880  INFO 15112 --- [           main] ShardingSphere-SQL                       : Logic SQL: insert into t_order(order_id, address_id, customer_id, order_sn) values (?, ?, ?, ?)
2020-12-12 14:18:28.880  INFO 15112 --- [           main] ShardingSphere-SQL                       : SQLStatement: MySQLInsertStatement(setAssignment=Optional.empty, onDuplicateKeyColumns=Optional.empty)
2020-12-12 14:18:28.880  INFO 15112 --- [           main] ShardingSphere-SQL                       : Actual SQL: ds_1 ::: insert into t_order_0(order_id, address_id, customer_id, order_sn) values (?, ?, ?, ?) ::: [16, 1596726651, 16, 42336040057605405051]
2020-12-12 14:18:28.891  INFO 15112 --- [           main] ShardingSphere-SQL                       : Logic SQL: insert into t_order_item(order_item_id, order_id) values (?, ?)
2020-12-12 14:18:28.891  INFO 15112 --- [           main] ShardingSphere-SQL                       : SQLStatement: MySQLInsertStatement(setAssignment=Optional.empty, onDuplicateKeyColumns=Optional.empty)
2020-12-12 14:18:28.891  INFO 15112 --- [           main] ShardingSphere-SQL                       : Actual SQL: ds_1 ::: insert into t_order_item_0(order_item_id, order_id) values (?, ?) ::: [16, 16]
2020-12-12 14:18:28.899  INFO 15112 --- [           main] ShardingSphere-SQL                       : Logic SQL: insert into t_order(order_id, address_id, customer_id, order_sn) values (?, ?, ?, ?)
2020-12-12 14:18:28.899  INFO 15112 --- [           main] ShardingSphere-SQL                       : SQLStatement: MySQLInsertStatement(setAssignment=Optional.empty, onDuplicateKeyColumns=Optional.empty)
2020-12-12 14:18:28.899  INFO 15112 --- [           main] ShardingSphere-SQL                       : Actual SQL: ds_1 ::: insert into t_order_1(order_id, address_id, customer_id, order_sn) values (?, ?, ?, ?) ::: [17, 1958488054, 16, 58703364011208088520]
```

**3.（选做）**模拟 1000 万的订单单表数据，迁移到上面作业 2 的分库分表中。



**4.（选做）**重新搭建一套 4 个库各 64 个表的分库分表，将作业 2 中的数据迁移到新分库。





# **Week08 作业题目（周六）：**

**1.（选做）**列举常见的分布式事务，简单分析其使用场景和优缺点。



**2.（必做）**基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布式事务应用 demo（二选一），提交到 Github。



**3.（选做）**基于 ShardingSphere narayana XA 实现一个简单的分布式事务 demo。



**4.（选做）**基于 seata 框架实现 TCC 或 AT 模式的分布式事务 demo。



**5.（选做☆）**设计实现一个简单的 XA 分布式事务框架 demo，只需要能管理和调用 2 个 MySQL 的本地事务即可，不需要考虑全局事务的持久化和恢复、高可用等。



**6.（选做☆）**设计实现一个 TCC 分布式事务框架的简单 Demo，需要实现事务管理器，不需要实现全局事务的持久化和恢复、高可用等。



**7.（选做☆）**设计实现一个 AT 分布式事务框架的简单 Demo，仅需要支持根据主键 id 进行的单个删改操作的 SQL 或插入操作的事务。



