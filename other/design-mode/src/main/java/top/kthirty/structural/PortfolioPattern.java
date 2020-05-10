package top.kthirty.structural;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式：组合模式是将存在某种包含关系的数据组织在一起，典型的例子就是树状结构。例如菜单功能，一个菜单除了自己该有的属性，还可能包含子菜单，创建的时候可以使用递归的方法。
 */
public class PortfolioPattern{
    public static void main(String[] args) {
        Menu menu = new Menu("测试一", new ArrayList<>()).addChild(new Menu("测试二", new ArrayList<>()));
        System.out.println(menu);
    }
}
@Data
@ToString
@AllArgsConstructor
class Menu{
    private String name;
    private List<Menu> childMenus;
    public Menu addChild(Menu menu){
        childMenus.add(menu);
        return this;
    }
}
