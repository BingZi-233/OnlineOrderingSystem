package online.bingzi.usermodule.common.util;

import online.bingzi.usermodule.common.entity.ResultModel;
import online.bingzi.usermodule.common.entity.type.ResponseType;

public class ResultModelBuilder {
    public static <T> ResultModel<T> build(ResponseType responseType, String msg, T data) {
        return new ResultModel<T>().setCode(responseType.getCode()).setMsg(msg).setData(data);
    }

    public static <T> ResultModel<T> build(ResponseType responseType, T data) {
        return new ResultModel<T>().setCode(responseType.getCode()).setMsg(responseType.getMsg()).setData(data);
    }

    public static <T> ResultModel<T> build(ResponseType responseType, String msg) {
        return new ResultModel<T>().setCode(responseType.getCode()).setMsg(msg);
    }

    public static <T> ResultModel<T> build(ResponseType responseType) {
        return new ResultModel<T>().setCode(responseType.getCode()).setMsg(responseType.getMsg());
    }
}
