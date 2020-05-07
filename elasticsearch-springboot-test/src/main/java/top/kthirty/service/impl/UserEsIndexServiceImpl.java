package top.kthirty.service.impl;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.elasticsearch.action.update.UpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.stereotype.Service;
import top.kthirty.constant.UserIndexConstant;
import top.kthirty.domain.UserCO;
import top.kthirty.service.IUserEsIndexService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserEsIndexServiceImpl implements IUserEsIndexService {
    private ElasticsearchOperations elasticsearchOperations;


    @Override
    public void insert(List<UserCO> userCOs) {
        // index不存在则创建
        if (!elasticsearchOperations.indexExists(UserCO.class)) {
            elasticsearchOperations.createIndex(UserCO.class);
        }

        List<IndexQuery> collect = userCOs.stream().map(userCO -> {
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setId(userCO.getId().toString());
            indexQuery.setObject(userCO);
            return indexQuery;
        }).collect(Collectors.toList());
        elasticsearchOperations.bulkIndex(collect);
    }

    @Override
    public void delete(UserCO userCO) {
        elasticsearchOperations.delete(UserIndexConstant.INDEX_NAME, UserIndexConstant.TYPE, userCO.getId().toString());
    }

    @Override
    public void update(UserCO userCO) {
        this.delete(userCO);
        List<UserCO> list = new ArrayList<UserCO>();
        list.add(userCO);
        this.insert(list);
    }

    @Override
    public void deleteAll() {
        elasticsearchOperations.deleteIndex(UserIndexConstant.INDEX_NAME);
    }

    @Override
    public <T> List<T> queryForList(SearchQuery query, Class<T> clazz) {
        return this.elasticsearchOperations.queryForList(query,clazz);
    }

    @Override
    public <T> Page<T> queryForPage(SearchQuery query, Class<T> clazz) {
        return this.elasticsearchOperations.queryForPage(query,clazz);
    }
}
