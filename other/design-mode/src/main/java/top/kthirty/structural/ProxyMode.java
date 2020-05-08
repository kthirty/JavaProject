package top.kthirty.structural;

/**
 * 代理模式
 * 用一个代理来隐藏具体实现类的实现细节，通常还用于在真实的实现的前后添加一部分逻辑。
 * 既然说是代理，那就要对客户端隐藏真实实现，由代理来负责客户端的所有请求。当然，代理只是个代理，它不会完成实际的业务逻辑，而是一层皮而已，但是对于客户端来说，它必须表现得就是客户端需要的真实实现。
 */
public class ProxyMode {
    public static void main(String[] args) {
        SayHelloProxy sayHelloProxy = new SayHelloProxy();
        sayHelloProxy.say();
        // 开始SayHello
        // Hello, I`m SayHelloServiceImpl!
        // 结束SayHello
    }
}
interface ISayHelloService{
    public void say();
}
class SayHelloServiceImpl implements ISayHelloService{
    @Override
    public void say() {
        System.out.println("Hello, I`m SayHelloServiceImpl!");
    }
}
// 代理类，实际逻辑不变，一般在前后加其他操作（日志记录，耗时记录）
class SayHelloProxy implements ISayHelloService{
    private ISayHelloService sayHelloService = new SayHelloServiceImpl();
    @Override
    public void say() {
        System.out.println("开始SayHello");
        sayHelloService.say();
        System.out.println("结束SayHello");
    }
}