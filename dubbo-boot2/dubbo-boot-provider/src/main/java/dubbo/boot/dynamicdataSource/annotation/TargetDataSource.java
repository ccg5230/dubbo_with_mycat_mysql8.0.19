package dubbo.boot.dynamicdataSource.annotation;


import dubbo.boot.dynamicdataSource.common.DatabaseType;

import java.lang.annotation.*;

/**
 * 在方法上使用，用于指定使用哪个数据源
 *
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    DatabaseType source() default DatabaseType.SPRING_DATA_SOURCE;
}