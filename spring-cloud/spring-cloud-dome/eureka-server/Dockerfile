#基础镜像：仓库是java，tag是8
FROM openjdk:8u222-slim
#将打包好的spring程序拷贝到容器中的指定位置
ADD target/eureka-server-0.0.1.jar app.jar
#容器对外暴露18761端口
EXPOSE 18761
#指定环境变量
ENV JAVA_OPTS="-Deureka.instance.ip-address=192.168.163.131"
#容器启动后需要执行的命令
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar"]