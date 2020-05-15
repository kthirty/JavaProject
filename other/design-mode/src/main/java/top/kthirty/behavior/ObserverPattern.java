package top.kthirty.behavior;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式：多个观察者观察到某对象的变化后进行相应操作
 * 下面展示一个例子，比如需要订单状态变更后给开发者发送短信以及邮件通知
 */
public class ObserverPattern {
    public static void main(String[] args) {
        Order order = new Order();
        order.setId(1L);
        order.setStatus("1");
        order.attach(new OrderStatusChangeSendMailObserver()); // 发送邮件：订单1状态变更，变更前状态1,变更后状态2
        order.attach(new OrderStatusChangeSendMsgObserver()); // 发送短信：订单1状态变更，变更前状态1,变更后状态2
        order.setStatus("2");
    }
}

abstract class OrderStatusChangeObserver {
    protected Order order;
    // 变化事件(变化订单，前更前状态，变更后状态)
    public abstract void change(Order order,String beforeStatus,String afterStatus);
}
@ToString
class Order{
    // 多个观察者
    private List<OrderStatusChangeObserver> orderStatusChangeObservers = new ArrayList<>();
    private String status; // 当前状态
    private Long id; // id
    // 添加观察者
    public void attach(OrderStatusChangeObserver orderStatusChangeObserver){
        orderStatusChangeObservers.add(orderStatusChangeObserver);
    }
    public String getStatus() {
        return status;
    }
    public Long getId() {
        return id;
    }
    public void setStatus(String status){
        String beforeStatus = this.status;
        this.status = status;
        orderStatusChangeObservers.forEach(o -> o.change(this,beforeStatus,status));
    }
    public void setId(Long id){
        this.id = id;
    }
}
class OrderStatusChangeSendMsgObserver extends OrderStatusChangeObserver{
    @Override
    public void change(Order order, String beforeStatus, String afterStatus) {
        System.out.println(String.format("发送短信：订单%s状态变更，变更前状态%s,变更后状态%s",order.getId(),beforeStatus,afterStatus));
    }
}
class OrderStatusChangeSendMailObserver extends OrderStatusChangeObserver{
    @Override
    public void change(Order order, String beforeStatus, String afterStatus) {
        System.out.println(String.format("发送邮件：订单%s状态变更，变更前状态%s,变更后状态%s",order.getId(),beforeStatus,afterStatus));
    }
}