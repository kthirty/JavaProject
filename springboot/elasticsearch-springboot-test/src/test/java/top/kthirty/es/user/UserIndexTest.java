package top.kthirty.es.user;

import lombok.AllArgsConstructor;
import org.assertj.core.util.Lists;
import org.elasticsearch.common.Randomness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;
import top.kthirty.Application;
import top.kthirty.domain.UserCO;
import top.kthirty.service.IUserEsIndexService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})// 指定启动类
public class UserIndexTest {
    @Autowired
    private IUserEsIndexService userEsIndexService;

    @Test
    public void testInsert() {
        String[] name1 = new String[]{"张", "王", "刘", "李", "白", "郝", "马"};
        String[] name2 = new String[]{"小", "大", "海", "镇", "天", "时", "里"};
        String[] name3 = new String[]{"渡", "嗄", "无", "图", "与", "可", "真"};
        List<UserCO> userCOList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int i1 = Math.abs(new Random().nextInt()) % 7;
            int i2 = Math.abs(new Random().nextInt()) % 7;
            int i3 = Math.abs(new Random().nextInt()) % 7;
            int age = (Math.abs(new Random().nextInt()) % 50) + 20;
            UserCO userCO = new UserCO((long) i, name1[i1] + name2[i2] + name3[i3], age);
            System.out.println(userCO);
            userCOList.add(userCO);
        }
        userEsIndexService.insert(userCOList);
    }

    @Test
    public void testQuery(){
        SearchQuery searchQuery = new NativeSearchQuery(new QueryStringQueryBuilder("1"));
        List<UserCO> userCOS = userEsIndexService.queryForList(searchQuery, UserCO.class);
        userCOS.forEach(System.out::println);
    }

    @Test
    public void testDelete(){
        UserCO userCO = new UserCO();
        userCO.setId(1L);
        userEsIndexService.delete(userCO);
    }
    @Test
    public void testUpdate() throws InterruptedException {
        SearchQuery searchQuery = new NativeSearchQuery(new QueryStringQueryBuilder("2"));
        List<UserCO> userCOS = userEsIndexService.queryForList(searchQuery, UserCO.class);
        userCOS.forEach(System.out::println);

        UserCO userCO = new UserCO(2L,"测试变更"+System.currentTimeMillis(),100);
        userEsIndexService.update(userCO);

        Thread.sleep(500);
        List<UserCO> userCOS2 = userEsIndexService.queryForList(searchQuery, UserCO.class);
        userCOS2.forEach(System.out::println);
    }
}
