package online.bingzi.usermodule.common.entity.type;

/**
 * 响应类型
 */
public enum ResponseType {
    /**
     * 成功
     */
    SUCCESS(200, "成功"),
    /**
     * 失败
     */
    FAIL(400, "失败"),
    /**
     * 未知错误
     */
    UNKNOWN(500, "未知错误"),
    /**
     * 未登录
     */
    NOT_LOGIN(401, "未登录"),
    /**
     * 未授权
     */
    NOT_AUTH(403, "未授权"),
    /**
     * 未找到
     */
    NOT_FOUND(404, "未找到"),
    /**
     * 参数错误
     */
    PARAM_ERROR(406, "参数错误"),
    /**
     * 重复操作
     */
    REPEAT(409, "重复操作"),
    /**
     * 服务器错误
     */
    SERVER_ERROR(500, "服务器错误"),
    /**
     * 服务降级
     */
    SERVER_DEGRADE(503, "服务降级");
    private final int code;
    private final String msg;

    ResponseType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseType getResponseType(int code) {
        for (ResponseType responseType : ResponseType.values()) {
            if (responseType.getCode() == code) {
                return responseType;
            }
        }
        return UNKNOWN;
    }

    public static ResponseType getResponseType(String msg) {
        for (ResponseType responseType : ResponseType.values()) {
            if (responseType.getMsg().equals(msg)) {
                return responseType;
            }
        }
        return UNKNOWN;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
