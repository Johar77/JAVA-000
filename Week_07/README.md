# 第13节课作业实践

## 1、用今天课上学习的知识，分析自己系统的SQL和表结构  

- 遵守基本的数据库规则，命名规范，不同的字段选择合适的字段，主键使用单调递增，但是时间没有考虑bigint，原因是bigint占用的字节数和datatime都是8个字节，另外datetime作为时间特有类型，可以保存时区信息
- 表表之间没有增加外键关联
- 将热点数据进行拆分，如用户的登录数据单调一张数据表





## 2、按自己设计的表结构，插入100万订单模拟数据，测试不同方式的插入效率  

- 用自己写的原始jdbc的方法，一般10000次连接后，可用短裤资源耗尽，创建连接失败
- 用带线程池的原生jdbc方法，很慢，小时级，没有继续测试
- 用多线程的方法，20min左右
- 批量处理的方式，分批将数据准备好，然后调用批量插入的接口，可以做到几分钟
- 代码路径：https://github.com/Johar77/JAVA-000/tree/main/Week_07/spring-mock-data



# 第14节课作业实践

## 1、配置一遍异步复制，半同步复制、组复制 

-  配置异步复制遇到如下坑：

  本机使用免安装版mysql的包，笔记本缺少依赖库，将vc_redist_2013,vc_redist_2015,vc_redist_2019都安装才好

  安装mysql5.7根目录的my.ini配置没有起作用，data文件生成到bin目录下面，没有找到好的解决方法，将其直接拷贝到mysql根路径，启动的时候指定my.ini配置文件

  a安装mysql8.0根目录my.ini配置没有起左右，data文件生成路径没有找到，先指定my.ini配置文件安装服务，最后再创建data

  

## 2、读写分离-动态切换数据源版本1.0  



## 3、读写分离-数据库框架版本2.0  



