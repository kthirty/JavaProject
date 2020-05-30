package top.kthirty.seckill;

import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.kthirty.rocketmq.RocketMQConsumer;

/**
 * 秒杀消息配置
 * <br/>
 * @author KThirty
 * @since 2020/5/30 14:12
 */
@Configuration
public class SeckillMQConfig {
    @Value("${rocketmq.namesrv}")
    private String namesrv;
    @Value("${rocketmq.seckill.topic}")
    private String seckillTopic;
    @Value("${rocketmq.seckill.groupname}")
    private String seckillGroupName;

    /**
     * 注入秒杀mq message listener
     * <br/>
     * @return org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently
     * @author KThirty
     * @since 2020/5/30 14:42
     */
    @Bean
    public MessageListenerConcurrently getSeckillMessageListener(){
        return new SeckillMessageListener();
    }
    /**
     *创建并注入消费者
     * 使用集群方式消费（因为集群时，如果多次消费反而无效，只消费一次即可）
     * 这里备注一下消费方式
     * 1. 集群消费：当使用集群消费模式时，MQ 认为任意一条消息只需要被集群内的任意一个消费者处理即可。
     * 2. 广播消费：当使用广播消费模式时，MQ 会将每条消息推送给集群内所有注册过的客户端，保证消息至少被每台机器消费一次。
     * <br/>
     * @return top.kthirty.rocketmq.RocketMQConsumer
     * @author KThirty
     * @since 2020/5/30 14:45
     */
    @Bean
    public RocketMQConsumer getSeckillMQConsumer(){
        return new RocketMQConsumer(getSeckillMessageListener(),namesrv,seckillTopic,seckillGroupName,null, MessageModel.CLUSTERING);
    }
}
