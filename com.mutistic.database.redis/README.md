# <a id="a_top">Redis：Key-Value内存数据库</a>
[Redis中文网](http://www.Redis.cn)  
[Redis操作命令详解](http://doc.redisfans.com)  
[Redis文档](http://www.redis.cn/documentation.html)   
[附录A：Redis操作命令速查表](https://github.com/mutistic/mutistic.database/blob/master/com.mutistic.database.redis/redis-command.md)  

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
97. <a href="#a_appendix1">附录A：Redis操作命令速查表</a>
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
    bind：默认Redis监听服务器上所有可用网络接口的连接，默认127.0.0.1
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
### <a id="a_list">五、list类型：</a> <a href="#a_hash">last</a> <a href="#">next</a>
一、Hash（哈希表）：
```
```
二、添加元素：
```
LPUSH list item [item ...]：将一个或多个元素添加到列表的左端
LPUSHX list item：仅在列表已经存在的情况下，将一个元素添加到列表的左端
RPUSH list item [item ...]：将一个或多个元素添加到列表的右端
RPUSHX list item：仅在列表已经存在的情况下，将一个元素添加到列表的右端
```
三、移除元素：
```
LPOP list：移除并返回列表左端第一个元素
RPOP list：移除并返回列表右端第一个元素
BLPOP list [list ...] timeout：在指定的时限内，移除首个非空列表的最左端元素
BRPOP list [list ...] timeout：在指定的时限内，移除首个非空列表的最右端元素移除元素然后添加元素
RPOPLPUSH source_list target_list：移除源列表的最右端元素，并将该元素添加到目标列表的左端
BRPOPLPUSH source_list target_list timeout：在指定的时限内，尝试移除源列表的最右端元素，并将该元素添加到目标列表的左端
```
四、元素的获取与管理：
```
LINDEX list index：获取列表在给定索引上的元素
LLEN list：返回列表包含的元素数量
LRANGE list start end：返回列表在指定索引范围内的所有元素
LINSERT list BEFORE|AFTER target item：将给定的元素插入到目标元素的前面或者后面
LREM list count item：从列表中移除给定的元素
LSET list index item：把列表在指定索引上的值修改为给定的元素
LTRIM list start end：对列表进行修剪，只保留指定索引范围内的元素
```

---
### <a id="a_appendix1">[附录A：Redis操作命令速查表](https://github.com/mutistic/mutistic.database/blob/master/com.mutistic.database.redis/redis-command.md)</a> <a href="#">last</a> <a href="#a_notes">next</a>

---
### <a id="a_notes">[Notes](https://github.com/mutistic/mutistic.database/blob/master/com.mutisitc.database.hibernate/notes)</a> <a href="#a_appendix1">last</a> <a href="#a_catalogue">next</a>

---
<a id="a_down"></a>  
<a href="#a_top">Top</a> 
<a href="#a_catalogue">Catalogue</a>
