package top.kthirty.behavior;

import lombok.Data;

/**
 * 策略模式
 * 个人理解：比如说现在有用户需要支付，而支付有多种途径（策略），现在就可以把支付动作提取出来，有多种实现方式
 * 从而达到微信支付类只管微信支付的目的
 */
public class StrategyPattern {
    public static void main(String[] args) {
        Payer payer = new Payer();
        payer.setPay(new WechatPay());
        payer.pay(1L,100D); // 用户1使用微信支付100.0元
    }
}
interface IPay{
    public void pay(Long userId,Double amount);
}
class WechatPay implements IPay{
    @Override
    public void pay(Long userId, Double amount) {
        System.out.println(String.format("用户%s使用微信支付%s元",userId,amount));
    }
}
class AliPay implements IPay{
    @Override
    public void pay(Long userId, Double amount) {
        System.out.println(String.format("用户%s使用支付宝支付%s元",userId,amount));
    }
}
@Data
class Payer{
    private IPay pay;
    public void pay(Long userId, Double amount){
        this.pay.pay(userId, amount);
    }
}
