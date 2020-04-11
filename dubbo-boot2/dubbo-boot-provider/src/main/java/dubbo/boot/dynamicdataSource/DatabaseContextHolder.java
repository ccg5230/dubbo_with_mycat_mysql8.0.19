package dubbo.boot.dynamicdataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @className DatabaseContextHolder
 * @Description 数据源切换工具类
 **/
public class DatabaseContextHolder {

    private static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    /**
     * 存储已经注册的数据源的key
     */
    public static final List<String> dataSourceIds = new ArrayList<>();

    /**
     * 线程级别的私有变量
     */
    private static final ThreadLocal<String> contextHolder  = new ThreadLocal<>();

    public static String getDataSource() {
        return contextHolder .get();
    }

    public static void setDataSource(String dataSourceKey) {
        logger.info("切换至{}数据源", dataSourceKey);
        contextHolder .set(dataSourceKey);
    }

    /**
     * 设置数据源之前一定要先移除
     */
    public static void clear() {
        contextHolder .remove();
    }

    /**
     * 判断指定DataSrouce当前是否存在
     *
     * @param dataSourceId
     * @return
     */
    public static boolean containsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }
}