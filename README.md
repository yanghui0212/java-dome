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
        docker run --name config-server -p 9393:9393 --link d584e7053e67:eureka-server --link 59c341dd9614:rabbitmq-server  -d config-server:9393
  ## 4.vertx