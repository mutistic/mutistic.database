# <a id="a_top">JDBC：Java数据库连接</a>
[Java 10 API](https://docs.oracle.com/javase/10/docs/api/overview-summary.html)<br/>
[JDBC-百度百科](https://baike.baidu.com/item/jdbc)<br/>
[Java数据库连接](https://baike.baidu.com/item/Java%E6%95%B0%E6%8D%AE%E5%BA%93%E8%BF%9E%E6%8E%A5)<br/>
[JDBC API](https://docs.oracle.com/javase/10/docs/api/java.sql-summary.html)<br/>
[mysql:mysql-connector-java:8.0.12](https://search.maven.org/artifact/mysql/mysql-connector-java/8.0.12/jar)<br/>

---
### <a id="a_catalogue">目录</a>：
1. <a href="#a_jdbc">JDBC：Java数据库连接</a>

99. <a href="#a_down">down</a>

---
### <a id="a_jdbc">一、JDBC：Java数据库连接：</a> <a href="#">last</a> <a href="#">next</a>

[JDBC数据库驱动模型](https://github.com/mutistic/mutistic.database/blob/master/com.mutisitc.database.jdbc/notes/01_JDBCPModel.png)<br/>
一、定义：
```
JDBC：
  全称Java DataBase Connectivity，Java 数据库连接，是一种用于执行SQL的Java API，可以为多种关系型数据库提供统一访问，
它由一组用Java语言编写的类和接口组成。JDBC提供了一种基准：是Java语言中用来规范客户端程序如何来访问数据库的应用程序接口，
据此可以构建更高级的工具和接口，使数据库开发人员能够编写数据库应用程序

  JDBC也是Sun Microsystems的商标。通常说的JDBC是面向关系型数据库的
```

二、了解JDBC：</br>
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

四、API概述：</br>
[JDBC API](https://docs.oracle.com/javase/10/docs/api/java.sql-summary.html)  
[javax.sql包](https://docs.oracle.com/javase/10/docs/api/java/sql/package-summary.html)  
[javax.sql包](https://docs.oracle.com/javase/10/docs/api/javax/sql/package-summary.html)<br/>
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
4.3、数据类型的映射：
```
从SQL到Java数据类型映射的JDBC规范：
SQL类型			    Java类型
CHAR			java.lang.String
VARCHAR			java.lang.String
LONGVARCHAR		java.lang.String
NUMERIC			java.math.BigDecimal
DECIMAL			java.math.BigDecimal
BIT			    boolean
TINYINT			byte
SMALLINT		short
INTEGER			int
BIGINT			long
REAL			float
FLOAT			double
DOUBLE			double
BINARY			byte[]
VARBINARY		byte[]
LONGVARBINARY	byte[]
DATE			java.sql.Date
TIME			java.sql.Time
TIMESTAMP		java.sql.Timestamp
BLOB			java.sql.Blob
CLOB			java.sql.Clob
Array			java.sql.Array
REF				java.sql.Ref
Struct			java.sql.Struct

注：这种类型匹配不是强制性标准，特定的JDBC厂商可能会改变这种类型匹配。
例如Oracle中的DATE类型是包含时分秒，而java.sql.Date仅仅支持年月日
```

五、程序类型：</br>
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

六、SQL一致性：</br>
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
<a id="a_down"></a>  
<a href="#a_top">Top</a> 
<a href="#a_catalogue">Catalogue</a>