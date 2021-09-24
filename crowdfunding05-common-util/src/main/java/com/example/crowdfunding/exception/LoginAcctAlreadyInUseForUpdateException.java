package com.example.crowdfunding.exception;

/**
 * @author wanghu
 * @discrption： 更新用户账号时已使用时抛出的异常
 * @create 2021-09-24 15:08
 */
public class LoginAcctAlreadyInUseForUpdateException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public LoginAcctAlreadyInUseForUpdateException() {
    }

    public LoginAcctAlreadyInUseForUpdateException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUseForUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUseForUpdateException(Throwable cause) {
        super(cause);
    }

    public LoginAcctAlreadyInUseForUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
