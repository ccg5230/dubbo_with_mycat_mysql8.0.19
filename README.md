项目介绍
========

springboot2+dubbo+mycat+mybatis实现mysql8.0.19主从读写分离
----------------------------------------------------------    
增加springboot自定义starter实践  20200403
-------------------------------------------
增加Sharding-JDBC分库分表:主从复制分表不分库实战 20200409
-------------------------------------------------------------------
[Sharding-JDBC](https://shardingsphere.apache.org/)可以快速分库分表，支持分布式主键和事务，是个好中间件，maven boot-starter引入,够轻量apache官网文档不够详细，处于[Apache孵化器阶段](http://incubator.apache.org/projects/shardingsphere.html)，
值得持续关注，和学习源码！！！

mycat\conf目录为mycat连接mysql8.0.19配置，关于mycat连接mysql8.0.19和全局主键配置<br>
`网上内容很多不一定正确，连接mysql的url地址mycat不需要更改带时区带参数的jdbc驱动模式`



mycat\mycat_seqence.sql 为mycat主键队列生成方式为数据库，需要执行sql

:stuck_out_tongue_closed_eyes:待续


