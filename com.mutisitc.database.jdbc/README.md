# <a id="a_top">JDBC：Java数据库连接</a>
[Java 8-API](https://docs.oracle.com/javase/8/docs/api/overview-summary.html)  
[Java 10-API](https://docs.oracle.com/javase/10/docs/api/overview-summary.html)  
[JDBC](https://baike.baidu.com/item/jdbc)  
[Java数据库连接](https://baike.baidu.com/item/Java%E6%95%B0%E6%8D%AE%E5%BA%93%E8%BF%9E%E6%8E%A5)  
[JDBC-API](https://docs.oracle.com/javase/10/docs/api/java.sql-summary.html)  
[Search Maven](https://search.maven.org/search?q=search)  
[mysql:mysql-connector-java:8.0.12](https://search.maven.org/artifact/mysql/mysql-connector-java/8.0.12/jar)  

联系：  
|Author|Mutistic|
|---|---|
|E-mail|mutistic@qq.com|

---
### <a id="a_catalogue">目录</a>：
1. <a href="#a_jdbc">JDBC：Java数据库连接</a>
2. <a href="#a_step">JDBC连接数据库步骤</a>
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
|SQL类型 | Java类型 |
|--- | --- |
|CHAR | java.lang.String |
|VARCHAR | java.lang.String |
|LONGVARCHAR | java.lang.String |
|NUMERIC | java.math.BigDecimal |
|DECIMAL | java.math.BigDecimal |
|BIT | boolean |
|TINYINT | byte |
|SMALLINT | short |
|INTEGER | int |
|BIGINT | long |
|REAL | float |
|FLOAT | double |
|DOUBLE | double |
|BINARY | byte[] |
|VARBINARY | byte[] |
|LONGVARBINARY | byte[] |
|DATE | java.sql.Date |
|TIME | java.sql.Time |
|TIMESTAMP | java.sql.Timestamp |
|BLOB | java.sql.Blob |
|CLOB | java.sql.Clob |
|Array | java.sql.Array |
|REF | java.sql.Ref |
|Struct | java.sql.Struct |
|Struct | java.sql.Struct |
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
### <a id="a_step">二、JDBC连接数据库步骤：</a> <a href="#a_jdbc">last</a> <a href="#">next</a>
[mysql:mysql-connector-java:8.0.12](https://search.maven.org/artifact/mysql/mysql-connector-java/8.0.12/jar)  
一、配置数据库驱动（以Mysql数据库为例）：  
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

二、 加载方式：
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

三、获取数据库连接(会话)：
```
3.1、使用DriverManager.getConnection()方法获取数据库连接：
Connection connection = DriverManager.getConnection("jdbcUrl", "username", "password");

3.2、使用完毕需要关闭数据库连接:
connection.close();

3.3、Mysql数据列的链接地址格式：
  jdbc:mysql://IP地址:端口号/数据库名称
  jdbc协议：JDBU URL中的协议总是 jdbc
  子协议：驱动程序或数据列链接机制（这种机制可由一个或多个驱动程序支持）的名称，如mysql，oracle
  子名称：一种标识数据库的方法。必须遵循“//主机名:端口/子协议”的标准URL命名约定，mysql默认端口为3306
如：//localhost:3306/study           //127.0.0.1:3306/study

3.4、获取数据库连接完整示例：
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

DriverMain.java：
```Java
package com.mutisitc.step;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mutisitc.utils.PrintUtil;
/**
 * JDBC连接数据库步骤
 */
public class DriverMain {
    public static void main(String[] args) {
        PrintUtil.one("JDBC连接数据库步骤");
        
        Connection connection = null;
        try {
            Class<?> driver = Class.forName("com.mysql.cj.jdbc.Driver");
            PrintUtil.two("加载Driver驱动类", driver);
            
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/study?useSSL=false&serverTimezone=GMT%2B8", "root", "root");
            PrintUtil.two("DriverManager链接数据库获取Connection", connection);

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
                    PrintUtil.two("Connection.close()", "成功关闭连接");
                } catch (SQLException e) {
                    PrintUtil.err("Connection.close()关闭连接出现异常，打印异常堆栈信息：");
                    e.printStackTrace();
                }
            }
        }
    }
}
```

四、[java.sql.Driver](https://docs.oracle.com/javase/8/docs/api/java/sql/Driver.html)  
4.1、描述：
```
  每个驱动程序类必须实现的接口。 
  Java SQL 框架允许多个数据库驱动程序。 
  每个驱动程序都应该提供一个实现 Driver接口的类。 

  DriverManager 会试着加载尽可能多的它可以找到的驱动程序，然后，对于任何给定连接请求，它会让每个驱动程序依次试着连接到目标 URL。 
  强烈建议每个 Driver类应该是小型的并且是单独的，这样就可以在不必引入大量支持代码的情况下加载和查询 Driver类。 

  在加载某一 Driver类时，它应该创建自己的实例并向 DriverManager 注册该实例。这意味着用户可以通过调用以下程序加载和注册一个驱动程序 
  Class.forName("foo.bah.Driver")
```
4.2、方法说明：  
| 返回类型 | 方法 | 说明 |
| --- | --- | --- |
| boolean | acceptsURL(String url) | 检索驱动程序是否认为它可以打开与给定URL的连接 |
| Connection | connect(String url, Properties info) | 尝试与给定的URL建立数据库连接 |
| int | getMajorVersion() | 检索驱动程序的主要版本号 |
| int | getMinorVersion() | 获取驱动程序的次要版本号 |
| Logger | getParentLogger() | 返回此驱动程序使用的所有记录器的父记录器 |
| DriverPropertyInfo[] | getPropertyInfo(String url, Properties info) | 获取有关此驱动程序的可能属性的信息 |
| boolean | jdbcCompliant() | 报告此驱动程序是否为真正的JDBC Compliant驱动程序 |

五、[java.sql.DriverManager](https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html)   
5.1、描述：
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

5.2、方法说明：  
| 返回类型 | 方法 | 说明 |
| --- | --- | --- |
| static void | deregisterDriver(Driver driver) | 从已DriverManager注册的驱动程序列表中删除指定的驱动程序 |
| static Connection | getConnection(String url) | 尝试建立与给定数据库URL的连接 |
| static Connection | getConnection(String url, Properties info) | 尝试建立与给定数据库URL的连接 |
| static Connection | getConnection(String url, String user, String password) | 尝试建立与给定数据库URL的连接 |
| static Driver | getDriver(String url) | 尝试查找理解给定URL的驱动程序 |
| static Enumeration<Driver> | getDrivers() | 检索当前调用者可以访问的所有当前加载的JDBC驱动程序的枚举 |
| static int | getLoginTimeout() | 获取驱动程序在尝试登录数据库时可以等待的最长时间（以秒为单位） |
| static PrintStream | getLogStream() | ~~已过时~~。使用 getLogWriter，检索由DriverManager 和所有驱动程序使用的日志记录/跟踪PrintStream |
| static PrintWriter | getLogWriter() | 检索日志编写器 |
| static void | println(String message) | 将消息打印到当前JDBC日志流 |
| static void | registerDriver(Driver driver) | 注册给定的驱动程序DriverManager |
| static void | registerDriver(Driver driver, DriverAction da) | 注册给定的驱动程序DriverManager |
| static void | setLoginTimeout(int seconds) | 设置驱动程序在识别驱动程序后尝试连接数据库时等待的最长时间（以秒为单位） |
| static void | setLogStream(PrintStream out) | ~~已过时。~~使用 setLogWriter，检索由DriverManager 和所有驱动程序使用的日志记录/跟踪PrintStream |
| static void | setLogWriter(PrintWriter out) | 设置和所有驱动程序PrintWriter使用的日志记录/跟踪对象DriverManager |

六、[java.sql.Connection](https://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html)  
[UDT：互联网数据传输协议](https://baike.baidu.com/item/UDT)  
6.1、描述：
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
6.2、字段说明：  
|数据类型|方法|说明|
|---|---|---|
|static int|TRANSACTION_NONE|一个常量，指示不支持事务|
|static int|TRANSACTION_NONE|一个常量，指示不支持事务|
|static int|TRANSACTION_READ_COMMITTED|一个常量，表示防止脏读; 可以发生不可重复的读取和幻像读取|
|static int|TRANSACTION_READ_UNCOMMITTED|一个常量，表示可以发生脏读，不可重复读和幻像读|
|static int|TRANSACTION_REPEATABLE_READ|一个常量，表示防止脏读和不可重复读; 可以发生幻像读取|
|static int|TRANSACTION_SERIALIZABLE|一个常量，表示禁止脏读，不可重复读和幻像读|

6.3、方法说明：  
 | 返回类型 | 方法 | 说明 | 
| --- | --- | --- | 
| void | abort(Executor executor) | 终止打开的连接 |
| void | clearWarnings() | 清除为此Connection对象报告的所有警告 |
| void | close() | 立即释放此Connection对象的数据库和JDBC资源，而不是等待它们自动释放 |
| void | commit() | 使自上次提交/回滚以来所做的所有更改成为永久更改，并释放此Connection对象当前持有的所有数据库锁 |
| Array | createArrayOf(String typeName, Object[] elements) | 用于创建Array对象的工厂方法 |
| Blob | createBlob() | 构造一个实现Blob接口的对象 |
| Clob | createClob() | 构造一个实现Clob接口的对象 |
| NClob | createNClob() | 构造一个实现NClob接口的对象 |
| SQLXML | createSQLXML() | 构造一个实现SQLXML接口的对象 |
| Statement | createStatement() | 创建Statement用于将SQL语句发送到数据库的对象 |
| Statement | createStatement(int resultSetType, int resultSetConcurrency) | 创建一个Statement将生成 ResultSet具有给定类型和并发性的对象的对象 |
| Statement | createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) | 创建一个Statement对象，该对象将生成ResultSet具有给定类型，并发性和可保持性的 对象 |
| Struct | createStruct(String typeName, Object[] attributes) | 用于创建Struct对象的工厂方法 |
| boolean | getAutoCommit() | 检索此Connection 对象的当前自动提交模式 |
| String | getCatalog() | 检索此Connection对象的当前目录名称 |
| Properties | getClientInfo() | 返回一个列表，其中包含驱动程序支持的每个客户端信息属性的名称和当前值 |
| String | getClientInfo(String name) | 返回name指定的客户端信息属性的值 |
| int | getHoldability() | 检索ResultSet使用此Connection对象创建的对象的当前可保存性 |
| DatabaseMetaData | getMetaData() | 检索DatabaseMetaData包含有关此Connection对象表示连接的数据库的元数据的 对象 |
| int | getNetworkTimeout() | 检索驱动程序等待数据库请求完成的毫秒数 |
| String | getSchema() | 检索此Connection对象的当前架构名称 |
| int | getTransactionIsolation() | 检索此Connection对象的当前事务隔离级别 |
| Map<String,Class<?>> | getTypeMap() | 检索Map与此Connection对象关联的 对象 |
| SQLWarning | getWarnings() | 检索此Connection对象上的调用报告的第一个警告  |
| boolean | isClosed() | 检索此Connection对象是否已关闭 |
| boolean | isReadOnly() | 检索此Connection 对象是否处于只读模式 |
| boolean | isValid(int timeout) | 如果连接尚未关闭且仍然有效，则返回true |
| String | nativeSQL(String sql) | 将给定的SQL语句转换为系统的本机SQL语法 |
| CallableStatement | prepareCall(String sql) | 创建一个CallableStatement用于调用数据库存储过程的对象 |
| CallableStatement | prepareCall(String sql, int resultSetType, int resultSetConcurrency) | 创建一个CallableStatement将生成 ResultSet具有给定类型和并发性的对象的对象 |
| CallableStatement | prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) | 创建一个CallableStatement将生成 ResultSet具有给定类型和并发性的对象的对象 |
| PreparedStatement | prepareStatement(String sql) | 创建PreparedStatement用于将参数化SQL语句发送到数据库的对象 |
| PreparedStatement | prepareStatement(String sql, int autoGeneratedKeys) | 创建一个PreparedStatement能够检索自动生成的密钥的默认对象 |
| PreparedStatement | prepareStatement(String sql, int[] columnIndexes) | 创建一个PreparedStatement能够返回给定数组指定的自动生成的键的默认对象 |
| PreparedStatement | prepareStatement(String sql, int resultSetType, int resultSetConcurrency) | 创建一个PreparedStatement将生成 ResultSet具有给定类型和并发性的对象的对象 |
| PreparedStatement | prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) | 创建一个PreparedStatement对象，该对象将生成ResultSet具有给定类型，并发性和可保持性的 对象 |
| PreparedStatement | prepareStatement(String sql, String[] columnNames) | 创建一个PreparedStatement能够返回给定数组指定的自动生成的键的默认对象 |
| void | releaseSavepoint(Savepoint savepoint) | 从当前事务中删除指定的Savepoint 和后续的Savepoint对象 |
| void | rollback() | 撤消当前事务中所做的所有更改，并释放此Connection对象当前持有的所有数据库锁 |
| void | rollback(Savepoint savepoint) | 取消Savepoint设置给定对象后所做的所有更改 |
| void | setAutoCommit(boolean autoCommit) | 将此连接的自动提交模式设置为给定状态 |
| void | setCatalog(String catalog) | 设置给定的目录名称，以便选择要Connection在其中工作的此对象的数据库的子空间 |
| void | setClientInfo(Properties properties) | 设置连接的客户端信息属性的值 |
| void | setClientInfo(String name, String value) | 将name指定的客户端信息属性的值设置为value指定的值 |
| void | setHoldability(int holdability) | 将ResultSet使用此Connection对象创建的对象的默认可保存性更改为给定的可保持性 |
| void | setNetworkTimeout(Executor executor, int milliseconds) | 设置Connection从Connection 遗嘱中创建的最大句点或对象，等待数据库回复任何一个请求 |
| void | setReadOnly(boolean readOnly) | 将此连接置于只读模式，作为驱动程序的提示以启用数据库优化 |
| Savepoint | setSavepoint() | 在当前事务中创建一个未命名的保存点，并返回Savepoint表示它的新对象 |
| Savepoint | setSavepoint(String name) | 在当前事务中创建具有给定名称的保存点，并返回Savepoint表示它的新对象 |
| void | setSchema(String schema) | 设置要访问的给定模式名称 |
| void | setTransactionIsolation(int level) | 尝试将此Connection对象的事务隔离级别更改为 给定的对象 |
| void | setTypeMap(Map<String,Class<?>> map) | 将给定TypeMap对象安装为此Connection对象的类型映射 |


---
<a id="a_down"></a>  
<a href="#a_top">Top</a> 
<a href="#a_catalogue">Catalogue</a>
