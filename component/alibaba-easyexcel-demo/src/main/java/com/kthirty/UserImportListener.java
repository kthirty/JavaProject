package com.kthirty;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.kthirty.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户读取信息监听器（监听读取Excel行为）
 * <br/>
 * @author KThirty
 * @since 2020/5/15 16:41
 */
// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class UserImportListener extends AnalysisEventListener<User> {
    // 这里可以解释为什么不能使用Spring管理，因为spring管理Bean是单例的，里面的对象也就会共享了，这里的importData就不一定是本次导入的数据了
    private List<User> importData = new ArrayList<>();
    /**
     * 这个每一条数据解析都会来调用
     * <br/>
     * @param user 本条读取出来的数据
     * @param context AnalysisContext
     * @return void
     * @author KThirty
     * @since 2020/5/15 16:43
     */
    @Override
    public void invoke(User user, AnalysisContext context) {
        System.out.println("读取到数据"+user);
        importData.add(user);
    }

    /**
     * 所有数据解析完成了
     * <br/>
     * @param context 上下文
     * @return void
     * @author KThirty
     * @since 2020/5/15 16:48
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("本次一共导入了"+importData.size()+"条数据");
    }
}
