package online.bingzi.usermodule.subModule.userModule.controller;

import lombok.AllArgsConstructor;
import online.bingzi.usermodule.common.entity.ResultModel;
import online.bingzi.usermodule.common.entity.type.ResponseType;
import online.bingzi.usermodule.common.entity.validated.Insert;
import online.bingzi.usermodule.common.util.ResultModelBuilder;
import online.bingzi.usermodule.subModule.userModule.entity.User;
import online.bingzi.usermodule.subModule.userModule.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("register")
    public ResultModel<Object> register(@Validated(value = Insert.class) User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultModelBuilder.build(ResponseType.PARAM_ERROR, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return userService.register(user);
    }
}
