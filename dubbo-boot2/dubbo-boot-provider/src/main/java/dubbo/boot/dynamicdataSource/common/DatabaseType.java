package dubbo.boot.dynamicdataSource.common;

public enum DatabaseType {

    SHARDING_DATA_SOURCE("sharding","sharding-jdbc数据源"),
    SPRING_DATA_SOURCE("spring","spring数据源"),
    MYCAT_DATA_SOURCE("mycat","mycat数据源"),
    MYSQL_DATA_SOURCE("mysql","msyql数据源");

    private String source;

    private String desc;

    DatabaseType(String source,String desc){
        this.source=source;
        this.desc=desc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}