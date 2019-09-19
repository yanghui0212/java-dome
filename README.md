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
  ## 4.vertx