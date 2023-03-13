package online.bingzi.usermodule.subModule.userModule.service;

import online.bingzi.usermodule.common.entity.ResultModel;
import online.bingzi.usermodule.subModule.userModule.entity.User;

public interface UserService {
    ResultModel<Object> register(User user);
}
