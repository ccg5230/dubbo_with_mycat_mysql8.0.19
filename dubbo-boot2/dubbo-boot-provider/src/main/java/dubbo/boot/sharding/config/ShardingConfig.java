package dubbo.boot.sharding.config;

import org.apache.shardingsphere.api.config.masterslave.LoadBalanceStrategyConfiguration;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.NoneShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

/**
 * @className ShardingConfiguration
 * @Description Sharding分库分表手动配置
 * @Author chungaochen
 * Date 2020/4/11 0:22
 * Version 1.0
 **/
@Configuration
public class ShardingConfig {
    @Bean
    @Qualifier("shardingRuleConfiguration")
    @Primary
    public ShardingRuleConfiguration shardingRuleConfiguration() {
        final ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        //绑定表规则列表
        List<String> bindingTableGroups = new ArrayList<>();
        bindingTableGroups.add("user");
        shardingRuleConfiguration.setBindingTableGroups(bindingTableGroups);
        //选需要分片的对应的库和表
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration("user","ds0.user$->{0..1}");
        // 配置分库 + 分表 策略
        tableRuleConfiguration.setDatabaseShardingStrategyConfig(new NoneShardingStrategyConfiguration());
        tableRuleConfiguration.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new TableShardingAlgorithm()));
        //配置主键生成策略
        KeyGeneratorConfiguration keyGeneratorConfiguration = new KeyGeneratorConfiguration("SNOWFLAKE","id");
        tableRuleConfiguration.setKeyGeneratorConfig(keyGeneratorConfiguration);
        //配置主库从库
        List<String> slaveDataSourceNames = new ArrayList<String>();
        slaveDataSourceNames.add("master0slave0");
        //从库负载均衡算法类型
        LoadBalanceStrategyConfiguration loadBalance = new LoadBalanceStrategyConfiguration("round_robin");
        MasterSlaveRuleConfiguration masterSlaveRuleConfiguration = new MasterSlaveRuleConfiguration("ds0","master0",slaveDataSourceNames, loadBalance );
        // 配置分片规则
        shardingRuleConfiguration.getTableRuleConfigs().add(tableRuleConfiguration);
        shardingRuleConfiguration.getMasterSlaveRuleConfigs().add(masterSlaveRuleConfiguration);
        return shardingRuleConfiguration;
    }
}