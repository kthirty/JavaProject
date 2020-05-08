package top.kthirty.structural;

/**
 * @author KThirty
 * @since 2020-5-8
 * 桥接模式： 桥接模式的目的是为了将抽象部分与实现部分解耦，可以将一个 N * N 的系统进行拆分，减少类的数量。
 * 比如我们现在要画红色矩形、蓝色矩形、红色圆形、蓝色圆形 ，这样形状和颜色就形成了一个N*N 的系统
 * 普通形式的话，我们需要一共四个类实现各种画法,且不利于扩展
 * 桥接模式的优点是： 优秀的扩展能力
 * 缺点很明显：会增加系统的理解与设计难度
 */
public class BridgeMode {
    public static void main(String[] args) {
        // 蓝色圆形 ， new 一个圆形传入蓝色笔，就可以画出来蓝色圆形
        Shape blueCircle = new Circle(10, new BluePen());
        blueCircle.draw();// 用蓝色笔画图，radius:10, x:0, y:0
        Shape redCircle = new Circle(10, new RedPen());
        redCircle.draw(); // 用红色笔画图，radius:10, x:0, y:0
        Shape blueRectangle = new Rectangle(1,2, new BluePen());
        blueRectangle.draw(); // 用蓝色笔画图，radius:0, x:1, y:2
        Shape redRectangle = new Rectangle(1,2, new RedPen());
        redRectangle.draw(); // 用红色笔画图，radius:0, x:1, y:2
    }
}

// 我们首先需要一个桥梁，它是一个接口，定义提供的接口方法。也就是画笔的父类
interface DrawAPI {
    public void draw(int radius, int x, int y);
}

// 红色笔
class RedPen implements DrawAPI {
    @Override
    public void draw(int radius, int x, int y) {
        System.out.println("用红色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
    }
}

// 蓝色笔
class BluePen implements DrawAPI {
    @Override
    public void draw(int radius, int x, int y) {
        System.out.println("用蓝色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
    }
}

// 后续如果需要增加颜色直接添加实现类即可
// 定义一个抽象类用来画的动作，可以传入不同颜色的画笔
abstract class Shape {
    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}

// 圆形
class Circle extends Shape {
    private int radius;

    public Circle(int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.radius = radius;
    }

    public void draw() {
        drawAPI.draw(radius, 0, 0);
    }
}

// 长方形
class Rectangle extends Shape {
    private int x;
    private int y;

    public Rectangle(int x, int y, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
    }

    public void draw() {
        drawAPI.draw(0, x, y);
    }
}
