# 项目介绍

springboot2+dubbo+mycat+mybatis实现mysql8.0.19主从读写分离

## mycat\conf目录为mycat连接mysql8.0.19配置，关于mycat连接mysql8.0.19和全局主键配置，网上内容很多
##     不一定正确，连接mysql的url地址mycat不需要更改带时区带参数的jdbc驱动模式

## mycat\mycat_seqence.sql 为mycat主键队列生成方式为数据库，需要执行sql