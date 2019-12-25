package com.demo.version;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName ApiVersion
 * @Description TODO
 * @Author yangguoqing
 * @Date 2019/12/25 9:35 上午
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {
	/**
	 * @return 版本号
	 */
	int value() default 1;
}
