package dubbo.boot.cofig;

import dubbo.boot.dynamicdataSource.DatabaseContextHolder;
import dubbo.boot.dynamicdataSource.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @className MybatisConfig
 * @Description
 * @Author chungaochen
 * Date 2020/4/11 11:14
 * Version 1.0
 **/
@Configuration
@MapperScan(basePackages = "dubbo.boot.dao.mapper", sqlSessionFactoryRef = "sessionFactory")
public class MybatisConfig {

    @Bean(name = "mycatDataSource")
    @Qualifier("mycatDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.mycat")
    public DataSource mycatDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("mysqlDataSource")
    @Qualifier("mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("shardingBaseDataSourceMaster0")
    @Qualifier("shardingBaseDataSourceMaster0")
    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.master0")
    public DataSource shardingBaseDataSourceMaster0() {
        return DataSourceBuilder.create().build();
    }

    @Bean("shardingBaseDataSourceMaster0Slave0")
    @Qualifier("shardingBaseDataSourceMaster0Slave0")
    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.master0slave0")
    public DataSource shardingBaseDataSourceMaster0Slave0() {
        return DataSourceBuilder.create().build();
    }

    @Bean("shardingDataSourceMy")
    @Qualifier("shardingDataSourceMy")
    public DataSource shardingDataSource(@Qualifier("shardingBaseDataSourceMaster0") DataSource shardingBaseDataSourceMaster0,
                                            @Qualifier("shardingBaseDataSourceMaster0Slave0") DataSource shardingBaseDataSourceMaster0Slave0,
                                            ShardingRuleConfiguration shardingRuleConfiguration) throws SQLException {
        final Properties properties = new Properties();
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds0", shardingBaseDataSourceMaster0);
        dataSourceMap.put("master0", shardingBaseDataSourceMaster0);
        dataSourceMap.put("master0slave0", shardingBaseDataSourceMaster0Slave0);
        // 获取数据源对象
        final DataSource shardingDatasource = ShardingDataSourceFactory.createDataSource(dataSourceMap,
                shardingRuleConfiguration, properties);
        return shardingDatasource;
    }


    @Bean("dynamicDataSource")
    @Qualifier("dynamicDataSource") //@Qualifier("shardingDataSourceMy")强制不使用springboot自动实例的shardingDataSource
    public DataSource dynamicDataSource(@Qualifier("mycatDataSource") DataSource mycatDataSource,
                                        @Qualifier("mysqlDataSource") DataSource mysqlDataSource,
                                        @Qualifier("shardingDataSourceMy")DataSource shardingDataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("mycat", mycatDataSource);
        dataSourceMap.put("mysql", mysqlDataSource);
        dataSourceMap.put("sharding", shardingDataSource);
        // 将 master 数据源作为默认指定的数据源
        dynamicDataSource.setDefaultTargetDataSource(mycatDataSource);
        // 将 master 和 slave 数据源作为指定的数据源
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        // 保存数据源key,后面切换数据源，要判断key是否存在
        DatabaseContextHolder.dataSourceIds.add("mycat");
        DatabaseContextHolder.dataSourceIds.add("mysql");
        DatabaseContextHolder.dataSourceIds.add("sharding");
        return dynamicDataSource;
    }

    /**
     * 需要在springboot启动类自动禁用数据源装配，并且加上@Primary
     * MybatisAutoConfiguration required a single bean, but 2 were found
     */
    @Primary
    @Bean(name = "sqlSessionFactoryBean")
    @Qualifier("sqlSessionFactoryBean")
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource作为数据源则不能实现切换
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));    // 扫描映射文件
        return sqlSessionFactoryBean;
    }

    /**
     * @Description:根据动态数据源创建sessionFactory
     */
    @Bean(name = "sessionFactory")
    public SqlSessionFactory sessionFactory(@Qualifier("sqlSessionFactoryBean") SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource")DataSource dynamicDataSource) {
        // 配置事务管理, 使用事务时在方法头部添加@Transactional注解即可
        return new DataSourceTransactionManager(dynamicDataSource);
    }


}