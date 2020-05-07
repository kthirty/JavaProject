package top.kthirty.creator;

/**
 * 单例模式
 * @author KThirty
 * @since 2020-5-7
 * 单例模式又分 饿汉模式、饱汉模式、嵌套模式
 */
public class Singleton {
    public static void main(String[] args) {
        Singleton1.getInstance().say();// 输出：饿汉模式
        Singleton2.getInstance().say();// 输出： 饱汉模式（线程安全）
        Singleton3.getInstance().say();// 输出：嵌套类模式
    }
}

/**
 * 饿汉模式：饿汉模式，很饿很着急，所以类加载时即创建实例对象，但是加载比较慢，获取对象比较快。
 */
class Singleton1{
    // 构造方法私有化，禁止外部调用
    private Singleton1() {};
    // 创建私有静态实例，意味着这个类第一次使用的时候就会进行创建
    private static Singleton1 instance = new Singleton1();
    public static Singleton1 getInstance() {
        return instance;
    }
    public void say(){ System.out.println("饿汉模式"); }
    /**
     * 本方法无意义，仅为了演示一种情况
     * 随便写一个静态方法，假如现在只想要调用静态方法（Singleton1.getData），并不需要生成Singleton1实例，但是没办法，已经生成了
     * 这样就造成了资源的浪费
     */
    public static String getData(String mode) {return mode;}
}

/**
 * 饱汉模式(线程安全)： 饱汉模式，很饱不着急，延迟加载，啥时候用啥时候创建实例，存在线程安全问题
 */
class Singleton2{
    // 构造方法私有化，禁止外部调用
    private Singleton2() {};
    // 和饿汉模式相比，这边不需要先实例化出来，注意这里的 volatile，它是必须的
    private static volatile Singleton2 instance = null;
    public static Singleton2 getInstance() {
        if (instance == null) {
            // 加锁
            synchronized (Singleton2.class) {
                // 这一次判断也是必须的，不然会有并发问题
                if (instance == null) {
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
    public void say(){
        System.out.println("饱汉模式（线程安全）");
    }
}

/**
 * 嵌套类模式： 利用嵌套类可以访问外部类的静态属性和静态方法
 */
class Singleton3 {
    // 构造方法私有化，禁止外部调用
    private Singleton3() {}
    // 主要是使用了 嵌套类可以访问外部类的静态属性和静态方法 的特性
    private static class Holder {
        private static Singleton3 instance = new Singleton3();
    }
    public static Singleton3 getInstance() {
        return Holder.instance;
    }
    public void say(){
        System.out.println("嵌套类模式");
    }
}