package top.kthirty.service.impl;

import lombok.AllArgsConstructor;
import org.elasticsearch.action.update.UpdateRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.stereotype.Service;
import top.kthirty.constant.UserIndexConstant;
import top.kthirty.domain.UserCO;
import top.kthirty.service.IUserEsIndexService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserEsIndexServiceImpl implements IUserEsIndexService {
    private ElasticsearchOperations elasticsearchOperations;


    @Override
    public void insert(List<UserCO> userCOs) {
        // index不存在则创建
        if(!elasticsearchOperations.indexExists(UserCO.class)){
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
    public void update(List<UserCO> userCOs) {
        elasticsearchOperations.bulkUpdate(userCOs.stream().map(userCO -> {
            UpdateQuery updateQuery = new UpdateQuery();
            updateQuery.setId(userCO.getId().toString());
            updateQuery.setClazz(UserCO.class);
            updateQuery.setIndexName(UserIndexConstant.INDEX_NAME);
            updateQuery.setType(UserIndexConstant.TYPE);
            updateQuery.setDoUpsert(true);
            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.doc(userCO);
            updateQuery.setUpdateRequest(updateRequest);
            return updateQuery;
        }).collect(Collectors.toList()));
    }

    @Override
    public void deleteAll() {
        elasticsearchOperations.deleteIndex(UserIndexConstant.INDEX_NAME);
    }
}
