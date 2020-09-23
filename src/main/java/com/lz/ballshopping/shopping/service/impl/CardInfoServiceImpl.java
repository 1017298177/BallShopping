package com.lz.ballshopping.shopping.service.impl;

import com.lz.ballshopping.commons.entity.UserInfo;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.shopping.dao.CardInfoDao;
import com.lz.ballshopping.shopping.entity.CardInfo;
import com.lz.ballshopping.shopping.service.CardInfoService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardInfoServiceImpl implements CardInfoService {

    @Autowired
    private CardInfoDao cardInfoDao;

    @Override
    public Result<String> updateMoney(CardInfo cardInfo) {
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        cardInfo.setUserName(userInfo.getUserName());
        if("0.0".equals(cardInfo.getFlag())){
            cardInfoDao.insertPaymentWay(cardInfo);
        }else if(cardInfo.getFlag()==null){
            cardInfoDao.updateMoney(cardInfo);
        }
        return new Result<String>("充值成功",Result.ResultStatus.SUCCESS.status,cardInfo.getCardMoney()+"");
    }

    @Override
    public Double getMoneyByUserNameAndCardName(String cardName) {
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        CardInfo cardInfo = new CardInfo();
        cardInfo.setUserName(userInfo.getUserName());
        cardInfo.setCardName(cardName);
        CardInfo cardInfoFlag = cardInfoDao.getCardInfoNameAndCardName(cardInfo);
        return cardInfoFlag!=null?cardInfoDao.getMoneyByUserNameAndCardName(cardInfo):(0.0);
    }
}
