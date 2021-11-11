package com.example.crowdfunding.util;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-16 16:18
 */
public class ResultEntity<T> {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    public static final String NO_MESSAGE = "NO_MESSAGE";
    public static final String NO_DATA = "NO_DATA";

    /**
     * 返回操作结果为成功，不带数据
     * @return
     */
    public static <E> ResultEntity<E> successWithoutData() {
        return new ResultEntity<E>(SUCCESS, NO_MESSAGE, null);
    }

    /**
     * 返回操作结果为成功，携带数据
     * @param data
     * @return
     */
    public static <E> ResultEntity<E> successWithData(E data) {
        return new ResultEntity<E>(SUCCESS, NO_MESSAGE, data);
    }

    /**
     * 返回操作结果为失败，不带数据
     * @param message
     * @return
     */
    public static <E> ResultEntity<E> failed(String message) {
        return new ResultEntity<E>(FAILED, message, null);
    }

    private String result;
    private String message;
    private T data;

    public ResultEntity() {
    }

    public ResultEntity(String result, String message, T data) {
        super();
        this.result = result;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "AjaxResultEntity [result=" + result + ", message=" + message + ", data=" + data + "]";
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
