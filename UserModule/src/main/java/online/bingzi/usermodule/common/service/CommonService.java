package online.bingzi.usermodule.common.service;

import online.bingzi.usermodule.common.entity.Dictionary;
import online.bingzi.usermodule.common.entity.RequestContainer;
import online.bingzi.usermodule.common.entity.ResultModel;

import java.util.ArrayList;
import java.util.Map;

public interface CommonService {
    ResultModel<Map<String, Dictionary>> getDropBox(RequestContainer<ArrayList<String>> container);

    ResultModel<Object> insertDropBox(Dictionary dictionary);

    ResultModel<Object> updateDropBox(Dictionary dictionary);

    ResultModel<Object> deleteDropBox(Dictionary dictionary);
}
