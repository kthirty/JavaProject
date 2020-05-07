package top.kthirty.creator;

import java.lang.reflect.Parameter;
import java.security.InvalidParameterException;

/**
 * 简单工厂模式
 * @author KThirty
 * @since 2020-5-7
 * 简单地说，简单工厂模式通常就是这样，一个工厂类 XxxFactory，里面有一个静态方法，根据我们不同的参数，返回不同的派生自同一个父类（或实现同一接口）的实例对象。
 */
// 汽车父类
interface Car{public void whoAmI();}
// 宝马

class BMWCar implements Car {
    @Override
    public void whoAmI() { System.out.println("I`m BWM");}
}
// 奔驰
class BenzCar implements Car{

    @Override
    public void whoAmI() { System.out.println("I`m Benz"); }
}

public class SimpleFactory {
    public static Car create(String name){
        if("Benz".equals(name)){
            return new BenzCar();
        }
        if("BWM".equals(name)){
            return new BMWCar();
        }
        throw new InvalidParameterException("参数异常");
    }

    public static void main(String[] args) {
        SimpleFactory.create("Benz").whoAmI();
        SimpleFactory.create("BWM").whoAmI();
    }
}
