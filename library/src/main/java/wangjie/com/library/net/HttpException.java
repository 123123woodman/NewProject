package wangjie.com.library.net;

public class HttpException extends Exception{

    public String displayMessage;//显示信息
    public  int code; //错误码

    public HttpException(Throwable throwable, int code) {
        super(throwable);
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
