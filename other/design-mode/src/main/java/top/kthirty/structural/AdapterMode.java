package top.kthirty.structural;

import lombok.AllArgsConstructor;

/**
 * 适配器模式
 * 分为：默认适配器模式、对象适配器模式、类适配器模式三类
 */
public class AdapterMode {
    public static void main(String[] args) {
        // ---------- 默认适配器
        // I`m Adapter Func1
        // I`m Func1
        new DefaultAdapter().func1();
        // ----------- 默认适配器结束
    }
}
// ---------- 默认适配器开始-------------------
/**
 * 默认适配器模式，一般用于顶级接口有很多接口，但是我们现在并不需要实现所有接口，可以加一个适配器，默认实现接口但是什么都不做
 */
class DefaultAdapter extends TopInterfaceAdapter{
    @Override
    public void func1() {
        super.func1();
        System.out.println("I`m Func1");
    }
}
interface TopInterface{
    void func1();
    void func2();
    void func3();
}
// 适配器
class TopInterfaceAdapter implements TopInterface{
    @Override
    public void func1() {
        System.out.println("I`m Adapter Func1");
    }
    @Override
    public void func2() { }
    @Override
    public void func3() { }
}
// ---------- 默认适配器结束-------------------
// ----------- 对象适配器开始-----------

/**
 * 对象适配器，一般用来将一个对象转换为其他类型对象，下面将用一个鸡和一个鸭来演示如何用适配器将鸡适配成鸭
 */
class ObjectAdapter{
    public static void main(String[] args) {
        Cock cock = new WildCock();
        // 创建适配器，将野鸡转换为鸭类
        Duck duck = new CockAdapter(cock);
        duck.fly(); // 鸡也会飞哦
        duck.quack(); // 咕咕叫
    }
}
interface Duck {
    public void quack(); // 鸭的呱呱叫
    public void fly(); // 飞
}
interface Cock {
    public void gobble(); // 鸡的咕咕叫
    public void fly(); // 飞
}
// 野鸡
class WildCock implements Cock {
    public void gobble() {
        System.out.println("咕咕叫");
    }
    public void fly() {
        System.out.println("鸡也会飞哦");
    }
}
/*
现在假设有一只野鸡，怎么样才能将它适配成鸭类呢，fly方法已经有了，但是没有鸭的quack方法，所以需要实现这个方法
 */
@AllArgsConstructor
class CockAdapter implements Duck{
    // 鸡类
    Cock cock;
    @Override
    public void quack() {
        // 实际是鸡叫
        cock.gobble();
    }

    @Override
    public void fly() {
        cock.fly();
    }
}
// ----------- 对象适配器结束-----------
// ----------- 类适配器开始-----------

/**
 * 类适配器其实就是通过继承的形式，将顶级接口的某些方法默认实现
 */
interface Target{
    void func1();
    void func2();
    void func3();
}
class SomeThing{
    public void func1(){
        System.out.println("I`m SomeThing Func1");
    }
    public void func2(){
        System.out.println("I`m SomeThing Func2");
    }
}
class TargetAdapter extends SomeThing implements Target{
    @Override
    public void func3() {
        System.out.println("I`m TargetAdapter func3");
    }
}
class ClassAdapter{
    public static void main(String[] args) {
        Target target = new TargetAdapter();
        target.func1();// I`m SomeThing Func1
        target.func2();// I`m SomeThing Func2
        target.func3();// I`m TargetAdapter func3
    }
}
// ---------------类适配器结束---------------