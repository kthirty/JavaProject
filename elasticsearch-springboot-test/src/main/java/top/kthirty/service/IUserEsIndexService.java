package top.kthirty.service;

import top.kthirty.domain.UserCO;

import java.util.List;

public interface IUserEsIndexService {
    public void insert(List<UserCO> userCOs);
    public void delete(UserCO userCO);
    public void update(List<UserCO> userCOs);
    public void deleteAll();
}
