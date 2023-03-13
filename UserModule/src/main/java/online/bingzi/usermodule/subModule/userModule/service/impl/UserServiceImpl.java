package online.bingzi.usermodule.subModule.userModule.service.impl;

import lombok.AllArgsConstructor;
import online.bingzi.usermodule.common.entity.ResultModel;
import online.bingzi.usermodule.common.entity.type.ResponseType;
import online.bingzi.usermodule.common.util.ResultModelBuilder;
import online.bingzi.usermodule.subModule.userModule.entity.User;
import online.bingzi.usermodule.subModule.userModule.repository.UserRepository;
import online.bingzi.usermodule.subModule.userModule.service.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResultModel<Object> register(User user) {
        try {
            // 因为数据库侧已经做了用户名，邮箱，手机号唯一性校验，所以这里不需要再做了
            userRepository.save(user);
            return ResultModelBuilder.build(ResponseType.SUCCESS);
        } catch (Exception e) {
            return ResultModelBuilder.build(ResponseType.PARAM_ERROR);
        }
    }
}
