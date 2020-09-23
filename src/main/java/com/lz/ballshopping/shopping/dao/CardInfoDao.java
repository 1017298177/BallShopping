package com.lz.ballshopping.shopping.dao;

import com.lz.ballshopping.shopping.entity.CardInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CardInfoDao {

    @Insert("insert into card_info(user_name,card_money,card_name) values(#{userName},#{cardMoney},#{cardName})")
    void insertPaymentWay(CardInfo cardInfo);
    @Update("update card_info set card_money = #{cardMoney} where user_name = #{userName} and card_name = #{cardName}")
    void updateMoney(CardInfo cardInfo);
    @Select("select card_money from card_info where user_name = #{userName} and card_name = #{cardName}")
    Double getMoneyByUserNameAndCardName(CardInfo cardInfo);
    @Select("select * from card_info where user_name = #{userName} and card_name = #{cardName}")
    CardInfo getCardInfoNameAndCardName(CardInfo cardInfo);
}
