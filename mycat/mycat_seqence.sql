-- 需要在mycat对应的schema.xml的dataNode配置对应的真实数据库执行，如果是多个分片库都需要执行

DROP TABLE IF EXISTS MYCAT_SEQUENCE;
CREATE TABLE MYCAT_SEQUENCE (
NAME VARCHAR (50) NOT NULL,
current_value INT NOT NULL,
increment INT NOT NULL DEFAULT 100,
remark varchar(100),
PRIMARY KEY (NAME)
) ENGINE = INNODB ;


-- 创建存储函数1--– 获取当前sequence的值（返回当前值,增量）
DROP FUNCTION IF EXISTS `mycat_seq_currval`;
DELIMITER ;;
CREATE  FUNCTION `mycat_seq_currval`(seq_name VARCHAR(50)) 
RETURNS varchar(64) CHARSET utf8
    DETERMINISTIC
BEGIN 
        DECLARE retval VARCHAR(64);
        SET retval="-999999999,null";  
        SELECT concat(CAST(current_value AS CHAR),",",CAST(increment AS CHAR) ) INTO retval 
          FROM MYCAT_SEQUENCE  WHERE name = seq_name;  
        RETURN retval ; 
END
;;
DELIMITER ;

-- 创建存储函数2-- 获取下一个sequence值
DROP FUNCTION IF EXISTS `mycat_seq_nextval`;
DELIMITER ;;
CREATE FUNCTION `mycat_seq_nextval`(seq_name VARCHAR(50)) RETURNS varchar(64)
 CHARSET utf8
    DETERMINISTIC
BEGIN 
         UPDATE MYCAT_SEQUENCE  
                 SET current_value = current_value + increment 
                  WHERE name = seq_name;  
         RETURN mycat_seq_currval(seq_name);  
END
;;
DELIMITER ;

-- 创建存储函数3--设置sequence值
DROP FUNCTION IF EXISTS `mycat_seq_setval`;
DELIMITER ;;
CREATE FUNCTION `mycat_seq_setval`(seq_name VARCHAR(50), value INTEGER) 
RETURNS varchar(64) CHARSET utf8
    DETERMINISTIC
BEGIN 
         UPDATE MYCAT_SEQUENCE  
                   SET current_value = value  
                   WHERE name = seq_name;  
         RETURN mycat_seq_currval(seq_name);  
END
;;
DELIMITER ;

-- 插入sequence记录，当前值和步长根据自己需要配置

INSERT INTO MYCAT_SEQUENCE(name,current_value,increment,remark) VALUES ('GLOBAL', 1, 100,'GLOBAL');
INSERT INTO MYCAT_SEQUENCE(name,current_value,increment,remark) VALUES ('USER_ADDRESS', 1, 100,'match:USER_ADDRESS');