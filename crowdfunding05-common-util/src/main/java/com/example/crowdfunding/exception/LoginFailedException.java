package com.example.crowdfunding.exception;

/**
 * @author wanghu
 * @discrption： 登录失败后抛出的异常
 * @create 2021-09-18 15:41
 */
public class LoginFailedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LoginFailedException() {
        super();
    }

    public LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

}
