package top.kthirty.structural;

import lombok.AllArgsConstructor;

/**
 * 装饰模式：给某个类添加装饰（其实就是添加某种功能）
 * 类似代理模式，但是代理模式用来做与原业务无关的事，不会修改原业务，装饰模式则是用来修改原业务的
 * 比如我们现在有个制造奶茶的类，如果需要记录制造耗时，则使用代理模式
 * 如果需要在奶茶添加佐料或者添加其他附加品则使用装饰模式
 * 装饰模式一般用在功能迭代上
 */
public class DecorativeMode {
    public static void main(String[] args) {
        // 红茶饮料
        Beverage beverage = new RedTea();
        // 红茶加柠檬
        beverage = new Lemon(beverage);
        System.out.println(beverage.getDescription());// 红茶加柠檬
        System.out.println(beverage.cost());// 12.0
    }
}
// 抽象饮料类
abstract class Beverage{
    // 返回描述
    public abstract String getDescription();
    // 返回价格
    public abstract double cost();
}
// 红茶
class RedTea extends Beverage{
    @Override
    public String getDescription() {
        return "红茶";
    }
    @Override
    public double cost() {
        return 10;
    }
}

// 定义调料，也就是装饰者的基类，此类必须继承自 饮料类：
abstract class Condiment extends Beverage { }
// 柠檬调料
@AllArgsConstructor
class Lemon extends Condiment{
    private Beverage beverage;
    @Override
    public String getDescription() {
        return beverage.getDescription()+"加柠檬";
    }
    @Override
    public double cost() {
        // 加柠檬另加两元
        return beverage.cost()+2;
    }
}
