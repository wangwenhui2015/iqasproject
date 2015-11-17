一、说明：
1.首先该工程是使用maven来管理的，所以对于项目中需要的jar包都需要在pom.xml文件中添加依赖。
2.项目采用了SpringMVC3+Hibernate3+Mysql+Maven3.3
3.项目采用了jdk1.7，编码采用了UTF-8
4.项目发布在tomcat上的访问路径：http://loaclhost:8080/iqasweb/

二、个性化配置：
1.在jdbc.properties中配置了本地mysql数据库的连接信息。
2.wordnet.properties文件中配置了wordnet在本地的存放路径和版本信息。


三、运行项目：
1.项目热部署到tomcat7上，但是在eclipse中修改代码信息，页面不会变化，需要重新发布才可以（重新发布不需要重启tomcat），所以对于一些信息可以业务方法的验证可以采用单元测试方法。
方法：先运行tomcat，选中pom.xml文件右击->maven build（第二个）  在Goals中输入：tomcat7:deploy   最后点击run  。下次在运行选中第一个maven build
访问路径：http://localhost:8088/iqasweb/

2.项目发布到jetty容器上，实现修改代码后可以立即在网页上看到效果。（可以用于方便测试项目）
方法：选中pom.xml文件右击->maven build  在Main页的Goals中输入：clean jetty:run   在JRE页的VM argument中输入：-Xms1024m -Xmx2048m   最后点击run。。下次在运行选中第一个maven build
访问路径：http://localhost:8088/iqasweb/

[ERROR] EXCEPTION 
java.lang.OutOfMemoryError: PermGen space


四、手机端统一访问网址：http://192.168.0.110:8080/iqasweb/mobile/**/**.html