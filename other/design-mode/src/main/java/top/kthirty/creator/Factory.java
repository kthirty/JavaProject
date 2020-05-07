package top.kthirty.creator;

import java.security.InvalidParameterException;

/**
 * 工厂模式
 * @author KThirty
 * @since 2020-5-7
 * 这个其实和简单工厂模式差不太多，就是将工厂继续拆分，单一分工，达到更明细的产品生产
 */
public class Factory {
    public static void main(String[] args) {
        CarFactory carFactory1 = new ChineseFactory();
        CarFactory carFactory2 = new GermanyFactory();
        carFactory1.create("Benz").whoAmI(); // I`m Chinese Benz
        carFactory1.create("BWM").whoAmI(); // I`m Chinese BWM
        carFactory2.create("Benz").whoAmI(); // I`m Germany Benz
        carFactory2.create("BWM").whoAmI(); // I`m Germany BWM
    }
}
// 国产奔驰
class ChineseBenz implements Car{
    @Override
    public void whoAmI() { System.out.println("I`m Chinese Benz"); }
}
// 进口奔驰
class GermanyBenz implements Car{
    @Override public void whoAmI() { System.out.println("I`m Germany Benz"); }
}
// 国产宝马
class ChineseBWM implements Car{
    @Override
    public void whoAmI() { System.out.println("I`m Chinese BWM"); }
}
// 进口宝马
class GermanyBWM implements Car{
    @Override public void whoAmI() { System.out.println("I`m Germany BWM"); }
}
// 工厂基类
interface CarFactory{
    public Car create(String name);
}
// 国内工厂
class ChineseFactory implements CarFactory{
    @Override
    public Car create(String name) {
        if("Benz".equals(name)){
            return new ChineseBenz();
        }
        if("BWM".equals(name)){
            return new ChineseBWM();
        }
        throw new InvalidParameterException("参数异常");
    }
}
// 德国工厂
class GermanyFactory implements CarFactory{
    @Override
    public Car create(String name) {
        if("Benz".equals(name)){
            return new GermanyBenz();
        }
        if("BWM".equals(name)){
            return new GermanyBWM();
        }
        throw new InvalidParameterException("参数异常");
    }
}


