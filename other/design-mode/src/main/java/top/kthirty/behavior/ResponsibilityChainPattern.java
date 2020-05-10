package top.kthirty.behavior;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 责任链模式，多个类，每个有自己的责任，以链表的形式存储每个类并安排顺序
 * 假设现在有一个抢购需求，需要验证商品库存、用户余额是否充足
 */
public class ResponsibilityChainPattern {
    public static void main(String[] args) {
        RuleHandler goodsCountValidRuleHandler = new GoodsCountValidRuleHandler();// 商品库存
        goodsCountValidRuleHandler.setSuccessor(new UserAmountValidRuleHandler()); // 用户余额
        User user = new User(1L, 100D);// 一个余额为100的用户
        Goods goods = new Goods(1L, 110D, 1L); // 一个售价110库存1的商品
        goodsCountValidRuleHandler.apply(user,goods); // 理想：余额验证不通过
    }
}
// 用户
@Data
@AllArgsConstructor
class User{
    private Long id;
    private Double amount;
}
// 商品
@Data
@AllArgsConstructor
class Goods{
    private Long id;
    private Double price;
    private Long count;
}
// 规则父类
@Data
abstract class RuleHandler {
    // 后继节点
    protected RuleHandler successor;
    public abstract void apply(User user,Goods goods);
    //验证完成后发送给后续节点
    public void sendNext(User user,Goods goods){
        if(successor != null){
            successor.apply(user,goods);
        }
    }
}
// 商品库存
class GoodsCountValidRuleHandler extends RuleHandler{
    @Override
    public void apply(User user, Goods goods) {
        if(goods.getCount()<=0){
            throw new RuntimeException("库存不足");
        }
        sendNext(user,goods);
    }
}
// 用户余额
class UserAmountValidRuleHandler extends RuleHandler{
    @Override
    public void apply(User user, Goods goods) {
        if(user.getAmount() < goods.getPrice()){
            throw new RuntimeException("余额不足");
        }
        sendNext(user,goods);
    }
}
