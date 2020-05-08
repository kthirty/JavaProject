package top.kthirty.creator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 原型模式：这个用的很少
 * 作用是：用于创建重复对象。需要实现Cloneable 可以选择重写clone()方法。clone分为浅克隆和深克隆。浅克隆只是克隆引用，对象还是一个。深克隆是对象也新创建了一个
 */
public class Prototype {
    public static void main(String[] args) {
        CloneableUser user5 = CloneableUser.builder().name("王五").password("000").build();
        CloneableUser user6 = user5.clone();
        System.out.println(user5); // CloneableUser(name=王五, password=000)
        user5.setName("王五-1");
        System.out.println(user5); // CloneableUser(name=王五-1, password=000)
        System.out.println(user6); // CloneableUser(name=王五, password=000)
    }
}
@Data
@Builder
@AllArgsConstructor
class CloneableUser {
    private String name;
    private String password;
    @Override
    protected CloneableUser clone() {
        return new CloneableUser(this.name,this.password);
    }
}