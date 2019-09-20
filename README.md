# java-dome
  ## 1.dubbo
  ## 2.spring-boot
  ## 3.spring-cloud
    1.1: docker部署eureka-server
        Dockerfile文件目录下执行：
        
        docker build -t admin-server:20000 .
        
        构建镜像
    1.2: docker部署config-server
        注意：eureka-server，rabbitmq不能直接写ip，必须写服务名，并且docker启动加参数，完整命令如下：  
                
        docker run --name fonfig -p 9393:9393 -v /logs:/data/logs -v /etc/localtime:/etc/localtime:ro  -e JAVA_OPTS="-Deureka.instance.ip-address=10.134.240.116"  --link eureka:eureka-server --link 59c341dd9614:rabbitmq-server  -d config-server:9393
     
        --name: 指定容器名称
        -p:端口映射
        -v:文件映射  -v /etc/localtime:/etc/localtime:ro  修改容器时间为虚拟机时间
        -e:运行参数配置
        --link:同一台虚拟机上容器链接
        -d:后台运行
        --privileged=true 授权
        
     docker运行：
     redis：   docker run -d -p 6379:6379 --name redis --privileged=true  -v /data/redis/conf:/conf -v /data/redis/data:/data redis:4.0 redis-server --appendonly yes --requirepass "123456"
     mysql:   docker run -p 3306:3306 --name mysql --privileged=true  -v /data/mysql/conf:/etc/mysql/conf.d -v /data/mysql/logs:/logs -v /data/mysql/data:/var/lib/mysql -v /etc/localtime:/etc/localtime:ro -e MYSQL_ROOT_PASSWORD=123456 -d docker.io/mysql:5.7.27
     mongo:   docker run --name mongo --privileged=true  -p 27017:27017 -v /data/mongo/db:/data/db -d docker.io/mongo:4.0.12
     rabbit:  docker run -d --privileged=true --name rabbit -p 15672:15672 -p 5672:5672 -v /data/rabbitmq:/var/lib/rabbitmq docker.io/rabbitmq:3.8-rc-management
     zookeeper:   docker run --privileged=true -d --name zookeeper -p 2181:2181  -d docker.io/zookeeper:3.5.5 
     
     运行自定义镜像
     eureka: docker run --privileged=true --name eureka -p 18761:18761 -v /data/java/logs/eureka:/logs -v /etc/localtime:/etc/localtime:ro  -d eureka:18761
     config: docker run --privileged=true --name config -p 9393:9393 -v /data/java/logs/config:/logs -v /etc/localtime:/etc/localtime:ro -e JAVA_OPTS="-Deureka.instance.ip-address=10.134.240.116"  --link eureka:eureka-server --link rabbit:rabbitmq-server  -d config:9393

  ## 4.vertx