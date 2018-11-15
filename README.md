### 行业二维码项目

#### 注意：项目中的db2驱动包为手动导入到本地maven仓库中，使用下面命令导入（注意修改命令中路径）
```$xslt

mvn install:install-file -DgroupId=com.ibm.db2.jcc -DartifactId=db2jcc -Dversion=1.0.0 -Dpackaging=jar -Dfile=/Users/qinyupeng/Downloads/db2jcc.jar
mvn install:install-file -DgroupId=com.ibm.db2.jcc -DartifactId=db2jcc-license-cu -Dversion=1.0.0 -Dpackaging=jar -Dfile=/Users/qinyupeng/Downloads/db2jcc.jar
```
> 备注：jar包在这里 
https://pan.baidu.com/s/11r0y44dk2J4j0yxycX7URw


#### 环境和版本相关
> spring4.3.18，mybatis3.4.6，netty4.1.25，druid1.1.10，pageHelper4.1.6；


#### 配置文件相关
* config.properties
> 数据库配置相关信息、netty通讯服务端口配置、HttpClient通讯链接配置；


#### mybatis-plus提供了代码生成工具
> 如需偷懒使用代码生成注解，请移至：
https://github.com/ASmallSaltFish/mybatis-generator

