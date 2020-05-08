package top.kthirty.creator;

import lombok.Data;
import lombok.ToString;

/**
 * 建造者模式Builder
 * 套路就是先 new 一个 Builder，然后可以链式地调用一堆方法，最后再调用一次 build() 方法，我们需要的对象就有了。
 * 其实就是有一个专门的builder用来构建对象
 */
public class Builder {
    public static void main(String[] args) {
        User user = User.builder().name("张三").password("123456").build();
        System.out.println(user); // User(name=张三, password=123456)
        // lombok自动生成版
        User1 user1 = User1.builder().name("李四").password("123").build();
        System.out.println(user1);// User1(name=李四, password=123)
    }
}
@Data
@ToString
class User{
    private String name;
    private String password;
    private User(String name,String password){
        this.name = name;
        this.password = password;
    }
    static class UserBuilder {
        private String name;
        private String password;
        public UserBuilder name(String name){
            this.name = name;
            // 链式调用
            return this;
        }
        public UserBuilder password(String password){
            this.password = password;
            return this;
        }
        public User build(){
            if (name == null || password == null) {
                throw new RuntimeException("用户名和密码必填");
            }
            return new User(name, password);
        }
    }
    public static UserBuilder builder() {
        return new UserBuilder();
    }

}

/**
 * Lombok自动生成builder
 */
@Data
@ToString
@lombok.Builder
class User1 {
    private String name;
    private String password;

    private User1(String name, String password) {
        this.name = name;
        this.password = password;
    }
}