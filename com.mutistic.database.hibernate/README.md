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
98. <a href="#a_notes">Notes</a>
99. <a href="#a_down">down</a>

---
### <a id="a_hibernate">一、Hibernate：对象关系映射框架：</a> <a href="#a_catalogue">last</a> <a href="#a_step">next</a>
[Hibernate：对象关系映射框架](https://baike.baidu.com/item/Hibernate/206989)  
[ORM框架](https://baike.baidu.com/item/ORM%E6%A1%86%E6%9E%B6)  
[数据持久化](https://baike.baidu.com/item/%E6%95%B0%E6%8D%AE%E6%8C%81%E4%B9%85%E5%8C%96/5777076)  
一、Hibernate是什么：
```
Hibernate：开放源代码的对象关系映射框架。
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



### <a id="a_notes">[Notes](https://github.com/mutistic/mutistic.database/blob/master/com.mutisitc.database.hibernate/notes)</a> <a href="#a_transaction">last</a> <a href="#a_catalogue">next</a>

---
<a id="a_down"></a>  
<a href="#a_top">Top</a> 
<a href="#a_catalogue">Catalogue</a>
