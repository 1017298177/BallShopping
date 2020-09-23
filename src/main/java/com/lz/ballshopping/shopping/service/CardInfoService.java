package com.lz.ballshopping.shopping.service;

import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.shopping.entity.CardInfo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CardInfoService {


    Result<String> updateMoney(CardInfo cardInfo);

    Double getMoneyByUserNameAndCardName(String cardName);

}
