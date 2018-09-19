# <a id="a_top">JDBC：Java数据库连接</a>
[Java 8-API](https://docs.oracle.com/javase/8/docs/api/overview-summary.html)  
[Java 10-API](https://docs.oracle.com/javase/10/docs/api/overview-summary.html)  
[JDBC](https://baike.baidu.com/item/jdbc)  
[Java数据库连接](https://baike.baidu.com/item/Java%E6%95%B0%E6%8D%AE%E5%BA%93%E8%BF%9E%E6%8E%A5)  
[JDBC-API](https://docs.oracle.com/javase/10/docs/api/java.sql-summary.html)  
[Search Maven](https://search.maven.org/search?q=search)  
[mysql:mysql-connector-java:8.0.12](https://search.maven.org/artifact/mysql/mysql-connector-java/8.0.12/jar)  

|作者|Mutistic|
|---|---|
|邮箱|mutistic@qq.com|

---
### <a id="a_catalogue">目录</a>：
1. <a href="#a_jdbc">JDBC：Java数据库连接</a>
2. <a href="#a_step">JDBC连接数据库步骤</a>
3. <a href="#a_driver">java.sql.Driver：数据库驱动</a>
4. <a href="#a_manager">java.sql.DriverManager：驱动管理</a>
5. <a href="#a_connection">java.sql.Connection：数据库连接</a>

99. <a href="#a_down">down</a>

---
### <a id="a_jdbc">一、JDBC：Java数据库连接：</a> <a href="#a_catalogue">last</a> <a href="#a_step">next</a>
[JDBC数据库驱动模型](https://github.com/mutistic/mutistic.database/blob/master/com.mutisitc.database.jdbc/notes/01_JDBCPModel.png)  
一、定义：
```
JDBC：
  全称Java DataBase Connectivity，Java 数据库连接，是一种用于执行SQL的Java API，可以为多种关系型数据库提供统一访问，
它由一组用Java语言编写的类和接口组成。JDBC提供了一种基准：是Java语言中用来规范客户端程序如何来访问数据库的应用程序接口，
据此可以构建更高级的工具和接口，使数据库开发人员能够编写数据库应用程序

  JDBC也是Sun Microsystems的商标。通常说的JDBC是面向关系型数据库的
```

二、了解JDBC：  
[DBMS：Database Management System，数据库管理系统](https://baike.baidu.com/item/%E6%95%B0%E6%8D%AE%E5%BA%93%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F)  
[RDBMS：Relational Database Management System，关系数据库管理系统](https://baike.baidu.com/item/RDBMS)
```
  有了JDBC，向各种关系数据发送SQL语句就是一件很容易的事。换言之，有了JDBC API，就不必为访问
Sybase数据库[一种典型的UNIX或WindowsNT平台上客户机/服务器环境下的大型数据库系统]专门写一个程序，
为访问Oracle数据库又专门写一个程序，或为访问Informix数据库[IBM公司出品的关系数据库管理系统（RDBMS）家族]又编写另一个程序等等，
程序员只需用JDBC API写一个程序就够了，它可向相应数据库发送SQL调用。同时，将Java语言和JDBC结合起来使程序员不必为不同的平台编写不同的应用程序，
只须写一遍程序就可以让它在任何平台上运行，这也是Java语言“编写一次，处处运行”的优势。

  Java数据库连接体系结构是用于Java应用程序连接数据库的标准方法。JDBC对Java程序员而言是API，对实现与数据库连接的服务提供商而言是接口模型。
作为API，JDBC为程序开发提供标准的接口，并为数据库厂商及第三方中间件厂商实现与数据库的连接提供了标准方法。
JDBC使用已有的SQL标准并支持与其它数据库连接标准，如ODBC之间的桥接。JDBC实现了所有这些面向标准的目标并且具有简单、严格类型定义且高性能实现的接口。

  Java具有坚固、安全、易于使用、易于理解和可从网络上自动下载等特性，是编写数据库应用程序的杰出语言。
所需要的只是Java应用程序与各种不同数据库之间进行对话的方法。而JDBC正是作为此种用途的机制。

  JDBC扩展了Java的功能。例如，用Java和JDBC API可以发布含有applet的网页，而该applet使用的信息可能来自远程数据库。
企业也可以用JDBC通过Intranet[企业内部网]将所有职员连到一个或多个内部数据库中（即使这些职员所用的计算机有 Windows、 Macintosh[Mac] 
和UNIX 等各种不同的操作系统）。随着越来越多的程序员开始使用Java 编程语言，对从Java中便捷地访问数据库的要求也在日益增加。

  MIS[管理信息系统，Management Information System]管理员们都喜欢Java和JDBC的结合，因为它使信息传播变得容易和经济。
企业可继续使用它们安装好的数据库，并能便捷地存取信息，即使这些信息是储存在不同数据库管理系统上。新程序的开发期很短。
安装和版本控制将大为简化。程序员可只编写一遍应用程序或只更新一次，然后将它放到服务器上，随后任何人就都可得到最新版本的应用程序。
对于商务上的销售信息服务，Java和JDBC可为外部客户提供获取信息更新的更好方法。
```

三、用途
```
JDBC 可做三件事：与数据库建立连接、发送 操作数据库的语句并处理结果：
代码示例：
    Class.forName("xxx.xxx.xxx.Driver");
    Connection con = DriverManager.getConnection("jdbcUrl", "username", "password");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT colunm1,colunm2 FROM table");
    while (rs.next()) {
        int colunm1 = rs.getInt("colunm1");
        String colunm2 = rs.getString("colunm2");
    }
```

四、API概述：  
[JDBC-API](https://docs.oracle.com/javase/8/docs/api/java.sql-summary.html)  
[javax.sql包](https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html)  
[javax.sql包](https://docs.oracle.com/javase/8/docs/api/javax/sql/package-summary.html)
```
4.1、JDBC API主要位于JDK中的java.sql包中（之后扩展的内容位于javax.sql包中），主要包括：
  DriverManager：负责加载各种不同驱动程序（Driver），并根据不同的请求，向调用者返回相应的数据库连接（Connection）。
  Driver：驱动程序，会将自身加载到DriverManager中去，并处理相应的请求并返回相应的数据库连接（Connection）。
  Connection：数据库连接，负责与进行数据库间通讯，SQL执行以及事务处理都是在某个特定Connection环境中进行的。可以产生用以执行SQL的Statement。
  Statement：用以执行SQL查询和更新（针对静态SQL语句和单次执行）。
  PreparedStatement：用以执行包含动态参数的SQL查询和更新（在服务器端编译，允许重复执行以提高效率）。
  CallableStatement：用以调用数据库中的存储过程。
  SQLException：代表在数据库连接的建立和关闭和SQL语句的执行过程中发生了例外情况（即错误）

4.2、JDBC是个"低级"接口，也就是说，它用于直接调用SQL命令。在这方面它的功能极佳，并比其它的数据库连接API易于使用，
但它同时也被设计为一种基础接口，在它之上可以建立高级接口和工具。高级接口是"对用户友好的"接口，
它使用的是一种更易理解和更为方便的 API，这种API在幕后被转换为诸如JDBC这样的低级接口。

  在关系数据库的"对象/关系"映射中，表中的每行对应于类的一个实例，而每列的值对应于该实例的一个属性。
于是，程序员可直接对 Java 对象进行操作；存取数据所需的SQL调用将在"掩盖下"自动生成。此外还可提供更复杂的映射，
例如将多个表中的行结合进一个 Java 类中。

  随着人们对JDBC的兴趣日益增涨，越来越多的开发人员一直在使用基于JDBC的工具，以使程序的编写更加容易。
程序员也一直在编写力图使最终用户对数据库的访问变得更为简单的应用程序。例如应用程序可提供一个选择数据库任务的菜单。
任务被选定后，应用程序将给出提示及空白供填写执行选定任务所需的信息。所需信息输入应用程序将自动调用所需的SQL命令。
在这样一种程序的协助下，即使用户根本不懂SQL的语法，也可以执行数据库任务
```
4.3、从SQL到Java数据类型映射的JDBC规范：

|SQL类型|Java类型|
|---|---|
|CHAR|java.lang.String|
|VARCHAR|java.lang.String|
|LONGVARCHAR|java.lang.String|
|NUMERIC|java.math.BigDecimal|
|DECIMAL|java.math.BigDecimal|
|BIT|boolean|
|TINYINT|byte|
|SMALLINT|short|
|INTEGER|int|
|BIGINT|long|
|REAL|float|
|FLOAT|double|
|DOUBLE|double|
|BINARY|byte[]|
|VARBINARY|byte[]|
|LONGVARBINARY|byte[]|
|DATE|java.sql.Date|
|TIME|java.sql.Time|
|TIMESTAMP|java.sql.Timestamp|
|BLOB|java.sql.Blob|
|CLOB|java.sql.Clob|
|Array|java.sql.Array|
|REF|java.sql.Ref|
|Struct|java.sql.Struct|
|Struct|java.sql.Struct|
```
注意：这种类型匹配不是强制性标准，特定的JDBC厂商可能会改变这种类型匹配。
例如Oracle中的DATE类型是包含时分秒，而java.sql.Date仅仅支持年月日
```

五、程序类型：  
[ODBC：开放数据库互连](https://baike.baidu.com/item/%E5%BC%80%E6%94%BE%E6%95%B0%E6%8D%AE%E5%BA%93%E4%BA%92%E8%BF%9E/10418782)  
```
JDBC驱动程序共分四种类型：
5.1、JDBC-ODBC桥加ODBC驱动程序：
  JavaSoft桥产品利用ODBC驱动程序提供JDBC访问。注意，必须将ODBC二进制代码（许多情况下还包括数据库客户机代码）加载到使用该驱动程序的每个客户机上。
因此，这种类型的驱动程序最适合于企业网（这种网络上客户机的安装不是主要问题），或者是用Java编写的三层结构的应用程序服务器代码。

5.2、本地API：
  这种类型的驱动程序把客户机API上的JDBC调用转换为Oracle、Sybase、Informix、DB2或其它DBMS的调用。注意，象桥驱动程序一样，
这种类型的驱动程序要求将某些二进制代码加载到每台客户机上。

5.3、JDBC网络纯Java驱动程序：
  这种驱动程序将JDBC转换为与DBMS无关的网络协议，之后这种协议又被某个服务器转换为一种DBMS协议。这种网络服务器中间件能够将它的
纯Java客户机连接到多种不同的数据库上。所用的具体协议取决于提供者。通常，这是最为灵活的JDBC驱动程序。
有可能所有这种解决方案的提供者都提供适合于Intranet用的产品。为了使这些产品也支持Internet访问，
它们必须处理Web所提出的安全性、通过防火墙的访问等方面的额外要求。几家提供者正将JDBC驱动程序加到他们现有的数据库中间件产品中。

5.4、本地协议纯Java驱动程序：
  这种类型的驱动程序将JDBC调用直接转换为DBMS所使用的网络协议。这将允许从客户机机器上直接调用DBMS服务器，
是Intranet访问的一个很实用的解决方法。由于许多这样的协议都是专用的，因此数据库提供者自己将是主要来源，有几家提供者已在着手做这件事了。
获取驱动：目前已有几十个（1）类的驱动程序，即可与Javasoft桥联合使用的ODBC驱动程序的驱动程序。
有大约十多个属于种类（2）的驱动程序是以DBMS的本地API为基础编写的。只有几个属于种类（3）的驱动程序，
其首批提供者是SCO、OpenHorizon、Visigenic和WebLogic。
此外，JavaSoft和数据库连接的领先提供者Intersolv还合作研制了JDBC-ODBC桥和JDBC驱动程序测试工具包。
```

六、SQL一致性：
```
  结构化查询语言（SQL） 是访问关系数据库的标准语言。困难之处在于：虽然大多数的 DBMS（数据库管理系统对其基本功能都使用了标准形式的SQL，
但它们却不符合最近为更高级的功能定义的标准SQL语法或语义。例如，并非所有的数据库都支持储存程序或外部连接，那些支持这一功能的数据库又相互不一致。
人们希望SQL中真正标准的那部份能够进行扩展以包括越来越多的功能。但同时JDBC API又必须支持现有的SQL。

  JDBC API解决这个问题的一种方法是允许将任何查询字符串一直传到所涉及的DBMS驱动程序上。这意味着应用程序可以使用任意多的SQL功能，
但它必须冒这样的风险：有可能在某些DBMS上出错。事实上，应用程序查询甚至不一定要是SQL，
或者说它可以是个为特定的DBMS设计的SQL的专用派生物（例如，文档或图象查询）。
  
  JDBC处理SQL一致性问题的第二种方法是提供ODBC风格的转义子句，转义语法为几个常见的SQL分歧提供了一种标准的JDBC语法。
例如，对日期文字和已储存过程的调用都有转义语法。

  对于复杂的应用程序，JDBC用第三种方法来处理SQL的一致性问题它利用DatabaseMetaData接口来提供关于DBMS的描述性信息，
从而使应用程序能适应每个DBMS的要求和功能。

  由于JDBC API将用作开发高级数据库访问工具和API的基础API，因此它还必须注意其所有上层建筑的一致性。"符合JDBC标准TM"
代表用户可依赖的JDBC 功能的标准级别。要使用这一说明，驱动程序至少必须支持 
ANSISQL-2 Entry Level（ANSISQL-2 代表美国国家标准局1992 年所采用的标准。Entry Level代表SQL功能的特定清单）。
驱动程序开发人员可用 JDBCAPI所带的测试工具包来确定他们的驱动程序是否符合这些标准。

  "符合JDBC标准TM" 表示提供者的JDBC实现已经通过了JavaSoft提供的一致性测试。这些一致性测试将检查JDBCAPI中定义的所有类和方法是否都存在，
并尽可能地检查程序是否具有SQL Entry Level 功能。当然，这些测试并不完全，而且JavaSoft目前也无意对各提供者的实现进行标级。
但这种一致性定义的确可对JDBC实现提供一定的可信度。随着越来越多的数据库提供者、连接提供者、Internet提供者和应用程序编程员对JDBC API的接受，
JDBC也正迅速成为Java数据库访问的标准
```

七、层次结构：
```
根据处理对象的不同，数据库管理系统的层次结构由高级到低级依次为应用层、语言翻译处理层、数据存取层、数据存储层、操作系统。
7.1、应用层：应用层是DBMS与终端用户和应用程序的界面层，处理的对象是各种各样的数据库应用。
7.2、语言翻译：语言翻译处理层是对数据库语言的各类语句进行语法分析、视图转换、授权检查、完整性检查等。
7.3、数据存取层：数据存取层处理的对象是单个元组，它将上层的集合操作转换为单记录操作。
7.4、数据存储层：数据存储层处理的对象是数据页和系统缓冲区。
7.5、操作系统：操作系统是DBMS的基础。操作系统提供的存取原语和基本的存取方法通常是作为和DBMS存储层的接口。
```

八、不足
```
尽管JDBC在JAVA语言层面实现了统一，但不同数据库仍旧有许多差异。为了更好地实现跨数据库操作，
于是诞生了Hibernate、Mybatis等项目，Hibernate是对JDBC的再封装，实现了对数据库操作更宽泛的统一和更好的可移植性
```

---
### <a id="a_step">二、JDBC连接数据库步骤：</a> <a href="#a_jdbc">last</a> <a href="#a_dirver">next</a>
[mysql:mysql-connector-java:8.0.12](https://search.maven.org/artifact/mysql/mysql-connector-java/8.0.12/jar)  
一、配置数据库驱动（以Mysql数据库创建一张表为例）：  
```
1、项目直接依赖mysql相关包：
  可以从Maven中央仓库下载mysql相关包，这里选用最新版本：mysql:mysql-connector-java:8.0.12
  Eclipse常规添加JAR包方式：
  右键项目 > Build Path > Configure Build Path > Libraries > Add JARs > 选择mysql-connector-java-8.0.11.jar

2、项目使用Maven统一配置依赖：
  pom.xml文件添加mysql依赖：
  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.12</version>
  </dependency>
```

二、 装载驱动程序：  
```
1、普通Java项目：
  使用Class.forName()加载驱动类：
  Class.forName("com.mysql.jdbc.Driver");

说明1：如果选用mysql-connector-java-8.x版本加载驱动会提示：
  Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. 
The driveris automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
  建议使用：com.mysql.cj.jdbc.Driver驱动类

1.2、JDBC 4.0新特性：
  JDBC 4.0 Drivers必须包括META-INF/services/java.sql.Driver文件。此文件包含java.sql.Driver的JDBC驱动程序实现的名称。
例如，要加载my.sql.Driver类，META-INF/services/java.sql.Driver 文件需要包含下面的条目： my.sql.Driver
应用程序不再需要使用Class.forName()显式地加载JDBC驱动程序。当前使用Class.forName()加载JDBC驱动程序的现有程序将在不作修改的情况下继续工作
  在mysql-connector-java-8.0.11.jar中包含java.sql.Driver，具体位置：
  mysql-connector-java-8.0.11.jar\META-INF\services\java.sql.Driver

2、Spring、Spring boot等项目：
  可以在application.properties配置datasource参数信息：
  ## 配置jdbc驱动：使用mysql驱动
  spring.datasource.driver-class-name=com.mysql.jdbc.Driver
  ## 配置 jdbc url
  spring.datasource.url=jdbc:mysql://127.0.0.1:3306/study
  ## 配置 jdbc 用户名
  spring.datasource.username=root
  ## 配置 jdbc 密码
  spring.datasource.password=root
```

三、建立连接(会话)：
[URL：统一资源定位符](https://baike.baidu.com/item/%E7%BB%9F%E4%B8%80%E8%B5%84%E6%BA%90%E5%AE%9A%E4%BD%8D%E7%AC%A6)
```
3.1、与数据库建立连接的标准方法是调用DriverManager.getConnection方法。
  该方法接受含有某个URL的字符串。DriverManager类（即所谓的JDBC管理层）将尝试找到可与那个URL所代表的数据库进行连接的驱动程序。
DriverManager类存有已注册的Driver类的清单。当调用方法getConnection时，它将检查清单中的每个驱动程序，
直到找到可与URL中指定的数据库进行连接的驱动程序为止。Driver的方法connect使用这个URL来建立实际的连接。
  用户可绕过JDBC管理层直接调用Driver方法。这在以下特殊情况下将很有用：当两个驱动器可同时连接到数据库中，
而用户需要明确地选用其中特定的驱动器。但一般情况下，让DriverManager类处理打开连接这种事将更为简单
  
  使用DriverManager.getConnection()方法获取数据库连接：
  Connection connection = DriverManager.getConnection("jdbcUrl", "username", "password");

3.2、使用完毕需要关闭数据库连接:
connection.close();

3.3、普通URL：
  URL[统一资源定位符]提供在Internet上定位资源所需的信息。
  可将其想象为一个地址。URL的第一部份指定了访问信息所用的协议，后面总是跟着冒号。常用的协议有"ftp[文件传输协议]"和"http[超文本传输协议]"。
如果协议是"file"，表示资源是在某个本地文件系统上而非在Internet上（下例用于表示我们所描述的部分；它并非URL的组成部分）。

  URL的其余部份（冒号后面的）给出了数据资源所处位置的有关信息。如果协议是file，则URL的其余部份是文件的路径。对于ftp和http协议，
URL的其余部份标识了主机并可选地给出某个更详尽的地址路径。

3.4、JDBC URL：
  JDBC URL提供了一种标识数据库的方法，可以使相应的驱动程序能识别该数据库并与之建立连接。
  实际上，驱动程序编程员将决定用什么JDBC URL来标识特定的驱动程序。
用户不必关心如何来形成JDBC URL；他们只须使用与所用的驱动程序一起提供的URL即可。JDBC的作用是提供某些约定，驱动程序编程员在构造他们的JDBC URL时应该遵循这些约定。
  由于JDBC URL要与各种不同的驱动程序一起使用，因此这些约定应非常灵活。首先，它们应允许不同的驱动程序使用不同的方案来命名数据库。
例如，odbc子协议允许（但并不是要求）URL含有属性值。

其次，JDBC URL应允许驱动程序编程员将一切所需的信息编入其中。这样就可以让要与给定数据库对话的applet打开数据库连接，而无须要求用户去做任何系统管理工作。

最后，JDBC URL应允许某种程度的间接性。也就是说，JDBC URL可指向逻辑主机或数据库名，而这种逻辑主机或数据库名将由网络命名系统动态地转换为实际的名称。
这可以使系统管理员不必将特定主机声明为JDBC名称的一部份。网络命名服务（例如DNS、NIS和DCE）有多种，而对于使用哪种命名服务并无限制。 JDBC URL的标准语法如下所示。
它由三部分组成，各部分间用冒号分隔，JDBC URL的三个部分可分解如下：
  jdbc协议：JDBC URL中的协议总是jdbc。

  <子协议>：驱动程序名或数据库连接机制（这种机制可由一个或多个驱动程序支持）的名称。子协议名的典型示例是"odbc"，
  该名称是为用于指定ODBC风格的数据资源名称的URL专门保留的。例如，为了通过JDBC-ODBC桥来访问某个数据库，可以用如下所示的URL：jdbc:odbc:book。本例中，
子协议为"odbc"，子名称"book"是本地ODBC数据资源。如果要用网络命名服务（这样JDBC URL中的数据库名称不必是实际名称），则命名服务可以作为子协议。

  <子名称>：一种标识数据库的方法。子名称可以依不同的子协议而变化。它还可以有子名称的子名称（含有驱动程序编程员所选的任何内部语法）。
使用子名称的目的是为定位数据库提供足够的信息。前例中，因为ODBC将提供其余部份的信息，因此用"book"就已足够。
然而，位于远程服务器上的数据库需要更多的信息。例如，如果数据库是通过Internet来访问的，则在JDBC URL中应将网络地址作为子名称的一部份包括进去，
且必须遵循如下所示的标准URL命名约定：//主机名:端口/子协议


3.5、Mysql数据库的链接地址格式：
  jdbc:mysql://IP地址:端口号/数据库名称
  jdbc协议：JDBU URL中的协议总是 jdbc
  子协议：驱动程序或数据列链接机制（这种机制可由一个或多个驱动程序支持）的名称，如mysql，oracle
  子名称：一种标识数据库的方法。必须遵循“//主机名:端口/子协议”的标准URL命名约定，mysql默认端口为3306
如：//localhost:3306/study           //127.0.0.1:3306/study

3.6、获取数据库连接完整示例：
Connection connection = null;
try {
     connection = DriverManager.getConnection(
         "jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8", "root", "root");
} catch (SQLException e) {
     e.printStackTrace();
} finally {
     if (null != connection) {
          try {
               connection.close();
          } catch (SQLException e) {
               e.printStackTrace();
          }
     }
}

说明1：Mysql5.5.x以上版本，jdbc url上未取消SSL验证时会提示：
  Tue Sep 18 14:15:07 CST 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. 
According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit 
option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property 
is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and 
provide truststore for server certificate verification.
  解决提示：可以在jdbc url添加参数：useSSL=false
  eg：jdbc:mysql://127.0.0.1:3306/study?useSSL=false

说明2：jdbc url如果未指定时区，会报java.sql.SQLException异常：
  java.sql.SQLException: The server time zone value '' is unrecognized or represents more than one time zone. 
You must configure either the server or JDBC driver(via the serverTimezone configuration property) 
to use a more specifc time zone value if you want to utilize time zone support.
  解决异常：在jdbc url添加参数serverTimezone=GMT%2B8   
  eg：jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8
```

四、发送SQL语句：
```
  4.1、创建Statement对象：
  Statement对象用于把SQL语句发送到DBMS。使用一个活跃的Connection连接的来创建Statement对象的实例
  Statement statement = connection.createStatement();

  4.2、执行语句：
  使用 executeUpdate 方法是因为在createTableCoffees中的SQL语句是DDL（数据定义语言）语句。
创建表，改变表，删除表都是DDL语句的，要用executeUpdate方法来执行。可以从它的名字里看出，方法executeUpdate也被用于执行更新表SQL语句。
实际上，相对于创建表来说，executeUpdate 用于更新表的操作更多，因为表只需要创建一次，但经常被更新。
被使用最多的执行SQL语句的方法是executeQuery。这个方法被用来执行SELECT语句，它几乎是使用最多的SQL语句
  statement.executeQuery("SELECT bookId,title,author,remark,createrTime FROM book");

  4.3、获取查询的数据：
  ResultSet rs = statement.executeQuery("SELECT bookId,title,author,remark,createrTime FROM book");
  while (rs.next()) {
     Long bookId = rs.getLong("bookId");
     String title = rs.getString("title");
     String author = rs.getString("author");
     String remark = rs.getString("remark");
     Date createrTime = rs.getDate("createrTime");
  }

  4.4、关闭Statement对象：
  Statement对象将由Java垃圾收集程序自动关闭。而作为一种好的编程风格，应在不需要Statement对象时显式地关闭它们。
这将立即释放DBMS资源，有助于避免潜在的内存问题。
  statement.close();
```

StepMain.java：
```Java
package com.mutisitc.step;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mutisitc.utils.PrintUtil;
/**
 * DBC连接数据库步骤
 */
public class StepMain {
    public static void main(String[] args) {
        PrintUtil.one("JDBC连接数据库步骤");
        
        Connection connection = null;
        PrintUtil.two("0.创建Connection数据库连接引用", connection);
        
        try {
            Class<?> driver = Class.forName("com.mysql.cj.jdbc.Driver");
            PrintUtil.two("1.通过Class.forName()加载Driver驱动类：com.mysql.cj.jdbc.Driver", driver);

            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8", "root", "root");
            PrintUtil.two("2.通过DriverManager.getConnection()，获取数据库连接Connection", connection);

            Statement statement = connection.createStatement();
            PrintUtil.two("3.通过Connection.createStatement()连接，创建Statement对象", statement);
            
            ResultSet rs = statement.executeQuery("SELECT bookId,title,author,remark,createrTime FROM book");
            PrintUtil.two("4.通过Statement.executeQuery()，执行查询SQL，获取查询结果集ResultSet对象", rs);
            
            PrintUtil.println();
            while (rs.next()) {
                Long bookId = rs.getLong("bookId");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String remark = rs.getString("remark");
                Date createrTime = rs.getDate("createrTime");
                PrintUtil.three("5.通过ResultSet结果集，获取数据列信息", "bookId="+bookId+", title="+title+
                        ",author="+author+"remark="+remark+",createrTime="+createrTime);
            }
            statement.close();
            PrintUtil.two("6.通过Statement.close()","关闭Statement对象，释放资源");
        } catch (ClassNotFoundException e) {
            PrintUtil.err("Class.forName()加载Dirver驱动类出现异常，打印异常堆栈信息：");
            e.printStackTrace();
        } catch (SQLException e) {
            PrintUtil.err("DriverManager.getConnection()获取连接Connection出现异常，打印异常堆栈信息：");
            e.printStackTrace();
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                    PrintUtil.two("7.通过Connection.close()","关闭数据库连接，释放资源");
                } catch (SQLException e) {
                    PrintUtil.err("Connection.close()关闭连接出现异常，打印异常堆栈信息：");
                    e.printStackTrace();
                }
            }
        }
    }
}
```
输出验证：
```
JDBC连接数据库步骤
0.创建Connection数据库连接引用
1.通过Class.forName()加载Driver驱动类：com.mysql.cj.jdbc.Driver【class com.mysql.cj.jdbc.Driver】
2.通过DriverManager.getConnection()，获取数据库连接Connection【com.mysql.cj.jdbc.ConnectionImpl@6f79caec】
3.通过Connection.createStatement()连接，创建Statement对象【com.mysql.cj.jdbc.StatementImpl@2d6a9952】
4.通过Statement.executeQuery()，执行查询SQL，获取查询结果集ResultSet对象【com.mysql.cj.jdbc.result.ResultSetImpl@2b80d80f】
5.通过ResultSet结果集，获取数据列信息【bookId=111, title=222,author=333remark=444,createrTime=2018-09-18】
6.通过Statement.close()【关闭Statement对象，释放资源】
7.通过Connection.close()【关闭数据库连接，释放资源】
```

### <a id="a_driver">三、java.sql.Driver：数据库驱动：</a> <a href="#a_step">last</a> <a href="#a_manager">next</a>
[java.sql.Driver](https://docs.oracle.com/javase/8/docs/api/java/sql/Driver.html)  
一、描述：
```
  每个驱动程序类必须实现的接口。 
  Java SQL 框架允许多个数据库驱动程序。 
  每个驱动程序都应该提供一个实现 Driver接口的类。 

  DriverManager 会试着加载尽可能多的它可以找到的驱动程序，然后，对于任何给定连接请求，它会让每个驱动程序依次试着连接到目标 URL。 
  强烈建议每个 Driver类应该是小型的并且是单独的，这样就可以在不必引入大量支持代码的情况下加载和查询 Driver类。 

  在加载某一 Driver类时，它应该创建自己的实例并向 DriverManager 注册该实例。这意味着用户可以通过调用以下程序加载和注册一个驱动程序 
  Class.forName("foo.bah.Driver")
```
二、理解：
```
  加载Driver类，然后自动在DriverManager中注册的方式有两种：
  2.1、调用方法Class.forName：
  这将显式地加载驱动程序类。由于这与外部设置无关，因此推荐使用这种加载驱动程序的方法。如果加载Mysql驱动类com.mysql.cj.jdbc.Driver：
  Class.forName("com.mysql.cj.jdbc.Driver")。

如果将com.mysql.cj.jdbc.Driver编写为加载时创建实例，并调用以该实例为参数的DriverManager.registerDriver()，
则它在DriverManager的驱动程序列表中，并可用于创建连接。

  2.2、将驱动程序添加到Java.lang.System的属性jdbc.drivers中：
  这是一个由DriverManager类加载的驱动程序类名的列表，由冒号分隔：初始化DriverManager类时，它搜索系统属性jdbc.drivers，
如果用户已输入了一个或多个驱动程序，则DriverManager类将试图加载它们。以下代码说明程序员如何在~/.hotJava/properties
中输入三个驱动程序类（启动时，HotJava将ivers=com.mysql.cj.jdbc.Driver;xxx.xxx.Driver;xxx.xxx.Driver)
对DriverManager方法的第一次调用将自动加载这些驱动程序类。

  注意：加载驱动程序的第二种方法需要持久的预设环境。如果对这一点不能保证，则调用方法Class.forName显式地加载每个驱动程序就显得更为安全。
这也是引入特定驱动程序的方法，因为一旦DriverManager类被初始化，它将不再检查jdbc.drivers属性列表
```

三、方法说明：
[java.util.Properties](https://docs.oracle.com/javase/8/docs/api/java/util/Properties.html)

|返回类型|方法|说明|
|---|---|---|
|boolean|acceptsURL(String url)|检索驱动程序是否认为它可以打开与给定URL的连接|
|Connection|connect(String url, Properties info)|尝试与给定的URL建立数据库连接|
|int|getMajorVersion()|检索驱动程序的主要版本号|
|int|getMinorVersion()|获取驱动程序的次要版本号|
|Logger|getParentLogger()|返回此驱动程序使用的所有记录器的父记录器|
|DriverPropertyInfo[]|getPropertyInfo(String url, Properties info)|获取有关此驱动程序的可能属性的信息|
|boolean|jdbcCompliant()|报告此驱动程序是否为真正的JDBC Compliant驱动程序|

四、[java.sql.DriverPropertyInfo](https://docs.oracle.com/javase/8/docs/api/java/sql/DriverPropertyInfo.html)
4.1、描述：
```
用于建立连接的驱动程序属性。只有那些需要通过getDriverProperties方法与Driver交互来发现
并提供用于连接的属性的高级编程人员，才需要对类DriverPropertyInfo感兴趣。 
```

4.2、方法说明：

|返回类型|方法|说明|
|---|---|---|
|String[]|choices|如果DriverPropertyInfo.value可以从特定值集中选择字段的值，则可以是值的数组;否则为null|
|String|description|属性的简要描述，可能为 null|
|String|name|属性的名称|
|boolean|required|如果在 Driver.connect 期间必须为此属性提供一个值，则 required 字段为 true，否则为 false|
|String|value|value 字段通过综合为 getPropertyInfo 方法提供的信息、Java 环境和驱动程序提供的默认值来指定当前属性值|

DriverMain.java：
```Java
package com.mutisitc.driver;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;
import com.mutisitc.utils.PrintUtil;
/**
 * java.sql.Driver：数据库驱动
 */
public class DriverMain {
	public static void main(String[] args) {
		PrintUtil.one("java.sql.Driver：数据库驱动：");
		try {
			String jdbcURL = "jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8";
			String userName = "root";
			String password = "root";
			PrintUtil.three("0.Mysql数据库连接信息：", null);
			PrintUtil.three("JDBC URL", jdbcURL);
			PrintUtil.three("userName", userName);
			PrintUtil.three("password", password);
			
			Driver driver = DriverManager.getDriver(jdbcURL);
			PrintUtil.two("1.通过DriverManager.getDriver()：获取到的数据库驱动", "Driver="+driver);
			
			boolean isacceptsURL = driver.acceptsURL(jdbcURL);
			PrintUtil.two("2.Driver.isacceptsURL()：获取驱动程序是否认为它可以打开与给定URL的连接：获取结果", "isacceptsURL="+isacceptsURL);
			
			PrintUtil.two("3.使用Driver获取数据库连接", "Driver.connect(String url, Properties info)");
			Properties properties = new Properties();
			properties.put("user", userName); 
			properties.put("password", password);
			PrintUtil.three("3.1.配置Properties属性，至少包含user和password信息", properties.getClass()+"="+properties);
			
			Connection connection = driver.connect(jdbcURL, properties);
			PrintUtil.three("3.2.Driver.connect()：尝试与给定的URL建立数据库连接", "Connection="+connection);
			connection.close();
			
			int majorVersion = driver.getMajorVersion();
			PrintUtil.two("4.通过Driver.getMajorVersion()：获取驱动程序的主要版本号", "MajorVersion="+majorVersion);
			
			int minorVersion = driver.getMinorVersion();
			PrintUtil.two("5.通过Driver.getMinorVersion()：获取驱动程序的次要版本号", "MinorVersion="+minorVersion);
			
			boolean jdbcCompliant = driver.jdbcCompliant();
			PrintUtil.two("6.通过Driver.jdbcCompliant()：报告此驱动程序是否为真正的JDBC Compliant驱动程序", "jdbcCompliant="+jdbcCompliant);
			
			DriverPropertyInfo[] driverPropertyInfos = driver.getPropertyInfo(jdbcURL, properties);
			PrintUtil.two("7.通过Driver.getPropertyInfo()：获取有关此驱动程序的可能属性的信息", "DriverPropertyInfo="+Arrays.asList(driverPropertyInfos));
			int index = 0;
			for (DriverPropertyInfo driverPropertyInfo : driverPropertyInfos) {
				index++;
				PrintUtil.two("7."+index+".驱动程序的属性信息：DriverPropertyInfo", driverPropertyInfo);
				PrintUtil.three("DriverPropertyInfo.name：属性的名称", driverPropertyInfo.name);
				PrintUtil.three("DriverPropertyInfo.required：是否在Driver.connect期间必须为此属性提供一个值", driverPropertyInfo.required);
				PrintUtil.three("DriverPropertyInfo.value：value 字段通过综合为 getPropertyInfo 方法提供的信息、Java 环境和驱动程序提供的默认值来指定当前属性值", driverPropertyInfo.value);
				PrintUtil.three("DriverPropertyInfo.choices：可以从特定值集中选择字段的值：", driverPropertyInfo.choices == null ? null : Arrays.asList(driverPropertyInfo.choices));
				PrintUtil.three("DriverPropertyInfo.description：属性的名称",driverPropertyInfo.description); 
			}
		} catch (SQLException e) {
			PrintUtil.err("演示 java.sql.Driver：数据库驱动，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
```
输出验证：
```
java.sql.Driver：数据库驱动：
0.Mysql数据库连接信息：
  JDBC URL【jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8】
  userName【root】
  password【root】
1.通过DriverManager.getDriver()：获取到的数据库驱动【Driver=com.mysql.cj.jdbc.Driver@6d6f6e28】
2.Driver.isacceptsURL()：获取驱动程序是否认为它可以打开与给定URL的连接：获取结果【isacceptsURL=true】
3.使用Driver获取数据库连接【Driver.connect(String url, Properties info)】
  3.1.配置Properties属性，至少包含user和password信息【class java.util.Properties={user=root, password=root}】
  3.2.Driver.connect()：尝试与给定的URL建立数据库连接【Connection=com.mysql.cj.jdbc.ConnectionImpl@6f79caec】
4.通过Driver.getMajorVersion()：获取驱动程序的主要版本号【MajorVersion=8】
5.通过Driver.getMinorVersion()：获取驱动程序的次要版本号【MinorVersion=0】
6.通过Driver.jdbcCompliant()：报告此驱动程序是否为真正的JDBC Compliant驱动程序【jdbcCompliant=false】
7.通过Driver.getPropertyInfo()：获取有关此驱动程序的可能属性的信息【DriverPropertyInfo=[java.sql.DriverPropertyInfo@22a71081, ...]】
  7.1.驱动程序的属性信息：DriverPropertyInfo【java.sql.DriverPropertyInfo@22a71081】
  DriverPropertyInfo.name：属性的名称【HOST】
  DriverPropertyInfo.required：是否在Driver.connect期间必须为此属性提供一个值【true】
  DriverPropertyInfo.value：value 字段通过综合为 getPropertyInfo 方法提供的信息、Java 环境和驱动程序提供的默认值来指定当前属性值【127.0.0.1】
  DriverPropertyInfo.choices：可以从特定值集中选择字段的值：
  DriverPropertyInfo.description：属性的名称【Hostname of MySQL Server】
  7.2.驱动程序的属性信息：DriverPropertyInfo【java.sql.DriverPropertyInfo@3930015a】
  DriverPropertyInfo.name：属性的名称【PORT】
  DriverPropertyInfo.required：是否在Driver.connect期间必须为此属性提供一个值【false】
  DriverPropertyInfo.value：value 字段通过综合为 getPropertyInfo 方法提供的信息、Java 环境和驱动程序提供的默认值来指定当前属性值【3306】
  DriverPropertyInfo.choices：可以从特定值集中选择字段的值：
  DriverPropertyInfo.description：属性的名称【Port number of MySQL Server】
  7.xxxx...
```


### <a id="a_manager">四、java.sql.DriverManager：驱动管理：</a> <a href="#a_driver">last</a> <a href="#a_connection">next</a>
[java.sql.DriverManager](https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html)   
一、描述：
```
  管理一组 JDBC 驱动程序的基本服务。
  注：DataSource 接口是JDBC 2.0 API中的新增内容，它提供了连接到数据源的另一种方法。使用 DataSource 对象是连接到数据源的首选方法。 
作为初始化的一部分，DriverManager 类会尝试加载在 "jdbc.drivers" 系统属性中引用的驱动程序类。这允许用户定制由他们的应用程序使用的 JDBC Driver。
例如，在 ~/.hotjava/properties 文件中，用户可以指定： 
jdbc.drivers=foo.bah.Driver:wombat.sql.Driver:bad.taste.ourDriver
  
  DriverManager 类的方法getConnection和getDrivers已经得到提高以支持Java Standard Edition Service Provider机制。 
 
  在调用getConnection方法时，DriverManager会试着从初始化时加载的那些驱动程序以及使用与当前applet或应用程序相同的类加载器显式加载的那些驱动程序中查找合适的驱动程序。 
  
  从Java 2 SDK标准版本1.3版开始，只有当已授予适当权限时设置日志流。通常这将使用工具PolicyTool完成，该工具可用于授予permission java.sql.SQLPermission "setLog"权限
```
二、理解：
```
  DriverManager 类是 JDBC 的管理层，作用于用户和驱动程序之间。它跟踪可用的驱动程序，并在数据库和相应驱动程序之间建立连接。
另外，DriverManager类也处理诸如驱动程序登录时间限制及登录和跟踪消息的显示等事务。 　

  对于简单的应用程序，一般程序员需要在此类中直接使用的唯一方法是DriverManager.getConnection。正如名称所示，该方法将建立与数据库的连接。
JDBC允许用户调用DriverManager的方法getDriver、getDrivers和registerDriver及Driver的方法connect。但多数情况下，让DriverManager类管理建立连接的细节为上策

  由于安全方面的原因，JDBC管理层将跟踪哪个类加载器提供哪个驱动程序。这样，当DriverManager类打开连接时，
它仅使用本地文件系统或与发出连接请求的代码相同的类加载器提供的驱动程序

  加载Driver类并在DriverManager类中注册后，它们即可用来与数据库建立连接。当调用DriverManager.getConnection方法发出连接请求时，
DriverManager将检查每个驱动程序，查看它是否可以建立连接

  有时可能有多个JDBC驱动程序可以与给定的URL连接。例如，与给定远程数据库连接时，可以使用JDBC-ODBC桥驱动程序、JDBC到通用网络协议驱动程序或数据库厂商提供的驱动程序。
在这种情况下测试驱动程序的顺序至关重要，因为DriverManager将使用它所找到的第一个可以成功连接到给定URL的驱动程序。
  首先DriverManager试图按注册的顺序使用每个驱动程序（jdbc.drivers中列出的驱动程序总是先注册）。它将跳过代码不可信任的驱动程序，
除非加载它们的源与试图打开连接的代码的源相同。它通过轮流在每个驱动程序上调用方法Driver.connect，并向它们传递用户开始传递给方法
DriverManager.getConnection的URL来对驱动程序进行测试，然后连接第一个认出该URL的驱动程序。这种方法初看起来效率不高，
但由于不可能同时加载数十个驱动程序，因此每次连接实际只需几个过程调用和字符串比较
```
三、方法说明：  
[java.sql.DriverAction](https://docs.oracle.com/javase/8/docs/api/java/sql/DriverAction.html)  
[java.util.Enumeration](https://docs.oracle.com/javase/8/docs/api/java/util/Enumeration.html)  
[java.io.File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)  
[java.io.PrintStream](https://docs.oracle.com/javase/8/docs/api/java/io/PrintStream.html)  
[java.io.PrintWriter](https://docs.oracle.com/javase/8/docs/api/java/io/PrintWriter.html)

|返回类型|方法|说明|
|---|---|---|
|static void|deregisterDriver(Driver driver)|从已DriverManager注册的驱动程序列表中删除指定的驱动程序|
|static Connection|getConnection(String url)|尝试建立与给定数据库URL的连接|
|static Connection|getConnection(String url, Properties info)|尝试建立与给定数据库URL的连接|
|static Connection|getConnection(String url, String user, String password)|尝试建立与给定数据库URL的连接|
|static Driver|getDriver(String url)|尝试查找理解给定URL的驱动程序|
|static Enumeration<Driver>|getDrivers()|检索当前调用者可以访问的所有当前加载的JDBC驱动程序的枚举|
|static int|getLoginTimeout()|获取驱动程序在尝试登录数据库时可以等待的最长时间（以秒为单位）|
|static PrintStream|getLogStream()|~~已过时~~。使用 getLogWriter，检索由DriverManager和所有驱动程序使用的日志记录/跟踪PrintStream|
|static PrintWriter|getLogWriter()|检索日志编写器|
|static void|println(String message)|将消息打印到当前JDBC日志流|
|static void|registerDriver(Driver driver)|注册给定的驱动程序DriverManager|
|static void|registerDriver(Driver driver, DriverAction da)|注册给定的驱动程序DriverManager|
|static void|setLoginTimeout(int seconds)|设置驱动程序在识别驱动程序后尝试连接数据库时等待的最长时间（以秒为单位）|
|static void|setLogStream(PrintStream out)|~~已过时。~~使用 setLogWriter，检索由DriverManager 和所有驱动程序使用的日志记录/跟踪PrintStream|
|static void|setLogWriter(PrintWriter out)|设置和所有驱动程序PrintWriter使用的日志记录/跟踪对象DriverManager|

DriverManagerMain.java：
```Java
package com.mutisitc.drivermanager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import com.mutisitc.utils.PrintUtil;
/**
 * java.sql.DriverManager：驱动管理
 */
public class DriverManagerMain {
	public static void main(String[] args) {
		PrintUtil.one("java.sql.DriverManager：驱动管理：");
		try {
			String jdbcURL = "jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8";
			String userName = "root";
			String password = "root";
			PrintUtil.two("0.Mysql数据库连接信息：", null);
			PrintUtil.three("0.1.JDBC URL", jdbcURL);
			PrintUtil.three("0.2.userName", userName);
			PrintUtil.three("0.3.password", password);
			
			PrintUtil.two("1.使用DriverManager.getConnection()获取数据库连接", "DriverManager.getConnection(String url, Properties info)");
			Properties properties = new Properties();
			properties.put("user", userName); 
			properties.put("password", password);
			PrintUtil.three("1.1.配置Properties属性，至少包含user和password信息", properties.getClass()+"="+properties);
			
			Connection connection = DriverManager.getConnection(jdbcURL, properties);
			PrintUtil.three("1.2.DriverManager.getConnection(String url, Properties info)：尝试建立与给定数据库URL的连接", "Connection="+connection);
			connection.close();
			
			Connection connection2 = DriverManager.getConnection(jdbcURL, userName, password);
			PrintUtil.two("2.DriverManager.getConnection(String url, String user, String password)：尝试建立与给定数据库URL的连接", "Connection="+connection2);
			connection2.close();
			
			Driver driver = DriverManager.getDriver(jdbcURL);
			PrintUtil.two("3.DriverManager.getDriver()：获取到的数据库驱动", "Driver="+driver);
			
			com.mysql.jdbc.Driver dirver2 = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(dirver2);
			PrintUtil.two("4.DriverManager.registerDriver(Driver driver)：注册给定的驱动程序DriverManager", "Driver="+dirver2);
			
			Enumeration<Driver> driverEnums = DriverManager.getDrivers();
			PrintUtil.two("5.DriverManager.getDrivers()：检索当前调用者可以访问的所有当前加载的JDBC驱动程序的枚举", "Enumeration<Driver>="+driverEnums);
			int index = 0;
			while(driverEnums.hasMoreElements()) {
				index++;
				Driver driverTemp = driverEnums.nextElement();
				PrintUtil.three("5."+index+".当前加载的JDBC驱动程序", "Driver="+driverTemp);
			}
			
			DriverManager.deregisterDriver(dirver2);
			PrintUtil.two("6.DriverManager.deregisterDriver(Driver driver)：从已DriverManager注册的驱动程序列表中删除指定的驱动程序", "Driver="+dirver2);
			
			int loginTimeout = DriverManager.getLoginTimeout();
			PrintUtil.two("7.DriverManager.getLoginTimeout()：获取驱动程序在尝试登录数据库时可以等待的最长时间（以秒为单位）", "loginTimeout="+loginTimeout);
			
			loginTimeout = 10000;
			DriverManager.setLoginTimeout(loginTimeout);
			PrintUtil.two("8.DriverManager.setLoginTimeout(int seconds)：设置驱动程序在识别驱动程序后尝试连接数据库时等待的最长时间（以秒为单位）", "loginTimeout="+loginTimeout);
			
			PrintStream printStream = DriverManager.getLogStream();
			PrintUtil.two("9.DriverManager.getLogStream()：已过时，检索由DriverManager和所有驱动程序使用的日志记录/跟踪PrintStream", "PrintStream="+printStream);
			
			File file = new File("src/com/mutisitc/drivermanager/logStream.txt");
			PrintUtil.two("10.加载文件", "File="+file);
			
			PrintStream newPrintStream = new PrintStream(file);
			DriverManager.setLogStream(newPrintStream);
			PrintUtil.three("10.1.通过DriverManager.setLogStream(PrintStream out)：已过时，检索由DriverManager 和所有驱动程序使用的日志记录/跟踪PrintStream", "PrintStream="+newPrintStream);
			
			PrintWriter printWriter = DriverManager.getLogWriter();
			PrintUtil.two("11.通过DriverManager.getLogStream()：检索日志编写器", "PrintWriter="+printWriter);
			
			PrintUtil.two("12.加载文件", "File="+file);
			PrintWriter newPrintWriter = new PrintWriter(file);
			DriverManager.setLogWriter(newPrintWriter);
			PrintUtil.three("12.1.通过DriverManager.setLogWriter(PrintWriter out)：设置和所有驱动程序PrintWriter使用的日志记录/跟踪对象DriverManager", "PrintWriter="+newPrintWriter);
		
			String message = "测试打印数据";
			DriverManager.println(message);
			PrintUtil.two("13.DriverManager.println(String message)：将消息打印到当前JDBC日志流", "Message="+message);
			
		} catch (SQLException e) {
			PrintUtil.err("演示 java.sql.DriverManager：驱动管理出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			PrintUtil.err("加载文件路径出现异常，打印异常堆栈信息：");
			e.printStackTrace();
		}
	}
}
```
输出验证：
```
Console：
java.sql.DriverManager：驱动管理：
0.Mysql数据库连接信息：
  0.1.JDBC URL【jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8】
  0.2.userName【root】
  0.3.password【root】
1.使用DriverManager.getConnection()获取数据库连接【DriverManager.getConnection(String url, Properties info)】
  1.1.配置Properties属性，至少包含user和password信息【class java.util.Properties={user=root, password=root}】
  1.2.DriverManager.getConnection(String url, Properties info)：尝试建立与给定数据库URL的连接【Connection=com.mysql.cj.jdbc.ConnectionImpl@5d3411d】
2.DriverManager.getConnection(String url, String user, String password)：尝试建立与给定数据库URL的连接【Connection=com.mysql.cj.jdbc.ConnectionImpl@763d9750】
3.DriverManager.getDriver()：获取到的数据库驱动【Driver=com.mysql.cj.jdbc.Driver@45ee12a7】

Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. 
The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
4.DriverManager.registerDriver(Driver driver)：注册给定的驱动程序DriverManager【Driver=com.mysql.jdbc.Driver@d70c109】

5.DriverManager.getDrivers()：检索当前调用者可以访问的所有当前加载的JDBC驱动程序的枚举【Enumeration<Driver>=java.util.Vector$1@17ed40e0】
  5.1.当前加载的JDBC驱动程序【Driver=com.mysql.cj.jdbc.Driver@45ee12a7】
  5.2.当前加载的JDBC驱动程序【Driver=com.mysql.jdbc.Driver@d70c109】
6.DriverManager.deregisterDriver(Driver driver)：从已DriverManager注册的驱动程序列表中删除指定的驱动程序【Driver=com.mysql.jdbc.Driver@d70c109】
7.DriverManager.getLoginTimeout()：获取驱动程序在尝试登录数据库时可以等待的最长时间（以秒为单位）【loginTimeout=0】
8.DriverManager.setLoginTimeout(int seconds)：设置驱动程序在识别驱动程序后尝试连接数据库时等待的最长时间（以秒为单位）【loginTimeout=10000】
9.DriverManager.getLogStream()：已过时，检索由DriverManager和所有驱动程序使用的日志记录/跟踪PrintStream【PrintStream=null】
10.加载文件【File=src\com\mutisitc\drivermanager\logStream.txt】
  10.1.通过DriverManager.setLogStream(PrintStream out)：已过时，检索由DriverManager 和所有驱动程序使用的日志记录/跟踪PrintStream【PrintStream=java.io.PrintStream@50675690】
11.通过DriverManager.getLogStream()：检索日志编写器【PrintWriter=java.io.PrintWriter@31b7dea0】
12.加载文件【File=src\com\mutisitc\drivermanager\logStream.txt】
  12.1.通过DriverManager.setLogWriter(PrintWriter out)：设置和所有驱动程序PrintWriter使用的日志记录/跟踪对象DriverManager【PrintWriter=java.io.PrintWriter@3ac42916】
13.DriverManager.println(String message)：将消息打印到当前JDBC日志流【Message=测试打印数据】

logStream.txt：
测试打印数据
```


### <a id="a_connection">五、java.sql.Connection：数据库连接：</a> <a href="#a_manager">last</a> <a href="#">next</a>
[java.sql.Connection](https://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html)  
[UDT：互联网数据传输协议](https://baike.baidu.com/item/UDT)  
一、描述：
```
  与特定数据库的连接（会话）。在连接上下文中执行SQL语句并返回结果。 
  
  Connection 对象的数据库能够提供描述其表、所支持的SQL语法、存储过程、此连接功能等等的信息。此信息是使用 getMetaData 方法获得的。 
  注：在配置Connection时，JDBC应用程序应该使用适当的Connection方法，比如setAutoCommit或 setTransactionIsolation。
在有可用的JDBC方法时，应用程序不能直接调用SQL命令更改连接的配置。默认情况下，Connection对象处于自动提交模式下，
这意味着它在执行每个语句后都会自动提交更改。如果禁用了自动提交模式，那么要提交更改就必须显式调用 commit 方法；否则无法保存数据库更改。 
  
  使用JDBC 2.1核心API创建的新Connection对象有一个与之关联的最初为空的类型映射。用户可以为此类型映射中的
UDT[数据传输协议（UDP-based Data Transfer Protocol，简称UDT）是一种互联网数据传输协议]输入一个自定义映射关系。
在使用ResultSet.getObject 方法从数据源中获取UDT时，getObject方法将检查该连接的类型映射是否有对应该UDT的条目。如果有，
那么getObject方法将该UDT映射到所指示的类。如果没有条目，则使用标准映射关系映射该UDT。 
  
  用户可以创建一个新的类型映射，该映射是一个 java.util.Map 对象，可在其中创建一个条目，并将该条目传递给可以执行自定义映射关系的 java.sql 方法。
在这种情况下，该方法将使用给定的类型映射，而不是与连接关联的映射。 
```
二、字段说明：

|数据类型|方法|说明|
|---|---|---|
|static int|TRANSACTION_NONE|一个常量，指示不支持事务|
|static int|TRANSACTION_NONE|一个常量，指示不支持事务|
|static int|TRANSACTION_READ_COMMITTED|一个常量，表示防止脏读; 可以发生不可重复的读取和幻像读取|
|static int|TRANSACTION_READ_UNCOMMITTED|一个常量，表示可以发生脏读，不可重复读和幻像读|
|static int|TRANSACTION_REPEATABLE_READ|一个常量，表示防止脏读和不可重复读; 可以发生幻像读取|
|static int|TRANSACTION_SERIALIZABLE|一个常量，表示禁止脏读，不可重复读和幻像读|

三、方法说明：

|返回类型|方法|说明|
|---|---|---|
|void|abort(Executor executor)|终止打开的连接|
|void|clearWarnings()|清除为此Connection对象报告的所有警告|
|void|close()|立即释放此Connection对象的数据库和JDBC资源，而不是等待它们自动释放|
|void|commit()|使自上次提交/回滚以来所做的所有更改成为永久更改，并释放此Connection对象当前持有的所有数据库锁|
|Array|createArrayOf(String typeName, Object[] elements)|用于创建Array对象的工厂方法|
|Blob|createBlob()|构造一个实现Blob接口的对象|
|Clob|createClob()|构造一个实现Clob接口的对象|
|NClob|createNClob()|构造一个实现NClob接口的对象|
|SQLXML|createSQLXML()|构造一个实现SQLXML接口的对象|
|Statement|createStatement()|创建Statement用于将SQL语句发送到数据库的对象|
|Statement|createStatement(int resultSetType, int resultSetConcurrency)|创建一个Statement将生成 ResultSet具有给定类型和并发性的对象的对象|
|Statement|createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)|创建一个Statement对象，该对象将生成ResultSet具有给定类型，并发性和可保持性的对象|
|Struct|createStruct(String typeName, Object[] attributes)|用于创建Struct对象的工厂方法|
|boolean|getAutoCommit()|检索此Connection 对象的当前自动提交模式|
|String|getCatalog()|检索此Connection对象的当前目录名称|
|Properties|getClientInfo()|返回一个列表，其中包含驱动程序支持的每个客户端信息属性的名称和当前值|
|String|getClientInfo(String name)|返回name指定的客户端信息属性的值|
|int|getHoldability()|检索ResultSet使用此Connection对象创建的对象的当前可保存性|
|DatabaseMetaData|getMetaData()|检索DatabaseMetaData包含有关此Connection对象表示连接的数据库的元数据的对象|
|int|getNetworkTimeout()|检索驱动程序等待数据库请求完成的毫秒数|
|String|getSchema()|检索此Connection对象的当前架构名称|
|int|getTransactionIsolation()|检索此Connection对象的当前事务隔离级别|
|Map<String,Class<?>>|getTypeMap()|检索Map与此Connection对象关联的 对象|
|SQLWarning|getWarnings()|检索此Connection对象上的调用报告的第一个警告 |
|boolean|isClosed()|检索此Connection对象是否已关闭|
|boolean|isReadOnly()|检索此Connection 对象是否处于只读模式|
|boolean|isValid(int timeout)|如果连接尚未关闭且仍然有效，则返回true|
|String|nativeSQL(String sql)|将给定的SQL语句转换为系统的本机SQL语法|
|CallableStatement|prepareCall(String sql)|创建一个CallableStatement用于调用数据库存储过程的对象|
|CallableStatement|prepareCall(String sql, int resultSetType, int resultSetConcurrency)|创建一个CallableStatement将生成 ResultSet具有给定类型和并发性的对象的对象|
|CallableStatement|prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)|创建一个CallableStatement将生成 ResultSet具有给定类型和并发性的对象的对象|
|PreparedStatement|prepareStatement(String sql)|创建PreparedStatement用于将参数化SQL语句发送到数据库的对象|
|PreparedStatement|prepareStatement(String sql, int autoGeneratedKeys)|创建一个PreparedStatement能够检索自动生成的密钥的默认对象|
|PreparedStatement|prepareStatement(String sql, int[] columnIndexes)|创建一个PreparedStatement能够返回给定数组指定的自动生成的键的默认对象|
|PreparedStatement|prepareStatement(String sql, int resultSetType, int resultSetConcurrency)|创建一个PreparedStatement将生成 ResultSet具有给定类型和并发性的对象的对象|
|PreparedStatement|prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)|创建一个PreparedStatement对象，该对象将生成ResultSet具有给定类型，并发性和可保持性的 对象|
|PreparedStatement|prepareStatement(String sql, String[] columnNames)|创建一个PreparedStatement能够返回给定数组指定的自动生成的键的默认对象|
|void|releaseSavepoint(Savepoint savepoint)|从当前事务中删除指定的Savepoint 和后续的Savepoint对象|
|void|rollback()|撤消当前事务中所做的所有更改，并释放此Connection对象当前持有的所有数据库锁|
|void|rollback(Savepoint savepoint)|取消Savepoint设置给定对象后所做的所有更改|
|void|setAutoCommit(boolean autoCommit)|将此连接的自动提交模式设置为给定状态|
|void|setCatalog(String catalog)|设置给定的目录名称，以便选择要Connection在其中工作的此对象的数据库的子空间|
|void|setClientInfo(Properties properties)|设置连接的客户端信息属性的值|
|void|setClientInfo(String name, String value)|将name指定的客户端信息属性的值设置为value指定的值|
|void|setHoldability(int holdability)|将ResultSet使用此Connection对象创建的对象的默认可保存性更改为给定的可保持性|
|void|setNetworkTimeout(Executor executor, int milliseconds)|设置Connection从Connection 遗嘱中创建的最大句点或对象，等待数据库回复任何一个请求|
|void|setReadOnly(boolean readOnly)|将此连接置于只读模式，作为驱动程序的提示以启用数据库优化|
|Savepoint|setSavepoint()|在当前事务中创建一个未命名的保存点，并返回Savepoint表示它的新对象|
|Savepoint|setSavepoint(String name)|在当前事务中创建具有给定名称的保存点，并返回Savepoint表示它的新对象|
|void|setSchema(String schema)|设置要访问的给定模式名称|
|void|setTransactionIsolation(int level)|尝试将此Connection对象的事务隔离级别更改为 给定的对象|
|void|setTypeMap(Map<String,Class<?>> map)|将给定TypeMap对象安装为此Connection对象的类型映射|


---
<a id="a_down"></a>  
<a href="#a_top">Top</a> 
<a href="#a_catalogue">Catalogue</a>
