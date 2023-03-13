package online.bingzi.usermodule.subModule.userModule.service.impl;

import lombok.AllArgsConstructor;
import online.bingzi.usermodule.common.entity.ResultModel;
import online.bingzi.usermodule.common.entity.type.ResponseType;
import online.bingzi.usermodule.common.util.ResultModelBuilder;
import online.bingzi.usermodule.subModule.userModule.entity.User;
import online.bingzi.usermodule.subModule.userModule.repository.UserRepository;
import online.bingzi.usermodule.subModule.userModule.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResultModel<Object> register(User user) {
        userRepository.save(user);
        return ResultModelBuilder.build(ResponseType.SUCCESS);
    }
}
