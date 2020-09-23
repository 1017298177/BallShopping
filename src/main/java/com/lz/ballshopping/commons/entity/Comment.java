package com.lz.ballshopping.commons.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author makejava
 * @since 2020-08-23 09:54:59
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = 548048997941421677L;
    
    private Integer commentId;
    
    private Integer commentProductId;
    
    private String commentContent;
    
    private String commentProductName;
    
    private String commentUserName;
    
    private Integer commentUserId;
    /**
    * 订单编号
    */
    private String commentOrderNumber;
    /**
    * 几星评论
    */
    private Integer commentGrade;
    /**
    * 评论时间
    */
    private Date commentTime;


    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentProductId() {
        return commentProductId;
    }

    public void setCommentProductId(Integer commentProductId) {
        this.commentProductId = commentProductId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentProductName() {
        return commentProductName;
    }

    public void setCommentProductName(String commentProductName) {
        this.commentProductName = commentProductName;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public Integer getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(Integer commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentOrderNumber() {
        return commentOrderNumber;
    }

    public void setCommentOrderNumber(String commentOrderNumber) {
        this.commentOrderNumber = commentOrderNumber;
    }

    public Integer getCommentGrade() {
        return commentGrade;
    }

    public void setCommentGrade(Integer commentGrade) {
        this.commentGrade = commentGrade;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

}