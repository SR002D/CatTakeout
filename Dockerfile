# 从jdk17创建镜像
FROM openjdk:17.0.2
# 将时区设置为上海
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
# 创建文件夹
RUN mkdir -p /opt/projects/cattakeout
# 设置工作路径，后续命令都会在这里进行
WORKDIR /opt/projects/cattakeout
# 将jar包复制到工作路径
ADD ./target/CatTakeout-0.0.1-SNAPSHOT.jar app.jar
# 暴露端口
EXPOSE 8080
# 执行
ENTRYPOINT ["java", "-jar", "app.jar"]