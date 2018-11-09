### 行业二维码项目

#### 注意：项目中的db2驱动包为手动导入到本地maven仓库中，使用下面命令导入（注意修改命令中路径）
```$xslt

mvn install:install-file -DgroupId=com.ibm.db2.jcc -DartifactId=db2jcc -Dversion=1.0.0 -Dpackaging=jar -Dfile=/Users/qinyupeng/Downloads/db2jcc.jar
mvn install:install-file -DgroupId=com.ibm.db2.jcc -DartifactId=db2jcc-license-cu -Dversion=1.0.0 -Dpackaging=jar -Dfile=/Users/qinyupeng/Downloads/db2jcc.jar
```
> 备注：jar包在这里 
https://pan.baidu.com/s/11r0y44dk2J4j0yxycX7URw


#### 环境和版本相关
> spring4.3.18，mybatis3.2.8，netty4.1.25

#### 配置文件相关
* config.properties
> 数据库配置相关信息、netty通讯服务端口配置、HttpClient通讯链接配置；

#### 使用maven集成的mybatis逆向工程插件
1、generator-db.properties配置逆向工程的数据库信息，包括驱动路径；
```$xslt
#配置数据库驱动包路径（db2）
jdbc.driverLocation=src/main/webapp/WEB-INF/lib/db2jcc-1.0.0.jar
jdbc.driverClass=com.ibm.db2.jcc.DB2Driver
jdbc.connectionURL=jdbc:db2://192.168.43.203:50000/SAMPLE
jdbc.userId=db2admin
jdbc.password=db2admin
```

2、generatorConfig.xml配置逆向工程的数据表信息，生成的目录信息等；（本项目只需要添加表信息即可）
```$xslt
<!-- 配置表
    schema：不用填写
    tableName: 表名
    enableCountByExample、enableSelectByExample、enableDeleteByExample、enableUpdateByExample、selectByExampleQueryId：
    去除自动生成的例子
-->
<table schema="" tableName="t_user" domainObjectName="User" enableCountByExample="false"
       enableSelectByExample="true"
       enableDeleteByExample="false" enableUpdateByExample="true" selectByExampleQueryId="true">
</table>
```

3、idea中Edit Configuration配置maven命令
```$xslt
mybatis-generator:generate -e
```

4、点击idea中plugin对应的maven命令即可

