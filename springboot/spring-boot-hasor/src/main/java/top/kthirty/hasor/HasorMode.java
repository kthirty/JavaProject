package top.kthirty.hasor;

import lombok.AllArgsConstructor;
import net.hasor.core.ApiBinder;
import net.hasor.core.AppContext;
import net.hasor.core.DimModule;
import net.hasor.core.Module;
import net.hasor.db.JdbcModule;
import net.hasor.db.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * // 使用 DataQL 拼接字符串
 * var orderBy = ${orderField} + " " + ${orderType};
 *
 * // 声明一个可以注入的 SQL
 * var dataSet = @@sql(apiType,orderString) <%
 *     select * from interface_info where api_type = #{apiType} order by ${orderString} ;
 * %>
 * // 执行这个 SQL，并返回结果
 * return dataSet(${apiType}, orderBy);
 *
 * -- param
 * {
 *   "apiType": "DataQL",
 *   "orderField":"api_type",
 *   "orderType":"desc"
 * }
 */

@DimModule
@Component
public class HasorMode implements Module {
    @Autowired
    private DataSource dataSource;
    @Override
    public void loadModule(ApiBinder apiBinder) throws Throwable {
        apiBinder.installModule(new JdbcModule(Level.Full,dataSource));
    }

    @Override
    public void onStart(AppContext appContext) throws Throwable {
        System.out.println("hasor启动---->"+appContext);
    }

    @Override
    public void onStop(AppContext appContext) throws Throwable {
        System.out.println("hasor停止---->"+appContext);
    }
}
