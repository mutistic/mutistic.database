# <a id="a_top">Redis：Key-Value内存数据库</a>
[Redis中文网](http://www.Redis.cn)  
[Redis操作命令详解](http://doc.redisfans.com)  
[Redis文档](http://www.redis.cn/documentation.html)   
[Redis持久化机制](https://www.cnblogs.com/xingzc/p/5988080.html)  
[Redis文档-持久化](http://redis.io/topics/persistence)  
[Redis文档-发布/通知](http://redis.io/topics/keyspace-events)  
[附录A：Redis操作命令速查表](https://github.com/mutistic/mutistic.database/blob/master/com.mutistic.database.redis/redis-command.md)  
[附录B：Redis配置文件说明](https://github.com/mutistic/mutistic.database/blob/master/com.mutistic.database.redis/redis-conf.md)   

|作者|Mutistic|
|---|---|
|邮箱|mutistic@qq.com|

---
### <a id="a_catalogue">目录</a>：
1. <a href="#a_redis">Redis：Key-Value内存数据库</a>
2. <a href="#a_nosql">NoSQL简介</a>
3. <a href="#a_string">String类型</a>
4. <a href="#a_hash">hash类型</a>
5. <a href="#a_list">list类型</a>
6. <a href="#a_set">set(集合)</a>
7. <a href="#a_zset">zset(sorted set，有序集合)</a>
8. <a href="#a_common">常用操作命令</a>
9. <a href="#a_transaction">事务、持久化、发布订阅</a>
10. <a href="#a_jedis">Jedis：Redis的Java版本的客户端实现</a>
11. <a href="#a_jString">使用Jedis API操作String数据类型</a>
12. <a href="#a_jHash">使用Jedis API操作Hash数据类型</a>
13. <a href="#a_jList">使用Jedis API操作List数据类型</a>
14. <a href="#a_jSet">使用Jedis API操作Set数据类型</a>
15. <a href="#a_jZSet">使用Jedis API操作ZSet数据类型</a>
16. <a href="#a_jDatabase">使用Jedis API操作数据库</a>
17. <a href="#a_jExpiration">使用Jedis API管理过期时间</a>
18. <a href="#a_jTransaction">使用Jedis API管理事务</a>
19. <a href="#a_jPubSub">使用Jedis API管理发布与订阅</a>
20. <a href="#a_junit">使用JUnit简单操作Redis</a>
20. <a href="#a_springboot">使用SpringBoot中使用Redis</a>
96. <a href="#a_appendix1">附录A：Redis操作命令速查表</a>
97. <a href="#a_appendix2">附录B：Redis配置文件说明</a>
98. <a href="#a_notes">Notes</a>
99. <a href="#a_down">down</a>

---
### <a id="a_redis">一、Redis：Key-Value内存数据库：</a> <a href="#a_catalogue">last</a> <a href="#a_nosql">next</a>
[Redis官网](http://www.Redis.io)  
一、什么是Redis：
```
  Redis是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。
从2010年3月15日起，Redis的开发工作由VMware主持。从2013年5月开始，Redis的开发由Pivotal赞助。

  Redis的作者：Salvatore Sanfilippo，来自意大利的西西里岛，现在居住在卡塔尼亚。目前供职于Pivotal公司。他使用的网名是antirez。
  
  目前，Vmware在资助着Redis项目的开发和维护
```
二、定义：
```
  Redis是一个key-value存储系统，是NoSQL的一种。和传统的关系型数据库不一样，不一定遵循传统数据库的一些基本要求：
非关系型的、分布式的、开源的、水平可扩展的
  
  和Memcached类似，它支持存储的value类型相对更多，包括string(字符串)、list(链表)、set(集合)、zset(sorted set --有序集合)和hash（哈希类型）。
这些数据类型都支持push/pop、add/remove及取交集并集和差集及更丰富的操作，而且这些操作都是原子性的。
在此基础上，Redis支持各种不同方式的排序。与memcached一样，为了保证效率，数据都是缓存在内存中。
区别的是Redis会周期性的把更新的数据写入磁盘或者把修改操作写入追加的记录文件，并且在此基础上实现了master-slave(主从)同步。
  
  Redis 是一个高性能的key-value数据库。 Redis的出现，很大程度补偿了memcached这类key/value存储的不足，在部分场合可以对关系数据库起到很好的补充作用。
它提供了Java，C/C++，C#，PHP，JavaScript，Perl，Object-C，Python，Ruby，Erlang等客户端，使用很方便。 

  Redis支持主从同步。数据可以从主服务器向任意数量的从服务器上同步，从服务器可以是关联其他从服务器的主服务器。这使得Redis可执行单层树复制。
存盘可以有意无意的对数据进行写操作。由于完全实现了发布/订阅机制，使得从数据库在任何地方同步树时，可订阅一个频道并接收主服务器完整的消息发布记录。
同步对读取操作的可扩展性和数据冗余很有帮助。
```
三、常用命令：  
[Redis操作命令详解](http://doc.redisfans.com/)  
```
  Redis的根本是使用内存存储，持久化的关键是这三条指令：SAVE BGSAVE LASTSAVE 
  当接收到SAVE指令的时候，Redis就会dump数据到一个文件里面。值得一说的是它的独家功能：存储列表和集合，这是它与memcached相比更有竞争力的地方。

  TYPE key — 用来获取某key的类型
  KEYS pattern — 匹配所有符合模式的key，比如KEYS * 就列出所有的key了，复杂度O(n)
  RANDOMKEY - 返回随机的一个key
  RENAME oldkeynewkey— key也可以改名
```
四、数据模型：
```
  Redis的外围由一个键、值映射的字典构成。键是区分大小写的。与其他非关系型数据库主要不同在于：Redis中值的类型不仅限于字符串，
还支持如下抽象数据类型：
  1、字符串列表
  2、无序不重复的字符串集合
  3、有序不重复的字符串集合
  4、键、值都为字符串的哈希表

  值的类型决定了值本身支持的操作。Redis支持不同无序、有序的列表，无序、有序的集合间的交集、并集等高级服务器端原子操作
```
五、数据结构：
```
  Redis提供五种数据类型：String，hash，list，set及zset(sorted set)。

  1、String（字符串）：
  String是最简单的类型，可以理解成与Memcached一模一样的类型，一个key对应一个value，其上支持的操作与Memcached的操作类似。但它的功能更丰富

  2、list(双向链表)：
  List是一个链表结构，主要功能是push、pop、获取一个范围的所有值等等。操作中key理解为链表的名字

  3、hash(哈希表)：
  包含键值对无序散列表，添加，获取，移除当键值对，获取所有键值对

  4、set(集合)：
  Set是集合，和数学中的集合概念相似，对集合的操作有添加删除元素，有对多个集合求交并差等操作。操作中key理解为集合的名字
  包含字符串的无序收集器(unordered collection)、并且被包含的每个字符串都是独一无二的。添加，获取，移除单个元素，检查一个元素是否存在于集合中，
计算交集，并集，差集，从集合里面随机获取元素

  5、zset(排序set，有序集合)：
  zset是set的一个升级版本，他在set的基础上增加了一个顺序属性，这一属性在添加修改元素的时候可以指定，每次指定后，zset会自动重新按新的值调整顺序。可以理解了有两列的mysql表，一列存value，一列存顺序。操作中key理解为zset的名字
```
六、优缺点：  
[ACID-数据库事务正确执行的四个基本要素](https://baike.baidu.com/item/acid/10738)
```
  1、优点：
  对数据高并发读写，支持读写分离。
  对海量数据的高效率存储和访问
  对数据的可扩展性和高可用性，比如：主从模式、哨兵模式(2.x版本)、集群模式（3.x版本）

  2、缺点：
  Redis的ACID处理非常简单，无法做到太复杂的关系数据库模型。
```
七、简单使用Redis：
```
  1、Redis下载：http://www.redis.cn/download.html
  2、Redis客户端：Redis Desktop Manager：https://redisdesktop.com/download
  3、Redis配置文件：redis/rredis.conf
    daemonize：默认Rdis不会作为守护进程运行。如果需要的话配置成'yes'
    port：接受连接的特定端口，默认是6379
    bind：默认Redis监听服务器上所有可用网络接口的连接，默认127.0.0.1。
      ps：当设置的ip地址于当前ip不一致时启动服务端会报错：
        [12540] 30 Nov 15:56:39.223 # Creating Server TCP listening socket 192.168.16.113:6379: bind: No error
    timeout：一个客户端空闲多少秒后关闭连接。(0代表禁用，永不关闭)
    logfile：指明日志文件名，eg：logs/redis.log
    databases：设置数据库个数。默认数据库是 DB 0
    dir：持久化数据库的文件名，eg: data/

  4、Redis服务端启动脚本下： 
    Windows下执行：redis/redis-server.exe
    Windows-CMD命令，进入redis目录：redis-server.exe redis.conf
    Linux或Windows：（server服务 redis配置文件）：/usr/local/redis/redis-server /usr/local/redis/redis.conf
  5、Redis服务端启动脚本-windos下： redis/redis-cli.exe
    Windows下执行：redis/redis-cli.exe
    Windows-CMD命令，进入redis目录：redis-cli.exe -h 192.168.16.113 -p 6379 --raw
    Linux：（cli客户端 -h IP地址 -p 端口号[默认6379]）：/usr/local/redis/redis-cli -h 192.168.16.113 -p 6379
    ps：解决中文乱码的问题可以在redis-cli 后加载 --raw
  6、查看Redis版本信息：启动服务端时可以看到 或 在客户端输入命令：  info
  7、客户端操作key：【查看key：keys *】【添加key： set key value】【获取key的值： get key】【删除key：del key】
  10、关闭Redis服务：
    Windows下：客户端执行命令：shutdown
    Linux下（cli客户端 -h IP地址 -p 端口号[默认6379] shutdown）：/usr/local/redis/redis-cli -h 192.168.16.113 -p 6379 shutdown
```
```
  守护进程（Daemon Process），也就是通常说的 Daemon 进程（精灵进程），
是 Linux 中的后台服务进程。它是一个生存期较长的进程，通常独立于控制终端并且周期性地执行某种任务或等待处理某些发生的事件。

  守护进程是个特殊的孤儿进程，这种进程脱离终端，为什么要脱离终端呢？
之所以脱离于终端是为了避免进程被任何终端所产生的信息所打断，其在执行过程中的信息也不在任何终端上显示。
由于在 linux 中，每一个系统与用户进行交流的界面称为终端，每一个从此终端开始运行的进程都会依附于这个终端，
这个终端就称为这些进程的控制终端，当控制终端被关闭时，相应的进程都会自动关闭
```

---
### <a id="a_nosql">二、介绍NoSQL：</a> <a href="#a_redis">last</a> <a href="#a_string">next</a>
[NoSQL-百度百科](https://baike.baidu.com/item/NoSQL)  
一、什么是NoSQL：
```
  NoSQL(NoSQL = Not Only SQL )，意即“不仅仅是SQL”，泛指非关系型的数据库，比如Redis、HBase、MongoDB。
关系型数据库常见的如Oracle、MySQL。
```
二、NoSQL数据库的四大分类：
```
  1、键值(Key-Value)存储数据库：
  这一类数据库主要会使用到一个哈希表，这个表中有一个特定的键和一个指针指向特定的数据。Key/value模型对于IT系统来说的优势在于简单、易部署。
但是如果DBA只对部分值进行查询或更新的时候，Key/value就显得效率低下了。如：Tokyo Cabinet/Tyrant, Redis, Voldemort, Oracle BDB.

  2、列存储数据库：
  这部分数据库通常是用来应对分布式存储的海量数据。键仍然存在，但是它们的特点是指向了多个列。这些列是由列家族来安排的。如：Cassandra, HBase, Riak.

  3、文档型数据库：
  文档型数据库的灵感是来自于Lotus Notes办公软件的，而且它同第一种键值存储相类似。该类型的数据模型是版本化的文档，半结构化的文档以特定的格式存储，比如JSON。
文档型数据库可 以看作是键值数据库的升级版，允许之间嵌套键值。而且文档型数据库比键值数据库的查询效率更高。
如：CouchDB, MongoDb. 国内也有文档型数据库SequoiaDB，已经开源。

  4、图形(Graph)数据库：
  图形结构的数据库同其他行列以及刚性结构的SQL数据库不同，它是使用灵活的图形模型，并且能够扩展到多个服务器上。
NoSQL数据库没有标准的查询语言(SQL)，因此进行数据库查询需要制定数据模型。许多NoSQL数据库都有REST式的数据接口或者查询API。
如：Neo4J, InfoGrid, Infinite Graph.
```
三、NoSQL数据库的四大分类表格分析：

|分类|Examples举例|典型应用场景|数据模型|优点|缺点|
|---|---|---|---|---|---|
|键值（key-value）|Tokyo Cabinet/Tyrant, Redis, Voldemort, Oracle BDB|内容缓存，主要用于处理大量数据的高访问负载，也用于一些日志系统等等。|Key 指向 Value 的键值对，通常用hash table来实现|查找速度快|数据无结构化，通常只被当作字符串或者二进制数据|
|列存储数据库|Cassandra, HBase, Riak|分布式的文件系统|以列簇式存储，将同一列数据存在一起|查找速度快，可扩展性强，更容易进行分布式扩展|功能相对局限|
|文档型数据库|CouchDB, MongoDb|Web应用（与Key-Value类似，Value是结构化的，不同的是数据库能够了解Value的内容）|Key-Value对应的键值对，Value为结构化数据|数据结构要求不严格，表结构可变，不需要像关系型数据库一样需要预先定义表结构|查询性能不高，而且缺乏统一的查询语法。|
|图形(Graph)数据库|Neo4J, InfoGrid, Infinite Graph|社交网络，推荐系统等。专注于构建关系图谱|图结构|利用图结构相关算法。比如最短路径寻址，N度关系查找等|很多时候需要对整个图做计算才能得出需要的信息，而且这种结构不太好做分布式的集群方案。|

四、共同特征：
```
  对于NoSQL并没有一个明确的范围和定义，但是他们都普遍存在下面一些共同特征：
  1、不需要预定义模式：不需要事先定义数据模式，预定义表结构。数据中的每条记录都可能有不同的属性和格式。当插入数据时，并不需要预先定义它们的模式。
  
  2、无共享架构：相对于将所有数据存储的存储区域网络中的全共享架构。NoSQL往往将数据划分后存储在各个本地服务器上。
因为从本地磁盘读取数据的性能往往好于通过网络传输读取数据的性能，从而提高了系统的性能。

  3、弹性可扩展：可以在系统运行的时候，动态增加或者删除结点。不需要停机维护，数据可以自动迁移。
  
  4、分区：相对于将数据存放于同一个节点，NoSQL数据库需要将数据进行分区，将记录分散在多个节点上面。并且通常分区的同时还要做复制。
这样既提高了并行性能，又能保证没有单点失效的问题。
  
  5、异步复制：和RAID存储系统不同的是，NoSQL中的复制，往往是基于日志的异步复制。这样，数据就可以尽快地写入一个节点，而不会被网络传输引起迟延。
缺点是并不总是能保证一致性，这样的方式在出现故障的时候，可能会丢失少量的数据。
  
  6、BASE：相对于事务严格的ACID特性，NoSQL数据库保证的是BASE特性。BASE是最终一致性和软事务。
  
  NoSQL数据库并没有一个统一的架构，两种NoSQL数据库之间的不同，甚至远远超过两种关系型数据库的不同。可以说，NoSQL各有所长，
成功的NoSQL必然特别适用于某些场合或者某些应用，在这些场合中会远远胜过关系型数据库和其他的NoSQL
```
五、适用场景：
```
  NoSQL数据库在以下的这几种情况下比较适用：
  1、数据模型比较简单；
  2、需要灵活性更强的IT系统；
  3、对数据库性能要求较高；
  4、不需要高度的数据一致性；
  5、对于给定key，比较容易映射复杂值的环境
```
六、发展现状：
```
  计算机体系结构在数据存储方面要求具备庞大的水平扩展性，而NoSQL致力于改变这一现状。Google的 BigTable 和Amazon 的Dynamo使用的就是NoSQL型数据库。

  NoSQL项目的名字上看不出什么相同之处，但是，它们通常在某些方面相同：它们可以处理超大量的数据。

  这场革命仍然需要等待。的确，NoSQL对大型企业来说还不是主流，但是，一两年之后很可能就会变个样子。在NoSQL运动的最新一次聚会中，
来自世界各地的150人挤满了CBS Interactive的一间会议室。分享他们如何推翻缓慢而昂贵的关系数据库的暴增的经验，怎样使用更有效和更便宜的方法来管理数据。

  “关系型数据库给你强加了太多东西。它们要你强行修改对象数据，以满足RDBMS （relational database management system，关系型数据库管理系统）的需要，”
在NoSQL拥护者们看来，基于NoSQL的替代方案“只是给你所需要的”。

  水平扩展性(horizontal scalability)指能够连接多个软硬件的特性,这样可以将多个服务器从逻辑上看成一个实体。
```
七、挑战：
```
  尽管大多数NoSQL数据存储系统都已被部署于实际应用中，但归纳其研究现状，还有许多挑战性问题。
  1、已有key-value数据库产品大多是面向特定应用自治构建的，缺乏通用性；
  2、已有产品支持的功能有限（不支持事务特性），导致其应用具有一定的局限性；
  3、已有一些研究成果和改进的NoSQL数据存储系统，但它们都是针对不同应用需求而提出的相应解决方案，如支持组内事务特性、弹性事务等，很少从全局考虑系统的通用性，也没有形成系列化的研究成果；
  4、缺乏类似关系数据库所具有的强有力的理论(如armstrong公理系统)、技术（如成熟的基于启发式的优化策略、两段封锁协议等）、标准规范（如SQL语言）的支持。
  5、目前，HBase数据库是安全特性最完善的NoSQL数据库产品之一，而其他的NoSQL数据库多数没有提供内建的安全机制，但随着NoSQL的发展，越来越多的人开始意识到安全的重要，部分NoSQL产品逐渐开始提供一些安全方面的支持。
  
  随着云计算、互联网等技术的发展，大数据广泛存在，同时也呈现出了许多云环境下的新型应用，如社交网络网、移动服务、协作编辑等。
这些新型应用对海量数据管理或称云数据管理系统也提出了新的需求，如事务的支持、系统的弹性等。
同时云计算时代海量数据管理系统的设计目标为可扩展性、弹性、容错性、自管理性和“强一致性”。
目前，已有系统通过支持可随意增减节点来满足可扩展性；通过副本策略保证系统的容错性；基于监测的状态消息协调实现系统的自管理性。
“弹性”的目标是满足Pay-per-use 模型，以提高系统资源的利用率。
该特性是已有典型NoSQL数据库系统所不完善的，但却是云系统应具有的典型特点；“强一致性”主要是新应用的需求。
```

---
### <a id="a_string">三、String类型：</a> <a href="#a_nosql">last</a> <a href="#a_hash">next</a>
一、String（字符串）：
```
  string 是Redis 最基本的类型，你可以理解成与 Memcached 一模一样的类型，一个 key 对应一个 value。
  string 类型是二进制安全的。意思是Redis 的 string 可以包含任何数据。比如jpg图片或者序列化的对象。
  string 类型是Redis 最基本的数据类型，string 类型的值最大能存储 512MB。 
```
二、设置和获取方法：
```
  SET key value [EX seconds] [PX milliseconds] [NX/XX]：为字符串键设置值和过期时间（可选），设置多次会覆盖之前的值
    [EX seconds]：可选参数：设置参数过期时间，单位秒，不设置代表不过期
    [PX milliseconds]：可选参数：设置参数过期时间，单位毫秒。EX PX 同时存在时，优先级高于EX，不设置代表不过期
    [NX/XX]：可选参数：设置方式
      1、NX 当 key 不存在时进行设置，key不存在设置成功返回OK，key存在设置失败返回(nil)
      2、XX 当 key 存在时进行设置，key存在设置成功返回OK，key不存在设置失败返回(nil)

  GET key：获取字符串键的值，不存在或过期后，返回(nil)
  GETSET key new-value：为字符串键设置新值，并返回键被设置之前的旧值，若key不存在，等同于set key，但是返回(nil)
  DEL key：删除字符串键，key存在时返回(integer) 1，key不存在时返回(integer) 0

  SETNX key value：仅在字符串键尚未有值的情况下，为它设置值，SET key value NX的简写
  SETEX key seconds value：为字符串键设置值和秒级精度的过期时间，SET key value EX seconds的简写
  PSETEX key milliseconds value：为字符串键设置值和毫秒级精度的过期时间，SET key value PX milliseconds的简写  

  TTL key：查看key的过期时间，单位秒。如不过期返回(integer) -1，已过期或不存在返回(integer) -2
  PTTL key：查看key的过期时间，单位毫秒。如不过期返回(integer) -1，已过期或不存在返回(integer) -2
```

三、批量设置与获取：
```
  MSET key value [key2 value ...]：一次为多个字符串键设置值
  MGET key [key2 ...]：一次获取多个字符串键的值
  MSETNX key value [key2 value ...]：仅在所有给定字符串键都尚未有值的情况下，为它们设置值。
    设置成功返回(integer) 1，设置不成功返回(integer) 0
    不支持批量设置过期时间，即没有MSETEX命令。当批量设置值时，会忽略指定的 EX PX属性
```

四、获取或修改内容：
```
  STRLEN key：获取字符串值的长度，如key不存在或为空串，则返回 (integer) 0
  SETRANGE key offset value：对字符串值在指定索引位置上的内容进行修改，返回字符串的长度。若key 不存在，则等同于set key 
  GETRANGE key start end：获取字符串值在指定索引范围内的内容，索引从0开始，若key不存在，则返回空串
  APPEND key value：将指定的内容追加到字符串值的末尾，返回字符串的长度。若key不存在，则等同于 set key
```

五、自增与自减：
```
  INCR key：为字符串键储存的整数值加上一。若key不存在，等同于 set 1 1，若key不为整数，这返回错误
  DECR key：为字符串键储存的整数值减去一。若key不存在，等同于 set 1 -1，若key不为整数，这返回错误
  INCRBY key increment：为字符串键储存的整数值加上指定的整数增量。若key不存在，等同于 set key increment，若key不为整数，这返回错误
  DECRBY key decrement：为字符串键储存的整数值减去指定的整数减量。若key不存在，等同于 set key decrement，若key不为整数，这返回错误
  INCRBYFLOAT key increment：为字符串键储存的数字值加上指定的浮点数增量。
    若key不存在，等同于 set key increment，若key不为数值，这返回错误
    没有减去浮点数的命令，若想减去指定浮点数，则使用 负数：INCRBYFLOAT key -increment
```

---
### <a id="a_hash">四、hash类型：</a> <a href="#a_string">last</a> <a href="#a_list">next</a>
一、Hash（哈希表）：
```
  Redis hash类型：是一个string类型的field和value的映射表，或者说一个String集合。类似Java的HashMap数据类型。
hash特别适合用于存储对象。
  相比较而言，将一个对象类型存储在Hashs类型要比存储在String类型里占用更少的内存空间，并方便存取整个对象。
  Redis 中每个 hash 可以存储 2的32次方-1 键值对（40多亿）
```
二、设置与获取：
```
  HSET hash key value：为散列中的键设置值。
    如果key不存在时，返回(integer) 1， key 存在时返回(integer)0，但是value会覆盖。
  HSETNX hash key value：仅在散列中的给定键尚未有值的情况下，为该键设置值。
    当 key 不存在时进行设置，key不存在设置成功返回1，key存在设置失败返回0，没有HSETXX命令。
  HGET hash key：返回散列中与给定键相关联的值
  HMSET hash key value [key2 value]：一次为散列中的多个键设置值
  HMGET hash key [key2 ...]：一次获取散列中多个键的值
```
三、自增与自减：
```
  HINCRBY hash key increment：为散列中给定键储存的整数值加上指定的整数增量，同INCRBY，减法使用负数即可
  HINCRBYFLOAT hash key increment：为散列中给定键储存的数字值加上指定的浮点数增量，同INCRBYFLOAT，减法使用负数即可
```
四、检测与管理：  
[SCAN命令的用法](https://blog.csdn.net/qq_34579060/article/details/80451298)
```
  HEXISTS hash key：检查给定键在散列中是否存在，存在返回1，不存在返回0
  HLEN hash：返回散列包含的键值对数量。hash不存在时返回0
  HDEL hash key [key2 ...]：删除散列中的一个或多个键，以及这些键的值批量获取散列键值。hash或key不存在时，返回0
  HKEYS hash：返回散列包含的所有键。hash不存在时，返回(empty list or set)
  HVALS hash：返回散列包含的所有键的值。hash不存在时，返回(empty list or set)
  HGETALL hash：返回散列包含的所有键值对。hash不存在时，返回(empty list or set)
  
  HSCAN hash cursor [MATCH pattern] [COUNT count]：以渐进的方式返回散列包含的键值对。
    SCAN命令：是一个基于游标的迭代器，这意味着命令每次被调用都需要使用上一次这个调用返回的游标作为该次调用的游标参数，以此来延续之前的迭代过程，
    SCAN命令：的返回值 是一个包含两个元素的数组， 第一个数组元素是用于进行下一次迭代的新游标， 而第二个数组元素则是一个数组， 这个数组中包含了所有被迭代的元素
    cursor：游标参数，被设置为 0 时。服务器将开始一次新的迭代， 而当服务器向用户返回值为 0 的游标时， 表示迭代已结束。
    [MATCH pattern]：让命令只返回和给定模式相匹配的元素，即模糊匹配。
    [COUNT count]：选项的作用就是让用户告知迭代命令， 在每次迭代中应该从数据集里返回多少元素
```
五、redis.conf配置优化：
```
  # 当hash只有少量的entry时，并且最大的entry所占空间没有超过指定的限制时，会用一种节省内存的
  # 数据结构来编码。可以通过下面的指令来设定限制
  hash-max-ziplist-entries 512
  hash-max-ziplist-value 64
```

---
### <a id="a_list">五、list类型：</a> <a href="#a_hash">last</a> <a href="#a_set">next</a>
一、List（列表）：
```
  Redis列表是一个双向链表结构的集合。简单的字符串列表，按照插入顺序排序。可以添加一个元素到列表的头部（左边）或者尾部（右边）
List设计的非常简单精巧，既可以作为栈，又可以作为队列。

  一个列表最多可以包含 232 - 1 个元素 (4294967295, 每个列表超过40亿个元素)
```
二、添加元素：
```
  LPUSH list item [item ...]：将一个或多个元素添加到列表的左端。从头部加入元素（栈）先进后出。返回list的长度。
  LPUSHX list item：仅在列表已经存在的情况下，将一个元素添加到列表的左端。返回list的长度。
  RPUSH list item [item ...]：将一个或多个元素添加到列表的右端。从尾部加入元素（队列）先进先出。返回list的长度。
  RPUSHX list item：仅在列表已经存在的情况下，将一个元素添加到列表的右端。返回list的长度。
```
三、移除元素：
```
  LPOP list：移除并返回列表左端第一个元素。返回被移除元素的值
  RPOP list：移除并返回列表右端第一个元素。返回被移除元素的值
  BLPOP list [list ...] timeout：在指定的时限内，移除首个非空列表的最左端元素
  BRPOP list [list ...] timeout：在指定的时限内，移除首个非空列表的最右端元素
  RPOPLPUSH source_list target_list：移除源列表的最右端元素，并将该元素添加到目标列表的左端
  BRPOPLPUSH source_list target_list timeout：在指定的时限内，尝试移除源列表的最右端元素，并将该元素添加到目标列表的左端
```
四、元素的获取与管理：
```
  LINDEX list index：获取列表在给定索引上的元素。
    从左到右的索引从0开始：LINDEX list 0，从右到左的索引从-1开始：LINDEX list -1

  LLEN list：返回列表包含的元素数量
  LRANGE list start end：返回列表在指定索引范围内的所有元素。
    从左到右的索引从0开始：LRANGE list 0 2，从右到左的索引从-1开始：LRANGE list -1 -3

  LINSERT list BEFORE|AFTER target item：将给定的元素插入到目标元素的前面或者后面。目标元素不存在时，插入不成功。
    BEFORE|AFTER：插入规则。BEFORE：插入到目标元素前面。AFTER：插入到目标元素后面。

  LREM list count item：根据参数COUNT的值，移除列表中与参数VALUE相等的元素。列表不存在或元素不存在返回(integer) 0
    count：移除元素的规则。
    count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
    count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
    count = 0 : 移除表中所有与 VALUE 相等的值。

  LSET list index item：把列表在指定索引上的值修改为给定的元素。
    当索引参数超出范围，或对一个空列表进行 LSET 时，返回一个错误。list的索引规则一致。
  
  LTRIM list start end：对列表进行截断，只保留指定索引范围内的元素。当指定索引不存在时，不会报错。
```

---
### <a id="a_set">六、set(集合)：</a> <a href="#a_list">last</a> <a href="#a_zset">next</a>
一、set(集合)：
```
  Redis 的 Set 是 String 类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据。
Redis 中集合是通过hashtable(哈希表)实现的，所以添加，删除，查找的复杂度都是 O(1)。

  集合中最大的成员数为 232 - 1 (4294967295, 每个集合可存储40多亿个成员)。 
```
二、元素的添加与移除：
```
  SADD set element [element ...]：将一个或多个元素添加到集合当中。
    返回插入element的数量。element存在重复时，将不会插入到集合中
  
  SPOP set：随机地移除并返回集合中的某个元素。删除不存在的集合或集合为空fil时返回(nil)

  SMOVE source_set target_set element：将指定的元素从源集合移动到目标集合。
    源集合或指定元素不存在时，不会报错，返回(integer)0

  SREM set element [element ...]：移除集合中的一个或多个元素。元素不存在是返回(integer)0sc
```
三、元素的获取与检测：
```
  SCARD set：返回集合包含的元素数量
  SISMEMBER set element：检查集合是否包含了给定的元素。存在时返回(integer)1，不存在时返回(integer)0
  
  SRANDMEMBER set [count]：随机地返回集合包含的元素
   [count]：可选参数：随机返回元素的规则
    如果 count 为0，这会报错，报错信息：(empty list or set)
    如果 count 为正数，且小于集合基数，那么命令返回一个包含 count 个元素的数组，数组中的元素各不相同。
    如果 count 大于等于集合长度，那么返回整个集合。
    如果 count 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次(当count的绝对值大于集合长度)，而数组的长度为count的绝对值。 
    因为set的底层是通过hashtable(哈希表)实现的，所以这些元素被分配到不同的hash桶中，SRANDMEMBER随机返回的元素可能是同一个

  SMEMBERS set：返回集合包含的所有元素。元素的顺序是无序的。如果set不存在或为空，则会报错
  SSCAN set cursor [MATCH pattern] [COUNT count]：以渐进的方式返回集合包含的元素。参上述hash的scan用法。
```
四、集合运算：
```
  SDIFF set [set ...]：计算并返回多个集合的差集计算结果。计算规则是以首个集合为标准对进行后续集合的比较
  SDIFFSTORE target_set set [set ...]：对多个集合执行差集计算，并将结果储存到目标集合当中
    差集运算set集合为空或不存在时返回(empty list or set)。
    差集运算的首个set集合的长度小于或等于要比较的set集合的长度时返回(empty list or set)。
    
  SINTER set [set ...]：计算并返回多个集合的交集计算结果
  SINTERSTORE target_set set [set ...]：对多个集合执行交集计算，并将结果储存到目标集合当中
    交集运算时set集合不能为空或不存在。否则会报错，报错信息：(empty list or set)

  SUNION set [set ...]：计算并返回多个集合的并集计算结果
  SUNIONSTORE target_set set [set ...]：对多个集合执行并集计算，并将结果储存到目标集合当中
    并集运算时set集合可以为空或不存在。

  SDIFFSTORE、SINTERSTORE、SUNIONSTORE运算后。目标集合无论是否存在或是否为空，都会清空，重新存放运算结果
```

---
### <a id="a_zset">七、zset(sorted set，有序集合)：</a> <a href="#a_set">last</a> <a href="#a_commont">next</a>
一、set(集合)：
```
  Redis 有序集合和集合一样也是string类型元素的集合,且不允许重复的成员。
不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。

  有序集合的成员是唯一的,但分数(score)却可以重复。

  集合中最大的成员数为 2的32次方-1 (4294967295, 每个集合可存储40多亿个成员)

  在redis sorted sets里面当items内容大于64的时候同时使用了hash(哈希表)和skiplist(跳跃表)两种设计实现。这也会为了排序和查找性能做的优化。
添加和删除都需要修改skiplist，所以复杂度为O(log(n))。 但是如果仅仅是查找元素的话可以直接使用hash，其复杂度为O(1) 
其他的range操作复杂度一般为O(log(n))当然如果是小于64的时候，因为是采用了ziplist的设计，其时间复杂度为O(n)
```
二、元素的检测与管理：
```
  ZADD sorted_set score member [[score member] [score member] ...]：将给定的元素及其分值添加到有序集合，按照分数排序
    score：分数值可以是整数值或双精度浮点数，如不是返回一个(ERR)错误：(error)ERR value is not a valid float
    如果某个成员已经是有序集的成员，那么更新这个成员的分数值，并通过重新插入这个成员元素，来保证该成员在正确的位置上
    如果有序集合 key 不存在，则创建一个空的有序集并执行 ZADD 操作。被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
    当 key 存在但不是有序集类型时，返回一个(WRONGNTYPE)错误：(error) WRONGNTYPE Opeartion against a key holding the wrong kind of value

  ZINCRBY sorted_set increment member：为元素的分值加上指定的整数增量。
    可以通过传递一个负数值 increment ，让分数减去相应的值，比如 ZINCRBY key -5 member ，就是让 member 的 score 值减去 5 。
    当 key 不存在，或分数不是 key 的成员时， ZINCRBY key increment member 等同于 ZADD key increment member 。
    当 key 不是有序集类型时，返回一个(WRONGNTYPE)错误。
    分数值可以是整数值或双精度浮点数，如不是返回一个(ERR)错误。

  ZSCORE sorted_set member：返回给定元素的分值。当key或元素不存在时，返回(nil)

  ZCARD sorted_set：返回有序集合包含的元素数量。
    当 key 存在且是有序集类型时，返回有序集的基数。 当 key 不存在时，返回 0。当key不是zset类型时，返回一个(WRONGNTYPE)错误。

  ZRANK sorted_set member：返回有序集合元素在按照分值从小到大进行排列时，给定的元素在有序集合中所处的排名。当key不存在时返回(nil)
  ZREVRANK sorted_set member：返回有序集合元素在按照分值从大到小进行排列时，给定的元素在有序集合中所处的排名。当key不存在时返回(nil)
```

三、批量处理多个元素：
```
  ZCOUNT sorted_set min max：返回有序集合中，分值介于指定区间之内的元素数量

  ZRANGE sorted_set start end [WITHSCORES]：按照分值从小到大的顺序，返回指定索引范围之内的元素及其分值（可选）。
    一个快速获取集合中的所有元素的用法：ZRANGE sorted_set 0 -1  。 0表示集合的第一个元素。-1表示集合的最后一个元素。 
  ZREVRANGE sorted_set start end [WITHSCORES]：按照分值从大到小的顺序，返回指定索引范围之内的元素及其分值（可选）
    具有相同分数值的成员按字典序(lexicographical order，该属性是有序集提供的，不需要额外的计算)来排列。
    start end参数：从左往右索引从0开始。可以使用负数下标，从右往左索引从-1开始，eg：ZRANGE sorted_set -3 -1
    [WITHSCORES]：可选参数，指定时，显示元素的分值

  ZRANGEBYSCORE sorted_set min max [WITHSCORES] [LIMIT offset count]：按照分值从小到大的顺序，返回指定分值范围之内的元素
  ZREVRANGEBYSCORE sorted_set max min [WITHSCORES] [LIMIT offset count]：按照分值从大到小的顺序，返回指定分值范围之内的元素
    默认情况下，区间的取值使用闭区间 (小于等于或大于等于)，可以通过给参数前增加 ( 符号来使用可选的开区间 (小于或大于)，
      但是不能使用闭区间 [ 符号。
      eg: 闭区间0 <= score <= 3：ZRANGEBYSCORE sorted_set 0 3。
      开区间0 < score < 3：ZRANGEBYSCORE sorted_set (0 (3 
    [LIMIT offset count]：可选参数，指分页，offset表示第几页，count表示每页显示内容数

  ZSCAN sorted_set cursor [MATCH pattern] [COUNT count]：以渐进的方式，返回有序集合包含的元素及其分值。参考list中的SCAN的用法
  ZREM sorted_set member [member ...]：从有序集合中移除指定的一个或多个元素。返回被移除成员的数量。
  ZREMRANGEBYRANK sorted_set start end：移除有序集合中，位于指定排名范围内的元素，其中元素按照分值从小到大进行排列。返回被移除成员的数量。
  ZREMRANGEBYSCORE sorted_set min max：移除有序集合中，分值位于指定范围内的元素。返回被移除成员的数量。

  注意：INF(infinite的缩写)表示无穷大，此处redis支持此特殊字符，使用分值或区间时，-INF表示负无穷大，+INF表示正无穷大。
  eg： ZRANGEBYSCORE sorted_set -INF +INF：按照分值从小到大的顺序，返回所有的元素。
  -INF和+INF可以简写为 - +，eg：ZRANGEBYSCORE sorted_set - +
```

四、集合运算：
```
  ZINTERSTORE target number [sorted_set ...] [WEIGHTS weight [weight ...]][AGGREGATE SUM|MIN|MAX]：对给定数量的有序集合执行交集计算，并将计算的结果储存到目标有序集合里面
  ZUNIONSTORE target number [sorted_set ...] [WEIGHTS weight [weight ...]][AGGREGATE SUM|MIN|MAX]：对给定数量的有序集合执行并集计算，并将计算的结果储存到目标有序集合里面根据元素的大小对其进行处理
    number： numkeys 参数指定给定 key 的数量，必要传输
    [WEIGHTS]：可选参数，可以为每个给定的有序集指定一个乘法因子，每个给定有序集的所有成员的score值在传递给聚合函数之前都要先乘以该因子。
如果WEIGHTS没有给定，默认就是1
    [AGGREGATE]：可选参数，指定计算的结果集的聚合方式，默认使用的参数SUM。
      SUM：可以将所有集合中某个成员的score值之和作为结果集中该成员的score值
      MIN：结果集就是所有集合中元素按照分值排列的最小的元素
      MAX：结果集就是所有集合中元素按照分值排列的最大的元素

  ZLEXCOUNT sorted_set min max：统计有序集合里面，位于指定字典区间内的元素的数量
  ZRANGEBYLEX sorted_set min max [LIMIT offset count]：按照从小到大的顺序，返回有序集合里面位于指定字典区间之内的元素
  ZREMRANGEBYLEX sorted_set min max：从有序集合里面，移除位于指定字典区间之内的元素
    min：字典区间较小值
    max：字典区间较大值
    min和max特制成员，其名称前需要加 [ 符号(闭区间)或 ( 符号(开区间)作为开头, 符号与成员之间不能有空格
    min 和 max 不能反, max 放前面 min放后面会导致返回结果为0， 可以使用 -或-INF 和 +或+INF 表示得分最小值和最大值
```

---
### <a id="a_commont">八、常用操作命令：</a> <a href="#a_zset">last</a> <a href="#a_transaction">next</a>
一、常用命令：
```
  REDIS-SERVER [redis.config]：开启Redis服务端，redis.config指定配置文件及路径
  REDIS-CLI -h host -p port [-a password][--raw]：开启Redis客户端，-h指定ip地址，-p指定端口号，-a可选参数指定密码，--raw处理中文乱码
  AUTH password：使用给定的密码连接服务器
  ECHO message：让服务器打印指定的消息，用于测试连接
  PING：向服务器发送一条 PING 消息，用于测试连接或者测量延迟值
  QUIT：请求服务器关闭与当前客户端的连接
  SELECT number：切换至指定的数据库。默认16个数据库，索引从0开始，通过redis.conf的databases参数配置

  CONFIG SET option value：为给定的配置选项设置值。来设置requirepass参数。
  CONFIG GET option：返回给定配置选项的值
  CONFIG REWRITE：对服务器的配置选项文件进行重写，并将改写后的文件储存在硬盘里面
  CONFIG RESETSTAT：重置服务器的某些统计数据
  INFO [section]：返回与服务器相关的统计信息
  TIME：返回服务器当前的 UNIX 时间戳
  SHUTDOWN [SAVE|NOSAVE]：关闭服务器。
    [SAVE|NOSAVE]：可选参数，是否备份数据。SAVE备份数据，NOSAVE不备份。
```
二、数据库键管理：  
[SORT命令参考](http://doc.redisfans.com/key/sort.html)
```
  KEYS pattern：从数据库里面获取所有符合给定模式的键
  SCAN cursor [MATCH pattern] [COUNT count]：以渐进的方式获取数据库中的键
  RANDOMKEY：从数据库里面随机地返回一个键
  SORT key [BY pattern] [LIMIT offset count] [GET pattern [GET pattern ...]][ASC|DESC] [ALPHA] [STORE destination]：对给定的键进行排序
    BY pattern：通过使用 BY 选项，可以让 uid 按其他键的元素来排序（默认情况下， SORT uid 直接按 uid 中的值排序），
 通过将一个不存在的键作为参数传给 BY 选项， 可以让 SORT 跳过排序操作
    LIMIT offset count：使用 LIMIT 修饰符限制返回结果
	  offset 指定要跳过的元素数量。
	  count 指定跳过 offset 个指定的元素之后，要返回多少个对象
	GET pattern [GET pattern ...]：使用 GET 选项， 可以根据排序的结果来取出相应的键值。
通过组合使用 BY 和 GET ， 可以让排序结果以更直观的方式显示出来。除了可以将字符串键之外， 哈希表也可以作为 GET 或 BY 选项的参数来使用
	ASC|DESC： 返回键值按照升序或降序排序的结果，默认ASC升序
	ALPHA：使用 ALPHA 修饰符对字符串进行排序。SORT 命令默认排序对象为数字， 当需要对字符串进行排序时， 需要显式地在 SORT 命令之后添加 ALPHA 修饰符
    STORE destination：通过给 STORE 选项指定一个 key 参数，可以将排序结果保存到给定的键上，如果被指定的 key 已存在，那么原有的值将被排序结果覆盖
  
  EXISTS key：检查给定的键是否存在于数据库
  DBSIZE：返回当前正在使用的数据库包含的键值对数量
  TYPE key：返回给定键储存的值的类型。共五种：string、hash、list、set、zset
  RENAME key new-key：为给定键设置一个新名字
  RENAMENX key new-key：仅在新名字尚未被使用的情况下，为给定键设置一个新名字
  MOVE key db：将当前数据库中的给定键移动到指定的数据库
  DEL key [key ...]：从数据库中删除给定的一个或多个键
  FLUSHDB：删除当前数据库中的所有键
  FLUSHALL：删除服务器中，所有数据库的所有键
```
四、过期时间管理：
```
  EXPIRE key seconds：为键设置秒级精度的过期时间
  PEXPIRE key milliseconds：为键设置毫秒级精度的过期时间
  EXPIREAT key timestamp-in-seconds：为键设置秒级精度的过期 UNIX 时间戳
  PEXPIREAT key timestamp-in-milliseconds：为键设置毫秒级精度的过期 UNIX 时间戳
 
  TTL key：以秒级精度返回给定键的剩余存活时间
  PTTL key：以毫秒级精度返回给定键的剩余存活时间移除过期时间
  PERSIST key：移除键的过期时间
```
五、安全：
```
  1、在redis.conf配置文件，通过requirepass参数设置密码保护，那么slave在开始同步之前必须进行身份验证，否则它的同步请求会被拒绝
  2、通过客户端CONFIG SET option value：为给定的配置选项设置值。来设置requirepass参数。
  链接客户端命令也可以通过 -a password 追加密码登录。也可以通过 AUTH password：使用给定的密码连接服务器
  3、可以通过过期时间+访问次数来限制访问。
```

---
### <a id="a_transaction">九、事务、持久化、发布订阅：</a> <a href="#a_commont">last</a> <a href="#a_jedis">next</a>
一、redis事务(transaction)：
```
1、Redis 事务可以一次执行多个命令， 并且带有以下两个重要的保证：
    批量操作在发送 EXEC 命令前被放入队列缓存。
    收到 EXEC 命令后进入事务执行，事务中任意命令执行失败，其余的命令依然被执行。
    在事务执行过程，其他客户端提交的命令请求不会插入到事务执行命令序列中。

2、一个事务从开始到执行会经历以下三个阶段： 开始事务、命令入队、执行事务

3、redis是的使用方法：
  首先还是使用 multi方法打开事务，然后进行设置。这时设置的数据都会放入队列里进行保存，最后使用exec命令执行，把数据依次存储到redis中，
使用discard方法取消事务。

4、redis的事务不能保证同事成功或失败进行提交或回滚。

5、单个 Redis 命令的执行是原子性的，但 Redis 没有在事务上增加任何维持原子性的机制，所以 Redis 事务的执行并不是原子性的。
事务可以理解为一个打包的批量执行脚本，但批量指令并非原子化的操作，中间某条指令的失败不会导致前面已做指令的回滚，也不会造成后续的指令不做

6、基本事务操作：
  MULTI：开始一次事务。支持将插入不同的数据库。
  EXEC：执行事务。执行exec命令后会取消对所有键的监控，如果不想执行事务中的命令
    语法错误或执行时出错，抛出(EXECABORT)异常：(error) EXECABORT Transaction discarded because of previous errors.
    没有事务时，抛出一个(ERR)异常：(error)ERR DISCARD without MULTI
  
  DISCARD：取消事务。没有事务时，抛出一个(ERR)异常：(error)ERR DISCARD without MULTI

7、乐观锁事务操作：
  WATCH key [key ...]：监视给定的键，看它们在事务执行之前是否已被修改
  UNWATCH：取消对所有键的监视
```
二、持久化：  
[Redis持久化机制](https://www.cnblogs.com/xingzc/p/5988080.html)  
[Redis文档-持久化](http://redis.io/topics/persistence)
```
1、redis是一个支持持久化的内存数据库，也就是说redis需要经常见内存中的数据同步到硬盘来保证持久化

2、redis持久化的方式：
  2.1、snapshotting（快照）默认方式，将内存中以快照的方式写入到二进制文件中，默认文件名为dump.rdb。
可以通过配置项dbfilename设置文件名，dir配置项指定持久化目录。如不想保存则注释所有的 save配置项。
    snapshotting参数设置：save <seconds> <changes>：表示经过多少秒且数据变化次数之后，则发起快照保存，把数据库写到磁盘上
    eg：save 900 1       # 900秒（15分钟）之后，且至少1次变更
        save 300 10      # 300秒（5分钟）之后，且至少10次变更
        save 60  10000   # 60秒之后，且至少10000次变更

  2.2、append-only file（简写aof）的方式(类似于oracle的commit日志)由于快照方式时在一定时间间隔执行的，所以可能发生redis意外的down的情况，
就会失去最后一次快照后的所有修改的数据、aof比快照方式有更好的持久化性，是由于redis会讲没一个收到的写命令都通过write函数追加到命令中，
当redis重新启动是回重新执行文件中保存的写命令来在内存中重建这个数据库的内容，文件默认为：bin/appendonly.aof。通过dir和appendfilename参数修改。
不是立即写到硬盘上，可以通过配置文件修改强制写到硬盘中。
    aof参数配置：
      appendonly no   # yes表示启动aof持久化，no表示不启动。默认情况下，Redis是异步的把数据导出到磁盘上
      # appendfsync always # 收到写命令就立即写入到磁盘，效率最慢，但是保证完全的持久化
      appendfsync everysec # 每隔一秒写入磁盘一次，在性能和持久化方面做了折中
      # appendfsync no     # 完全依赖OS(Operating System，操作系统)性能最好，不立刻执行，只有在操作系统需要刷的时候再刷。比较快
      appendfilename "appendonly.aof" # 纯累加文件名字（默认："appendonly.aof"）
```
三、发布订阅(pub/sub)：  
[Redis文档-发布/通知](http://redis.io/topics/keyspace-events)
```
1、Redis 发布订阅(pub/sub)是一种消息通信模式：发送者(pub)发送消息，订阅者(sub)接收消息。
2、Redis 客户端可以订阅任意数量的频道。
3、Redis 发布订阅在大数据量下会有性能瓶颈，所以一般用于小数据量的。
4、发布消息：
  PUBLISH channel message：向指定频道发布一条消息。发送成功返回(integer)1，不成功返回(integer)0

5、订阅消息：
  SUBSCRIBE channel [channel ...]：订阅给定的一个或多个频道
  PSUBSCRIBE pattern [pattern ...]：订阅给定的一个或多个模式

6、退订消息：
  UNSUBSCRIBE [channel [channel ...]]：退订给定的一个或多个频道，如果没有给定频道则退订全部频道
  PUNSUBSCRIBE [pattern [pattern ...]]：退订给定的一个或多个模式，如果没有给定模式则退订全部模式

7、查看订阅信息：
  PUBSUB CHANNELS [pattern]：列出当前被订阅的频道
  PUBSUB NUMSUB [channel channel ...]：返回给定频道的订阅者数量
  PUBSUB NUMPAT：返回当前被订阅模式的数量
```

---
### <a id="a_jedis">十、Jedis：Redis的Java版本的客户端实现：</a> <a href="#a_transaction">last</a> <a href="#a_jString">next</a>
一、Jedis是什么：  
[Jedis Git](https://github.com/xetorthio/jedis) [Jedis Wiki](https://github.com/xetorthio/jedis/wiki)   
```
  Jedis是redis的java版本的客户端实现。jedis就是集成了redis的一些命令操作，封装了redis的java客户端。提供了连接池管理。
一般不直接使用jedis，而是在其上在封装一层，作为业务的使用。如果用spring的话，可以看看spring 封装的 redis Spring Data Redis

  Jedis与redis 2.8.x和3.xx完全兼容
```
二、使用Jedis所需要的jar包：  
2.1、可以从[Sonatype](https://search.maven.org)上下载：  
[redis.clients:jedis:2.9.0](https://search.maven.org/search?q=g:redis.clients) 
[org.apache.commons:commons-pool2:2.6.0](https://search.maven.org/search?q=g:org.apache.commons%20AND%20a:commons-pool2)  
2.2、Maven依赖方式：redis.clients:jedis:2.9.0 的pom中包含org.apache.commons:commons-pool2:2.6.0 所以不用再添加commons-pool2的依赖
```xml
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>2.8.0</version>
    <type>jar</type>
    <scope>compile</scope>
</dependency>
```
2.3、Spring boot依赖方式：
```xml
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
</dependency>
```
三、直接创建Jedis实例：  
ClientByJedis.java：
```Java
package com.mutistic.redis.java;
import com.mutisitc.utils.PrintUtil;
import redis.clients.jedis.Jedis;
/**
 * 直接创建Jedis实例
 */
public class ClientByJedis {
  public static void main(String[] args) {
    PrintUtil.one("直接创建Jedis实例：");
    
    String host = "192.168.16.113";
    PrintUtil.two("1、定义redis的IP地址(和redis.conf的bind参数一致)：", host);
    
    int port = 6379;
    PrintUtil.two("2、定义redis的端口号(和redis.conf的port参数一致，默认为：6379)：", port);
    
    Jedis jedis = null;
    try {
      jedis = new Jedis(host, port);
      PrintUtil.two("3、通过构造函数创建redis.clients.jedis.Jedis实例对象", jedis);
      
      String ping = jedis.ping();
      PrintUtil.two("4、通过Jedis.ping()测试连接redis服务", ping);
      
      String result = jedis.set("test", "hello Jedis!");
      PrintUtil.two("5、通过Jedis.set(String key, String value)为字符串键设置值", result);
      
      String value = jedis.get("test");
      PrintUtil.two("6、通过Jedis.get(String key)获取字符串键的值", value);
    } catch (Exception e) {
      PrintUtil.err("直接创建Jedis实例，出现异常，打印异常信息：");
      e.printStackTrace();
    } finally {
      if (jedis != null) {
        jedis.close();
        PrintUtil.two("7、Jedis.close()", "关闭Jedis");
      }
    }
  }
}
```
四、使用JedisPool获取Jedis实例：  
ClientByJedisPool.java：
```Java
package com.mutistic.redis.java;
import com.mutisitc.utils.PrintUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
// 使用JedisPool获取Jedis实例
public class ClientByJedisPool {
  public static void main(String[] args) {
    PrintUtil.one("使用JedisPool获取Jedis实例");
    
    JedisPoolConfig config = new JedisPoolConfig();
    PrintUtil.two("1、创建redis.clients.jedis.JedisPoolConfig实例对象：", config);
    
    String host = "192.168.16.113";
    PrintUtil.two("2、定义redis的IP地址(和redis.conf的bind参数一致)：", host);
    
    JedisPool pool = new JedisPool(config, host);
    PrintUtil.two("3、创建redis.clients.jedis.JedisPool实例对象", pool);
    
    Jedis jedis = null;
    try {
      jedis = pool.getResource();
      PrintUtil.two("4、通过JedisPool.getResource()获取redis.clients.jedis.Jedis实例对象", jedis);
      
      String ping = jedis.ping();
      PrintUtil.two("5、通过Jedis.ping()测试连接redis服务", ping);
      
      String result = jedis.set("test", "hello JedisPool!");
      PrintUtil.two("6、通过Jedis.set(String key, String value)为字符串键设置值", result);
      
      String value = jedis.get("test");
      PrintUtil.two("7、通过Jedis.get(String key)获取字符串键的值", value);
    } catch (Exception e) {
      PrintUtil.err("使用JedisPool获取Jedis实例，出现异常，打印异常信息：");
      e.printStackTrace();
    } finally {
      if (jedis != null) {
        jedis.close();
        PrintUtil.two("8、Jedis.close()", "关闭Jedis");
      }
      pool.close();
      PrintUtil.two("9、JedisPool.close()", "关闭JedisPool");
    }
  }
}
```
五、在spring boot run启动时使用redis：  
pom.xml：redis相关依赖：
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```
application.properties：redis相关参数配置：
```properties
## Redis配置信息
# Redis数据库索引（默认为0）
spring.redis.database=0
# Redis服务器地址
spring.redis.host=192.168.16.113
# Redis服务器连接端口
spring.redis.port=6379
# Redis服务器连接密码（默认为空）
spring.redis.password=
# Redis连接池最大连接数（使用负值表示没有限制）
spring.redis.jedis.pool.max-active=8
# Redis连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.jedis.pool.max-wait=-1
# Redis连接池中的最大空闲连接
spring.redis.jedis.pool.max-idle=8
# Redis连接池中的最小空闲连接
spring.redis.jedis.pool.min-idle=0
# Redis连接超时时间（毫秒）：设置小于0会导致启动报错，设置为0会照成直接超时，
#   与原本redis定义的timeout=0（永不过时）含义冲突，可以不设置此项，采用spring默认的超时时间
#spring.redis.timeout=1000000
```
ClientByApplication.java：
```Java
package com.mutistic.redis.connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import com.mutisitc.utils.PrintUtil;
// 在spring boot run启动时使用redis
@SpringBootApplication
public class ClientByApplication {
  public static void main(String[] args) {
    ConfigurableApplicationContext context  = SpringApplication.run(ClientByApplication.class, args);
    PrintUtil.one("在spring boot run启动时使用redis：");
    
    PrintUtil.two("1、通过SpringApplication.run()获取ConfigurableApplicationContext实例：：", context);
    
    StringRedisTemplate stringRedisTemplate = context.getBean(StringRedisTemplate.class);
    PrintUtil.two("2、通过ConfigurableApplicationContext.getBean(StringRedisTemplate.class)"
        + "获取org.springframework.data.redis.core.StringRedisTemplate实例：：", stringRedisTemplate);
    
    stringRedisTemplate.opsForValue().set("testByRun", "Hello StringRedisTemplate By Run");
    PrintUtil.two("3、通过StringRedisTemplate.opsForValue().set()：", "字符串键设置值");
    
    String value = stringRedisTemplate.opsForValue().get("testByRun");
    PrintUtil.two("4、通过StringRedisTemplate.opsForValue().get(Object key)获取字符串键的值：", value);
    
    PrintUtil.two("注意：redis连接超时时间（单位毫秒）不能设置为小于等于0的数字，"
        + "小于0会导致启动报错，等于0会照成直接超时，与原本redis定义的timeout=0（禁止含义冲突）。可以不设置或设置为：", "spring.redis.timeout=1000000");
    
    context.close();
  }
}
```
六、在Controller中使用redis：  
ClientByApplication.java：
```Java
package com.mutistic.redis.connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// 在Controller中使用redis
@RestController
@RequestMapping("/clientByController/")
public class ClientByController {
  /**
   * 自动注入StringRedisTemplate实例
   */
  @Autowired
  private StringRedisTemplate stringRedisTemplate;
  /**
   * 为字符串键设置值
   * @param key 字符串键
   * @param value 字符串值
   * @return
   */
  @GetMapping("setString")
  public String set(String key, String value) {
    stringRedisTemplate.opsForValue().set(key, value);
    return "SUCCESS";
  }
  
  /**
   * 获取字符串键的值
   * @param key 字符串键
   * @return 字符串值
   */
  @GetMapping("getString")
  public String get(String key) {
    return stringRedisTemplate.opsForValue().get(key);
  }
}
```

---
### <a id="a_jString">十一、使用Jedis API操作String数据类型：</a> <a href="#a_jedis">last</a> <a href="#a_jHash">next</a>
JedisUtil.java：
```Java
package com.mutisitc.utils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
// Jedis Client 工具类
public class JedisUtil {
  /** redis IP地址 */
  private static String REDIS_HOST = "192.168.16.113";
  /** redis 端口号 */
  private static int REDIS_PORT = 6379;
  /** redis.clients.jedis.JedisPool实例 */
  private static JedisPool JEDIS_POOL = null;
  
  // 获取Jedis实例对象 
  public static Jedis getJedis() {
    PrintUtil.one("0、使用JedisPool获取Jedis实例");
    
    JedisPoolConfig config = new JedisPoolConfig();
    PrintUtil.two("0.1、创建redis.clients.jedis.JedisPoolConfig实例对象：", config);
    
    PrintUtil.two("0.2、获取配置的redis的IP地址和端口号", "host="+REDIS_HOST+",prot="+REDIS_PORT);
    
    if(JEDIS_POOL == null) {
       JEDIS_POOL = new JedisPool(config, REDIS_HOST, REDIS_PORT);
    }
    PrintUtil.two("0.3、创建redis.clients.jedis.JedisPool实例对象", JEDIS_POOL);
    
    Jedis jedis = null;
    try {
      jedis = JEDIS_POOL.getResource();
      PrintUtil.two("0.4、通过JedisPool.getResource()获取redis.clients.jedis.Jedis实例对象", jedis);
    } catch (Exception e) {
      PrintUtil.err("使用JedisPool获取Jedis实例，出现异常，打印异常信息：");
      e.printStackTrace();
      close(jedis);
    } 
    return jedis;
  }
  
  // 关闭Jedis链接
  public static void close(Jedis jedis) {
    if(jedis != null) {
      jedis.close();
    }
    if(JEDIS_POOL != null) {
      JEDIS_POOL.close();
    }
  }
}
```
StringCommand.java：
```Java
package com.mutistic.redis.jedis;
import java.util.List;
import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;
import redis.clients.jedis.Jedis;
// 使用Jedis API操作String数据类型
public class StringCommand {
  public static void main(String[] args) {
    PrintUtil.one("使用Jedis API操作String数据类型：");

    Jedis jedis = JedisUtil.getJedis();
    jedis.flushDB();

    showByGetAndSet(jedis);
    showByBatch(jedis);
    showByUpdate(jedis);
    showByIncrAndDecr(jedis);
    
    JedisUtil.close(jedis);
  }

  // 1、字符串的设置与获取
  private static void showByGetAndSet(Jedis jedis) {
    PrintUtil.one("1、字符串的设置与获取：");
    
    String result = jedis.set("String:SET", "字符串的设置与获取");
    PrintUtil.two("1.1、set(String key, String value)：为字符串键设置值【SET key value】", "key=String:SET, result=" + result);

    String value = jedis.get("String:SET");
    PrintUtil.two("1.2、get(Object key)：获取字符串键的值【GET key】", "key=String:SET, value=" + value);

    String oldValue = jedis.getSet("String:GETSET", "为字符串键设置值，返回旧值");
    PrintUtil.two("1.3、getSet(String key, String value)：为字符串键设置值，返回旧值【GETSET key new-value】",
        "key=String:SET, oldValue=" + oldValue);

    String result2 = jedis.set("String:SETByTimeout", "为字符串键设置值、方式、过期时间", "nx", "ex", 100l);
    PrintUtil.two(
        "1.3、set(String key, String value, String nxxx, String expx, long time)："
            + "为字符串键设置值、方式、过期时间【SET key value [EX seconds][PX milliseconds]】",
        "key=String:SETByTimeout, result=" + result2);
    
    Long result3 = jedis.setnx("String:SETNX", "仅在字符串键尚未有值的情况下，为它设置值");
    PrintUtil.two("1.4、setnx(String key, String value)：为仅在字符串键尚未有值的情况下，为它设置值【SETNX key value】",
        "key=String:SETNX, result=" + result3);
    
    String result4 = jedis.setex("String:SETEX", 100, "为字符串键设置值和秒级精度的过期时间");
    PrintUtil.two("1.5、setex(String key, int seconds, String value)：为字符串键设置值和秒级精度的过期时间【SETEX key seconds value】",
        "key=String:SETEX, result=" + result4);
    
    jedis.psetex("String:PSETEX", 10000l, "为字符串键设置值和毫秒级精度的过期时间");
    PrintUtil.two("1.6、psetex(String key, long milliseconds, String value)：为字符串键设置值和毫秒级精度的过期时间【PSETEX key milliseconds value】",
        "key=String:PSETEX, result=" + result4);
  }
  // 2、字符串的批量设置与获取
  private static void showByBatch(Jedis jedis) {
    PrintUtil.one("2、字符串的批量设置与获取：");
    
    String result = jedis.mset("String:MSET", "一次为多个字符串键设置值", "String:MSET2", "批量为字符串键设置值");
    PrintUtil.two("2.1、mset(String... keysvalues)：一次为多个字符串键设置值【MSET key value [key2 value ...]】", 
        "key=String:MSET,String:MSET2, result=" + result);
    
    List<String> valueList = jedis.mget("String:MSET", "String:MSET2");
    PrintUtil.two("2.2、mget(String... keys)：一次获取多个字符串键的值【MGET key [key2 ...]】", 
        "key=String:MSET,String:MSET2, values=" + valueList);
    
    Long result2 = jedis.msetnx("String:MSETNX", "仅在所有给定字符串键都尚未有值的情况下，为它们设置值", "String:MSETNX2", "批量为字符串键未有值的情况设置值");
    PrintUtil.two("2.3、msetnx(String... keysvalues)：仅在所有给定字符串键都尚未有值的情况下，为它们设置值【MSETNX key value [key2 value ...]】", 
        "key=String:MSETNX,String:MSETNX2, result=" + result2);
  }
  // 3、字符串的获取或修改内容
  private static void showByUpdate(Jedis jedis) {
    PrintUtil.one("3、字符串的获取或修改内容：");
    
    Long length = jedis.strlen("String:STRLEN");
    PrintUtil.two("3.1、strlen(String key)：获取字符串值的长度【STRLEN key】", "key=String:STRLEN, length=" + length);
    
    Long length2 = jedis.setrange("String:SETRANGE", 1, "对字符串值在指定索引位置上的内容进行修改");
    PrintUtil.two("3.2、setrange(String key, long offset, String value)：对字符串值在指定索引位置上的内容进行修改【SETRANGE key offset value】", 
        "key=String:SETRANGE, length=" + length2);

    String value = jedis.getrange("String:GETRANGE", 1, jedis.strlen("String:SET"));
    PrintUtil.two("3.3、getrange(String key, long startOffset, long endOffset)：获取字符串值在指定索引范围内的内容【GETRANGE key start end】", 
        "key=String:GETRANGE, value=" + value);
    
    Long length3 = jedis.append("String:APPEND", "将指定的内容追加到字符串值的末尾");
    PrintUtil.two("3.3、append(String key, String value)：将指定的内容追加到字符串值的末尾【APPEND key value】", 
        "key=String:APPEND, length=" + length3);
  }
  // 4、字符串的自增与自减
  private static void showByIncrAndDecr(Jedis jedis) {
    PrintUtil.one("4、字符串的自增与自减：");
    
    Long result = jedis.incr("String:INCR");
    PrintUtil.two("4.1、incr(String key)：为字符串键储存的整数值加上一【INCR key】", "key=String:INCR, result=" + result);
    
    result = jedis.decr("String:INCR");
    PrintUtil.two("4.2、decr(String key)：为字符串键储存的整数值减上一【DECR key】", "key=String:INCR, result=" + result);
    
    result = jedis.incrBy("String:INCR", 10);
    PrintUtil.two("4.3、incrBy(String key, long integer)：为字符串键储存的整数值加上指定的整数增量【INCRBY key increment】",
        "key=String:INCR, result=" + result);

    result = jedis.decrBy("String:INCR", 5);
    PrintUtil.two("4.3、decrBy(String key, long integer)：为字符串键储存的整数值减去指定的整数减量【DECRBY key increment】",
        "key=String:INCR, result=" + result);
    
    Double result2 = jedis.incrByFloat("String:INCR", 0.5);
    PrintUtil.two("4.3、incrByFloat(String key, double value)：为字符串键储存的数字值加上指定的浮点数增量【INCRBYFLOAT key increment】",
        "key=String:INCR, result=" + result2);
    
    result2 = jedis.incrByFloat("String:INCR", -0.8);
    PrintUtil.two("4.3.2、incrByFloat(String key, double -value)：为字符串键储存的数字值加上指定的浮点数减量【INCRBYFLOAT key -increment】",
        "key=String:INCR, result=" + result2);
  }
}
```

---
### <a id="a_jHash">十二、使用Jedis API操作Hash数据类型：</a> <a href="#a_jString">last</a> <a href="#a_jList">next</a>
HashCommand.java：
```Java
package com.mutistic.redis.jedis;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
// 使用Jedis API操作Hash数据类型
public class HashCommand {
  public static void main(String[] args) {
    PrintUtil.one("使用Jedis API操作Hash数据类型：");

    Jedis jedis = JedisUtil.getJedis();
    jedis.flushDB();

    showByGetAndSet(jedis);
    showByIncrAndDecr(jedis);
    showByManager(jedis);
    JedisUtil.close(jedis);
  }
  // 1、Hash的设置与获取
  private static void showByGetAndSet(Jedis jedis) {
    PrintUtil.one("1、Hash的设置与获取：");

    Long result = jedis.hset("Hash:HSET", "key1", "为散列中的键设置值");
    PrintUtil.two("1.1、hset(String key, String field, String value)：为散列中的键设置值【HSET hash key value】",
        "key=Hash:HSET, field=key1, result=" + result);

    Long result2 = jedis.hsetnx("Hash:HSET", "key2", "仅在散列中的给定键尚未有值的情况下，为该键设置值");
    PrintUtil.two(
        "1.2、hsetnx(String key, String field, String value)：仅在散列中的给定键尚未有值的情况下，为该键设置值【HSETNX hash key value】",
        "key=Hash:HSET, field=key2, result=" + result2);

    String value = jedis.hget("Hash:HSET", "key2");
    PrintUtil.two("1.3、hget(String key, String field)：返回散列中与给定键相关联的值【HGET hash key】",
        "key=Hash:HGET, field=key2, value=" + value);

    Map<String, String> valueMap = new HashMap<String, String>();
    valueMap.put("key1", "一次为散列中的多个键设置值");
    valueMap.put("key2", "为key2设置值");
    String result3 = jedis.hmset("Hash:HMSET", valueMap);
    PrintUtil.two(
        "1.4、hmset(String key, Map<String, String> hash)：一次为散列中的多个键设置值【HMSET hash key value [key2 value]】",
        "key=Hash:HMSET, field=key1,key2, result=" + result3);

    List<String> valueList = jedis.hmget("Hash:HMSET", "key1", "key2");
    PrintUtil.two("1.5、hmget(String key, String... fields)：一次获取散列中多个键的值【HMGET hash key [key2 ...]】",
        "key=Hash:HMSET, field=key1,key2, valueList=" + valueList);
  }
  // 2、Hash的自增与自减
  private static void showByIncrAndDecr(Jedis jedis) {
    PrintUtil.one("2、Hash的自增与自减：");

    Long result = jedis.hincrBy("Hash:HINCRBY", "key1", 1);
    PrintUtil.two(
        "2.1、hincrBy(String key, String field, long value)：为散列中给定键储存的整数值加上指定的整数增量【HINCRBY hash key increment】",
        "key=Hash:HINCRBY, field=key1, result=" + result);

    Long result2 = jedis.hincrBy("Hash:HINCRBY", "key1", -1);
    PrintUtil.two(
        "2.2、hincrBy(String key, String field, long -value)：为散列中给定键储存的整数值加上指定的整数减量【HINCRBY hash key -increment】",
        "key=Hash:HINCRBY, field=key1, result=" + result2);

    Double result3 = jedis.hincrByFloat("Hash:HINCRBYFLOAT", "key1", 0.5d);
    PrintUtil.two(
        "2.3、hincrByFloat(String key, String field, double value)：为散列中给定键储存的数字值加上指定的浮点数增量【HINCRBYFLOAT key increment】",
        "key=Hash:HINCRBYFLOAT, field=key1, result=" + result3);

    Double result4 = jedis.hincrByFloat("Hash:HINCRBYFLOAT", "key1", -0.8d);
    PrintUtil.two(
        "2.4、hincrByFloat(String key, String field, double -value)：为散列中给定键储存的数字值加上指定的浮点数减量【HINCRBYFLOAT key -increment】",
        "key=Hash:HINCRBYFLOAT, field=key1, result=" + result4);
  }
  // 3、Hash的检测与管理
  private static void showByManager(Jedis jedis) {
    PrintUtil.one("3、Hash的检测与管理：");

    Boolean exists = jedis.hexists("Hash:HSET", "key1");
    PrintUtil.two("3.1、hexists(String key, String field)：检查给定键在散列中是否存在【HEXISTS hash key】",
        "key=Hash:HSET, field=key1, exists=" + exists);

    Long length = jedis.hlen("Hash:HSET");
    PrintUtil.two("3.2、hlen(String key)：返回散列包含的键值对数量【HLEN hash】", "key=Hash:HSET, length=" + length);

    Long result = jedis.hdel("Hash:HSET", "key1", "key2");
    PrintUtil.two("3.3、hdel(String key, String... fields)：删除散列中的一个或多个键，以及这些键的值批量获取散列键值【HDEL hash key [key2 ...]】",
        "key=Hash:HSET, field=key1,key2, result=" + result);

    Set<String> keySet = jedis.hkeys("Hash:HMSET");
    PrintUtil.two("3.4、hkeys(String key)：返回散列包含的所有键【HKEYS hash】", "key=Hash:MHSET, keySet=" + keySet);

    List<String> valueList = jedis.hvals("Hash:HMSET");
    PrintUtil.two("3.5、hvals(String key)：返回散列包含的所有键的值【HVALS hash】", "key=Hash:MHSET, valueList=" + valueList);

    Map<String, String> allMap = jedis.hgetAll("Hash:HMSET");
    PrintUtil.two("3.6、hgetAll(String key)：返回散列包含的所有键值对【HGETALL hash】",
        "key=Hash:MHSET, allMap=" + PrintUtil.toString(allMap));

    ScanResult<Entry<String, String>> scanResult = jedis.hscan("Hash:HMSET", "0");
    PrintUtil.two("3.7、hscan(String key, String cursor)：以渐进的方式返回散列包含的键值对【HSCAN hash cursor】",
        "key=Hash:HMSET, scanResult=" + PrintUtil.toString(scanResult.getResult()));
    
    ScanParams scanParams = new ScanParams();
    scanParams.match("key*");
    scanParams.count(2);
    ScanResult<Entry<String, String>> scanResult2 = jedis.hscan("Hash:HMSET", "2", scanParams);
    PrintUtil.two("3.7.1、hscan(String key, String cursor, ScanParams params)：以渐进的方式返回散列包含的键值对【HSCAN hash cursor [MATCH pattern] [COUNT count]】",
        "key=Hash:HMSET, scanParams={match:key*,count:2}, scanResult=" + PrintUtil.toString(scanResult2.getResult()));
  }
}
```

---
### <a id="a_jList">十三、使用Jedis API操作List数据类型：</a> <a href="#a_jHash">last</a> <a href="#a_jSet">next</a>
ListCommand.java：
```Java
package com.mutistic.redis.jedis;
import java.util.List;
import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
// 使用Jedis API操作List数据类型
public class ListCommand {
  public static void main(String[] args) {
    PrintUtil.one("使用Jedis API操作List数据类型：");

    Jedis jedis = JedisUtil.getJedis();
    jedis.flushDB();

    showByPush(jedis);
    showByPop(jedis);
    showByManager(jedis);
    
    JedisUtil.close(jedis);
  }

  // 1、List的添加元素
  private static void showByPush(Jedis jedis) {
    PrintUtil.one("1、List的添加元素：");

    Long result = jedis.lpush("List:LPUSH", "将一个或多个元素添加到列表的左端", "Hello", "Redis", "!", "This", "is", "test");
    PrintUtil.two("1.1、lpush(String key, String... strings)：将一个或多个元素添加到列表的左端【LPUSH list item [item ...]】",
        "key=List:LPUSH, result=" + result);

    Long result2 = jedis.lpushx("List:LPUSH", "仅在列表已经存在的情况下，将一个元素添加到列表的左端");
    PrintUtil.two("1.2、lpushx(String key, String... strings)：仅在列表已经存在的情况下，将一个元素添加到列表的左端【LPUSHX list item】",
        "key=List:LPUSH, result=" + result2);

    Long result3 = jedis.rpush("List:RPUSH", "将一个或多个元素添加到列表的右端");
    PrintUtil.two("1.3、rpush(String key, String... strings)：将一个或多个元素添加到列表的右端【RPUSH list item [item ...]】",
        "key=List:RPUSH, result=" + result3);

    Long result4 = jedis.rpushx("List:RPUSH", "仅在列表已经存在的情况下，将一个元素添加到列表的右端");
    PrintUtil.two("1.4、rpushx(String key, String... strings)：仅在列表已经存在的情况下，将一个元素添加到列表的右端【RPUSHX list item】",
        "key=List:RPUSH, result=" + result4);
  }
  // 2、List的移除元素
  private static void showByPop(Jedis jedis) {
    PrintUtil.one("2、List的移除元素：");

    String value = jedis.lpop("List:LPUSH");
    PrintUtil.two("2.1、lpop(String key)：移除并返回列表左端第一个元素【LPOP list】", "key=List:LPUSH, value=" + value);

    String value2 = jedis.rpop("List:LPUSH");
    PrintUtil.two("2.2、rpop(String key)：移除并返回列表右端第一个元素【RPOP list】", "key=List:LPUSH, value=" + value2);

    List<String> valueList = jedis.blpop(100, "List:LPUSH");
    PrintUtil.two("2.3、blpop(int timeout, String key)：在指定的时限内，移除首个非空列表的最左端元素【BLPOP list [list ...] timeout】",
        "key=List:LPUSH, valueList=" + valueList);

    List<String> valueList2 = jedis.brpop(100, "List:LPUSH");
    PrintUtil.two("2.4、brpop(int timeout, String key)：在指定的时限内，移除首个非空列表的最右端元素【BRPOP list [list ...] timeout】",
        "key=List:LPUSH, valueList=" + valueList2);
    
    String value3 = jedis.rpoplpush("List:LPUSH", "List:RPOPLPUSH");
    PrintUtil.two("2.5、rpoplpush(String srckey, String dstkey)：移除源列表的最右端元素，并将该元素添加到目标列表的左端【RPOPLPUSH source_list target_list】",
        "srckey=List:LPUSH, dstkey=List:RPOPLPUSH, value=" + value3);
    
    String value4 = jedis.brpoplpush("List:RPOPLPUSH", "List:BRPOPLPUSH", 100);
    PrintUtil.two("2.6、brpoplpush(String srckey, String dstkey, int timeout)：在指定的时限内，尝试移除源列表的最右端元素，并将该元素添加到目标列表的左端【BRPOPLPUSH source_list target_list timeout】",
        "srckey=List:RPOPLPUSH, dstkey=List:BRPOPLPUSH, value=" + value4);
  }
  // 3、List的元素的获取与管理
  private static void showByManager(Jedis jedis) {
    PrintUtil.one("3、List的元素的获取与管理：");

    String value = jedis.lindex("List:LPUSH", 0);
    PrintUtil.two("3.1、lindex(String key, long index)：获取列表在给定索引上的元素【LINDEX list index】", 
        "key=List:LPUSH, index=0, value=" + value);
  
    Long length = jedis.llen("List:LPUSH");
    PrintUtil.two("3.2、llen(String key)：返回列表包含的元素数量【LLEN list】", "key=List:LPUSH, length=" + length);
    
    List<String> valueList = jedis.lrange("List:LPUSH", 0, length);
    PrintUtil.two("3.3、lrange(String key, long start, long end)：返回列表在指定索引范围内的所有元素【LRANGE list start end】",
        "key=List:LPUSH, start=0, end="+length+", valueList=" + valueList);

    Long result = jedis.linsert("List:LPUSH", LIST_POSITION.BEFORE, "is", "test");
    PrintUtil.two("3.4、linsert(String key, LIST_POSITION where, String pivot, String value)：将给定的元素插入到目标元素的前面或者后面【LINSERT list BEFORE/AFTER target item】",
        "key=List:LPUSH, where=LIST_POSITION.BEFORE, pivot=is, value=test, result=" + result);
    
    Long result2 = jedis.lrem("List:LPUSH", 0, "!");
    PrintUtil.two("3.5、lrem(String key, long count, String value)：根据参数COUNT的值，移除列表中与参数VALUE相等的元素【LREM list count item】",
        "key=List:LPUSH, count=0, value=!, result=" + result2);

    String result3 = jedis.lset("List:LPUSH", 0, "Hello Redis!");
    PrintUtil.two("3.6、lset(String key, long index, String value)：把列表在指定索引上的值修改为给定的元素【LSET list index item】",
        "key=List:LPUSH, index=0, value=Hello Redis!, result=" + result3);
    
    String result4 = jedis.ltrim("List:LPUSH", 0, 1);
    PrintUtil.two("3.7、ltrim(String key, long start, long end)：对列表进行截断，只保留指定索引范围内的元素【LTRIM list start end】",
        "key=List:LPUSH, start=0, end=1, result=" + result4);
    
  }
}
```

---
### <a id="a_jSet">十四、使用Jedis API操作Set数据类型：</a> <a href="#a_jList">last</a> <a href="#a_jZSet">next</a>
SetCommand.java：
```Java
package com.mutistic.redis.jedis;
import java.util.List;
import java.util.Set;
import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
// 使用Jedis API操作Set数据类型
public class SetCommand {
  public static void main(String[] args) {
    PrintUtil.one("使用Jedis API操作Set数据类型：");

    Jedis jedis = JedisUtil.getJedis();
    jedis.flushDB();

    showByAddAndPop(jedis);
    showByGet(jedis);
    showByOperation(jedis);

    JedisUtil.close(jedis);
  }

  // 1、Set的元素的添加与移除
  private static void showByAddAndPop(Jedis jedis) {
    PrintUtil.one("1、Set的元素的添加与移除：");

    Long size = jedis.sadd("Set:SADD", "将一个或多个元素添加到集合当中", "Hello", "Set", "!");
    PrintUtil.two("1.1、sadd(String key, String... members)：将一个或多个元素添加到集合当中【SADD set element [element ...]】",
        "key=Set:SADD, size=" + size);

    String popValue = jedis.spop("Set:SADD");
    PrintUtil.two("1.2、spop(String key)：随机地移除并返回集合中的某个元素【SPOP set】", "key=Set:SADD, popValue=" + popValue);

    Long size2 = jedis.smove("Set:SADD", "Set:SMOVE", "Hello");
    PrintUtil.two(
        "1.3、smove(String srckey, String dstkey, String member)：将指定的元素从源集合移动到目标集合【SMOVE source_set target_set element】",
        "srckey=Set:SADD, dstkey=Set:SMOVE, member=Hello, size=" + size2);

    Long size3 = jedis.srem("Set:SADD", "Hello");
    PrintUtil.two("1.4、srem(String key, String... members)：移除集合中的一个或多个元素【SREM set element [element ...]】",
        "key=Set:SADD, member=Hello, size=" + size3);
  }
  // 2、Set的元素的获取与检测
  private static void showByGet(Jedis jedis) {
    PrintUtil.one("2、Set的元素的获取与检测：");

    Long size = jedis.scard("Set:SADD");
    PrintUtil.two("2.1、scard(String key)：返回集合包含的元素数量【SCARD set】", "key=Set:SADD, size=" + size);

    Boolean exists = jedis.sismember("Set:SADD", "Set");
    PrintUtil.two("2.2、sismember(String key, String member)：检查集合是否包含了给定的元素【SISMEMBER set element】",
        "key=Set:SADD, member=Set, exists=" + exists);

    String value = jedis.srandmember("Set:SADD");
    PrintUtil.two("2.3、srandmember(String key)：随机地返回集合包含的元素【SRANDMEMBER set】", "key=Set:SADD, value=" + value);

    List<String> valueList = jedis.srandmember("Set:SADD", 2);
    PrintUtil.two("2.4、srandmember(String key, int count)：随机地返回集合包含的元素【SRANDMEMBER set [count]】",
        "key=Set:SADD, count=2, valueList=" + valueList);

    Set<String> valueSet = jedis.smembers("Set:SADD");
    PrintUtil.two("2.5、smembers(String key)：返回集合包含的所有元素【SRANDMEMBER set】", "key=Set:SADD, valueSet=" + valueSet);

    ScanResult<String> scanResult = jedis.sscan("Set:SADD", "0");
    PrintUtil.two("2.6、sscan(String key, String cursor)：以渐进的方式返回集合包含的元素【SSCAN set cursor】",
        "key=Set:SADD, scanResult=" + scanResult.getResult());

    ScanParams scanParams = new ScanParams();
    scanParams.match("*一*");
    scanParams.count(2);
    ScanResult<String> scanResult2 = jedis.sscan("Set:SADD", "0", scanParams);
    PrintUtil.two(
        "2.6.1、sscan(String key, String cursor, ScanParams params))：以渐进的方式返回集合包含的元素【SSCAN set cursor [MATCH pattern] [COUNT count]】",
        "key=Set:SADD, scanParams={match:一*,count:2}, scanResult=" + scanResult2.getResult());
  }
  // 3、Set的集合运算
  private static void showByOperation(Jedis jedis) {
    PrintUtil.one("3、Set的集合运算：");

    Set<String> result = jedis.sdiff("Set:SADD", "Set:SMOVE");
    PrintUtil.two("3.1、sdiff(String... keys)：计算并返回多个集合的差集计算结果【SDIFF set [set ...]】",
        "key1=Set:SADD, key2=Set:SMOVE, result=" + result);
    
    Long size = jedis.sdiffstore("Set:SDIFFSTORE", "Set:SADD", "Set:SMOVE");
    PrintUtil.two("3.2、sdiffstore(String dstkey, String... keys)：对多个集合执行差集计算，并将结果储存到目标集合当中【SDIFFSTORE target_set set [set ...]】", 
        "dstKey=Set:SDIFFSTORE, key1=Set:SADD, key2=Set:SMOVE, size=" + size);
    
    Set<String> result2 = jedis.sinter("Set:SADD", "Set:SDIFFSTORE");
    PrintUtil.two("3.3、sinter(String... keys)：计算并返回多个集合的交集计算结果【SINTER set [set ...]】",
        "key1=Set:SADD, key2=Set:SDIFFSTORE, result=" + result2);
    
    Long size2 = jedis.sdiffstore("Set:SINTERSTORE", "Set:SADD", "Set:SDIFFSTORE");
    PrintUtil.two("3.4、sdiffstore(String dstkey, String... keys)：对多个集合执行交集计算，并将结果储存到目标集合当中【SDIFFSTORE target_set set [set ...]】", 
        "dstKey=Set:SINTERSTORE, key1=Set:SADD, key2=Set:SDIFFSTORE, size=" + size2);
    
    Set<String> result3 = jedis.sunion("Set:SADD", "Set:SINTERSTORE");
    PrintUtil.two("3.5、sinter(String... keys)：计算并返回多个集合的并集计算结果【SINTER set [set ...]】",
        "key1=Set:SADD, key2=Set:SINTERSTORE, result=" + result3);
    
    Long size3 = jedis.sunionstore("Set:SUNIONSTORE", "Set:SADD", "Set:SINTERSTORE");
    PrintUtil.two("3.6、sdiffstore(String dstkey, String... keys)：对多个集合执行并集计算，并将结果储存到目标集合当中【SDIFFSTORE target_set set [set ...]】", 
        "dstKey=Set:SUNIONSTORE, key1=Set:SADD, key2=Set:SINTERSTORE, size=" + size3);
  }
}
```

---
### <a id="a_jZSet">十五、使用Jedis API操作ZSet数据类型：</a> <a href="#a_jSet">last</a> <a href="#a_jDatabase">next</a>
ZSetCommand.java：
```Java
package com.mutistic.redis.jedis;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.ZParams.Aggregate;
// 使用Jedis API操作ZSet数据类型
public class ZSetCommand {
  public static void main(String[] args) {
    PrintUtil.one("使用Jedis API操作ZSet数据类型：");

    Jedis jedis = JedisUtil.getJedis();
    jedis.flushDB();

    showByManager(jedis);
    showByBatch(jedis);
    showByOperation(jedis);

    JedisUtil.close(jedis);
  }
  // 1、ZSet的元素的检测与管理
  private static void showByManager(Jedis jedis) {
    PrintUtil.one("1、ZSet的元素的检测与管理：");

    Map<String, Double> scoreMembers = new HashMap<String, Double>();
    scoreMembers.put("将给定的元素及其分值添加到有序集合", 3D);
    scoreMembers.put("Hello", 0D);
    scoreMembers.put("ZSet", 0D);
    scoreMembers.put("!", 2D);
    Long size = jedis.zadd("ZSet:ZADD", scoreMembers);
    PrintUtil.two("1.1、zadd(String key, Map<String, Double> scoreMembers)：将给定的元素及其分值添加到有序集合"
        + "【ZADD sorted_set score member [[score member] [score member] ...]】",
        "key=ZSet:ZADD, scoreMembers=" + PrintUtil.toString(scoreMembers) + ", size=" + size);

    Double score = jedis.zincrby("ZSet:ZADD", 1D, "ZSet");
    PrintUtil.two("1.2、zincrby(String key, double score, String member)：为元素的分值加上指定的整数增量"
        + "【ZINCRBY sorted_set increment member】",
        "key=ZSet:ZADD, member=ZSet, score=" + score);

    Double source2 = jedis.zscore("ZSet:ZADD", "Hello");
    PrintUtil.two("1.3、zscore(String key, String member)：返回给定元素的分值【ZSCORE sorted_set member】",
        "srckey=ZSet:ZADD, member=Hello, source=" + source2);

    Long size2 = jedis.zcard("ZSet:ZADD");
    PrintUtil.two("1.4、zcard(String key)：返回有序集合包含的元素数量【ZCARD sorted_set】", "key=ZSet:ZADD, size=" + size2);

    Long result = jedis.zrank("ZSet:ZADD", "将给定的元素及其分值添加到有序集合");
    PrintUtil.two("1.5、zrank(String key, String member)：返回有序集合元素在按照分值从小到大进行排列时，给定的元素在有序集合中所处的排名"
        + "【ZRANK sorted_set member】",
        "key=ZSet:ZADD, member=将给定的元素及其分值添加到有序集合, result=" + result);

    Long result2 = jedis.zrevrank("ZSet:ZADD", "ZSet");
    PrintUtil.two("1.6、zrevrank(String key, String member)：返回有序集合元素在按照分值从大到小进行排列时，给定的元素在有序集合中所处的排名"
        + "【ZREVRANK sorted_set member】",
        "key=ZSet:ZADD, member=将给定的元素及其分值添加到有序集合, result=" + result2);

  }

  // 2、ZSet的批量处理多个元素
  private static void showByBatch(Jedis jedis) {
    PrintUtil.one("2、ZSet的批量处理多个元素：");

    Long size = jedis.zcount("ZSet:ZADD", 0D, 1D);
    PrintUtil.two("2.1、zcount(String key, double min, double max)：返回有序集合中，分值介于指定区间之内的元素数量"
        + "【ZCOUNT sorted_set min max】",
        "key=ZSet:ZADD, mix=0D, max=1D, size=" + size);

    Set<String> valueSet = jedis.zrange("ZSet:ZADD", 0, 3);
    PrintUtil.two("2.2、zrange(String key, long start, long end)：按照分值从小到大的顺序，返回指定索引范围之内的元素"
        + "【ZRANGE sorted_set start end】",
        "key=ZSet:ZADD, start=0, end=3, valueSet=" + valueSet);

    Set<Tuple> tupleSet = jedis.zrangeWithScores("ZSet:ZADD", 0, 3);
    PrintUtil.two("2.2.1、zrangeWithScores(String key, long start, long end)："
        + "按照分值从小到大的顺序，返回指定索引范围之内的元素及其分值（可选）"
        + "【ZRANGE sorted_set start end [WITHSCORES]】",
        "key=ZSet:ZADD, start=0, end=3, tupleSet=" + tupleSet);

    Set<String> valueSet2 = jedis.zrevrange("ZSet:ZADD", 0, 3);
    PrintUtil.two("2.3、zrange(String key, long start, long end)："
        + "按照分值从大到小的顺序，返回指定索引范围之内的元素"
        + "【ZRANGE sorted_set start end】",
        "key=ZSet:ZADD, start=0, end=3, valueSet=" + valueSet2);

    Set<Tuple> tupleSet2 = jedis.zrevrangeWithScores("ZSet:ZADD", 0, 3);
    PrintUtil.two("2.3.1、zrangeWithScores(String key, long start, long end)："
        + "按照分值从大到小的顺序，返回指定索引范围之内的元素及其分值（可选）"
        + "【ZRANGE sorted_set start end [WITHSCORES]】",
        "key=ZSet:ZADD, start=0, end=3, tupleSet=" + tupleSet2);

    Set<String> valueSet3 = jedis.zrangeByScore("ZSet:ZADD", 0D, 3D, 0, 10);
    PrintUtil.two("2.4、zrangeByScore(String key, double min, double max, int offset, int count)："
        + "按照分值从小到大的顺序，返回指定分值范围之内的元素"
        + "【ZRANGEBYSCORE sorted_set min max [LIMIT offset count]】",
        "key=ZSet:ZADD, mix=0D, max=1D, offset=0, count=10, valueSet=" + valueSet3);

    Set<Tuple> tupleSet3 = jedis.zrangeByScoreWithScores("ZSet:ZADD", 0D, 3D, 0, 10);
    PrintUtil.two("2.4.1、zrangeByScoreWithScores(String key, double min, double max, int offset, int count)："
            + "按照分值从小到大的顺序，返回指定分值范围之内的元素及其分值（可选）"
            + "【ZRANGEBYSCORE sorted_set min max [WITHSCORES] [LIMIT offset count]】",
        "key=ZSet:ZADD,  mix=0D, max=1D, offset=0, count=10, tupleSet=" + tupleSet3);

    Set<String> valueSet4 = jedis.zrevrangeByScore("ZSet:ZADD", 0D, 3D, 0, 10);
    PrintUtil.two("2.5、zrangeByScore(String key, double min, double max, int offset, int count)："
            + "按照分值从大到小的顺序，返回指定分值范围之内的元素" + "【ZRANGEBYSCORE sorted_set min max [LIMIT offset count]】",
        "key=ZSet:ZADD, mix=0D, max=1D, offset=0, count=10, valueSet=" + valueSet4);

    Set<Tuple> tupleSet4 = jedis.zrevrangeByScoreWithScores("ZSet:ZADD", 0D, 3D, 0, 10);
    PrintUtil.two("2.5.1、zrangeByScoreWithScores(String key, double min, double max, int offset, int count)："
            + "按照分值从大到小的顺序，返回指定分值范围之内的元素及其分值（可选）"
            + "【ZRANGEBYSCORE sorted_set min max [WITHSCORES] [LIMIT offset count]】",
        "key=ZSet:ZADD,  mix=0D, max=1D, offset=0, count=10, tupleSet=" + tupleSet4);

    ScanResult<Tuple> scanResult = jedis.zscan("ZSet:ZADD", "0");
    PrintUtil.two("2.6、zscan(String key, String cursor)：以渐进的方式，返回有序集合包含的元素及其分值"
        + "【ZSCAN sorted_set cursor】",
        "key=Set:SADD, scanResult=" + scanResult.getResult());

    ScanParams scanParams = new ScanParams();
    scanParams.match("*e*");
    scanParams.count(2);
    ScanResult<Tuple> scanResult2 = jedis.zscan("ZSet:ZADD", "0", scanParams);
    PrintUtil.two("2.6.1、zscan(String key, String cursor, ScanParams params)：以渐进的方式，返回有序集合包含的元素及其分值"
        + "【ZSCAN sorted_set cursor [MATCH pattern] [COUNT count]】",
        "key=Set:SADD, scanParams={match:一*,count:2}, scanResult=" + scanResult2.getResult());

    Long result = jedis.zrem("ZSet:ZADD", "将给定的元素及其分值添加到有序集合");
    PrintUtil.two("2.7、zrem(String key, String... members)：从有序集合中移除指定的一个或多个元素"
        + "【ZREM sorted_set member [member ...]】",
        "key=ZSet:ZADD, members=将给定的元素及其分值添加到有序集合, result=" + result);
    
    Long result2 = jedis.zremrangeByRank("ZSet:ZADD", 2, 3);
    PrintUtil.two("2.8、zremrangeByRank(String key, long start, long end)："
        + "移除有序集合中，位于指定排名范围内的元素，其中元素按照分值从小到大进行排列"
        + "【ZREMRANGEBYRANK sorted_set start end]】",
        "key=ZSet:ZADD, start=3, end=3, result=" + result2);
    
    Long result3 = jedis.zremrangeByScore("ZSet:ZADD", 2D, 3D);
    PrintUtil.two("2.9、zremrangeByScore(String key, double start, double end)：移除有序集合中，分值位于指定范围内的元素"
        + "【ZREMRANGEBYSCORE sorted_set min max]】",
        "key=ZSet:ZADD, start=3D, end=3D, result=" + result3);
  }
  // 3、ZSet的集合运算
  private static void showByOperation(Jedis jedis) {
    PrintUtil.one("3、ZSet的集合运算：");
    
    Map<String, Double> scoreMembers = new HashMap<String, Double>();
    scoreMembers.put("Hello", 0D);
    scoreMembers.put("ZSet", 0D);
    scoreMembers.put("!", 2D);
    jedis.zadd("ZSet:Test", scoreMembers);

    Long size = jedis.zinterstore("ZSet:ZINTERSTORE", "ZSet:ZADD", "ZSet:Test");
    PrintUtil.two("3.1、zinterstore(String dstkey, String... sets)：对给定数量的有序集合执行交集计算，并将计算的结果储存到目标有序集合里面"
        + "【ZINTERSTORE target number】",
        "dstkey=ZSet:ZINTERSTORE, number=sets.length, sets={ZSet:ZADD,ZSet:Test}, size=" + size);
    
    ZParams zparams = new ZParams();
    zparams.weightsByDouble(1D, 2D); 
    zparams.aggregate(Aggregate.MIN);
    
    Long size2 = jedis.zinterstore("ZSet:ZINTERSTORE:ZPARAMS", zparams, "ZSet:ZADD", "ZSet:Test");
    PrintUtil.two("3.2、zinterstore(String dstkey, ZParams params, String... sets)：对给定数量的有序集合执行交集计算，并将计算的结果储存到目标有序集合里面"
        + "【ZINTERSTORE target number [sorted_set ...] [WEIGHTS weight [weight ...]][AGGREGATE SUM|MIN|MAX]】",
        "dstkey=ZSet:ZINTERSTORE:ZPARAMS, number=sets.length, params={weight=[1D,2D],aggregat=min}, sets={ZSet:ZADD,ZSet:Test}, size=" + size2);
    
    Long size3 = jedis.zunionstore("ZSet:ZUNIONSTORE", "ZSet:ZADD", "ZSet:Test");
    PrintUtil.two("3.3、zinterstore(String dstkey, String... sets)：对给定数量的有序集合执行并集计算，并将计算的结果储存到目标有序集合里面"
        + "【ZINTERSTORE target number】",
        "dstkey=ZSet:ZUNIONSTORE, number=sets.length, sets={ZSet:ZADD,ZSet:Test}, size=" + size3);
    
    Long size4 = jedis.zunionstore("ZSet:ZUNIONSTORE:ZPARAMS", zparams, "ZSet:ZADD", "ZSet:Test");
    PrintUtil.two("3.4、zinterstore(String dstkey, ZParams params, String... sets)：对给定数量的有序集合执行并集计算，并将计算的结果储存到目标有序集合里面"
        + "【ZINTERSTORE target number [sorted_set ...] [WEIGHTS weight [weight ...]][AGGREGATE SUM|MIN|MAX]】",
        "dstkey=ZSet:ZUNIONSTORE:ZPARAMS, number=sets.length, params={weight=[1,2,3],aggregat=min}, sets={ZSet:ZADD,ZSet:Test}, size=" + size4);
    
    Map<String, Double> scoreMembers2 = new HashMap<String, Double>();
    scoreMembers2.put("a", 0D);
    scoreMembers2.put("b", 1D);
    scoreMembers2.put("c", 2D);
    scoreMembers2.put("d", 3D);
    jedis.zadd("ZSet:Test2", scoreMembers2);
    
    Long size5 = jedis.zlexcount("ZSet:Test2", "-", "+");
    PrintUtil.two("3.5、zlexcount(String key, String min, String max)：统计有序集合里面，位于指定字典区间内的元素的数量"
        + "【ZLEXCOUNT sorted_set min max】",
        "key=ZSet:Test2, min=-, max=+, size=" + size5);
    
    Set<String> valueSet = jedis.zrangeByLex("ZSet:Test2", "[a", "(d");
    PrintUtil.two("3.6、zrangeByLex(String key, String min, String max)：按照从小到大的顺序，返回有序集合里面位于指定字典区间内的元素"
        + "【ZRANGEBYLEX sorted_set min max】",
        "key=ZSet:ZUNIONSTORE, min=[a, max=(d, valueSet=" + valueSet);
    
    Set<String> valueSet2 = jedis.zrangeByLex("ZSet:Test2", "(a", "[d", 0, 2);
    PrintUtil.two("3.6.1、zrangeByLex(String key, String min, String max, int offset, int count)：按照从小到大的顺序，返回有序集合里面位于指定字典区间内的元素"
        + "【RANGEBYLEX sorted_set min max [LIMIT offset count]】",
        "key=ZSet:ZUNIONSTORE, min=(a, max=(d, offset=0, count=2, valueSet=" + valueSet2);
    
    Long remSize = jedis.zremrangeByLex("ZSet:Test2", "[a", "[d");
    PrintUtil.two("3.7、zrangeByLex(String key, String min, String max)：从有序集合里面，移除位于指定大小范围之内的元素"
        + "【ZREMRANGEBYLEX sorted_set min max】",
        "key=ZSet:ZINTERSTORE, min=[a, max=[d, remSize=" + remSize);
  }
}
```

---
### <a id="a_jDatabase">十六、使用Jedis API操作数据库：</a> <a href="#a_jZSet">last</a> <a href="#a_jExpiration">next</a>
DatabaseCommand.java：
```Java
package com.mutistic.redis.jedis;
import java.util.List;
import java.util.Set;
import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
// 使用Jedis API操作数据库
public class DatabaseCommand {
	public static void main(String[] args) {
		PrintUtil.one("使用Jedis API操作ZSet数据类型：");

		Jedis jedis = JedisUtil.getJedis();
		
		prepareTestData(jedis);
		showByGet(jedis);
		showByDetection(jedis);
		showByManager(jedis);
		showByCommon(jedis);
		
		JedisUtil.close(jedis);
	}
	// 准备测试数据 
	private static void prepareTestData(Jedis jedis) {
		if(!jedis.exists("test")) {
			jedis.lpush("test", "1", "2", "3", "4");
		}
	}
	// 1、数据库的获取命令
	private static void showByGet(Jedis jedis) {
		PrintUtil.one("1、数据库的获取命令：");
		
		Set<String> keySet = jedis.keys("*e*");
		PrintUtil.two("1.1、keys(String pattern)：从数据库里面获取所有符合给定模式的键【KEYS pattern】",
				"pattern=*e*, keySet=" + keySet);
		
		ScanResult<String> scanKey = jedis.scan("0");
		PrintUtil.two("1.2、scan(String cursor)：以渐进的方式获取数据库中的键【SCAN cursor】",
				"cursor=0, scanKey=" + scanKey.getResult());

		ScanParams scanParams = new ScanParams();
		scanParams.match("*e*");
		scanParams.count(2);
		ScanResult<String> scanKey2 = jedis.scan("0", scanParams);
		PrintUtil.two("1.2.1、scan(String key, String cursor, ScanParams params)：以渐进的方式获取数据库中的键"
				+ "【SCAN cursor [MATCH pattern] [COUNT count]】",
				"cursor=0, scanParams={match:一*,count:2}, scanKey=" + scanKey2.getResult());
		
		String key = jedis.randomKey();
		PrintUtil.two("1.3、randomKey()：从数据库里面随机地返回一个键【RANDOMKEY】", "key=" + key);
		
		List<String> sortValueList = jedis.sort("test");
		PrintUtil.two("1.4、sort(String key)：对给定的键进行排序【SORT key】", "key=test, sortValueList=" + sortValueList);
		
		Long sortSize = jedis.sort("test", "SORT:test");
		PrintUtil.two("1.4.1、sort(String key, String dstkey)：对给定的键进行排序【SORT key】", "key=test, dstkey=SORT:test, sortSize=" + sortSize);
		
		// SORT命令参考：http://doc.redisfans.com/key/sort.html
		SortingParams sortingParams = new SortingParams();
		// BY：通过使用 BY 选项，可以让 uid 按其他键的元素来排序（默认情况下， SORT uid 直接按 uid 中的值排序）
		sortingParams.by("*e*");
		// [LIMIT offset count]：使用 LIMIT 修饰符限制返回结果
		//   offset 指定要跳过的元素数量。
		//   count 指定跳过 offset 个指定的元素之后，要返回多少个对象
		sortingParams.limit(0, 10);
		// [ASC/DESC]：升序降序
		sortingParams.desc(); 
		// [ALPHA]：使用 ALPHA 修饰符对字符串进行排序(SORT命令默认排序对象为数字)
		sortingParams.alpha(); 
		
		List<String> sortValueList2 = jedis.sort("ZSet:ZADD", sortingParams);
		PrintUtil.two("1.4.2、sort(String key, SortingParams sortingParameters)：对给定的键进行排序"
				+ "【SORT key [BY pattern] [LIMIT offset count] [GET pattern [GET pattern ...]][ASC/DESC] [ALPHA] [STORE destination]】", 
				"key=ZSet:ZADD,sortingParams={[BY pattern=*e*],[LIMIT:offset=0,count=10], DESC, ALPHA}, sortValueList=" + sortValueList2);
	}
	// 2、数据库的检测命令
	private static void showByDetection(Jedis jedis) {
		PrintUtil.one("2、数据库的检测命令：");
		
		Boolean exists = jedis.exists("test");
		PrintUtil.two("2.1、exists(String key)：检查给定的键是否存在于数据库【EXISTS key】",
				"key=test, exists=" + exists);
		
		Long dbSize = jedis.dbSize();
		PrintUtil.two("2.2、dbSize()：返回当前正在使用的数据库包含的键值对数量【DBSIZE】",
				"DBIndex=0, dbSize=" + dbSize);
		
		String type = jedis.type("ZSet:ZADD");
		PrintUtil.two("2.3、type(String key)：返回给定键储存的值的类型【TYPE key】",
				"key=ZSet:ZADD, type=" + type);
	}
	// 3、数据库的管理命令
	private static void showByManager(Jedis jedis) {
		PrintUtil.one("3、数据库的管理命令：");
		
		String oldKey = jedis.rename("test", "RENAME:Test");
		PrintUtil.two("3.1、rename(String oldkey, String newkey)：为给定键设置一个新名字【RENAME key new-key】",
				"oldKey=test, newKey=RENAME:Test, oldKey=" + oldKey);
		
		Long result = jedis.renamenx("RENAME:Test", "RENAMENX:Test");
		PrintUtil.two("3.2、renamenx(String oldkey, String newkey)：仅在新名字尚未被使用的情况下，为给定键设置一个新名字【RENAMENX key new-key】",
				"oldkey=RENAME:Test, newKey=RENAMENX:Test, result=" + result);
		
		Long result2 =jedis.move("RENAMENX:Test", 1);
		PrintUtil.two("3.3、move(String key, int dbIndex)：将当前数据库中的给定键移动到指定的数据库【MOVE key db】",
				"key=RENAMENX:Test, dbIndex=1, result=" + result2);
		
		Long dbIndex = jedis.getDB();
		PrintUtil.two("3.4、getDB()：获取当前数据库索引", "dbIndex=" + dbIndex);
		
		String result3 = jedis.select(1);
		PrintUtil.two("3.5、select(int index)：切换至指定的数据库【SELECT number】", "index=1, result=" + result3);
		
		Long result4 = jedis.del("RENAMENX:Test", "test");
		PrintUtil.two("3.6、del(String key)：从数据库中删除给定的一个或多个键【DEL key [key ...]】", "key=[RENAMENX:Test, test], result=" + result4);
		
		String result5 = jedis.flushDB();
		PrintUtil.two("3.7、flushDB()：删除当前数据库中的所有键【FLUSHDB】", "dbIndex=1, result=" + result5);
		
		String result6 = jedis.flushAll();
		PrintUtil.two("3.7、flushAll()：删除服务器中，所有数据库的所有键【FLUSHALL】", "result=" + result6);
	}
	// 4、 数据库的常用命令
	private static void showByCommon(Jedis jedis) {
		PrintUtil.one("4、数据库的常用命令：");
		
		String result = jedis.echo("测试连接");
		PrintUtil.two("4.1、echo(String string)：让服务器打印指定的消息，用于测试连接【ECHO message】", "string=测试连接, result=" + result);
		
		String result2 = jedis.ping();
		PrintUtil.two("4.2、ping()：向服务器发送一条 PING 消息，用于测试连接或者测量延迟值【PING】", "result=" + result2);
		
		String result3 = jedis.configSet("timeout", "10000");
		PrintUtil.two("4.3、configSet(String parameter, String value)：为给定的配置选项设置值【CONFIG SET option value】", 
				"parameter=timeout, value=10000, result=" + result3);
		
//		String result4 = jedis.auth(null);
		PrintUtil.two("4.4、auth(String password)：使用给定的密码连接服务器【AUTH password】", "password=null");
		
		List<String> parameterValueList = jedis.configGet("bind");
		PrintUtil.two("4.5、configGet(String pattern)：返回给定配置选项的值【CONFIG GET option】", 
				"parameter=timeout, parameterValueList=" + parameterValueList);
		
		// CONFIG REWRITE：对服务器的配置选项文件进行重写，并将改写后的文件储存在硬盘里面
		
		String result5 = jedis.configResetStat();
		PrintUtil.two("4.5、configResetStat()：重置服务器的某些统计数据【CONFIG RESETSTAT】", "result=" + result5);

		List<String> time = jedis.time();
		PrintUtil.two("4.6、time()：返回服务器当前的 UNIX 时间戳【TIME】", "time=" + time);
		
//		String info = jedis.info("redis.conf");
		String info = jedis.info();
		PrintUtil.two("4.7、info()：返回与服务器相关的统计信息【INFO [section]】", "info=" + info);

		String quit = jedis.quit();
		PrintUtil.two("4.8、quit()：请求服务器关闭与当前客户端的连接【QUIT】", "quit=" + quit);
		
		// SHUTDOWN [SAVE|NOSAVE]：关闭服务器 [SAVE|NOSAVE]：可选参数，是否备份数据。SAVE备份数据，NOSAVE不备份。
//		String shutdown = jedis.shutdown();
		PrintUtil.two("4.9、shutdown()：关闭服务器【SHUTDOWN [SAVE|NOSAVE]】", "shutdown");
	}
}
```

---
### <a id="a_jExpiration">十七、使用Jedis API管理过期时间：</a> <a href="#a_jDatabase">last</a> <a href="#a_jTransaction">next</a>
ExpirationCommand.java：
```Java
package com.mutistic.redis.jedis;
import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;
import redis.clients.jedis.Jedis;
// 使用Jedis API管理过期时间
public class ExpirationCommand {
	public static void main(String[] args) {
		PrintUtil.one("使用Jedis API管理过期时间：");

		Jedis jedis = JedisUtil.newJedis();
		
		prepareTestData(jedis);
		showBySet(jedis);
		showByGet(jedis);
		
		JedisUtil.close(jedis);
	}
	// 准备测试数据
	private static void prepareTestData(Jedis jedis) {
		jedis.set("t1", "a");
		jedis.set("t2", "b");
		jedis.set("t3", "c");
		jedis.set("t4", "d");
	}
	private static void showBySet(Jedis jedis) {
		PrintUtil.one("1、设置过期时间：");
		
		Long result = jedis.expire("t1", 10);
		PrintUtil.two("1.1、expire(String key, int seconds)：为键设置秒级精度的过期时间【EXPIRE key seconds】", 
				"key=t1, result=" + result);
		
		Long result2 = jedis.pexpire("t2", 10000l);
		PrintUtil.two("1.2、pexpire(String key, long milliseconds)：为键设置毫秒级精度的过期时间【PEXPIRE key milliseconds】", 
				"key=t2, result=" + result2);
		
		Long result3 = jedis.expireAt("t3", 10);
		PrintUtil.two("1.3、expireAt(String key, long unixTime)：为键设置秒级精度的过期 UNIX 时间戳【EXPIREAT key timestamp-in-seconds】", 
				"key=t3, result=" + result3);
		
		Long result4 = jedis.pexpireAt("t4", 100000l);
		PrintUtil.two("1.4、pexpireAt(String key, long millisecondsTimestamp)：为键设置毫秒精度的过期 UNIX 时间戳【PEXPIREAT key timestamp-in-milliseconds】", 
				"key=t4, result=" + result4);
	}
	private static void showByGet(Jedis jedis) {
		PrintUtil.one("2、查看剩余存活时间：");
		
		Long seconds = jedis.ttl("t1");
		PrintUtil.two("2.1、ttl(String key)：以秒级精度返回给定键的剩余存活时间【TTL key】", 
				"key=t1, seconds=" + seconds);
		
		Long milliseconds = jedis.pttl("t2");
		PrintUtil.two("2.2、pttl(String key)：以毫秒级精度返回给定键的剩余存活时间移除过期时间【PTTL key】", 
				"key=t2, milliseconds=" + milliseconds);
		
		Long result = jedis.persist("t2");
		PrintUtil.two("2.2、persist(String key)：移除键的过期时间【PERSIST key】", 
				"key=t2, result=" + result);
	}
}
```

---
### <a id="a_jTransaction">十八、使用Jedis API管理事务：</a> <a href="#a_jExpiration">last</a> <a href="#a_jPubSub">next</a>
TransactionCommand.java：
```Java
package com.mutistic.redis.jedis;
import java.util.List;
import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;
// 使用Jedis API管理事务
public class TransactionCommand {
	public static void main(String[] args) {
		PrintUtil.one("使用Jedis API管理事务：");

		Jedis jedis = JedisUtil.newJedis();
		
		jedis.set("t1", "a");
		
		showByBase(jedis);
		showByWath(jedis);
		
		JedisUtil.close(jedis);
	}
	private static void showByBase(Jedis jedis) {
		PrintUtil.one("1、基本事务操作：");

		Transaction transaction = jedis.multi();
		PrintUtil.two("1.1、multi()：开始一次事务【MULTI】", "transaction=" + transaction);

		Response<String> response = transaction.set("t1", "a");
		transaction.set("t2", "b");
		PrintUtil.two("1.1.1、Transaction.set(String key, String value)：使用Transaction方法操作Redis", 
				"key=t1, value=a, response=" + response);
		
		List<Object> execResult = transaction.exec();
		PrintUtil.two("1.2、Transaction.exec()：执行事务【EXEC】", "execResult=" + execResult);

		transaction = jedis.multi();
		String discardResult = transaction.discard();
		PrintUtil.two("1.2、Transaction.discard()：取消事务【DISCARD】", "discardResult=" + discardResult);
		
	}
	private static void showByWath(Jedis jedis) {
		PrintUtil.one("2、乐观锁事务操作：");
		
		String watchResult = jedis.watch("t1");
		PrintUtil.two("2.1、watch(String... keys)：监视给定的键，看它们在事务执行之前是否已被修改【WATCH key [key ...]】", 
				"watchResult=" + watchResult);
		
		String unwatchResult = jedis.unwatch();
		PrintUtil.two("2.2、unwatch()：取消对所有键的监视【UNWATCH】", 
				"unwatchResult=" + unwatchResult);
	}
}
```

---
### <a id="a_jPubSub">十九、使用Jedis API管理发布与订阅：</a> <a href="#a_jTransaction">last</a> <a href="#a_junit">next</a>
PubSubCommand.java：
```Java
package com.mutistic.redis.jedis.pubsub;
import java.util.List;
import java.util.Map;
import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
// 使用Jedis API管理发布与订阅
public class PubSubCommand {
	//  程序主入口
	public static void main(String[] args) {
		PrintUtil.one("使用Jedis API管理发布与订阅：");

		JedisPool jedisPool = JedisUtil.getJedisPool();

		// 订阅线程：接收消息
		final Jedis subJedis = jedisPool.getResource();
		Subscriber subscriber = new Subscriber();
		new Thread(new Runnable() {
			public void run() {
				try {
					// 使用subscriber订阅channels上的消息，这一句之后，线程进入订阅模式，阻塞。
					subJedis.subscribe(subscriber, "CHANNEL_1");
					PrintUtil.two("2.1、Jedis.subscribe(JedisPubSub jedisPubSub, String... channels)：订阅给定的一个或多个频道"
							+ "【SUBSCRIBE channel [channel ...]】",
							"jedisPubSub=" + subscriber + ", channels=CHANNEL_1");
					subJedis.psubscribe(subscriber, "*C*");
					PrintUtil.two("2.2、Jedis.psubscribe(JedisPubSub jedisPubSub, String... patterns)：订阅给定的一个或多个模式"
							+ "【PSUBSCRIBE pattern [pattern ...]】",
							"jedisPubSub=" + subscriber + ", patterns=*C*");
				} catch (Exception e) {
					PrintUtil.err(e.getMessage());
				}
			}
		}).start();

		// 主线程：发布消息到频道上
		Jedis pubJedis = jedisPool.getResource();
		showByLook(pubJedis);
		Publisher publisher = new Publisher(pubJedis, "CHANNEL_1");
//		 publisher.publish();
		publisher.publishByInput();
		
		subscriber.unsubscribe();
		PrintUtil.two("3.1、JedisPubSub.unsubscribe()：退订给定的一个或多个频道，如果没有给定频道则退订全部频道",
				"UNSUBSCRIBE [channel [channel ...]]");
		subscriber.punsubscribe();
		PrintUtil.two("3.2、JedisPubSub.punsubscribe()：退订给定的一个或多个模式，如果没有给定模式则退订全部模式",
				"PUNSUBSCRIBE [pattern [pattern ...]]");
	}
	//  4、查看订阅信息
	private static void showByLook(Jedis pubJedis) {
		PrintUtil.one("4、查看订阅信息：");
		List<String> patternList = pubJedis.pubsubChannels("*C*");
		PrintUtil.two("4.1、Jedis.pubsubChannels(String pattern)：列出当前被订阅的频道【PUBSUB CHANNELS [pattern]】",
				"pattern=*C*, patternList="+patternList);
		
		Map<String, String> channelMap = pubJedis.pubsubNumSub("CHANNEL_1");
		PrintUtil.two("4.2、Jedis.pubsubNumSub(String... channels)：返回给定频道的订阅者数量【PUBSUB NUMSUB [channel channel ...]】",
				"channels=CHANNEL_1, channelMap="+PrintUtil.toString(channelMap));
		
		Long numPat = pubJedis.pubsubNumPat();
		PrintUtil.two("4.3、Jedis.pubsubNumPat()：返回当前被订阅模式的数量【PUBSUB NUMPAT】",
				"numPat="+numPat);
	}

}
```
Publisher.java：
```Java
package com.mutistic.redis.jedis.pubsub;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.mutisitc.utils.JedisUtil;
import com.mutisitc.utils.PrintUtil;
import redis.clients.jedis.Jedis;
// 使用Jedis API管理发布与订阅
// 发布者：向频道推送消息
public class Publisher {
	private Jedis jedis;
	/** 发布频道 */
	private String channelName;
	public Publisher(Jedis jedis, String channelName) {
		this.jedis = jedis;  
		this.channelName = channelName;
	}
	public void publish() {
		PrintUtil.one("1、直接发布消息 ：");
		Long publishResult = jedis.publish(channelName, "测试消息");
		PrintUtil.two("1.1、(发布者)Jedis.publish(String channel, String message)：向指定频道发布一条消息【PUBLISH channel message】",
				"channel=" + channelName + ", message=测试消息, publishResult=" + publishResult);
		JedisUtil.close(jedis);
	}
	public void publishByInput() {
		PrintUtil.one("2、从控制台输入发布消息：");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				PrintUtil.one("请输入需要推送的消息：");
				String message = reader.readLine();
				if (!"quit".equals(message)) {
					Long publishResult = jedis.publish(channelName, message);
					PrintUtil.two("1.1、(发布者)Jedis.publish(String channel, String message)：向指定频道发布一条消息【PUBLISH channel message】",
							"channel=" + channelName + ", message=" + message + ", publishResult=" + publishResult);
				} else {
					JedisUtil.close(jedis);
					break;
				}
			}
		} catch (IOException e) {
			PrintUtil.err(e.getMessage());
		}
	}
}
```
Subscriber.java：
```Java
package com.mutistic.redis.jedis.pubsub;
import com.mutisitc.utils.PrintUtil;
import redis.clients.jedis.JedisPubSub;
// 使用Jedis API管理发布与订阅
// 订阅者：实现订阅抽象JedisPubSub，重写订阅、接收、退订方法
public class Subscriber extends JedisPubSub {
	/**
	 * 重写onSubscribe：订阅频道
	 * @param channel
	 * @param subscribedChannels
	 * @see redis.clients.jedis.JedisPubSub#onSubscribe(java.lang.String, int)
	 */
	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		PrintUtil.two("\n订阅者：Subscriber.onSubscribe()：订阅频道",
				"channel=" + channel + ", subscribedChannels=" + subscribedChannels);
	}
	/**
	 * 重写onMessage：接收到频道消息
	 * @param channel
	 * @param message
	 * @see redis.clients.jedis.JedisPubSub#onMessage(java.lang.String, java.lang.String)
	 */
	@Override
	public void onMessage(String channel, String message) {
		PrintUtil.two("订阅者：Subscriber.onMessage()：接收到频道消息", "channel=" + channel + ", message=" + message);
	}
	/**
	 * 重写onUnsubscribe：退订频道
	 * @param channel
	 * @param subscribedChannels
	 * @see redis.clients.jedis.JedisPubSub#onUnsubscribe(java.lang.String, int)
	 */
	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		PrintUtil.two("\n订阅者：Subscriber.onUnsubscribe()：退订频道",
				"channel=" + channel + ", subscribedChannels=" + subscribedChannels);
	}
	/**
	 * 重写onPSubscribe：订阅模式
	 * @param pattern
	 * @param subscribedChannels
	 * @see redis.clients.jedis.JedisPubSub#onPSubscribe(java.lang.String, int)
	 */
	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		PrintUtil.two("\n订阅者：Subscriber.onPSubscribe()：订阅模式",
				"pattern=" + pattern + ", subscribedChannels=" + subscribedChannels);
	}
	/**
	 * 重写onPMessage：接收到模式消息
	 * @param pattern
	 * @param channel
	 * @param message
	 * @see redis.clients.jedis.JedisPubSub#onPMessage(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void onPMessage(String pattern, String channel, String message) {
		PrintUtil.two("订阅者：Subscriber.onPMessage()：接收到模式消息",
				"pattern=" + pattern + ", channel=" + channel + ", message=" + message);
	}
	/**
	 * 重写onPUnsubscribe：退订模式
	 * @param pattern
	 * @param subscribedChannels
	 * @see redis.clients.jedis.JedisPubSub#onPUnsubscribe(java.lang.String, int)
	 */
	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		PrintUtil.two("\n订阅者：Subscriber.onPUnsubscribe()：退订模式",
				"pattern=" + pattern + ", subscribedChannels=" + subscribedChannels);
	}
}
```

---
### <a id="a_junit">二十、使用JUnit简单操作Redis：</a> <a href="#a_jPubSub">last</a> <a href="#a_springboot">next</a>
[org.springframework.data.redis.core](https://docs.spring.io/spring-data/redis/docs/current/api/org/springframework/data/redis/core/package-summary.html)  
RedisTemplateCommandByJUnit.java：
```Java
package com.mutistic.redis.junit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisZSetCommands.Aggregate;
import org.springframework.data.redis.connection.RedisZSetCommands.Weights;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.mutisitc.utils.PrintUtil;
import com.mutistic.redis.Application;
// 使用RedisTemplate API+JUnit简单操作Redis
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTemplateCommandByJUnit {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Test
	public void test() throws Exception {
		PrintUtil.one("1、使用RedisTemplate API+JUnit简单操作Redis：");
		PrintUtil.two("1.1、通过@Autowired自动注入org.springframework.data.redis.core.RedisTemplate<Object, Object>实例：", redisTemplate);
		
		operationString(redisTemplate.opsForValue());
		operationHash(redisTemplate.opsForHash());
		operationList(redisTemplate.opsForList());
		operationSet(redisTemplate.opsForSet());
		operationZSet(redisTemplate.opsForZSet());
	}
	/**
	 * 2、简单操作字符串：String
	 * @param opration：org.springframework.data.redis.core.ValueOperations<Object, Object>
	 */
	private void operationString(ValueOperations<Object, Object> opration) {
		PrintUtil.one("2、操作字符串：String：");
		
		opration.set("String:Set", "test");
		PrintUtil.two("2.1、ValueOperations.set(String key, String value)：为字符串键设置值【SET key value】", 
				"key=String:Set, value=test");
		
		Object value = opration.get("String:Set");
		PrintUtil.two("2.2、ValueOperations.get(Object key)获取字符串键的值【GET key】", "key=String:Set, value="+value);
		
		Object oldValue = opration.getAndSet("String:SET", "GETSET key new-value：为字符串键设置值");
		PrintUtil.two("2.3、ValueOperations.getAndSet(String key, String value)：为字符串键设置值【 GETSET key new-value】",
				"key=String:SET, oldValue"+ oldValue);
		
		opration.set("String:SetByTime", "test", 100l, TimeUnit.SECONDS);
		PrintUtil.two("2.4、ValueOperations.set(String key, String value, long timeout, TimeUnit unit)：设置字符串的过期时间"
				+ "【SET key value [EX seconds] [PX milliseconds]】",
				"key=String:SetByTime, value=test, timeout=100l, unit=java.util.concurrent.TimeUnit.SECONDS");
	}
	/**
	 * 3、简单操作哈希表：Hash
	 * @param operation：org.springframework.data.redis.core.HashOperations<Object, Object, Object>
	 */
	private void operationHash(HashOperations<Object, Object, Object> operation) {
		PrintUtil.one("3、简单操作哈希表：Hash：");

		operation.put("Hash:Put", "key1", "test");
		PrintUtil.two("3.1、HashOperations.put(String key, Object hashKey, Object value)：为散列中的键设置值【HSET hash key value】", 
				"key=Hash:Put, hashKey=key1, value=test");
		
		Object value = operation.get("Hash:Put", "key1");
		PrintUtil.two("3.2、HashOperations.get(String key, Object hashKey)：返回散列中与给定键相关联的值【HGET hash key】", 
				"key=Hash:Put, hashKey=key1, value="+value);
		
		Map<Object, Object> hashKeyMap = new HashMap<Object, Object>();
		hashKeyMap.put("key1", "t1");
		hashKeyMap.put("key2", "t2");
		operation.putAll("Hash:PutAll", hashKeyMap);
		PrintUtil.two("3.3、HashOperations.putAll(String key, Map<? extends Object, ? extends Object> m)：一次为散列中的多个键设置值"
				+ "【HMSET hash key value [key2 value]】", 
				"key=Hash:PutAll, hashKeyMap="+PrintUtil.toString(hashKeyMap));
		
		List<Object> hashKeyList = new ArrayList<Object>();
		hashKeyList.add("key1");
		hashKeyList.add("key2");
		List<Object> valueList = operation.multiGet("Hash:PutAll", hashKeyList);
		PrintUtil.two("3.3、HashOperations.multiGet(String key, Collection<Object> hashKeys)：一次获取散列中多个键的值"
				+ "【HMGET hash key [key2 ...]】", 
				"key=Hash:PutAll, hashKeys="+hashKeyList+", valueList="+valueList);
	}
	
	/**
	 * 4、简单操作列表：List
	 * @author mutisitic
	 * @date 2019年1月14日
	 * @param operation：org.springframework.data.redis.core.ListOperations<Object, Object>
	 */
	private void operationList(ListOperations<Object, Object> operation) {
		PrintUtil.one("4、简单操作列表：List：");
		
		Long size = operation.leftPush("List:LPUSH", "t1");
		PrintUtil.two("4.1、ListOperations.leftPush(Object key, Object value)：将一个元素添加到列表的左端。从头部加入元素（栈）先进后出。返回list的长度"
				+ "【LPUSH list item】", 
				"key=List:LPUSH, value=t1, size="+size);
		
		List<String> valueList = new ArrayList<String>();
		valueList.add("t1");
		valueList.add("t2");
		operation.leftPushAll("List:LPUSHALL", valueList);
		PrintUtil.two("4.2、ListOperations.leftPushAll(Object key, Object... values)：将一个或多个元素添加到列表的左端。从头部加入元素（栈）先进后出。返回list的长度"
				+ "【LPUSH list item [item ...]】", 
				"key=List:LPUSHALL, value="+valueList+", size="+size);
		
		Object oldValue = operation.rightPop("List:LPUSHALL");
		PrintUtil.two("4.3、ListOperations.rightPop(Object key)：移除并返回列表右端第一个元素。返回被移除元素的值【RPOP list】", 
				"key=List:LPUSHALL,  oldValue="+oldValue);
		
		operation.set("List:LPUSH", 0, "test");
		PrintUtil.two("4.4、ListOperations.set(Object key, long index, Object value)：把列表在指定索引上的值修改为给定的元素【LSET list index item】", 
				"key=List:LPUSH, index=0, value=test");
	}
	/**
	 * 5、简单操作集合：Set
	 * @param operation：org.springframework.data.redis.core.SetOperations<Object, Object>
	 */
	private void operationSet(SetOperations<Object, Object> operation) {
		PrintUtil.one("5、简单操作集合：Set：");

		Long size = operation.add("SET:ADD", "t1", "t2", "t3");
		PrintUtil.two("5.1、SetOperations.add(Object key, Object... values)：将一个或多个元素添加到集合当中"
				+ "【SADD set element [element ...]】", 
				"key=SET:ADD, values=[t1,t2,t3], size="+size);

		Long size2 = operation.remove("SET:ADD", "t1");
		PrintUtil.two("5.2、SetOperations.remove(Object key, Object... values)：移除集合中的一个或多个元素"
				+ "【SREM set element [element ...]】", 
				"key=SET:ADD, values=[t1], size="+size2);
		
		Set<Object> valueSet = operation.members("SET:ADD");
		PrintUtil.two("5.3、SetOperations.members(Object key)：返回集合包含的所有元素【SMEMBERS set】", 
				"key=SET:ADD,valueSet="+valueSet);
		
		operation.add("SET:Test", "1", "2", "3");
		Set<Object> valueList = operation.union("SET:ADD", "SET:Test");
		PrintUtil.two("5.4、SetOperations.union(Object key, Object otherKey)：返回集合包含的所有元素【SMEMBERS set】", 
				"key=SET:ADD, otherKey=SET:Test, valueList="+valueList);
	}
	/**
	 * 6、简单操作集合：ZSet
	 * @param operation：org.springframework.data.redis.core.ZSetOperations<Object, Object>
	 */
	private void operationZSet(ZSetOperations<Object, Object> operation) {
		PrintUtil.one("6、简单操作集合：zSet：");
		
		Boolean result = operation.add("ZSET:ADD", "1", 1D);
		PrintUtil.two("6.1、ZSetOperations.add(Object key, Object value, double score)：将给定的元素及其分值添加到有序集合，按照分数排序"
				+ "【ZADD sorted_set score member】", 
				"key=ZSET:ADD, value=1, score=1D, result="+result);
		
		Set<TypedTuple<Object>> tupleSet = new HashSet<ZSetOperations.TypedTuple<Object>>();
		TypedTuple<Object> t1 = new DefaultTypedTuple<Object>("t1", 1D);
		TypedTuple<Object> t2 = new DefaultTypedTuple<Object>("t2", 2D);
		tupleSet.add(t1);
		tupleSet.add(t2);
		Long addSize = operation.add("ZSET:AddByTuple", tupleSet);
		PrintUtil.two("6.2、ZSetOperations.add(Object key, Set<TypedTuple<Object>> tuples)：将给定的元素及其分值添加到有序集合，按照分数排序"
				+ "【ZADD sorted_set score member [[score member] [score member] ...]】", 
				"key=ZSET:AddByTuple, tuples="+PrintUtil.toString(tupleSet)+", addSize="+addSize);
		
		Long length = operation.count("ZSET:AddByTuple", 0d, 2d);
		PrintUtil.two("6.3、ZSetOperations.count(Object key, double min, double max)：返回有序集合中，分值介于指定区间之内的元素数量"
				+ "【ZCOUNT sorted_set min max】", 
				"key=ZSET:AddByTuple, min=0d, max=2d, length="+length);
		
		Set<TypedTuple<Object>> valueTupleSet = operation.rangeByScoreWithScores("ZSET:AddByTuple", 0d, 2d);
		PrintUtil.two("6.4、ZSetOperations.rangeByScoreWithScores(Object key, double min, double max)：按照分值从小到大的顺序，返回指定分值范围之内的元素"
				+ "【ZRANGEBYSCORE sorted_set min max [WITHSCORES] [LIMIT offset count]】", 
				"key=ZSET:AddByTuple, min=0d, max=2d, valueTupleSet="+PrintUtil.toString(valueTupleSet));
		
		
		List<Object> otherKeyList = new ArrayList<Object>();
		otherKeyList.add("ZSET:ADD");
		Long unionSize = operation.unionAndStore("ZSET:AddByTuple", otherKeyList, "ZSET:ZUNIONSTORE", 
				Aggregate.MAX, Weights.of(1d, 2d));
		PrintUtil.two("6.5、ZSetOperations.unionAndStore(Object key, Collection<Object> otherKeys, Object destKey, Aggregate aggregate, Weights weights)"
				+ "：对给定数量的有序集合执行并集计算，并将计算的结果储存到目标有序集合里面根据元素的大小对其进行处理"
				+ "【ZUNIONSTORE target number [sorted_set ...] [WEIGHTS weight [weight ...]][AGGREGATE SUM|MIN|MAX]]】", 
				"key=ZSET:AddByTuple, otherKeys=[ZSET:ADD], aggregate=Aggregate.MAX, weights=[1d,2d], unionSize="+unionSize);
	}
}
```
StringRedisTemplateCommandByJUnit.java：
```Java
package com.mutistic.redis.junit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisZSetCommands.Aggregate;
import org.springframework.data.redis.connection.RedisZSetCommands.Weights;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.mutisitc.utils.PrintUtil;
import com.mutistic.redis.Application;
// 使用StringRedisTemplate API+JUnit简单操作Redis
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class StringRedisTemplateCommandByJUnit {
	@Autowired
	private StringRedisTemplate template;

	@Test
	public void test() throws Exception {
		PrintUtil.one("1、使用StringRedisTemplate API+JUnit简单操作Redis：");
		PrintUtil.two("1.1、通过@Autowired自动注入org.springframework.data.redis.core.StringRedisTemplate实例：", template);
		
		operationString(template.opsForValue());
		operationHash(template.opsForHash());
		operationList(template.opsForList());
		operationSet(template.opsForSet());
		operationZSet(template.opsForZSet());
	}
	/**
	 * 2、简单操作字符串：String
	 * @param opration：org.springframework.data.redis.core.ValueOperations<String, String>
	 */
	private void operationString(ValueOperations<String, String> opration) {
		PrintUtil.one("2、操作字符串：String：");
		
		opration.set("String:Set", "test");
		PrintUtil.two("2.1、ValueOperations.set(String key, String value)：为字符串键设置值【SET key value】", 
				"key=String:Set, value=test");
		
		String value = opration.get("String:Set");
		PrintUtil.two("2.2、ValueOperations.get(Object key)获取字符串键的值【GET key】", "key=String:Set, value="+value);
		
		String oldValue = opration.getAndSet("String:SET", "GETSET key new-value：为字符串键设置值");
		PrintUtil.two("2.3、ValueOperations.getAndSet(String key, String value)：为字符串键设置值【 GETSET key new-value】",
				"key=String:SET, oldValue"+ oldValue);
		
		opration.set("String:SetByTime", "test", 100l, TimeUnit.SECONDS);
		PrintUtil.two("2.4、ValueOperations.set(String key, String value, long timeout, TimeUnit unit)：设置字符串的过期时间"
				+ "【SET key value [EX seconds] [PX milliseconds]】",
				"key=String:SetByTime, value=test, timeout=100l, unit=java.util.concurrent.TimeUnit.SECONDS");
	}
	/**
	 * 3、简单操作哈希表：Hash
	 * @param operation：org.springframework.data.redis.core.HashOperations<String, Object, Object>
	 */
	private void operationHash(HashOperations<String, Object, Object> operation) {
		PrintUtil.one("3、简单操作哈希表：Hash：");

		operation.put("Hash:Put", "key1", "test");
		PrintUtil.two("3.1、HashOperations.put(String key, Object hashKey, Object value)：为散列中的键设置值【HSET hash key value】", 
				"key=Hash:Put, hashKey=key1, value=test");
		
		Object value = operation.get("Hash:Put", "key1");
		PrintUtil.two("3.2、HashOperations.get(String key, Object hashKey)：返回散列中与给定键相关联的值【HGET hash key】", 
				"key=Hash:Put, hashKey=key1, value="+value);
		
		Map<Object, Object> hashKeyMap = new HashMap<Object, Object>();
		hashKeyMap.put("key1", "t1");
		hashKeyMap.put("key2", "t2");
		operation.putAll("Hash:PutAll", hashKeyMap);
		PrintUtil.two("3.3、HashOperations.putAll(String key, Map<? extends Object, ? extends Object> m)：一次为散列中的多个键设置值"
				+ "【HMSET hash key value [key2 value]】", 
				"key=Hash:PutAll, hashKeyMap="+PrintUtil.toString(hashKeyMap));
		
		List<Object> hashKeyList = new ArrayList<Object>();
		hashKeyList.add("key1");
		hashKeyList.add("key2");
		List<Object> valueList = operation.multiGet("Hash:PutAll", hashKeyList);
		PrintUtil.two("3.3、HashOperations.multiGet(String key, Collection<Object> hashKeys)：一次获取散列中多个键的值"
				+ "【HMGET hash key [key2 ...]】", 
				"key=Hash:PutAll, hashKeys="+hashKeyList+", valueList="+valueList);
	}
	/**
	 * 4、简单操作列表：List
	 * @param operation：org.springframework.data.redis.core.ListOperations<String, String>
	 */
	private void operationList(ListOperations<String, String> pperations) {
		PrintUtil.one("4、简单操作列表：List：");
		
		Long size = pperations.leftPush("List:LPUSH", "t1");
		PrintUtil.two("4.1、ListOperations.leftPush(Object key, Object value)：将一个元素添加到列表的左端。从头部加入元素（栈）先进后出。返回list的长度"
				+ "【LPUSH list item】", 
				"key=List:LPUSH, value=t1, size="+size);
		
		List<String> valueList = new ArrayList<String>();
		valueList.add("t1");
		valueList.add("t2");
		pperations.leftPushAll("List:LPUSHALL", valueList);
		PrintUtil.two("4.2、ListOperations.leftPushAll(Object key, Object... values)：将一个或多个元素添加到列表的左端。从头部加入元素（栈）先进后出。返回list的长度"
				+ "【LPUSH list item [item ...]】", 
				"key=List:LPUSHALL, value="+valueList+", size="+size);
		
		String oldValue = pperations.rightPop("List:LPUSHALL");
		PrintUtil.two("4.3、ListOperations.rightPop(Object key)：移除并返回列表右端第一个元素。返回被移除元素的值【RPOP list】", 
				"key=List:LPUSHALL,  oldValue="+oldValue);
		
		pperations.set("List:LPUSH", 0, "test");
		PrintUtil.two("4.4、ListOperations.set(Object key, long index, Object value)：把列表在指定索引上的值修改为给定的元素【LSET list index item】", 
				"key=List:LPUSH, index=0, value=test");
	}
	/**
	 * 5、简单操作集合：Set
	 * @param operation：org.springframework.data.redis.core.SetOperations<String, String>
	 */
	private void operationSet(SetOperations<String, String> operation) {
		PrintUtil.one("5、简单操作集合：Set：");

		Long size = operation.add("SET:ADD", "t1", "t2", "t3");
		PrintUtil.two("5.1、SetOperations.add(Object key, Object... values)：将一个或多个元素添加到集合当中"
				+ "【SADD set element [element ...]】", 
				"key=SET:ADD, values=[t1,t2,t3], size="+size);

		Long size2 = operation.remove("SET:ADD", "t1");
		PrintUtil.two("5.2、SetOperations.remove(Object key, Object... values)：移除集合中的一个或多个元素"
				+ "【SREM set element [element ...]】", 
				"key=SET:ADD, values=[t1], size="+size2);
		
		Set<String> valueSet = operation.members("SET:ADD");
		PrintUtil.two("5.3、SetOperations.members(Object key)：返回集合包含的所有元素【SMEMBERS set】", 
				"key=SET:ADD,valueSet="+valueSet);
		
		operation.add("SET:Test", "1", "2", "3");
		Set<String> valueList = operation.union("SET:ADD", "SET:Test");
		PrintUtil.two("5.4、SetOperations.union(Object key, Object otherKey)：返回集合包含的所有元素【SMEMBERS set】", 
				"key=SET:ADD, otherKey=SET:Test, valueList="+valueList);
	}
	/**
	 * 6、简单操作集合：ZSet
	 * @param operation：org.springframework.data.redis.core.ZSetOperations<String, String>
	 */
	private void operationZSet(ZSetOperations<String, String> operation) {
		PrintUtil.one("6、简单操作集合：zSet：");
		
		Boolean result = operation.add("ZSET:ADD", "1", 1D);
		PrintUtil.two("6.1、ZSetOperations.add(Object key, Object value, double score)：将给定的元素及其分值添加到有序集合，按照分数排序"
				+ "【ZADD sorted_set score member】", 
				"key=ZSET:ADD, value=1, score=1D, result="+result);
		
		Set<TypedTuple<String>> tupleSet = new HashSet<ZSetOperations.TypedTuple<String>>();
		TypedTuple<String> t1 = new DefaultTypedTuple<String>("t1", 1D);
		TypedTuple<String> t2 = new DefaultTypedTuple<String>("t2", 2D);
		tupleSet.add(t1);
		tupleSet.add(t2);
		Long addSize = operation.add("ZSET:AddByTuple", tupleSet);
		PrintUtil.two("6.2、ZSetOperations.add(Object key, Set<TypedTuple<Object>> tuples)：将给定的元素及其分值添加到有序集合，按照分数排序"
				+ "【ZADD sorted_set score member [[score member] [score member] ...]】", 
				"key=ZSET:AddByTuple, tuples="+PrintUtil.toString(tupleSet)+", addSize="+addSize);
		
		Long length = operation.count("ZSET:AddByTuple", 0d, 2d);
		PrintUtil.two("6.3、ZSetOperations.count(Object key, double min, double max)：返回有序集合中，分值介于指定区间之内的元素数量"
				+ "【ZCOUNT sorted_set min max】", 
				"key=ZSET:AddByTuple, min=0d, max=2d, length="+length);
		
		Set<TypedTuple<String>> valueTupleSet = operation.rangeByScoreWithScores("ZSET:AddByTuple", 0d, 2d);
		PrintUtil.two("6.4、ZSetOperations.rangeByScoreWithScores(Object key, double min, double max)：按照分值从小到大的顺序，返回指定分值范围之内的元素"
				+ "【ZRANGEBYSCORE sorted_set min max [WITHSCORES] [LIMIT offset count]】", 
				"key=ZSET:AddByTuple, min=0d, max=2d, valueTupleSet="+PrintUtil.toString(valueTupleSet));
		
		
		List<String> otherKeyList = new ArrayList<String>();
		otherKeyList.add("ZSET:ADD");
		Long unionSize = operation.unionAndStore("ZSET:AddByTuple", otherKeyList, "ZSET:ZUNIONSTORE", 
				Aggregate.MAX, Weights.of(1d, 2d));
		PrintUtil.two("6.5、ZSetOperations.unionAndStore(Object key, Collection<Object> otherKeys, Object destKey, Aggregate aggregate, Weights weights)"
				+ "：对给定数量的有序集合执行并集计算，并将计算的结果储存到目标有序集合里面根据元素的大小对其进行处理"
				+ "【ZUNIONSTORE target number [sorted_set ...] [WEIGHTS weight [weight ...]][AGGREGATE SUM|MIN|MAX]]】", 
				"key=ZSET:AddByTuple, otherKeys=[ZSET:ADD], aggregate=Aggregate.MAX, weights=[1d,2d], unionSize="+unionSize);
	}
}
```
TransactionCommandByJUnit.java：
```Java
package com.mutistic.redis.junit;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.mutisitc.utils.PrintUtil;
import com.mutistic.redis.Application;
// 使用StringRedisTemplate API+JUnit简单操作Redis事物
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TransactionCommandByJUnit {

	@Autowired
	private StringRedisTemplate template;

	@Test
	public void test() throws Exception {
		PrintUtil.one("1、使用StringRedisTemplate API+JUnit简单操作Redis：");
		PrintUtil.two("1.1、通过@Autowired自动注入org.springframework.data.redis.core.StringRedisTemplate实例：", template);
		
		template.setEnableTransactionSupport(true);
		PrintUtil.two("2.1、StringRedisTemplate.setEnableTransactionSupport(true)：开启redis事务支持，默认关闭", 
				"RedisTemplate.enableTransactionSupport=true");
		
		template.multi();
		PrintUtil.two("2.2、StringRedisTemplate.multi()：开始一次事务", "MULTI");
		
		template.opsForValue().set("SET:String", "test");
		template.opsForValue().setIfAbsent("SET:String", "test2");
		PrintUtil.two("2.3、StringRedisTemplate.setIfAbsent(String key, String value)：仅在字符串键尚未有值的情况下，为它设置值"
				+ "【SETNX key value】", "key=SET:String, value=test2");
		template.opsForValue().set("SET:String3", "test3");

		List<Object> execResult = template.exec();
		PrintUtil.two("2.4、StringRedisTemplate.exec()：执行事务【EXEC】", "execResult="+execResult);
	}
}
```

---
### <a id="a_springboot">二十一、使用SpringBoot中使用Redis：</a> <a href="#a_junit">last</a> <a href="#a_appendix">next</a>
1、在SpringBoot Run启动时使用redis：  
ApplicationByRedis.java：
```Java
package com.mutistic.redis.connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import com.mutisitc.utils.PrintUtil;
// 在SpringBoot Run启动时使用redis
@SpringBootApplication
public class ApplicationByRedis {

	public static void main(String[] args) {
		ConfigurableApplicationContext context  = SpringApplication.run(ApplicationByRedis.class, args);
		PrintUtil.one("在spring boot run启动时使用redis：");
		
		PrintUtil.two("1、通过SpringApplication.run()获取ConfigurableApplicationContext实例：：", context);
		
		StringRedisTemplate stringRedisTemplate = context.getBean(StringRedisTemplate.class);
		PrintUtil.two("2、通过ConfigurableApplicationContext.getBean(StringRedisTemplate.class)"
				+ "获取org.springframework.data.redis.core.StringRedisTemplate实例：：", stringRedisTemplate);
		
		stringRedisTemplate.opsForValue().set("String:Run", "Hello StringRedisTemplate By Run");
		PrintUtil.two("3、通过StringRedisTemplate.opsForValue().set()：", "字符串键设置值");
		
		String value = stringRedisTemplate.opsForValue().get("String:Run");
		PrintUtil.two("4、通过StringRedisTemplate.opsForValue().get(Object key)获取字符串键的值：", value);
		
		PrintUtil.two("注意：redis连接超时时间（单位毫秒）不能设置为小于等于0的数字，"
				+ "小于0会导致启动报错，等于0会照成直接超时，与原本redis定义的timeout=0（禁止含义冲突）。可以不设置或设置为：", "spring.redis.timeout=1000000");
		
		context.close();
	}
}
```
2、在Controller中使用Redis：  
ApplicationByRedis.java：
```Java
package com.mutistic.redis.connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// 在Controller中使用Redis
@RestController
@RequestMapping("/redisController/")
public class RedisController {
	// 自动注入StringRedisTemplate实例
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 为字符串键设置值
	 * @param key 字符串键
	 * @param value 字符串值
	 * @return
	 */
	@GetMapping("setString")
	public String set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
		return "SUCCESS";
	}
	/**
	 * 获取字符串键的值
	 * @param key 字符串键
	 * @return 字符串值
	 */
	@GetMapping("getString")
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}
}
```

---
### <a id="a_appendix">附录：</a> <a href="#a_springboot">last</a> <a href="#a_notes">next</a>
<a id="a_appendix1">[附录A：Redis操作命令速查表](https://github.com/mutistic/mutistic.database/blob/master/com.mutistic.database.redis/redis-command.md)</a>  
<a id="a_appendix2">[附录B：Redis配置文件说明](https://github.com/mutistic/mutistic.database/blob/master/com.mutistic.database.redis/redis-conf.md)</a>

---
### <a id="a_notes">[Notes](https://github.com/mutistic/mutistic.database/blob/master/com.mutisitc.database.hibernate/notes)</a> <a href="#a_appendix1">last</a> <a href="#a_catalogue">next</a>

---
<a id="a_down"></a>  
<a href="#a_top">Top</a> 
<a href="#a_catalogue">Catalogue</a>
