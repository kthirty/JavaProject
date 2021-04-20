package top.kthirty.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kthirty.web.IgnoreRespResult;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RespTestController {
    @GetMapping("test1")
    public void test1(){ }

    @GetMapping("test2")
    public String test2(){
        return "test2";
    }
    @GetMapping("test3")
    public Map<String, Object> test3(){
        Map<String, Object> map = new HashMap(4);
        map.put("kty1","vle1");
        return map;
    }
    @GetMapping("test4")
    @IgnoreRespResult
    public Map<String, Object> test4(){
        Map<String, Object> map = new HashMap(4);
        map.put("kty1","vle1");
        return map;
    }
}
