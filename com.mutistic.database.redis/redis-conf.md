# <a id="a_top">附录B：Redis配置文件说明</a>
[Redis配置文件说明](http://www.runoob.com/redis/redis-conf.html)  

|作者|Mutistic|
|---|---|
|邮箱|mutistic@qq.com|

### <a id="a_catalogue">目录</a>：
1. <a href="#a_string">字符串（string）</a>
2. <a href="#a_hash">hash(哈希表)</a>
3. <a href="#a_list">list(双向链表)</a>
4. <a href="#a_set">set(集合)</a>
5. <a href="#a_zset">zset(sorted set，有序集合)</a>
6. <a href="#a_bitmap">位图（bitmap）</a>
7. <a href="#a_log">HyperLogLog</a>
8. <a href="#a_ego">地理位置（GEO）</a>
9. <a href="#a_database">数据库(database)</a>
10. <a href="#a_expiration">过期时间(expiration)</a>
11. <a href="#a_transaction">事务(transaction)</a>
12. <a href="#a_script">脚本(script)</a>
13. <a href="#a_pub">发布与订阅(pub/sub)</a>
14. <a href="#a_client">客户端与服务器(client&server)</a>
99. <a href="#a_down">down</a>

---
### <a id="a_base">一、基本配置：</a> <a href="#a_catalogue">last</a> <a href="#a_snapshotting">next</a>
1、注意单位: 当需要配置内存大小时, 可能需要指定像1k,5GB,4M等常见格式
```
  1k => 1000 bytes
  1kb => 1024 bytes
  1m => 1000000 bytes
  1mb => 1024*1024 bytes
  1g => 1000000000 bytes
  1gb => 1024*1024*1024 bytes

  单位是对大小写不敏感的 1GB 1Gb 1gB 是相同的
```
2、include参数：导入其他配置文件：
```Properties 
include /path/to/local.conf 
# include /path/to/other.conf
```
```
  include参数可以配置一个或多个其他的配置文件。如果你有一个适用于所有Redis服务器的标准配置模板但也需要一些每个服务器自定义的设置，这个功能将很有用。
被包含的配置文件也可以包含其他配置文件，所以需要谨慎的使用这个功能。
  注意“inclue”选项不能被admin或Redis哨兵的"CONFIG REWRITE"命令重写。
因为Redis总是使用最后解析的配置行最为配置指令的值, 你最好在这个文件的开头配置includes来 避免它在运行时重写配置。 
如果相反你想用includes的配置覆盖原来的配置，你最好在该文件的最后使用include
```
3、基本参数：
```Properties
daemonize no 
pidfile /var/run/redis.pid
```
```
  daemonize：默认Rdis不会作为守护进程运行。如果需要的话配置成'yes'，
注意配置成守护进程后Redis会将进程号写入文件/var/run/redis.pid

  pidfile：当以守护进程方式运行时，默认Redis会把进程ID写到 /var/run/redis.pid。可以在这个参数修改路径
```
```Properties
databases 16
port 6379
bind 192.168.16.113 
# bind 192.168.1.100
timeout 0
```
```
  database：设置数据库个数。默认数据库是 DB 0，数据库索引从0开始，可以通过select <dbid>来为每个连接使用不同的数据库。

  port：接受连接的特定端口，默认是6379，如果端口设置为0，Redis就不会监听TCP套接字。

  bind：默认Redis监听服务器上所有可用网络接口的连接。可以用"bind"配置指令跟一个或多个ip地址来实现。
    可以监听一个或多个网络接口，不建议配置为127.0.0.1或localhost
  
  timeout：一个客户端空闲多少秒后关闭连接。(0代表禁用，永不关闭)
```
```Properties
unixsocket /tmp/redis.sock
unixsocketperm 755
```
```
  unixsocket：指定用来监听Unix套套接字的路径。没有默认值，所以在没有指定的情况下Redis不会监听Unix套接字
  
  unixsocketperm：指定用来监听Unix套套接字大小
```
```Properties
tcp-backlog 511
tcp-keepalive 0
```
- tcp-backlog：在高并发环境下你需要一个高backlog值来避免慢客户端连接问题。
    注意Linux内核默默地将这个值减小到/proc/sys/net/core/somaxconn的值，所以需要确认增大somaxconn和tcp_max_syn_backlog两个值来达到想要的效果。
 
- tcp-keepalive：如果非零，则设置SO_KEEPALIVE选项来向空闲连接的客户端发送ACK，由于以下两个原因这是很有用的：
    1、能够检测无响应的对端。
    2、让该连接中间的网络设备知道这个连接还存活。
    在Linux上，这个指定的值(单位：秒)就是发送ACK的时间间隔。
    注意：要关闭这个连接需要两倍的这个时间值。在其他内核上这个时间间隔由内核配置决定。这个选项的一个合理值是60秒
```Properties
loglevel notice
logfile "logs/redis.log"
```
```
  logger：指定服务器调试等级：debug、verbose、notice、warning
    debug：大量信息，对开发/测试有用
    verbose：很多精简的有用信息，但是不像debug等级那么多
    notice：适量的信息，基本上是你生产环境中需要的
    warning：只有很重要/严重的信息会记录下来
  
  logfile：指明日志文件名。也可以使用"stdout"来强制让Redis把日志信息写到标准输出上。
    注意:如果Redis以守护进程方式运行，而设置日志显示到标准输出的话，日志会发送到/dev/null
```
```Properties
syslog-enabled yes
syslog-ident redis
syslog-facility local0
```
```
  ssyslog-enabled：要使用系统日志记录器，只要设置 "syslog-enabled" 为 "yes" 就可以了。
  
  syslog-ident：指明syslog身份
  
  syslog-facility：指明syslog的设备。必须是user或LOCAL0 ~ LOCAL7之一。
```

---
### <a id="a_snapshotting">二、持久化：</a> <a href="#a_base">last</a> <a href="#a_hash">next</a>
1、snapshotting（快照）持久化：
```Properties
save 900 1
save 300 10
save 60 10000
```
```
  save：snapshotting（快照）默认方式，将内存中以快照的方式写入到二进制文件中，默认文件名为dump.rdb。
  save <seconds> <changes>
    会在指定秒数和数据变化次数之后把数据库写到磁盘上。
    下面的例子将会进行把数据写入磁盘的操作:
      save 900 1：   900秒（15分钟）之后，且至少1次变更
      save 300 10：  300秒（5分钟）之后，且至少10次变更
      save 60 10000：60秒之后，且至少10000次变更
  注意：不写磁盘的话就把所有 "save" 设置注释掉就行了。
  通过添加一条带空字符串参数的save指令也能移除之前所有配置的save指令，像下面的例子： save "" 
```
```Properties
stop-writes-on-bgsave-error yes
rdbchecksum yes
dbfilename dump.rdb
dir data/
```
```
  stop-writes-on-bgsave-error：默认如果开启RDB快照(至少一条save指令)并且最新的后台保存失败，Redis将会停止接受写操作
这将使用户知道数据没有正确的持久化到硬盘，否则可能没人注意到并且造成一些灾难。  如果后台保存进程能重新开始工作，Redis将自动允许写操作
然而如果你已经部署了适当的Redis服务器和持久化的监控，你可能想关掉这个功能以便于即使是硬盘，权限等出问题了Redis也能够像平时一样正常工作，

  rdbcompression：当导出到 .rdb 数据库时是否用LZF压缩字符串对象。默认设置为 "yes"，因为几乎在任何情况下它都是不错的。
如果你想节省CPU的话你可以把这个设置为 "no"，但是如果你有可压缩的key和value的话，那数据文件就会更大了。

  rdbchecksum：因为版本5的RDB有一个CRC64算法的校验和放在了文件的最后。这将使文件格式更加可靠但在生产和加载RDB文件时，
这有一个性能消耗(大约10%)，所以你可以关掉它来获取最好的性能。生成的关闭校验的RDB文件有一个0的校验和，它将告诉加载代码跳过检查

  dbfilename：持久化数据库的文件名

  dir：累加文件也放这里。注意你这里指定的必须是目录，不是文件名。
```

---
<a id="a_down"></a>  
<a href="#a_top">Top</a> 
<a href="#a_catalogue">Catalogue</a>