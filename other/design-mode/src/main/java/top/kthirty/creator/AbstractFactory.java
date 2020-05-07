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

}
@Data
@AllArgsConstructor
@ToString
class BenzTire{
    String name;
}
@Data
@AllArgsConstructor
@ToString
class BenzFrame{

}