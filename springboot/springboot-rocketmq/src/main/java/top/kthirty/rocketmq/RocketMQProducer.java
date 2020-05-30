package top.kthirty.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 封装RocketMQ常用操作并自动装配
 * <br/>
 * @author KThirty
 * @since 2020/5/30 13:29
 */
@Component
@Slf4j
public class RocketMQProducer implements InitializingBean, DisposableBean {
    @Value("${rocketmq.namesrv}")
    private String namesrvAddr;
    private DefaultMQProducer producer;

    /**
     * 应用停止时自动调用，停止MQ
     * <br/>
     * @author KThirty
     * @since 2020/5/30 13:31
     */
    @Override
    public void destroy() throws Exception {
        if(producer != null){
            log.info("RocketMQ停止");
            producer.shutdown();
        }
    }

    /**
     * 应用启动完成后自动调用,用来初始化并启动生产者
     * <br/>
     * @author KThirty
     * @since 2020/5/30 13:32
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        producer = new DefaultMQProducer("defaultMQProducer");
        producer.setInstanceName("mqInstance_" + 1);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setVipChannelEnabled(false);
        producer.start();
        log.info("RocketMQ生成者启动成功，namesrv地址为[{}]",namesrvAddr);
    }

    /**
     * 发送消息
     * @param topic 主题
     * @param message 发送消息
     */
    public SendResult send(String topic, String message) throws Exception{
        log.info("RocketMQ发送消息,主题{},消息内容{}", topic, message);
        Message msg = new Message(topic, message.getBytes());
        return producer.send(msg);
    }
    /**
     * 发送消息（超时时间）
     * @param topic 主题
     * @param message 消息
     * @param timeout 超时时间
     */
    public SendResult send(String topic, String message,long timeout) throws Exception{
        log.info("RocketMQ发送消息,主题{},消息内容{}", topic, message);
        Message msg = new Message(topic, message.getBytes());
        return producer.send(msg,timeout);
    }
    /**
     * 发送消息（回调）
     * @param topic 主题
     * @param message 消息
     * @param sendCallback 回调信息
     */
    public void send(String topic, String message, SendCallback sendCallback) throws Exception{
        log.info("RocketMQ发送消息,主题{},消息内容{}", topic, message);
        Message msg = new Message(topic, message.getBytes());
        producer.send(msg,sendCallback);
    }

}
