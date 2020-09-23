package com.lz.ballshopping.shopping.controller;

import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.shopping.entity.CardInfo;
import com.lz.ballshopping.shopping.service.CardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CardInfoController {

    @Autowired
    private CardInfoService cardInfoService;

    @PostMapping(value = "/cardInfo",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> updateMoney(@RequestBody CardInfo cardInfo) {
        return cardInfoService.updateMoney(cardInfo);
    }

    @GetMapping(value = "/cardInfo/{cardName}")
    public String getMoneyByUserNameAndCardName(@PathVariable String cardName) {
        return cardInfoService.getMoneyByUserNameAndCardName(cardName)+"";
    }

}
