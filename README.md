#zookeeper 客户端与zk版本关系
对于zookeeper 3.4.x服务器版本，只有Curator 2.x.x才支持，我使用的是Curator 4.0.1版本，不支持Zookeeper 3.4.5 ,
需使用zk 3.5.5

#consul mac os 安装
1.下载地址https://www.consul.io/downloads.html

2.将解压后的文件consul  拷贝到/usr/local/bin下sudo cp consul /usr/local/bin

3.打开bin文件，执行consul，查看consul命令，如下即表示成功

5.启动：consul agent -dev

6.http://localhost:8500/ui/dc1/services