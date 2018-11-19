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
    |--src                                      #文档相关
        |--doc
        |--main
            |--java                             #代码主目录
                |--com.huateng.qrcode
                    |--common                   #spring通用配置（拦截器、监听）
                        |--interceptor
                        |--listener
                    |--controller               #控制器
                    |-mapper                    #mybatis数据接口
                    |--model                    #模型层（实体类、请求报文参数类）
                        |--entity
                        |--param
                    |--qrserver                 #netty提供socket服务相关
                        |--config               
                        |--manager
                        |--netty
                        |--parser
                    |--service                  #service接口
                    |--utils                    #工具类
            |--resources                        #资源文件主目录
                |--mybatis                      #mybatis相关配置
                    |--mapper                   #mybatis数据接口关联配置文件
                |--spring                       #spring框架整合配置文件
            |--webapp                           #webapp静态资源目录
                |--WEB-INF
                    |--web.xml                  #web应用启动入口配置文件
        |--test                                 #单元测试目录
            |--java                             #单元测试主目录
                
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




