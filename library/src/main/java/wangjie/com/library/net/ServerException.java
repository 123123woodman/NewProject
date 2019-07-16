package wangjie.com.library.net;

public class ServerException extends RuntimeException{

    private String displayMessage;
    private int code;

    String getDisplayMessage() {
        return displayMessage;
    }

     void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
