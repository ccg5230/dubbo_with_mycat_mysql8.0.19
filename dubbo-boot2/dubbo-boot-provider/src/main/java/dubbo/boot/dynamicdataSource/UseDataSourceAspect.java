package dubbo.boot.dynamicdataSource;

import dubbo.boot.dynamicdataSource.annotation.TargetDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @className UseDataSourceAspect
 * @Description 切面处理数据源切换的操作
 * @Author chungaochen
 * Date 2020/4/10 23:31
 * Version 1.0
 **/
//@Component
//@Aspect
//@Order(Ordered.LOWEST_PRECEDENCE-1)
public class UseDataSourceAspect {

    @Pointcut("@annotation(dubbo.boot.dynamicdataSource.annotation.TargetDataSource)")
    public void useDataSource() {
    }

    @Around("useDataSource() && @annotation(anno)")
    public Object dataSourceSwitcher(ProceedingJoinPoint joinPoint, TargetDataSource anno) throws Throwable {
        DatabaseContextHolder.setDataSource(anno.source().getSource());
        try {
            //执行方法
            Object result = joinPoint.proceed();
            return result;
        }catch (Exception e){
            throw e;
        }finally {
            //切换回原来的数据源（重要）
            DatabaseContextHolder.clear();
        }
    }

}