package online.bingzi.usermodule.common.controller;

import lombok.AllArgsConstructor;
import online.bingzi.usermodule.common.entity.Dictionary;
import online.bingzi.usermodule.common.entity.RequestContainer;
import online.bingzi.usermodule.common.entity.ResultModel;
import online.bingzi.usermodule.common.entity.type.ResponseType;
import online.bingzi.usermodule.common.entity.validated.Insert;
import online.bingzi.usermodule.common.entity.validated.Select;
import online.bingzi.usermodule.common.service.CommonService;
import online.bingzi.usermodule.common.util.ResultModelBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class CommonController {
    private final CommonService commonService;

    @PostMapping("getDropBox")
    public ResultModel<Map<String, Dictionary>> getDropBox(@Validated(Select.class) RequestContainer<ArrayList<String>> container, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultModelBuilder.build(ResponseType.PARAM_ERROR, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return commonService.getDropBox(container);
    }

    @PutMapping("insertDropBox")
    public ResultModel<Object> insertDropBox(@Validated(Insert.class) Dictionary dictionary, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultModelBuilder.build(ResponseType.PARAM_ERROR, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return commonService.insertDropBox(dictionary);
    }


}
