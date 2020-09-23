package com.lz.ballshopping.shopping.entity;

import java.io.Serializable;

/**
 * (CardInfo)实体类
 *
 * @author makejava
 * @since 2020-09-17 11:13:37
 */
public class CardInfo implements Serializable {
    private static final long serialVersionUID = -98771892483861845L;
    /**
    * 银行卡号
    */
    private Integer cardId;
    /**
    * 余额
    */
    private Double cardMoney;
    /**
    * 持有者
    */
    private String userName;
    /**
    * 银行名称
    */
    private String cardName;

    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Double getCardMoney() {
        return cardMoney;
    }

    public void setCardMoney(Double cardMoney) {
        this.cardMoney = cardMoney;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

}