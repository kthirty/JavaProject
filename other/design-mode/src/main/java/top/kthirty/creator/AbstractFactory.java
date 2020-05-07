package top.kthirty.creator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 抽象工厂模式
 * @author KThirty
 * @since 2020-5-7
 * 抽象工厂模式主要设计产品组的概念，就是某一个工厂生产出配套的一系列产品。
 * 比如，一个奔驰的工厂，可以生产轮胎，车架，发动机....等零件，再由三方进行拼装
 */
public class AbstractFactory {
    public static void main(String[] args) {
        // 选择一个工厂
        AssemblyCarFactory assemblyCarFactory = new BenzAssemblyCarFactory();
        Engine engine = assemblyCarFactory.createEngine();
        Frame frame = assemblyCarFactory.createFrame();
        Tire tire = assemblyCarFactory.createTire();
        // 组装成车 ， 全奔驰配件的组装车
        AssemblyCar benzAssemblyCar = new AssemblyCar(engine, frame, tire);
        System.out.println(benzAssemblyCar);// AssemblyCar(engine=Engine(name=Benz Engine), frame=Frame(name=Benz Frame), tire=Tire(name=Benz Tire))

    }
}
// 奔驰组装车工厂
class BenzAssemblyCarFactory implements AssemblyCarFactory{
    public Tire createTire(){
        return new BenzTire();
    }
    public Frame createFrame(){
        return new BenzFrame();
    }
    public Engine createEngine(){
        return new BenzEngine();
    }
}

// 组装车工程父类（定义配件）
interface AssemblyCarFactory{
    public Tire createTire();
    public Frame createFrame();
    public Engine createEngine();
}

// 轮胎
@Data
@AllArgsConstructor
class Tire{
    private String name;
}
class BenzTire extends Tire{
    public BenzTire() { super("Benz Tire"); }
}

// 车架
@Data
@AllArgsConstructor
class Frame{
    private String name;
}
class BenzFrame extends Frame{
    public BenzFrame() { super("Benz Frame"); }
}
// 引擎
@Data
@ToString
@AllArgsConstructor
class Engine{
    private String name;
}
class BenzEngine extends Engine{ public BenzEngine() { super("Benz Engine"); } }
@Data
@AllArgsConstructor
@ToString
class AssemblyCar {
    private Engine engine;
    private Frame frame;
    private Tire tire;
}
