package dubbo.boot.sharding.config;

import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.NoneShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @className ShardingConfiguration
 * @Description
 * @Author chungaochen
 * Date 2020/4/11 0:22
 * Version 1.0
 **/
@Configuration
public class ShardingConfig {
    @Bean
    @Qualifier()
    @Primary
    public ShardingRuleConfiguration shardingRuleConfiguration() {
        final ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        //选需要分片的对应的库和表
        TableRuleConfiguration sappPaperRankingTableRuleConfig = new TableRuleConfiguration("user","ds0.user$->{0..1}");
        // 配置分库 + 分表 策略
        sappPaperRankingTableRuleConfig.setDatabaseShardingStrategyConfig(new NoneShardingStrategyConfiguration());
        sappPaperRankingTableRuleConfig.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", new TableShardingAlgorithm()));
        // 配置分片规则
        shardingRuleConfiguration.getTableRuleConfigs().add(sappPaperRankingTableRuleConfig);
        return shardingRuleConfiguration;
    }
}