package com.lz.ballshopping.commons.vo;

public class Result<T> {

    private String message;
    private int status;
    private T data;

    public Result() {
    }

    public Result(String message, int status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public Result(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public enum  ResultStatus{
        SUCCESS(200),FIALD(500);
        public int status;
        ResultStatus(int status){
            this.status = status;
        }
    }

}
