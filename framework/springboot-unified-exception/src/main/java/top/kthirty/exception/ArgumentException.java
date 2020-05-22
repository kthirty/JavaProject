package top.kthirty.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.kthirty.api.IResultCode;

/**
 * 参数异常
 * <br/>
 * @author KThirty
 * @since 2020/5/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ArgumentException extends RuntimeException {
    private IResultCode resultCode;

    public ArgumentException(IResultCode resultCode,String message){
        super(resultCode.getMessage()+message);
        this.resultCode = resultCode;

    }
}
