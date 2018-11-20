# <a id="a_top">附录B：Redis配置文件说明</a>
[Redis配置文件说明](http://www.runoob.com/redis/redis-conf.html)  

|作者|Mutistic|
|---|---|
|邮箱|mutistic@qq.com|

### <a id="a_catalogue">目录</a>：
1. <a href="#a_base">基本配置</a>
2. <a href="#a_rdb">RDB持久化</a>
3. <a href="#a_aof">AOF持久化：</a>
4. <a href="#a_replication">主从同步</a>
5. <a href="#a_security">安全管理</a>
6. <a href="#a_limits">内存管理</a>
7. <a href="#a_advanced">高级参数</a>
8. <a href="#a_other">其他参数</a>
99. <a href="#a_down">down</a>

---
### <a id="a_base">一、基本配置：</a> <a href="#a_catalogue">last</a> <a href="#a_rdb">next</a>
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
2、include导入其他配置文件：
- include /path/to/local.conf # include /path/to/other.conf
```
  include：可以配置一个或多个其他的配置文件。如果你有一个适用于所有Redis服务器的标准配置模板但也需要一些每个服务器自定义的设置，这个功能将很有用。
被包含的配置文件也可以包含其他配置文件，所以需要谨慎的使用这个功能。
  注意“inclue”选项不能被admin或Redis哨兵的"CONFIG REWRITE"命令重写。
因为Redis总是使用最后解析的配置行最为配置指令的值, 你最好在这个文件的开头配置includes来 避免它在运行时重写配置。 
如果相反你想用includes的配置覆盖原来的配置，你最好在该文件的最后使用include
```
3、基本参数：
- daemonize no 
- pidfile /var/run/redis.pid
```
  daemonize：默认Rdis不会作为守护进程运行。如果需要的话配置成'yes'，
注意配置成守护进程后Redis会将进程号写入文件/var/run/redis.pid

  pidfile：当以守护进程方式运行时，默认Redis会把进程ID写到 /var/run/redis.pid。可以在这个参数修改路径
```
- databases 16
- port 6379
- bind 192.168.16.113 
- timeout 0
```
  database：设置数据库个数。默认数据库是 DB 0，数据库索引从0开始，可以通过select <dbid>来为每个连接使用不同的数据库。

  port：接受连接的特定端口，默认是6379，如果端口设置为0，Redis就不会监听TCP套接字。

  bind：默认Redis监听服务器上所有可用网络接口的连接。可以用"bind"配置指令跟一个或多个ip地址来实现。
    可以监听一个或多个网络接口，不建议配置为127.0.0.1或localhost
  
  timeout：一个客户端空闲多少秒后关闭连接。(0代表禁用，永不关闭)
```
- unixsocket /tmp/redis.sock
- unixsocketperm 755
```
  unixsocket：指定用来监听Unix套套接字的路径。没有默认值，所以在没有指定的情况下Redis不会监听Unix套接字
  
  unixsocketperm：指定用来监听Unix套套接字大小
```
- tcp-backlog 511
- tcp-keepalive 0
```
  tcp-backlog：在高并发环境下你需要一个高backlog值来避免慢客户端连接问题。
    注意Linux内核默默地将这个值减小到/proc/sys/net/core/somaxconn的值，所以需要确认增大somaxconn和tcp_max_syn_backlog两个值来达到想要的效果。
 
  tcp-keepalive：如果非零，则设置SO_KEEPALIVE选项来向空闲连接的客户端发送ACK，由于以下两个原因这是很有用的：
    1、能够检测无响应的对端。
    2、让该连接中间的网络设备知道这个连接还存活。
    在Linux上，这个指定的值(单位：秒)就是发送ACK的时间间隔。
    注意：要关闭这个连接需要两倍的这个时间值。在其他内核上这个时间间隔由内核配置决定。这个选项的一个合理值是60秒
```
- loglevel notice
- logfile "logs/redis.log"
```
  logger：指定服务器调试等级：debug、verbose、notice、warning
    debug：大量信息，对开发/测试有用
    verbose：很多精简的有用信息，但是不像debug等级那么多
    notice：适量的信息，基本上是你生产环境中需要的
    warning：只有很重要/严重的信息会记录下来
  
  logfile：指明日志文件名。也可以使用"stdout"来强制让Redis把日志信息写到标准输出上。
    注意:如果Redis以守护进程方式运行，而设置日志显示到标准输出的话，日志会发送到/dev/null
```
- syslog-enabled yes
- syslog-ident redis
- syslog-facility local0
```
  ssyslog-enabled：要使用系统日志记录器，只要设置 "syslog-enabled" 为 "yes" 就可以了。
  
  syslog-ident：指明syslog身份
  
  syslog-facility：指明syslog的设备。必须是user或LOCAL0 ~ LOCAL7之一。
```

---
### <a id="a_rdb">二、RDB持久化：</a> <a href="#a_base">last</a> <a href="#a_aof">next</a>
1、RDB持久化方式：指在指定的时间间隔内将内存中的数据集快照写入磁盘，也是默认的持久化方式：
- save 900 1
- save 300 10
- save 60 10000
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
- stop-writes-on-bgsave-error yes
- rdbchecksum yes
- dbfilename dump.rdb
- dir data/
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
### <a id="a_aof">三、AOF持久化：</a> <a href="#a_rdb">last</a> <a href="#a_replication">next</a>
1、AOF持久化：会将每一个收到的写命令都通过write函数追加到文件中(默认是 appendonly.aof)：
- appendonly no
- appendfilename "appendonly.aof"
```
  appendonly：默认情况下，Redis是异步的把数据导出到磁盘上。这种模式在很多应用里已经足够好，但Redis进程
出问题或断电时可能造成一段时间的写操作丢失(这取决于配置的save指令)。
  AOF是一种提供了更可靠的替代持久化模式，例如使用默认的数据写入文件策略（参见后面的配置）
在遇到像服务器断电或单写情况下Redis自身进程出问题但操作系统仍正常运行等突发事件时，Redis能只丢失1秒的写操作。
  AOF和RDB持久化能同时启动并且不会有问题。如果AOF开启，那么在启动时Redis将加载AOF文件，它更能保证数据的可靠性。
  参考http://redis.io/topics/persistence

  appendfilename：函数追加的文件名字（默认："appendonly.aof"），此处文件路径默认在bin，可以通过dir参数统一设置。
```
- appendfsync everysec
- auto-aof-rewrite-percentage 100
- auto-aof-rewrite-min-size 64mb
- no-appendfsync-on-rewrite no
- aof-rewrite-incremental-fsync yes
```
  appendfsync：fsync() 系统调用告诉操作系统把数据写到磁盘上，而不是等更多的数据进入输出缓冲区。
有些操作系统会真的把数据马上刷到磁盘上；有些则会尽快去尝试这么做。
  Redis支持三种不同的模式：
    appendfsync no：不要立刻刷，只有在操作系统需要刷的时候再刷。比较快。
    appendfsync always：每次写操作都立刻写入到aof文件。慢，但是最安全。
    appendfsync everysec：每秒写一次。折中方案。 
  默认的 "everysec" 通常来说能在速度和数据安全性之间取得比较好的平衡。根据你的理解来决定，如果你能放宽该配置为"no" 
来获取更好的性能(但如果你能忍受一些数据丢失，可以考虑使用默认的快照持久化模式)，或者相反，用“always”会比较慢但比everysec要更安全。
  参考：http://antirez.com/post/redis-persistence-demystified.html 如果不能确定，就用 "everysec"

  no-appendfsync-on-rewrite：如果AOF的同步策略设置成 "always" 或者 "everysec"，并且后台的存储进程
（后台存储或写入AOF日志）会产生很多磁盘I/O开销。某些Linux的配置下会使Redis因为 fsync()系统调用而阻塞很久。
  注意，目前对这个情况还没有完美修正，甚至不同线程的 fsync() 会阻塞我们同步的write(2)调用。
为了缓解这个问题，可以用这个选项。它可以在 BGSAVE 或 BGREWRITEAOF 处理时阻止fsync()。这就意味着如果有子进程在进行保存操作，
那么Redis就处于"不可同步"的状态。实际上是说，在最差的情况下可能会丢掉30秒钟的日志数据。
（默认Linux设定）如果把这个设置成"yes"带来了延迟问题，就保持"no"，这是保存持久数据的最安全的方式。

  auto-aof-rewrite：自动重写AOF文件
  如果AOF日志文件增大到指定百分比，Redis能够通过 BGREWRITEAOF 自动重写AOF日志文件。
  工作原理：Redis记住上次重写时AOF文件的大小（如果重启后还没有写操作，就直接用启动时的AOF大小）
  这个基准大小和当前大小做比较。如果当前大小超过指定比例，就会触发重写操作。你还需要指定被重写
日志的最小尺寸，这样避免了达到指定百分比但尺寸仍然很小的情况还要重写。
  指定百分比为0会禁用AOF自动重写特性。

  aof-rewrite-incremental-fsync：当一个子进程重写AOF文件时，如果启用该参数，则文件每生成32M数据会被同步。
为了增量式的写入硬盘并且避免大的延迟高峰这个指令是非常有用的
```

---
### <a id="a_replication">四、主从同步：</a> <a href="#a_aof">last</a> <a href="#a_security">next</a>
1、主从同步基本配置：
- slaveof <masterip> <masterport>
- masterauth <master-password>
```
  slaveof：主从同步。通过 slaveof 指令来实现Redis实例的备份。
注意，这里是本地从远端复制数据。也就是说，本地可以有不同的数据库文件、绑定不同的IP、监听不同的端口。

  masterauth：如果master设置了密码保护（通过 "requirepass" 选项来配置），
那么slave在开始同步之前必须进行身份验证，否则它的同步请求会被拒绝。
```
2、slave参数：
- slave-serve-stale-data yes
- slave-read-only yes
- slave-priority 100
```
  slave-serve-stale-data：当一个slave失去和master的连接，或者同步正在进行中，slave的行为有两种可能：
    1、设置为 "yes" (默认值)，slave会继续响应客户端请求，可能是正常数据，也可能是还没获得值的空数据。
    2、设置为 "no"，slave会回复"正在从master同步，SYNC with master in progress）"来处理各种请求，除了 INFO 和 SLAVEOF 命令。

  slave-read-only：可以配置salve实例是否接受写操作。可写的slave实例可能对存储临时数据比较有用
(因为写入salve的数据在同master同步之后将很容被删除)，但是如果客户端由于配置错误在写入时也可能产生一些问题。
  从Redis2.6默认所有的slave为只读
  注意:只读的slave不是为了暴露给互联网上不可信的客户端而设计的。它只是一个防止实例误用的保护层。
一个只读的slave支持所有的管理命令比如config,debug等。为了限制你可以用'rename-command'来隐藏所有的管理和危险命令来增强只读slave的安全性

  slave-priority：slave的优先级是一个整数展示在Redis的Info输出中。如果master不再正常工作了，哨兵将用它来选择一个slave提升=升为master。
优先级数字小的salve会优先考虑提升为master，所以例如有三个slave优先级分别为10，100，25，哨兵将挑选优先级最小数字为10的slave。
0作为一个特殊的优先级，标识这个slave不能作为master，所以一个优先级为0的slave永远不会被哨兵挑选提升为master。
默认优先级为100
```
3、repl参数：
- repl-ping-slave-period 10
- repl-timeout 60
- repl-disable-tcp-nodelay no
- repl-backlog-size 1mb
- repl-backlog-ttl 3600
```
  repl-ping-slave-period：slave根据指定的时间间隔向master发送ping请求。时间间隔可以通过 repl_ping_slave_period 来设置。默认10秒。

  repl-timeout：以下选项设置同步的超时时间
    1、slave在与master SYNC期间有大量数据传输，造成超时
    2、在slave角度，master超时，包括数据、ping等
    3、在master角度，slave超时，当master发送REPLCONF ACK pings
    确保这个值大于指定的repl-ping-slave-period，否则在主从间流量不高时每次都会检测到超时

  repl-disable-tcp-nodelay：是否在slave套接字发送SYNC之后禁用 TCP_NODELAY。
    1、设置为"yes"：Redis将使用更少的TCP包和带宽来向slaves发送数据。但是这将使数据传输到slave上有延迟，Linux内核的默认配置会达到40毫秒
    2、设置为"no"：数据传输到salve的延迟将会减少但要使用更多的带宽默认会为低延迟做优化，但高流量情况或主从之间的跳数过多时，
把这个选项设置为“yes”是个不错的选择。

  repl-backlog-size：设置数据备份的backlog大小。backlog是一个slave在一段时间内断开连接时记录salve数据的缓冲，所以一个slave在重新连接时，
不必要全量的同步，而是一个增量同步就足够了，将在断开连接的这段时间内slave丢失的部分数据传送给它。
同步的backlog越大，slave能够进行增量同步并且允许断开连接的时间就越长。backlog只分配一次并且至少需要一个slave连接

  repl-backlog-ttl：当master在一段时间内不再与任何slave连接，backlog将会释放。以下选项配置了从最后一个slave断开开始计时多少秒后，
backlog缓冲将会释放。0表示永不释放backlog
```
4、min-slaves参数：
- min-slaves-to-write 3
- min-slaves-max-lag 10
```
  如果master少于N个延时小于等于M秒的已连接slave，就可以停止接收写操作。
N个slave需要是“oneline”状态，延时是以秒为单位，并且必须小于等于指定值，是从最后一个从slave接收到的ping（通常每秒发送）开始计数。
  
  例如至少需要3个延时小于等于10秒的slave用下面的指令：
    min-slaves-to-write 3
    min-slaves-max-lag 10
    那么在从服务器的数量少于3个，或者三个从服务器的延迟（lag）值都大于或等于10秒时，主服务器将拒绝执行写命令，
这里的延迟值就是上面提到的INFO replication命令的lag值

  两者之一设置为0将禁用这个功能。
  默认 min-slaves-to-write 值是0（该功能禁用）并且 min-slaves-max-lag 值是10。
```

---
### <a id="a_security">五、安全管理：</a> <a href="#a_replication">last</a> <a href="#a_limits">next</a>
- requirepass foobared
- rename-command CONFIG ""
```
  requirepass：要求客户端在处理任何命令时都要验证身份和密码。这个功能在有你不信任的其它客户端能够访问redis服务器的环境里非常有用。
  为了向后兼容的话这段应该注释掉。而且大多数人不需要身份验证(例如:它们运行在自己的服务器上)
  警告：因为Redis太快了，所以外面的人可以尝试每秒150k的密码来试图破解密码。这意味着需要一个高强度的密码，否则破解太容易了。

  rename-command：命令重命名在共享环境下，可以为危险命令改变名字。
    方式1、你可以为 CONFIG 改个其他不太容易猜到的名字，这样内部的工具仍然可以使用，而普通的客户端将不行。
      例如：rename-command CONFIG b840fc02d524045429941cc15f59e41cb7be6c52
    方式2、也可以通过改名为空字符串来完全禁用一个命令
      rename-command CONFIG ""
    请注意：改变命令名字被记录到AOF文件或被传送到从服务器可能产生问题。
```

---
### <a id="a_limits">六、内存管理：</a> <a href="#a_security">last</a> <a href="#a_advanced">next</a>
- maxclients 10000
- maxmemory <bytes>
- maxmemory-policy volatile-lru
- maxmemory-samples 3
```
  maxclients：设置最多同时连接的客户端数量。默认这个限制是10000个客户端，然而如果Redis服务器不能配置
处理文件的限制数来满足指定的值，那么最大的客户端连接数就被设置成当前文件限制数减32（因为Redis服务器保留了一些文件描述符作为内部使用）
一旦达到这个限制，Redis会关闭所有新连接并发送错误'max number of clients reached'

  maxmemory：不要用比设置的上限更多的内存。一旦内存使用达到上限，Redis会根据选定的回收策略（参见：maxmemmory-policy）删除key。
如果因为删除策略Redis无法删除key，或者策略设置为 "noeviction"，Redis会回复需要更多内存的错误信息给命令。
    例如，SET,LPUSH等等，但是会继续响应像Get这样的只读命令。
  在使用Redis作为LRU缓存，或者为实例设置了硬性内存限制的时候（使用 "noeviction" 策略）的时候，这个选项通常事很有用的。
  警告：当有多个slave连上达到内存上限的实例时，master为同步slave的输出缓冲区所需内存不计算在使用内存中。这样当驱逐key时，
就不会因网络问题 / 重新同步事件触发驱逐key的循环，反过来slaves的输出缓冲区充满了key被驱逐的DEL命令，这将触发删除更多的key，
直到这个数据库完全被清空为止
  总之...如果你需要附加多个slave，建议你设置一个稍小maxmemory限制，这样系统就会有空闲的内存作为slave的输出缓存区
(但是如果最大内存策略设置为"noeviction"的话就没必要了)

  maxmemory-policy：最大内存策略：如果达到内存限制了，Redis如何选择删除key。你可以在下面五个行为里选：
    volatile-lru：默认值，根据LRU算法生成的过期时间来删除。
    allkeys-lru：根据LRU算法删除任何key。
    volatile-random：根据过期设置来随机删除key。 
    allkeys->random：无差别随机删。 
    volatile-ttl：根据最近过期时间来删除（辅以TTL） 
    noeviction：谁也不删，直接在写操作时返回错误。
  注意：对所有策略来说，如果Redis找不到合适的可以删除的key都会在写操作时返回一个错误。
  目前为止涉及的命令：set setnx setex append incr decr rpush lpush rpushx lpushx linsert lset rpoplpush sadd
sinter sinterstore sunion sunionstore sdiff sdiffstore zadd zincrby zunionstore zinterstore hset hsetnx 
hmset hincrby incrby decrby getset mset msetnx exec sort

  maxmemory-samples：LRU和最小TTL算法的实现都不是很精确，但是很接近（为了省内存），所以你可以用样本量做检测。
  例如：默认Redis会检查3个key然后取最旧的那个，你可以通过下面的配置指令来设置样本的个数。
```

---
### <a id="a_advanced">七、高级参数：</a> <a href="#a_security">last</a> <a href="#a_other">next</a>
1、hash、list、set、zset数据类型优化：
- hash-max-ziplist-entries 512
- hash-max-ziplist-value 64
- list-max-ziplist-entries 512
- list-max-ziplist-value 64
- set-max-intset-entries 512
- zset-max-ziplist-entries 128
- zset-max-ziplist-value 64
```
  hash-max-ziplist：当hash只有少量的entry时，并且最大的entry所占空间没有超过指定的限制时，会用一种节省内存的
数据结构来编码。可以通过hash-max-ziplist-entries和hash-max-ziplist-value组合使用

  list-max-ziplist：与hash似，数据元素较少的list，可以用另一种方式来编码从而节省大量空间。
可以通过list-max-ziplist-entries和list-max-ziplist-value组合使用

  set-max-intset-entries：set有一种特殊编码的情况：当set数据全是十进制64位有符号整型数字构成的字符串时。
该配置就是用来设置set使用这种编码来节省内存的最大长度。

  zset-max-ziplist：与hash和list相似，有序集合也可以用一种特别的编码方式来节省大量空间。
这种编码只适合长度和元素都小于下面限制的有序集合，可以通过zset-max-ziplist-entries和zset-max-ziplist-value组合使用
```
2、哈希刷新：
- activerehashing yes
- client-output-buffer-limit <class><hard limit> <soft limit> <soft seconds>
- hz 10
```
  activerehashing：启用哈希刷新，每100个CPU毫秒会拿出1个毫秒来刷新Redis的主哈希表（顶级键值映射表）。
redis所用的哈希表实现（见dict.c）采用延迟哈希刷新机制：你对一个哈希表操作越多，哈希刷新操作就越频繁；
反之，如果服务器是空闲的，那么哈希刷新就不会完成，哈希表就会占用更多的一些内存而已。
  默认是每秒钟进行10次哈希表刷新，用来刷新字典，然后尽快释放内存。
  建议：如果你对延迟比较在意，不能够接受Redis时不时的对请求有2毫秒的延迟的话，就用"activerehashing no"，
如果不太在意延迟而希望尽快释放内存就设置"activerehashing yes"

  client-output-buffer-limit：客户端的输出缓冲区的限制，可用于强制断开那些因为某种原因从服务器读取数据的速度不够快的客户端，
一个常见的原因是一个发布/订阅客户端消费消息的速度无法赶上生产它们的速度。
   1、可以对三种不同的客户端设置不同的限制：
     normal：正常客户端
     slave：slave和 MONITOR 客户端
     pubsub：至少订阅了一个pubsub channel或pattern的客户端
  2、一旦达到硬限制客户端会立即被断开，或者达到软限制并持续达到指定的秒数（连续的）。
  例如，如果硬限制为32兆字节和软限制为16兆字节/10秒，客户端将会立即断开，如果输出缓冲区的大小达到32兆字节，
或客户端达到16兆字节并连续超过了限制10秒，就将断开连接。
  默认normal客户端不做限制，因为他们在不主动请求时不接收数据（以推的方式），只有异步客户端可能会出现请求数据的速度比它可以读取的速度快的场景。
  pubsub和slave客户端会有一个默认值，因为订阅者和slaves以推的方式来接收数据
  3、把硬限制和软限制都设置为0来禁用该功能
  client-output-buffer-limit normal 0 0 0
  client-output-buffer-limit slave 256mb 64mb 60
  client-output-buffer-limit pubsub 32mb 8mb 60

  hz：Redis调用内部函数来执行许多后台任务，如关闭客户端超时的连接，清除未被请求过的过期Key等等。
不是所有的任务都以相同的频率执行，但Redis依照指定的“hz”值来执行检查任务。
  默认情况下，“hz”的被设定为10。提高该值将在Redis空闲时使用更多的CPU时，但同时当有多个key
同时到期会使Redis的反应更灵敏，以及超时可以更精确地处理。
  范围是1到500之间，但是值超过100通常不是一个好主意。大多数用户应该使用10这个默认值，只有在非常低的延迟要求时有必要提高到100。
```
3、其他：
- glueoutputbuf yes
```
  glueoutputbuf：设置在向客户端应答时，是否把较小的包合并为一个包发送，默认为开启
```

---
### <a id="a_other">八、其他参数：</a> <a href="#a_advanced">last</a> <a href="#a_down">next</a>
1、Lua脚本：
- lua-time-limit 5000
```
  lua-time-limit：Lua 脚本的最大执行时间，毫秒为单位
如果达到了最大的执行时间，Redis将要记录在达到最大允许时间之后一个脚本仍然在执行，并且将开始对查询进行错误响应。

  当一个长时间运行的脚本超过了最大执行时间，只有 SCRIPT KILL 和 SHUTDOWN NOSAVE 两个命令可用。
第一个可以用于停止一个还没有调用写命名的脚本。第二个是关闭服务器唯一方式，当

  写命令已经通过脚本开始执行，并且用户不想等到脚本的自然终止。
  设置成0或者负值表示不限制执行时间并且没有任何警告
```
2、显示日志：
- slowlog-log-slower-than 10000
- slowlog-max-len 128
```
  slowlog-log-slower-than：Redis慢查询日志可以记录超过指定时间的查询。运行时间不包括各种I/O时间，
例如：连接客户端，发送响应数据等，而只计算命令执行的实际时间（这只是线程阻塞而无法同时为其他请求服务的命令执行阶段）
  可以为慢查询日志配置两个参数:一个指明Redis的超时时间(单位为微秒)来记录超过这个时间的命令
另一个是慢查询日志长度。当一个新的命令被写进日志的时候，最老的那个记录从队列中移除。
  时间单位是微秒，所以1000000就是1秒。注意，负数时间会禁用慢查询日志，而0则会强制记录所有命令。

  slowlog-max-len：这个长度没有限制。只是要主要会消耗内存。你可以通过 SLOWLOG RESET 来回收内存。
```
3、发布通知：
- notify-keyspace-events ""
```
  notify-keyspace-events：Redis 能通知 Pub/Sub 客户端关于键空间发生的事件。可以参考：http://redis.io/topics/keyspace-events
  
  例如：如果键空间事件通知被开启，并且客户端对 0 号数据库的键 foo 执行 DEL 命令时，将通过
    Pub/Sub发布两条消息：
    PUBLISH __keyspace@0__:foo del
    PUBLISH __keyevent@0__:del foo

    可以在下表中选择Redis要通知的事件类型。事件类型由单个字符来标识：
      K    键空间通知，以__keyspace@<db>__为前缀
      E    键事件通知，以__keysevent@<db>__为前缀
      g    DEL , EXPIRE , RENAME 等类型无关的通用命令的通知, ...
      $    String命令
      l    List命令
      s    Set命令
      h    Hash命令
      z    有序集合命令
      x    过期事件（每次key过期时生成）
      e    驱逐事件（当key在内存满了被清除时生成）
      A    g$lshzxe的别名，因此”AKE”意味着所有的事件


  notify-keyspace-events 带一个由0到多个字符组成的字符串参数。空字符串意思是通知被禁用。
  如1：启用List和通用事件通知：notify-keyspace-events Elg
  如2：为了获取过期key的通知订阅名字为 __keyevent@__:expired 的频道，用以下配置：notify-keyspace-events Ex

  默认所用的通知被禁用，因为用户通常不需要该特性，并且该特性会有性能损耗。
  注意如果不指定至少K或E之一，不会发送任何事件。
```
3、vm虚拟机参数：
- vm-enabled no
- vm-swap-file /tmp/redis.swap
- vm-max-memory 0
- vm-page-size 32
- vm-pages 134217728
- vm-max-threads 4
```
  vm-enabled：指定是否启用虚拟内存机制，默认值为no，简单的介绍一下，VM机制将数据分页存放，
由Redis将访问量较少的页即冷数据swap到磁盘上，访问多的页面由磁盘自动换出到内存中

  vm-swap-file：虚拟内存文件路径，默认值为/tmp/redis.swap，不可多个Redis实例共享

  vm-max-memory：将所有大于vm-max-memory的数据存入虚拟内存,无论vm-max-memory设置多小,所有索引数据都是内存存储的(Redis的索引数据 就是keys),
也就是说,当vm-max-memory设置为0的时候,其实是所有value都存在于磁盘。默认值为0
  
  vm-page-size：Redis swap文件分成了很多的page，一个对象可以保存在多个page上面，但一个page上不能被多个对象共享，
vm-page-size是要根据存储的 数据大小来设定的，作者建议如果存储很多小对象，page大小最好设置为32或者64bytes；
如果存储很大大对象，则可以使用更大的page，如果不 确定，就使用默认值
 
  vm-pages：设置swap文件中的page数量，由于页表（一种表示页面空闲或使用的bitmap）是在放在内存中的，，在磁盘上每8个pages将消耗1byte的内存。
  
  vm-max-threads：设置访问swap文件的线程数,最好不要超过机器的核数,如果设置为0,那么所有对swap文件的操作都是串行的，
可能会造成比较长时间的延迟。默认值为4
```

---
<a id="a_down"></a>  
<a href="#a_top">Top</a> 
<a href="#a_catalogue">Catalogue</a>