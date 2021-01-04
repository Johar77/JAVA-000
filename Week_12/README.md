**Week12 作业题目：**

**1.（必做）**配置 redis 的主从复制，sentinel 高可用，Cluster 集群。

个人电脑环境为win10 19041，docker-desktop-window 版本为 20.10.0

**1.1 主从复制：**

使用docker直接配置，具体配置如下：

- 启动主节点

  ```bash
  docker pull redis
  docker run -itd --name redis-6379  -p 6379:6379 redis --appendonly yes --protected-mode no
  docker exec -it redis-6379 /bin/bash
  $redis-cli
  ```

- 启动从节点1

  ```bash
  docker pull redis
  docker run -itd --name redis-6380  -p 6380:6379 redis --appendonly yes --protected-mode no
  docker exec -it redis-6380 /bin/bash
  $redis-cli
  replicaof 172.19.16.1 6379
  ```

  tip：Redis5.0之前，主从配置命令使用**slaveof**

  使用命令**info**查看主从是否连接上，简介内容如下，表示已经连接上

  ```
  # Replication
  role:slave
  master_host:172.19.16.1
  master_port:6379
  master_link_status:up
  ```

  

- 启动从节点2

  ```bash
  docker pull redis
  docker run -itd --name redis-6381  -p 6380:6379 redis --appendonly yes --protected-mode no
  docker exec -it redis-6381 /bin/bash
  $redis-cli
  replicaof 172.19.16.1 6379
  ```

  tip：Redis5.0之前，主从配置命令使用**slaveof**

  使用命令**info**查看主从是否连接上，简介内容如下，表示已经连接上

  ```
  # Replication
  role:slave
  master_host:172.19.16.1
  master_port:6379
  master_link_status:up
  ```

- 主节点设置aa

  ```
  127.0.0.1:6379> keys *
  (empty array)
  127.0.0.1:6379> set aa bb
  OK
  127.0.0.1:6379> keys *
  1) "aa"
  ```

  

- 从节点获取aa

  ```
  127.0.0.1:6379> keys *
  (empty array)
  127.0.0.1:6379> keys *
  1) "aa"
  127.0.0.1:6379> get aa
  "bb"
  ```

  

**1.2 sentinel 高可用**

使用docker-compose进行配置

- redis1.conf文件

```bash
bind 0.0.0.0
protected-mode no
port 6379
tcp-backlog 511
timeout 0
tcp-keepalive 300
daemonize no
supervised no
pidfile /var/run/redis_6379.pid
loglevel notice
logfile ""
databases 16
always-show-logo yes
save 900 1
save 300 10
save 60 10000
stop-writes-on-bgsave-error yes
rdbcompression yes
rdbchecksum yes
dbfilename dump.rdb
dir ./
replica-serve-stale-data yes
replica-read-only yes
repl-diskless-sync no
repl-diskless-sync-delay 5
repl-disable-tcp-nodelay no
replica-priority 100
lazyfree-lazy-eviction no
lazyfree-lazy-expire no
lazyfree-lazy-server-del no
replica-lazy-flush no
appendonly no
appendfilename "appendonly.aof"
appendfsync everysec
no-appendfsync-on-rewrite no
auto-aof-rewrite-percentage 100
auto-aof-rewrite-min-size 64mb
aof-load-truncated yes
aof-use-rdb-preamble yes
lua-time-limit 5000
slowlog-log-slower-than 10000
slowlog-max-len 128
latency-monitor-threshold 0
notify-keyspace-events ""
hash-max-ziplist-entries 512
hash-max-ziplist-value 64
list-max-ziplist-size -2
list-compress-depth 0
set-max-intset-entries 512
zset-max-ziplist-entries 128
zset-max-ziplist-value 64
hll-sparse-max-bytes 3000
stream-node-max-bytes 4096
stream-node-max-entries 100
activerehashing yes
client-output-buffer-limit normal 0 0 0
client-output-buffer-limit replica 256mb 64mb 60
client-output-buffer-limit pubsub 32mb 8mb 60
hz 10
dynamic-hz yes
aof-rewrite-incremental-fsync yes
rdb-save-incremental-fsync yes
```

- redis2.conf


```
bind 0.0.0.0
protected-mode no
port 6380
tcp-backlog 511
timeout 0
tcp-keepalive 300
daemonize no
supervised no
pidfile /var/run/redis_6380.pid
loglevel notice
logfile ""
databases 16
always-show-logo yes
save 900 1
save 300 10
save 60 10000
stop-writes-on-bgsave-error yes
rdbcompression yes
rdbchecksum yes
dbfilename dump.rdb
dir ./
replica-serve-stale-data yes
replica-read-only yes
repl-diskless-sync no
repl-diskless-sync-delay 5
repl-disable-tcp-nodelay no
replica-priority 100
lazyfree-lazy-eviction no
lazyfree-lazy-expire no
lazyfree-lazy-server-del no
replica-lazy-flush no
appendonly no
appendfilename "appendonly.aof"
appendfsync everysec
no-appendfsync-on-rewrite no
auto-aof-rewrite-percentage 100
auto-aof-rewrite-min-size 64mb
aof-load-truncated yes
aof-use-rdb-preamble yes
lua-time-limit 5000
slowlog-log-slower-than 10000
slowlog-max-len 128
latency-monitor-threshold 0
notify-keyspace-events ""
hash-max-ziplist-entries 512
hash-max-ziplist-value 64
list-max-ziplist-size -2
list-compress-depth 0
set-max-intset-entries 512
zset-max-ziplist-entries 128
zset-max-ziplist-value 64
hll-sparse-max-bytes 3000
stream-node-max-bytes 4096
stream-node-max-entries 100
activerehashing yes
client-output-buffer-limit normal 0 0 0
client-output-buffer-limit replica 256mb 64mb 60
client-output-buffer-limit pubsub 32mb 8mb 60
hz 10
dynamic-hz yes
aof-rewrite-incremental-fsync yes
rdb-save-incremental-fsync yes
```

- redis3.conf


```bash
bind 0.0.0.0
protected-mode no
port 6381
tcp-backlog 511
timeout 0
tcp-keepalive 300
daemonize no
supervised no
pidfile /var/run/redis_6381.pid
loglevel notice
logfile ""
databases 16
always-show-logo yes
save 900 1
save 300 10
save 60 10000
stop-writes-on-bgsave-error yes
rdbcompression yes
rdbchecksum yes
dbfilename dump.rdb
dir ./
replica-serve-stale-data yes
replica-read-only yes
repl-diskless-sync no
repl-diskless-sync-delay 5
repl-disable-tcp-nodelay no
replica-priority 100
lazyfree-lazy-eviction no
lazyfree-lazy-expire no
lazyfree-lazy-server-del no
replica-lazy-flush no
appendonly no
appendfilename "appendonly.aof"
appendfsync everysec
no-appendfsync-on-rewrite no
auto-aof-rewrite-percentage 100
auto-aof-rewrite-min-size 64mb
aof-load-truncated yes
aof-use-rdb-preamble yes
lua-time-limit 5000
slowlog-log-slower-than 10000
slowlog-max-len 128
latency-monitor-threshold 0
notify-keyspace-events ""
hash-max-ziplist-entries 512
hash-max-ziplist-value 64
list-max-ziplist-size -2
list-compress-depth 0
set-max-intset-entries 512
zset-max-ziplist-entries 128
zset-max-ziplist-value 64
hll-sparse-max-bytes 3000
stream-node-max-bytes 4096
stream-node-max-entries 100
activerehashing yes
client-output-buffer-limit normal 0 0 0
client-output-buffer-limit replica 256mb 64mb 60
client-output-buffer-limit pubsub 32mb 8mb 60
hz 10
dynamic-hz yes
aof-rewrite-incremental-fsync yes
rdb-save-incremental-fsync yes
```

- sentinel1.conf


```
port 26379
sentinel monitor mymaster 127.0.0.1 6379 2
sentinel down-after-milliseconds mymaster 10000
sentinel failover-timeout mymaster 180000
sentinel parallel-syncs mymaster 1
```

- sentinel2.conf


```
port 26380
sentinel monitor mymaster 127.0.0.1 6379 2
sentinel down-after-milliseconds mymaster 10000
sentinel failover-timeout mymaster 180000
sentinel parallel-syncs mymaster 1
```

-  sentinel3.conf


```
port 26381
sentinel monitor mymaster 127.0.0.1 6379 2
sentinel down-after-milliseconds mymaster 10000
sentinel failover-timeout mymaster 180000
sentinel parallel-syncs mymaster 1
```

- docker-compose.yml

  ```dockerfile
  version: '2'
  services:
    master:
      image: redis
      container_name: redis-master
      ports:
        - 6379:6379
      network_mode: host
      command: redis-server /usr/local/etc/redis/redis.conf
      volumes:
        - ./redis1.conf:/usr/local/etc/redis/redis.conf
  
    slave1:
      image: redis
      container_name: redis-slave-1
      ports:
        - 6380:6380
      network_mode: host
      command: redis-server /usr/local/etc/redis/redis.conf --slaveof 127.0.0.1 6379
      volumes:
        - ./redis2.conf:/usr/local/etc/redis/redis.conf
  
    slave2:
      image: redis
      container_name: redis-slave-2
      ports:
        - 6381:6381
      network_mode: host
      command: redis-server /usr/local/etc/redis/redis.conf --slaveof 127.0.0.1 6379
      volumes:
        - ./redis3.conf:/usr/local/etc/redis/redis.conf
  
    sentinel1:
      image: redis
      container_name: redis-sentinel-1
      ports:
        - 26379:26379
      network_mode: host
      command: redis-sentinel /usr/local/etc/redis/sentinel.conf
      volumes:
        - ./sentinel1.conf:/usr/local/etc/redis/sentinel.conf
  
    sentinel2:
      image: redis
      container_name: redis-sentinel-2
      ports:
        - 26380:26380
      network_mode: host
      command: redis-sentinel /usr/local/etc/redis/sentinel.conf
      volumes:
        - ./sentinel2.conf:/usr/local/etc/redis/sentinel.conf
  
    sentinel3:
      image: redis
      container_name: redis-sentinel-3
      ports:
        - 26381:26381
      network_mode: host
      command: redis-sentinel /usr/local/etc/redis/sentinel.conf
      volumes:
        - ./sentinel3.conf:/usr/local/etc/redis/sentinel.conf
  ```

- 切换到上述文件路径，**docker-compose up** 启动docker-compse

- 在主节点**redis-master**上面，设置如下：

  ```
  127.0.0.1:6379> keys *
  (empty array)
  127.0.0.1:6379> set aa bb
  OK
  127.0.0.1:6379> get aa
  "bb"
  ```

- 在从节点，也可以获取到，下面以redis-slave-1为例：

  ```
  127.0.0.1:6380> keys *
  (empty array)
  127.0.0.1:6380> get aa
  "bb"
  ```

- 通过命令**docker stop redis-master**手动停止主节点，再查看redis-slave-1，redis-slave-2节点信息，可以看到，主节点已经转换到redis-slave-1

  ```
  # Replication
  role:master
  connected_slaves:1
  slave0:ip=127.0.0.1,port=6381,state=online,offset=208493,lag=1
  master_replid:5f165bd3f3598c9ccd3664a5b9fa38570e578404
  master_replid2:4517e518a722cb8dc801aff210b9400e10770592
  master_repl_offset:208759
  second_repl_offset:48668
  repl_backlog_active:1
  repl_backlog_size:1048576
  repl_backlog_first_byte_offset:1
  repl_backlog_histlen:208759
  ```

  ```
  # Replication
  role:slave
  master_host:127.0.0.1
  master_port:6380
  master_link_status:down
  master_last_io_seconds_ago:-1
  master_sync_in_progress:0
  slave_repl_offset:48667
  master_link_down_since_seconds:11
  slave_priority:100
  slave_read_only:1
  connected_slaves:0
  master_replid:4517e518a722cb8dc801aff210b9400e10770592
  master_replid2:0000000000000000000000000000000000000000
  master_repl_offset:48667
  second_repl_offset:-1
  repl_backlog_active:1
  repl_backlog_size:1048576
  repl_backlog_first_byte_offset:1
  repl_backlog_histlen:48667
  ```



**1.3 Cluster 集群**

- redis节点配置文件，一共6个节点，端口分别是7000,7001,7002,7003,7004,7005，配置文件以redis1为例，其他5个节点类似：

  ```
  bind 0.0.0.0
  protected-mode no
  port 7000
  tcp-backlog 511
  timeout 0
  tcp-keepalive 300
  daemonize no
  supervised no
  pidfile /var/run/redis_7000.pid
  loglevel notice
  logfile ""
  databases 16
  always-show-logo yes
  save 900 1
  save 300 10
  save 60 10000
  stop-writes-on-bgsave-error yes
  rdbcompression yes
  rdbchecksum yes
  dbfilename dump.rdb
  dir ./
  replica-serve-stale-data yes
  replica-read-only yes
  repl-diskless-sync no
  repl-diskless-sync-delay 5
  repl-disable-tcp-nodelay no
  replica-priority 100
  lazyfree-lazy-eviction no
  lazyfree-lazy-expire no
  lazyfree-lazy-server-del no
  replica-lazy-flush no
  appendonly no
  appendfilename "appendonly.aof"
  appendfsync everysec
  no-appendfsync-on-rewrite no
  auto-aof-rewrite-percentage 100
  auto-aof-rewrite-min-size 64mb
  aof-load-truncated yes
  aof-use-rdb-preamble yes
  lua-time-limit 5000
  slowlog-log-slower-than 10000
  slowlog-max-len 128
  latency-monitor-threshold 0
  notify-keyspace-events ""
  hash-max-ziplist-entries 512
  hash-max-ziplist-value 64
  list-max-ziplist-size -2
  list-compress-depth 0
  set-max-intset-entries 512
  zset-max-ziplist-entries 128
  zset-max-ziplist-value 64
  hll-sparse-max-bytes 3000
  stream-node-max-bytes 4096
  stream-node-max-entries 100
  activerehashing yes
  client-output-buffer-limit normal 0 0 0
  client-output-buffer-limit replica 256mb 64mb 60
  client-output-buffer-limit pubsub 32mb 8mb 60
  hz 10
  dynamic-hz yes
  aof-rewrite-incremental-fsync yes
  rdb-save-incremental-fsync yes
  cluster-enabled yes
  cluster-config-file nodes-7000.conf
  cluster-require-full-coverage no
  ```

- docker-compose.yml

  ```dockerfile
  version: '3'
  services:
    redis1:
      image: redis
      container_name: redis1
      ports:
        - 7000:7000
      network_mode: host
      command: redis-server /usr/local/etc/redis/redis.conf
      volumes:
        - ./redis1.conf:/usr/local/etc/redis/redis.conf
  
    redis2:
      image: redis
      container_name: redis2
      ports:
        - 7001:7001
      network_mode: host
      command: redis-server /usr/local/etc/redis/redis.conf
      volumes:
        - ./redis2.conf:/usr/local/etc/redis/redis.conf
  
    redis3:
      image: redis
      container_name: redis3
      ports:
        - 7002:7002
      network_mode: host
      command: redis-server /usr/local/etc/redis/redis.conf
      volumes:
        - ./redis3.conf:/usr/local/etc/redis/redis.conf
    
    redis4:
      image: redis
      container_name: redis4
      ports:
        - 7003:7003
      network_mode: host
      command: redis-server /usr/local/etc/redis/redis.conf
      volumes:
        - ./redis4.conf:/usr/local/etc/redis/redis.conf
  
    redis5:
      image: redis
      container_name: redis5
      ports:
        - 7004:7004
      network_mode: host
      command: redis-server /usr/local/etc/redis/redis.conf
      volumes:
        - ./redis5.conf:/usr/local/etc/redis/redis.conf
  
    redis6:
      image: redis
      container_name: redis6
      ports:
        - 7006:7006
      network_mode: host
      command: redis-server /usr/local/etc/redis/redis.conf
      volumes:
        - ./redis6.conf:/usr/local/etc/redis/redis.conf
  ```

- 使用**docker-compose up** 启动docker compose配置文件

- 创建redis cluster

  ```bash
  # 进入容器
  docker exec -it redis1 /bin/bash
  # 切换至指定目录
  cd /usr/local/bin
  # 创建redis 集群
  # replicas 1 表示集群中的每个主节点创建一个从节点
  redis-cli --cluster create 127.0.0.1:7000 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005 --cluster-replicas 1
  
  # 结果：
  >>> Performing hash slots allocation on 6 nodes...
  Master[0] -> Slots 0 - 5460
  Master[1] -> Slots 5461 - 10922
  Master[2] -> Slots 10923 - 16383
  Adding replica 127.0.0.1:7004 to 127.0.0.1:7000
  Adding replica 127.0.0.1:7005 to 127.0.0.1:7001
  Adding replica 127.0.0.1:7003 to 127.0.0.1:7002
  >>> Trying to optimize slaves allocation for anti-affinity
  [WARNING] Some slaves are in the same host as their master
  M: 7cef188d3e1ae38e0f86bdcf19947db2699c03f7 127.0.0.1:7000
     slots:[0-5460] (5461 slots) master
  M: 6084ddf3a1fe18e168113bc0ec1b1ba36d58e81b 127.0.0.1:7001
     slots:[5461-10922] (5462 slots) master
  M: ddbcb195cf4a5c2adac984b87f8ffa75006b39cd 127.0.0.1:7002
     slots:[10923-16383] (5461 slots) master
  S: 250848343e74d93fd70d79ae84b494ebcaf9e980 127.0.0.1:7003
     replicates 6084ddf3a1fe18e168113bc0ec1b1ba36d58e81b
  S: 62089c2690e14afecb73536ee77bb5ae5309b8dc 127.0.0.1:7004
     replicates ddbcb195cf4a5c2adac984b87f8ffa75006b39cd
  S: 6b245fcc3d2c7644c593af709fd7e41b9eb93506 127.0.0.1:7005
     replicates 7cef188d3e1ae38e0f86bdcf19947db2699c03f7
  Can I set the above configuration? (type 'yes' to accept): yes
  >>> Nodes configuration updated
  >>> Assign a different config epoch to each node
  >>> Sending CLUSTER MEET messages to join the cluster
  Waiting for the cluster to join
  ..
  >>> Performing Cluster Check (using node 127.0.0.1:7000)
  M: 7cef188d3e1ae38e0f86bdcf19947db2699c03f7 127.0.0.1:7000
     slots:[0-5460] (5461 slots) master
     1 additional replica(s)
  S: 250848343e74d93fd70d79ae84b494ebcaf9e980 127.0.0.1:7003
     slots: (0 slots) slave
     replicates 6084ddf3a1fe18e168113bc0ec1b1ba36d58e81b
  S: 6b245fcc3d2c7644c593af709fd7e41b9eb93506 127.0.0.1:7005
     slots: (0 slots) slave
     replicates 7cef188d3e1ae38e0f86bdcf19947db2699c03f7
  M: ddbcb195cf4a5c2adac984b87f8ffa75006b39cd 127.0.0.1:7002
     slots:[10923-16383] (5461 slots) master
     1 additional replica(s)
  S: 62089c2690e14afecb73536ee77bb5ae5309b8dc 127.0.0.1:7004
     slots: (0 slots) slave
     replicates ddbcb195cf4a5c2adac984b87f8ffa75006b39cd
  M: 6084ddf3a1fe18e168113bc0ec1b1ba36d58e81b 127.0.0.1:7001
     slots:[5461-10922] (5462 slots) master
     1 additional replica(s)
  [OK] All nodes agree about slots configuration.
  >>> Check for open slots...
  >>> Check slots coverage...
  [OK] All 16384 slots covered.
  ```

- 查看集群信息

  ```bash
  # 连接至某个节点
  root@docker-desktop:/usr/local/bin# redis-cli -p 7000
  127.0.0.1:7000> cluster info
  # 查看集群信息
  127.0.0.1:7000> cluster info
  cluster_state:ok
  cluster_slots_assigned:16384
  cluster_slots_ok:16384
  cluster_slots_pfail:0
  cluster_slots_fail:0
  cluster_known_nodes:6
  cluster_size:3
  cluster_current_epoch:6
  cluster_my_epoch:1
  cluster_stats_messages_ping_sent:682
  cluster_stats_messages_pong_sent:701
  cluster_stats_messages_sent:1383
  cluster_stats_messages_ping_received:696
  cluster_stats_messages_pong_received:682
  cluster_stats_messages_meet_received:5
  cluster_stats_messages_received:1383
  # 查看集群节点
  127.0.0.1:7000> cluster nodes
  7cef188d3e1ae38e0f86bdcf19947db2699c03f7 127.0.0.1:7000@17000 myself,master - 0 1609777930000 1 connected 0-5460
  250848343e74d93fd70d79ae84b494ebcaf9e980 127.0.0.1:7003@17003 slave 6084ddf3a1fe18e168113bc0ec1b1ba36d58e81b 0 1609777932000 2 connected
  6b245fcc3d2c7644c593af709fd7e41b9eb93506 127.0.0.1:7005@17005 slave 7cef188d3e1ae38e0f86bdcf19947db2699c03f7 0 1609777930812 1 connected
  ddbcb195cf4a5c2adac984b87f8ffa75006b39cd 127.0.0.1:7002@17002 master - 0 1609777932819 3 connected 10923-16383
  62089c2690e14afecb73536ee77bb5ae5309b8dc 127.0.0.1:7004@17004 slave ddbcb195cf4a5c2adac984b87f8ffa75006b39cd 0 1609777929000 3 connected
  6084ddf3a1fe18e168113bc0ec1b1ba36d58e81b 127.0.0.1:7001@17001 master - 0 1609777929809 2 connected 5461-10922
  ```

- redis操作

  ```bash
  127.0.0.1:7000> set aaa 111
  (error) MOVED 10439 127.0.0.1:7001
  127.0.0.1:7000> set bbb 222
  OK
  127.0.0.1:7000> set ccc 333
  OK
  127.0.0.1:7000> set ddd 444
  (error) MOVED 11367 127.0.0.1:7002
  127.0.0.1:7000> get aaa
  (error) MOVED 10439 127.0.0.1:7001
  127.0.0.1:7000> get bbb
  "222"
  127.0.0.1:7000>
  ```

  

**2.（选做）**练习示例代码里下列类中的作业题:
08cache/redis/src/main/java/io/kimmking/cache/RedisApplication.java

**3.（选做☆）**练习 redission 的各种功能。

**4.（选做☆☆）**练习 hazelcast 的各种功能。

**5.（选做☆☆☆）**搭建 hazelcast 3 节点集群，写入 100 万数据到一个 map，模拟和演 示高可用。