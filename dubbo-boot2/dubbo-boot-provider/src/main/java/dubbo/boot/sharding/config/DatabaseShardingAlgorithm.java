//package dubbo.boot.sharding.config;
//
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
//
//import java.util.Collection;
//
///**
// * @className TableShardingAlgorithm
// * @Description 精确分片算法：分库策略类
// * <p>参考官网：https://shardingsphere.apache.org/document/current/cn/features/sharding/concept/sharding/</p>
// * @Author chungaochen
// * Date 2020/4/8 15:25
// * Version 1.0
// **/
//public class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
//    @Override
//    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
//        for (String each : collection) {
//            if (each.endsWith(Long.parseLong(preciseShardingValue.getValue().toString()) % 2+"")) {
//                return each;
//            }
//        }
//        throw new IllegalArgumentException();
//    }
//}