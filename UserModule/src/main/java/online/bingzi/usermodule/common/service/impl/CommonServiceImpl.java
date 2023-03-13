package online.bingzi.usermodule.common.service.impl;

import lombok.AllArgsConstructor;
import online.bingzi.usermodule.common.entity.Dictionary;
import online.bingzi.usermodule.common.entity.RequestContainer;
import online.bingzi.usermodule.common.entity.ResultModel;
import online.bingzi.usermodule.common.entity.type.ResponseType;
import online.bingzi.usermodule.common.mapper.CommonMapper;
import online.bingzi.usermodule.common.repository.DictionaryRepository;
import online.bingzi.usermodule.common.service.CommonService;
import online.bingzi.usermodule.common.util.ResultModelBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommonServiceImpl implements CommonService {
    // JAP自动操作
    private final DictionaryRepository dictionaryRepository;
    // MyBatis手动操作
    private final CommonMapper commonMapper;

    @Override
    public ResultModel<Map<String, Dictionary>> getDropBox(RequestContainer<ArrayList<String>> container) {
        List<Dictionary> dictionaryList = dictionaryRepository.findByNameInIgnoreCase(container.getData());
        Map<String, Dictionary> collect = container.getData().stream().collect(Collectors.toMap(s -> s, s -> dictionaryList.stream().filter(dictionary -> dictionary.getName().equals(s)).findFirst().orElseGet(null)));
        return ResultModelBuilder.build(ResponseType.SUCCESS, collect);
    }

    @Override
    @Transactional
    public ResultModel<Object> insertDropBox(Dictionary dictionary) {
        if (Boolean.FALSE.equals(dictionary.getParent())) {
            Optional<Dictionary> optional = dictionaryRepository.findById(dictionary.getParentId().getId());
            if (optional.isPresent()) {
                optional.ifPresent(it -> {
                    it.getChildren().add(dictionary);
                    dictionaryRepository.save(it);
                });
            } else {
                return ResultModelBuilder.build(ResponseType.PARAM_ERROR, "父级不存在");
            }
        } else {
            dictionaryRepository.save(dictionary);
        }
        return ResultModelBuilder.build(ResponseType.SUCCESS);
    }

    @Override
    @Transactional
    public ResultModel<Object> updateDropBox(Dictionary dictionary) {
        Optional<Dictionary> optional = dictionaryRepository.findById(dictionary.getId());
        if (optional.isPresent()) {
            optional.ifPresent(it -> {
                it.setName(dictionary.getName());
                dictionaryRepository.save(it);
            });
        } else {
            return ResultModelBuilder.build(ResponseType.PARAM_ERROR, "字典不存在");
        }
        return ResultModelBuilder.build(ResponseType.SUCCESS);
    }

    @Override
    public ResultModel<Object> deleteDropBox(Dictionary dictionary) {
        Optional<Dictionary> optional = dictionaryRepository.findById(dictionary.getId());
        if (optional.isPresent()) {
            optional.ifPresent(it -> {
                // 先删除子级
                dictionaryRepository.deleteAllById(it.getChildren().stream().map(Dictionary::getId).collect(Collectors.toList()));
                // 删除自己
                dictionaryRepository.delete(it);
            });
        } else {
            return ResultModelBuilder.build(ResponseType.PARAM_ERROR, "字典不存在");
        }
        return ResultModelBuilder.build(ResponseType.SUCCESS);
    }
}
