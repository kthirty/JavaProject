package top.kthirty.behavior;

/**
 * 模板方法模式: 由父类定义一个模板（自定方法第一步做什么第二步做什么，具体实现由子类实现）
 * @author KThirty
 * @since 2020-5-10
 */
public class TemplateMethodPattern {
    public static void main(String[] args) {
        AbstractTemplate t = new ConcreteTemplate();
        t.templateMethod();
    }
}
abstract class AbstractTemplate{
    // 这就是模板方法
    public void templateMethod(){
        init();
        apply(); // 这个是重点
        end(); // 可以作为钩子方法
    }
    protected void init() {
        System.out.println("init 抽象层已经实现，子类也可以选择覆写");
    }
    // 留给子类实现
    protected abstract void apply();
    protected void end() {}
}
class ConcreteTemplate extends AbstractTemplate {
    public void apply() {
        System.out.println("子类实现抽象方法 apply");
    }
    public void end() {
        System.out.println("我们可以把 method3 当做钩子方法来使用，需要的时候覆写就可以了");
    }
}

