package top.kthirty.structural;

/**
 * 门面模式：也叫外观模式
 * 顾名思义，就是讲同类的几个东西放到一个门面里面，方便顾客购买使用
 */
public class FacadeMode {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();
        shapeMaker.drawRedCircle(); // 用红色笔画图，radius:10, x:0, y:0
        shapeMaker.drawRedRectangle(); // 用红色笔画图，radius:0, x:1, y:2
    }
}

/**
 * 方便起见，就直接使用桥接模式中定义好的类了，主要是为了解释门面模式的用法
 */
class ShapeMaker{
    private Shape redCircle;
    private Shape redRectangle;
    public ShapeMaker() {
        redCircle = new Circle(10,new RedPen());
        redRectangle = new Rectangle(1,2,new RedPen());
    }
    // 画红色圆
    public void drawRedCircle(){
        redCircle.draw();
    }
    // 画红色矩形
    public void drawRedRectangle(){
        redRectangle.draw();
    }
}