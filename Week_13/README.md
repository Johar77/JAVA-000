# 第 25 课作业实践

1、 （必做） 搭建一个3节点Kafka集群， 测试功能和性能； 实现spring kafka下对kafka集群
的操作， 将代码提交到github。

代码：https://github.com/Johar77/JAVA-000/tree/main/Week_13/spring-kafka-demo

集群搭建步骤：

环境：window 10

kafka：2.13

zookeeper：3.6.2

**步骤一：**修改zookeeper配置文件，zoo.cfg。然后运行zkServer.cmd，启动zookeeper，使用ZooInspector连接zookeeper。

![ZooInspector](https://github.com/Johar77/JAVA-000/tree/main/Week_13/pic/ZooInspector.png?raw=true)

**步骤二：**准备脚本，下面以kafka-1为例，server.properties中需要修改的项为：broker.id、port、listeners、log.dirs、zookeeper.connect

```
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# see kafka.server.KafkaConfig for additional details and defaults

############################# Server Basics #############################

# The id of the broker. This must be set to a unique integer for each broker.
broker.id=1
port=9001

############################# Socket Server Settings #############################

# The address the socket server listens on. It will get the value returned from 
# java.net.InetAddress.getCanonicalHostName() if not configured.
#   FORMAT:
#     listeners = listener_name://host_name:port
#   EXAMPLE:
#     listeners = PLAINTEXT://your.host.name:9092
listeners=PLAINTEXT://:9001

# Hostname and port the broker will advertise to producers and consumers. If not set, 
# it uses the value for "listeners" if configured.  Otherwise, it will use the value
# returned from java.net.InetAddress.getCanonicalHostName().
#advertised.listeners=PLAINTEXT://your.host.name:9092

# Maps listener names to security protocols, the default is for them to be the same. See the config documentation for more details
#listener.security.protocol.map=PLAINTEXT:PLAINTEXT,SSL:SSL,SASL_PLAINTEXT:SASL_PLAINTEXT,SASL_SSL:SASL_SSL

# The number of threads that the server uses for receiving requests from the network and sending responses to the network
num.network.threads=3

# The number of threads that the server uses for processing requests, which may include disk I/O
num.io.threads=8

# The send buffer (SO_SNDBUF) used by the socket server
socket.send.buffer.bytes=102400

# The receive buffer (SO_RCVBUF) used by the socket server
socket.receive.buffer.bytes=102400

# The maximum size of a request that the socket server will accept (protection against OOM)
socket.request.max.bytes=104857600


############################# Log Basics #############################

# A comma separated list of directories under which to store log files
log.dirs=/tmp/kafka-logs1

# The default number of log partitions per topic. More partitions allow greater
# parallelism for consumption, but this will also result in more files across
# the brokers.
num.partitions=1

# The number of threads per data directory to be used for log recovery at startup and flushing at shutdown.
# This value is recommended to be increased for installations with data dirs located in RAID array.
num.recovery.threads.per.data.dir=1

############################# Internal Topic Settings  #############################
# The replication factor for the group metadata internal topics "__consumer_offsets" and "__transaction_state"
# For anything other than development testing, a value greater than 1 is recommended to ensure availability such as 3.
offsets.topic.replication.factor=1
transaction.state.log.replication.factor=1
transaction.state.log.min.isr=1

############################# Log Flush Policy #############################

# Messages are immediately written to the filesystem but by default we only fsync() to sync
# the OS cache lazily. The following configurations control the flush of data to disk.
# There are a few important trade-offs here:
#    1. Durability: Unflushed data may be lost if you are not using replication.
#    2. Latency: Very large flush intervals may lead to latency spikes when the flush does occur as there will be a lot of data to flush.
#    3. Throughput: The flush is generally the most expensive operation, and a small flush interval may lead to excessive seeks.
# The settings below allow one to configure the flush policy to flush data after a period of time or
# every N messages (or both). This can be done globally and overridden on a per-topic basis.

# The number of messages to accept before forcing a flush of data to disk
#log.flush.interval.messages=10000

# The maximum amount of time a message can sit in a log before we force a flush
#log.flush.interval.ms=1000

############################# Log Retention Policy #############################

# The following configurations control the disposal of log segments. The policy can
# be set to delete segments after a period of time, or after a given size has accumulated.
# A segment will be deleted whenever *either* of these criteria are met. Deletion always happens
# from the end of the log.

# The minimum age of a log file to be eligible for deletion due to age
log.retention.hours=168

# A size-based retention policy for logs. Segments are pruned from the log unless the remaining
# segments drop below log.retention.bytes. Functions independently of log.retention.hours.
#log.retention.bytes=1073741824

# The maximum size of a log segment file. When this size is reached a new log segment will be created.
log.segment.bytes=1073741824

# The interval at which log segments are checked to see if they can be deleted according
# to the retention policies
log.retention.check.interval.ms=300000

############################# Zookeeper #############################

# Zookeeper connection string (see zookeeper docs for details).
# This is a comma separated host:port pairs, each corresponding to a zk
# server. e.g. "127.0.0.1:3000,127.0.0.1:3001,127.0.0.1:3002".
# You can also append an optional chroot string to the urls to specify the
# root directory for all kafka znodes.
zookeeper.connect=localhost:2181
broker.list=localhost:9001,localhost:9002,localhost:9003

# Timeout in ms for connecting to zookeeper
zookeeper.connection.timeout.ms=18000


############################# Group Coordinator Settings #############################

# The following configuration specifies the time, in milliseconds, that the GroupCoordinator will delay the initial consumer rebalance.
# The rebalance will be further delayed by the value of group.initial.rebalance.delay.ms as new members join the group, up to a maximum of max.poll.interval.ms.
# The default value for this is 3 seconds.
# We override this to 0 here as it makes for a better out-of-the-box experience for development and testing.
# However, in production environments the default value of 3 seconds is more suitable as this will help to avoid unnecessary, and potentially expensive, rebalances during application startup.
group.initial.rebalance.delay.ms=0
```

**步骤3：**分别启动3个kafka，命令为：.\bin\windows\kafka-server-start.bat .\config\server.properties，可以在Zookeeper中看到注册上来的3个节点

![kafka](https://github.com/Johar77/JAVA-000/tree/main/Week_13/pic/kafka.png?raw=true)

步骤4：程序启动后，收到的消息：

```
2021-01-13 00:47:17.034  INFO 24200 --- [ntainer#0-0-C-1] c.j.g.s.kafka.KafkaConsumer              : receive msg: ConsumerRecord(topic = test.order.topic, partition = 1, leaderEpoch = 0, offset = 17, CreateTime = 1610470037027, serialized key size = 4, serialized value size = 26, headers = RecordHeaders(headers = [], isReadOnly = false), key = 354222495, value = {"id":35,"name":"order36"})
2021-01-13 00:47:22.028  INFO 24200 --- [ntainer#0-0-C-1] c.j.g.s.kafka.KafkaConsumer              : receive msg: ConsumerRecord(topic = test.order.topic, partition = 1, leaderEpoch = 0, offset = 18, CreateTime = 1610470042023, serialized key size = 4, serialized value size = 26, headers = RecordHeaders(headers = [], isReadOnly = false), key = -490248415, value = {"id":36,"name":"order37"})
2021-01-13 00:47:27.019  INFO 24200 --- [ntainer#0-0-C-1] c.j.g.s.kafka.KafkaConsumer              : receive msg: ConsumerRecord(topic = test.order.topic, partition = 1, leaderEpoch = 0, offset = 19, CreateTime = 1610470047013, serialized key size = 4, serialized value size = 26, headers = RecordHeaders(headers = [], isReadOnly = false), key = -1334719325, value = {"id":37,"name":"order38"})
2021-01-13 00:47:32.024  INFO 24200 --- [ntainer#0-0-C-1] c.j.g.s.kafka.KafkaConsumer              : receive msg: ConsumerRecord(topic = test.order.topic, partition = 2, leaderEpoch = 0, offset = 12, CreateTime = 1610470052018, serialized key size = 4, serialized value size = 26, headers = RecordHeaders(headers = [], isReadOnly = false), key = 2115777061, value = {"id":38,"name":"order39"})
2021-01-13 00:47:37.028  INFO 24200 --- [ntainer#0-0-C-1] c.j.g.s.kafka.KafkaConsumer              : receive msg: ConsumerRecord(topic = test.order.topic, partition = 0, leaderEpoch = 0, offset = 10, CreateTime = 1610470057023, serialized key size = 4, serialized value size = 26, headers = RecordHeaders(headers = [], isReadOnly = false), key = 1271326332, value = {"id":39,"name":"order40"})
```

2、 （选做） 安装kafka-manager工具， 监控kafka集群状态。

步骤1：docker-compose.yml文件配置

```dockerfile
version: '3'
services:
  kafka-manager:
    image: sheepkiller/kafka-manager
    ports:
      - 9000:9000
    environment:
      ZK_HOSTS: 192.168.101.4:2181
```

步骤2：执行docker-compose up命令，访问localhost:9000，创建cluster：

![kafka-manager](https://github.com/Johar77/JAVA-000/tree/main/Week_13/pic/kafka-manager.png?raw=true)

3、 （挑战☆） 演练本课提及的各种生产者和消费者特性。

4、 （挑战☆☆☆） Kafka金融领域实战： 在证券或者外汇、 数字货币类金融核心交易系统里，
对于订单的处理， 大概可以分为收单、 定序、 撮合、 清算等步骤。 其中我们一般可以用mq来
实现订单定序， 然后将订单发送给撮合模块。
1） 收单： 请实现一个订单的rest接口， 能够接收一个订单Order对象；
2） 定序： 将Order对象写入到kafka集群的order.usd2cny队列， 要求数据有序并且不丢失；
3） 撮合： 模拟撮合程序（不需要实现撮合逻辑） ， 从kafka获取order数据， 并打印订单信息，
要求可重放, 顺序消费, 消息仅处理一次。



