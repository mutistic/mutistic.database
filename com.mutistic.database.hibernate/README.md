# <a id="a_top">Hibernate：对象关系映射框架</a>
[Hibernate 官网](http://hibernate.org/)  
[Hibernate 百度百科](https://baike.baidu.com/item/Hibernate/206989)  
[Hibernate 5.3 文档](http://hibernate.org/orm/documentation/5.3)  
[Hibernate 5.3 用户指南](http://docs.jboss.org/hibernate/orm/5.3/userguide/html_single/Hibernate_User_Guide.html)  
[Hibernate 5.3 JavaDoc](http://docs.jboss.org/hibernate/orm/5.3/javadocs/overview-summary.html)  
[Hibernate Git开源](https://github.com/hibernate/)  
[Hibernate Tool](http://hibernate.org/tools/)  
[Hibernate5:hibernate-core-5.2.17.Final.jar](https://search.maven.org/artifact/org.hibernate/hibernate-core/5.2.17.Final/jar)  

|作者|Mutistic|
|---|---|
|邮箱|mutistic@qq.com|

---
### <a id="a_catalogue">目录</a>：
1. <a href="#a_hibernate">Hibernate：对象关系映射框架</a>
2. <a href="#a_integration">集成Hibernate框架</a>
98. <a href="#a_notes">Notes</a>
99. <a href="#a_down">down</a>

---
### <a id="a_hibernate">一、Hibernate：对象关系映射框架：</a> <a href="#a_catalogue">last</a> <a href="#a_integration">next</a>
[Hibernate：对象关系映射框架](https://baike.baidu.com/item/Hibernate/206989)  
[ORM框架](https://baike.baidu.com/item/ORM%E6%A1%86%E6%9E%B6)  
[数据持久化](https://baike.baidu.com/item/%E6%95%B0%E6%8D%AE%E6%8C%81%E4%B9%85%E5%8C%96/5777076)  
一、Hibernate是什么：
```
  Hibernate：开放源代码的对象关系映射(Object/Relation Mapping)框架。
  Hibernate是一个开放源代码的对象关系映射框架，它对JDBC进行了非常轻量级的对象封装，它将POJO与数据库表建立映射关系，是一个全自动的orm框架，
hibernate可以自动生成SQL语句，自动执行，使得Java程序员可以随心所欲的使用对象编程思维来操纵数据库。 
Hibernate可以应用在任何使用JDBC的场合，既可以在Java的客户端程序使用，也可以在Servlet/JSP的Web应用中使用，
最具革命意义的是，Hibernate可以在应用EJB的J2EE架构中取代CMP，完成数据持久化的重任
```
二、发展历程：
```
  2001年，澳大利亚墨尔本一位名为Gavin King的27岁的程序员，上街买了一本SQL编程的书，他厌倦了实体bean，
认为自己可以开发出一个符合对象关系映射理论，并且真正好用的Java持久化层框架，因此他需要先学习一下SQL。这一年的11月，Hibernate的第一个版本发布了。
  
  2002年，已经有人开始关注和使用Hibernate了。

  2003年9月，Hibernate开发团队进入JBoss公司，开始全职开发Hibernate，从这个时候开始Hibernate得到了突飞猛进的普及和发展。
  2004年，整个Java社区开始从实体bean向Hibernate转移，特别是在Rod Johnson的著作《Expert One-on-One J2EE Development without EJB》出版后，
 由于这本书以扎实的理论、充分的论据和详实的论述否定了EJB，提出了轻量级敏捷开发理念之后，
以Hibernate和Spring为代表的轻量级开源框架开始成为Java世界的主流和事实标准。
在2004年Sun领导的J2EE5.0标准制定当中的持久化框架标准正式以Hibernate为蓝本。

 2006年，J2EE5.0标准正式发布以后，持久化框架标准Java Persistent API（简称JPA）基本上是参考Hibernate实现的，
而Hibernate在3.2版本开始，已经完全兼容JPA标准
```
三、语言特点：
```
1、将对数据库的操作转换为对Java对象的操作，从而简化开发。通过修改一个“持久化”对象的属性从而修改数据库表中对应的记录数据。
2、提供线程和进程两个级别的缓存提升应用程序性能。
3、有丰富的映射方式将Java对象之间的关系转换为数据库表之间的关系。
4、屏蔽不同数据库实现之间的差异。在Hibernate中只需要通过“方言”的形式指定当前使用的数据库，就可以根据底层数据库的实际情况生成适合的SQL语句。
5、非侵入式：Hibernate不要求持久化类实现任何接口或继承任何类，POJO即可
```
四、Hibernate模块/组件：

|模块/组件|描述|
|---|---|
|hibernate-core|主(核心)Hibernate模块。定义其ORM功能和API以及各种集成SPI| 
|hibernate-envers|Hibernate的历史实体版本控制功能|
|hibernate-spatial|Hibernate的Spatial/GIS数据类型支持|
|hibernate-osgi|Hibernate支持在OSGI容器中运行| 
|hibernate-agroal|将Agroal连接池库集成到Hibernate中|
|hibernate-c3p0|将C3P0连接池库集成到Hibernate中|
|hibernate-hikaricp|将HikariCP连接池库集成到Hibernate中|
|hibernate-vibur|将Vibur DBCP连接池库集成到Hibernate中|
|hibernate-proxool|将Proxool连接池库集成到Hibernate中|
|hibernate-jcache|将JCache缓存规范集成到Hibernate中，使任何兼容的实现成为二级缓存提供者| 
|hibernate-ehcache|将Ehcache缓存库集成到Hibernate中作为二级缓存提供程序|

五、核心API
```
  Hibernate的API一共有6个，分别为:Session、SessionFactory、Transaction、Query、Criteria和Configuration。
通过这些接口，可以对持久化对象进行存取、事务控制。

  5.1、Session：
  Session接口负责执行被持久化对象的CRUD操作(CRUD的任务是完成与数据库的交流，包含了很多常见的SQL语句)。
但需要注意的是Session对象是非线程安全的。同时，Hibernate的session不同于JSP应用中的HttpSession。这里当使用session这个术语时，
其实指的是Hibernate中的session，而以后会将HttpSession对象称为用户session。

  5.2、SessionFactory：
  SessionFactory接口负责初始化Hibernate。它充当数据存储源的代理，并负责创建Session对象。这里用到了工厂模式。
需要注意的是SessionFactory并不是轻量级的，因为一般情况下，一个项目通常只需要一个SessionFactory就够，当需要操作多个数据库时，
可以为每个数据库指定一个SessionFactory。

  5.3、Transaction：
  Transaction 接口是一个可选的API，可以选择不使用这个接口，取而代之的是Hibernate 的设计者自己写的底层事务处理代码。 
Transaction 接口是对实际事务实现的一个抽象，这些实现包括JDBC的事务、JTA 中的UserTransaction、甚至可以是CORBA 事务。
之所以这样设计是能让开发者能够使用一个统一事务的操作界面，使得自己的项目可以在不同的环境和容器之间方便地移植。

  5.4、Query：
  Query接口让你方便地对数据库及持久对象进行查询，它可以有两种表达方式：HQL语言或本地数据库的SQL语句。
Query经常被用来绑定查询参数、限制查询记录数量，并最终执行查询操作。

  5.5、Criteria：
  Criteria接口与Query接口非常类似，允许创建并执行面向对象的标准化查询。值得注意的是Criteria接口也是轻量级的，它不能在Session之外使用。

  5.6、Configuration
  Configuration 类的作用是对Hibernate 进行配置，以及对它进行启动。在Hibernate 的启动过程中，Configuration 类的实例首先定位映射文档的位置，
读取这些配置，然后创建一个SessionFactory对象。虽然Configuration 类在整个Hibernate 项目中只扮演着一个很小的角色，
但它是启动hibernate 时所遇到的第一个对象。
```
六、Hibernate包作用：

|模块/组件|描述|
|---|---|
|net.sf.hibernate.*|该包的类基本上都是接口类和异常类|
|net.sf.hibernate.cache.*|JCS的实现类|
|net.sf.hibernate.cfg.*|配置文件读取类|
|net.sf.hibernate.collection.*|Hibernate集合接口实现类，例如List，Set，Bag等等，Hibernate之所以要自行编写集合接口实现类是为了支持lazy loading|
|net.sf.hibernate.connection.*|几个数据库连接池的Provider|
|net.sf.hibernate.dialect.*|支持多种数据库特性，每个Dialect实现类代表一种数据库，描述了该数据库支持的数据类型和其它特点，例如是否有AutoIncrement，是否有Sequence，是否有分页sql等等|
|net.sf.hibernate. eg.*|Hibernate文档中用到的例子|
|net.sf.hibernate.engine.*|这个包的类作用比较散|
|net.sf.hibernate.expression.*|HQL支持的表达式|
|net.sf.hibernate.hq.*|HQL实现|
|net.sf.hibernate. id.*|ID生成器|
|net.sf.hibernate.impl.*|最核心的包，一些重要接口的实现类，如Session，SessionFactory，Query等|
|net.sf.hibernate.jca.*|JCA支持，把Session包装为支持JCA的接口实现类|
|net.sf.hibernate.jmx.*|JMX是用来编写App Server的管理程序的，大概是JMX部分接口的实现，使得App Server可以通过JMX接口管理Hibernate|
|net.sf.hibernate.loader.*|也是很核心的包，主要是生成sql语句的|
|net.sf.hibernate.lob.*|Blob和Clob支持|
|net.sf.hibernate.mapping.*|hbm文件的属性实现|
|net.sf.hibernate.metadata.*|PO的Meta实现|
|net.sf.hibernate.odmg.*|ODMG是一个ORM标准，这个包是ODMG标准的实现类|
|net.sf.hibernate.persister.*|核心包，实现持久对象和表之间的映射|
|net.sf.hibernate.proxy.*|Proxy和Lazy Loading支持|
|net.sf.hibernate. ps.*|该包是PreparedStatment Cache|
|net.sf.hibernate.sql.*|生成JDBC sql语句的包|
|net.sf.hibernate.test.*|测试类，你可以用junit来测试Hibernate|
|net.sf.hibernate.tool.hbm2ddl.*|用hbm配置文件生成DDL|
|net.sf.hibernate.transaction.*|Hibernate Transaction实现类|
|net.sf.hibernate.type.*|Hibernate中定义的持久对象的属性的数据类型|
|net.sf.hibernate.util.*|一些工具类，作用比较散|
|net.sf.hibernate.xml.*|XML数据绑定|

---
### <a id="a_integration">二、集成Hibernate框架：</a> <a href="#a_hibernate">last</a> <a href="#a_step">next</a>
1、Java Project直接集成Hibernate框架

2、Maven项目集成Hibernate框架

3、Spring项目集成Hibernate框架

4、Spring Boot项目集成Hibernate框架

---
### <a id="a_notes">[Notes](https://github.com/mutistic/mutistic.database/blob/master/com.mutisitc.database.hibernate/notes)</a> <a href="#a_transaction">last</a> <a href="#a_catalogue">next</a>

---
<a id="a_down"></a>  
<a href="#a_top">Top</a> 
<a href="#a_catalogue">Catalogue</a>
