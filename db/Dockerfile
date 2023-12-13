FROM mysql:5.7

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
# 将sql文件拷贝到 目录下， mysql镜像会自动执行
COPY ./db/db_cat.sql /docker-entrypoint-initdb.d