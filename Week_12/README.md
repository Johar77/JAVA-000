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



**2.（选做）**练习示例代码里下列类中的作业题:
08cache/redis/src/main/java/io/kimmking/cache/RedisApplication.java

**3.（选做☆）**练习 redission 的各种功能。

**4.（选做☆☆）**练习 hazelcast 的各种功能。

**5.（选做☆☆☆）**搭建 hazelcast 3 节点集群，写入 100 万数据到一个 map，模拟和演 示高可用。