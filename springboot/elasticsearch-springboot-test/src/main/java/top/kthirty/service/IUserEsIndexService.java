package top.kthirty.service;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import top.kthirty.domain.UserCO;

import java.util.List;

public interface IUserEsIndexService {
    public void insert(List<UserCO> userCOs);
    public void delete(UserCO userCO);
    public void update(UserCO userCO);
    public void deleteAll();
    public <T> List<T> queryForList(SearchQuery query, Class<T> clazz);
    public <T> Page<T> queryForPage(SearchQuery query, Class<T> clazz);
}
