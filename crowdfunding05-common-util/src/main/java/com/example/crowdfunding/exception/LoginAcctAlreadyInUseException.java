package com.example.crowdfunding.exception;

/**
 * @author wanghu
 * @discrption： 用户登录账号已使用时抛出的异常
 * @create 2021-09-23 18:17
 */
public class LoginAcctAlreadyInUseException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public LoginAcctAlreadyInUseException() {
    }

    public LoginAcctAlreadyInUseException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUseException(Throwable cause) {
        super(cause);
    }

    public LoginAcctAlreadyInUseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
