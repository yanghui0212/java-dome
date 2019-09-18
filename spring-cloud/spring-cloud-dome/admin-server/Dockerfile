#基础镜像：仓库是java，tag是8
FROM openjdk:8u222-slim
#维护者信息
MAINTAINER yangqianghui yangqianghui@hotmail
#将打包好的spring程序拷贝到容器中的指定位置
ADD target/admin-server-0.0.1.jar app.jar
#容器对外暴露20000端口
EXPOSE 20000
#容器启动后需要执行的命令
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar"]