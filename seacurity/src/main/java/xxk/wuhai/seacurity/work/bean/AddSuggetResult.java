package xxk.wuhai.seacurity.work.bean;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class AddSuggetResult {

    /**
     * code : 2004
     * message : 为了方便我们更好的理解您的反馈，描述请大于10个字，谢谢！
     * errorType : BUSI
     * result : null
     */

    private String code;
    private String message;
    private String errorType;
    private String result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

}
