package top.kthirty.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.kthirty.api.R;
import top.kthirty.api.ResultCode;

@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 父类异常处理
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e){
        return R.fail(ResultCode.FAILURE);
    }

    /**
     * 参数异常
     */
    @ExceptionHandler(ArgumentException.class)
    public R handleArgumentException(ArgumentException e){
        return R.fail(e.getResultCode(),e.getMessage());
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public R handleNullPointerException(NullPointerException e){
        return R.fail(ResultCode.FAILURE,"空指针异常");
    }
}
