#!/bin/sh
#这里可替换为你自己的执行程序，其他代码无需更改
APP_NAME=Cicada-0.0.1-SNAPSHOT.jar
#使用说明，用来提示输入参数
usage() {
       echo "Usage: sh demo.sh [start|stop|restart|status]"
       exit 1
}

#检查程序是否在运行
is_exist() {
       pid=`ps -ef | grep $APP_NAME | grep -v grep | awk '{print $2}' `
       echo "pid==${pid}"
       #如果不存在返回1，存在返回0
       if [ -z "${pid}" ]; then
          return 1
       else
          return 0
       fi
}

#启动方法
start() {
      echo "*********check is_exist in first*****"
      is_exist
      if [ $? -eq "0" ]; then
         echo "${APP_NAME} is already running. pid=${pid} ."
         kill $pid
         sleep 5s
         kill -9 $pid
         echo "kill pid " $pid
      else
         echo "${APP_NAME} is not running and pid is null ."
         nohup java -Xmx512m -Xms512m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/usr/local/application/smart-building-service/JeeSite-Web.dump -jar /usr/local/application/smart-building-service/JeeSite-Web.jar > /usr/local/application/smart-building-service/JeeSite-Web.log 2>&1 &
         echo "run end ************"
         #nohup java -jar $APP_NAME > /dev/null 2>&1 &
      fi
}

#停止方法
stop() {
    is_exist
    if [ $? -eq "0" ]; then
         kill -9 $pid
    else
          echo "${APP_NAME} is not running"
     fi
}

#输出运行状态
status() {
     is_exist
     if [ $? -eq "0" ]; then
        echo "${APP_NAME} is running. Pid is ${pid}"
     else
        echo "${APP_NAME} is not running."
     fi
}

#重启
restart() {
  stop
  start
}

#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
  "start")
     start
     ;;
  "stop")
     stop
     ;;
  "status")
     status
     ;;
  "restart")
     restart
     ;;
   *)
     usage
     ;;
esac