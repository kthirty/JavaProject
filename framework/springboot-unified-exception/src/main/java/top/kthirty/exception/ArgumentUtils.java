package top.kthirty.exception;

import org.springframework.lang.Nullable;
import top.kthirty.api.IResultCode;

/**
 * 参数验证工具类
 * <br/>
 * @author KThirty
 * @since 2020/5/22 15:58
 */
public class ArgumentUtils {
    /**
     * 验证参数非null
     * <br/>
     * @param o 需要验证的变量
     * @param resultCode 如果为null时希望接口返回什么
     * @author KThirty
     * @since 2020/5/22
     */
    public static void notNull(@Nullable Object o, IResultCode resultCode,String message){
        if(null == o){
            throw new ArgumentException(resultCode,message);
        }
    }
}
