package top.kthirty.web;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kthirty.rocketmq.RocketMQProducer;

import java.util.HashMap;
import java.util.Map;
/*
No route info of this topic: seckill_topic 无主题，测试环境可以允许自动创建主题，生产环境自行添加主题
sendDefaultImpl call timeout : docker 启动才会出现的问题，修改为内网即可
 */
@RestController
@RequestMapping("/seckill")
public class SeckillController {
    @Value("${rocketmq.seckill.topic}")
    private String seckillTopic;
    @Autowired
    private RocketMQProducer rocketMQProducer;
    // 同步发送
    @GetMapping("sysn")
    public String skillSysn(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Map<String,Object> param = new HashMap<>();
            param.put("userId",i);
            param.put("goodsId",i);
            try {
                rocketMQProducer.send(seckillTopic, JSON.toJSONString(param));
            } catch (Exception e) {
                e.printStackTrace();
                return "消息发送失败";
            }
        }
        System.out.println("同步发送耗时:"+(System.currentTimeMillis()-start));
        return "success";
    }
    // 异步发送
    @GetMapping("aysn")
    public String skillAysn(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Map<String,Object> param = new HashMap<>();
            param.put("userId",i);
            param.put("goodsId",i);
            try {
                rocketMQProducer.send(seckillTopic, JSON.toJSONString(param), new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println("发送成功");
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        System.out.println("发送失败");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                return "消息发送失败";
            }
        }
        System.out.println("异步发送耗时:"+(System.currentTimeMillis()-start));
        return "success";
    }
}
