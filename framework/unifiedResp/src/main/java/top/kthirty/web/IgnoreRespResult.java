package top.kthirty.web;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description : 忽略系统自带的参数响应处理
 * @author : KTHIRTY
 * @date : Created in 2020/9/28 15:03
 * <p>
 *     默认情况下，系统会将非<code>R</code>类型的响应参数
 *     封装为<code>R</code>类型
 *     例：方法响应参数为 Object 类型的 o，则实际响应信息为R.success(o)
 *     当方法或类上存在 @IgnoreRespResult 注解时则直接返回 o
 * </p>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface IgnoreRespResult { }