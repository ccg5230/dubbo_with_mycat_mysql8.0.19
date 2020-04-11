package dubbo.boot.dynamicdataSource;

import dubbo.boot.dynamicdataSource.annotation.TargetDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切换数据源Aop切面
 */
@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Exception {
        String dsId = ds.source().getSource();
        if (!DatabaseContextHolder.containsDataSource(dsId)) {
            logger.error("数据源[{}]不存在，使用默认数据源 > {}", ds.source().getSource(), point.getSignature());
        } else {
            logger.debug("Use DataSource : {} > {}", ds.source().getSource(), point.getSignature());
            DatabaseContextHolder.setDataSource(ds.source().getSource());
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
        logger.debug("Revert DataSource : {} > {}", ds.source().getSource(), point.getSignature());
        DatabaseContextHolder.clear();
    }

}