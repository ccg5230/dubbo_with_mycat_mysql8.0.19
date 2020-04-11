//package dubbo.boot.dynamicdataSource;
//
///**
// * @className MybatisConfiguration
// * @Description
// * @Author chungaochen
// * Date 2020/4/10 23:39
// * Version 1.0
// **/
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.github.pagehelper.PageHelper;
//import dubbo.boot.dynamicdataSource.common.DatabaseType;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
//import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * mybatis 配置数据源类
// *
// */
//@Configuration
//public class MybatisConfiguration {
//
//    @Bean(name="shardingDataSource")
//    @Qualifier("shardingDataSource")
//    public DataSource getShardingDataSource() throws SQLException {
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//
//        //  分库分表逻辑，在这里不做代码展示
//
//        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig,new ConcurrentHashMap(), properties);
//    }
//
//    @Bean(name="springDataSource")
//    @Qualifier("springDataSource")
//    public  DataSource getDataSource() {
//        druidDataSource  = new DruidDataSource();
//        druidDataSource.setDriverClassName("数据库驱动");
//        druidDataSource.setUrl("数据库链接");
//        druidDataSource.setUsername("用户名");
//        druidDataSource.setPassword("密码");
//        return druidDataSource ;
//    }
//
//    @Bean
//    @Primary
//    public DynamicDataSource dataSource(
//            @Qualifier("shardingDataSource")DataSource shardingDataSource,
//            @Qualifier("druidDataSource")DataSource druidDataSource) {
//        Map<Object, Object> targetDataSource=new HashMap<Object, Object>();
//        targetDataSource.put(DatabaseType.SHARDING_DATA_SOURCE.getSource(),shardingDataSource);
//        targetDataSource.put(DatabaseType.DRUID_DATA_SOURCE.getSource(),druidDataSource);
//
//        DynamicDataSource dynamicDataSource=new DynamicDataSource();
//        dynamicDataSource.setTargetDataSources(targetDataSource);
//        dynamicDataSource.setDefaultTargetDataSource(druidDataSource);
//
//        return dynamicDataSource;
//
//    }
//
//    @Bean(name = "sqlSessionFactory")
//    @ConfigurationProperties(prefix = "mybatis")
//    public SqlSessionFactory sqlSessionFactoryBean(
//            @Qualifier("shardingDataSource")DataSource shardingDataSource,
//            @Qualifier("druidDataSource")DataSource druidDataSource) {
//
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(this.dataSource(shardingDataSource, druidDataSource));
//        if(StringUtils.isNotBlank(typeAliasesPackage)){
//            bean.setTypeAliasesPackage(typeAliasesPackage);
//        }
//        bean.setPlugins(plugins);
//        try {
//            bean.setMapperLocations(resolver.getResources(xmlLocation));
//            return bean.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//
//
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//    @Bean
//    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//
//
//    public  Map<String, DataSource> createDataSourceMap() {
//        Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>(4);
//        // 配置第一个数据源
//        DruidDataSource data1 = new DruidDataSource();
//        data1.setDriverClassName("数据源驱动");
//        data1.setUrl("数据库链接1");
//        data1.setUsername("用户名");
//        data1.setPassword("密码");
//        dataSourceMap.put("data1 ", data1 );
//
//        // 配置第二个数据源
//        DruidDataSource data2 = new DruidDataSource();
//        data2.setDriverClassName("数据源驱动");
//        data2.setUrl("数据库链接2");
//        data2.setUsername("用户名");
//        data2.setPassword("密码");
//        dataSourceMap.put("data2 ", data2 );
//
//        // 配置第三个数据源
//        DruidDataSource data3 = new DruidDataSource();
//        data3.setDriverClassName("数据源驱动");
//        data3.setUrl("数据库链接3");
//        data3.setUsername("用户名");
//        data3.setPassword("密码");
//        dataSourceMap.put("data3", data3);
//
//        // 配置第四个数据源
//        DruidDataSource data4 = new DruidDataSource();
//        data4.setDriverClassName("数据源驱动");
//        data4.setUrl("j数据库链接4");
//        data4.setUsername("用户名");
//        data4.setPassword("密码");
//        dataSourceMap.put("data4", data4);
//
//        return dataSourceMap;
//    }
//
//}