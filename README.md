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

#### 项目目录结构相关
```$xslt
|--QRWebServer
    |--src
        |--doc
        |--main
            |--java                                         #主目录
                |--com.huateng.qrcode                      
                    |--base                                 #项目配置相关
                        |--interceptor
                        |--listener
                    |--common                               #项目通用代码    
                        |--constants
                        |--enums
                        |--exception
                        |--mapper
                        |--model
                    |--controller                           #控制器
                        |--base
                    |--parser                               #报文解析相关    
                        |--impl
                        |--param
                    |--qrserver                             #socket通讯相关        
                        |--manager
                        |--netty
                    |--service                              #服务接口相关
                        |--impl
                    |--utils                                #工具类
                        |--http
        |--resources
            |--mybatis                                      #mybatis配置
                |--mapper                                   #实体类对应的mapper.xml
            |--spring                                       #spring配置文件
        |--webapp
            |--static                                       #静态资源
            |--WEB-INF                                      #web.xml存放目录
    |--test                                                 #单元测试目录
        |--java
            |--com.huateng.test
```


#### 配置文件相关
* config.properties
> 数据库配置相关信息、netty通讯服务端口配置、HttpClient通讯链接配置；

* ServiceConfigEnum枚举类（或者使用service.properties）
> 将服务码和处理类关联映射，当有新的请求平台对接进来，只需要在枚举中维护配置，对应编写处理类业务逻辑即可；<br>
> 注意：在使用service.properties配置文件类维护服务码和处理类关联映射，需要在web.xml中添加监听
```$xslt
<listener>
        <description>应用启动加载服务码和接口关联数据</description>
        <listener-class>
            com.huateng.qrcode.common.listener.MappingServiceCodeListener
        </listener-class>
    </listener>
```

#### mybatis-plus提供了代码生成工具
> 如需偷懒使用代码生成注解，请移至：
https://github.com/ASmallSaltFish/mybatis-generator
1111111




