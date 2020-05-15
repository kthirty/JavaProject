package top.kthirty.behavior;

import lombok.Data;

/**
 * 状态模式: 简单来说，就是一个对象有不同的状态，根据状态不同，可能有不同的行为。
 * 这样可以达到每个类用来修改每个不同的状态，以及其他操作
 * @author KThirty
 * @since 2020-5-10
 */
public class StatePattern {
    public static void main(String[] args) {
        Context context = new Context();
        context.setId(1L);
        StartState startState = new StartState();
        startState.doAction(context);// 1Start State doAction
        StopState stopState = new StopState();
        stopState.doAction(context);// 1Stop State doAction
    }
}
@Data
class Context {
    private Long id;
    private State state = null;
}
interface State {
    public void doAction(Context context);
}
class StartState implements State{
    @Override
    public void doAction(Context context) {
        System.out.println(context.getId()+"Start State doAction");
    }
}
class StopState implements State{
    @Override
    public void doAction(Context context) {
        System.out.println(context.getId()+"Stop State doAction");
    }
}
