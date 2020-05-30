package top.kthirty.rocketmq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/** 
 * MQConsumer 消费者封装，方便使用
 * <br/>
 * @author KThirty
 * @since 2020/5/30 14:09
 */
@Slf4j
@AllArgsConstructor
public class RocketMQConsumer implements InitializingBean, DisposableBean {
    private MessageListenerConcurrently messageListener;
    private String namesrvAddr;
    private String topic;
    private String groupName;
    private DefaultMQPushConsumer consumer;
    private MessageModel messageModel;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(namesrvAddr,"namesrvAddr地址不能为空");
        Assert.hasText(topic,"topic不能为空");
        Assert.notNull(messageListener,"messageListener不能为null");
        try {
            consumer = new DefaultMQPushConsumer(groupName);
            consumer.setInstanceName("mqConsumerInstance_" + 1);
            consumer.setNamesrvAddr(namesrvAddr);
            consumer.subscribe(topic, "*");
            consumer.setMessageModel(messageModel);//消费模式
            consumer.registerMessageListener(messageListener);
            consumer.setVipChannelEnabled(false);
            consumer.start();
            log.info("RocketMQ消费者启动成功");
        } catch (Exception e) {
            log.error("RocketMQ消费者启动异常",e);
        }
    }

    @Override
    public void destroy() throws Exception {
        if(this.consumer != null){
            this.consumer.shutdown();
        }
    }
}
