package top.kthirty.structural;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式：享元模式尽可能的让用户复用已经有的对象，从而避免造成反复创建对象的资源浪费。
 * 比如Java字符串常量池，核心其实就是减少资源浪费
 */
public class FlyweightPattern {
    //定义一个池子
    private static Map<String,Flyweight> pool= new HashMap<String,Flyweight>();
    public static Flyweight getFlyweight(String subject){
        Flyweight flyweight;
        // 不存在则添加，存在就复用
        if (pool.containsKey(subject)){
            flyweight=pool.get(subject);
        }else {
            flyweight = new RealFlyweight(subject);
            pool.put(subject,flyweight);
        }
        return flyweight;
    }
    public static void main(String[] args) {
        System.out.println(pool.size());//0
        getFlyweight("math");
        System.out.println(pool.size());//1
        getFlyweight("english");
        System.out.println(pool.size());//2
        getFlyweight("math");
        System.out.println(pool.size());//2
    }
}
abstract class Flyweight{
    //内部状态
    private String name;
    private String age;
    //外部状态
    private final String subject;
    protected Flyweight(String subject) {
        this.subject = subject;
    }
    //行为
    public abstract void exam();
    public String getSubject() {
        return subject;
    }
}
class RealFlyweight extends Flyweight {
    @Override
    public void exam() {
        System.out.println(this.getSubject()+" is examing...");
    }
    public RealFlyweight(String subject){
        super(subject);
    }
}