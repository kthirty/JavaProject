package top.kthirty.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kthirty.api.R;
import top.kthirty.api.ResultCode;
import top.kthirty.exception.ArgumentUtils;

@RestController
@RequestMapping("/test/param")
public class TestParamController {
    @GetMapping("not-null")
    public R testParamNotNull(String name){
        ArgumentUtils.notNull(name, ResultCode.PARAM_MISS,"name 不可为空");
        return R.success();
    }
    @GetMapping("null-point")
    public R testNullPoint(Integer age){
        int i = age.intValue();
        return R.success();
    }
}
