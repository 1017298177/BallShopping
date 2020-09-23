package com.lz.ballshopping.account.dao;

import com.lz.ballshopping.commons.entity.OrderInfo;
import com.lz.ballshopping.commons.vo.SearchVo;
import com.lz.ballshopping.shopping.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderInfoDao {


    @Select("<script>" +
            "select " +
            "sum(order_product_total_price) order_product_total_price,order_address,\n" +
            "order_product_type,order_number,\n" +
            "order_trading_time_detail,order_trading_time_year\n" +
            "FROM\n" +
            "order_info\n"
            + "<where> "
            + " order_payment_status = 1 and order_back_money_status= 0 and order_confirm_status = 0 and order_comment_status = 0"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (order_number like '%${keyWord}%' or order_product_name like '%${keyWord}%'  or order_product_total_price like '%${keyWord}%' or order_product_type like '%${keyWord}%' ) "
            + "</if>"
            + "</where>"
            + "GROUP BY order_number"
            + "</script>")
    List<OrderInfo> getOrderInfoBySearchVo(SearchVo searchVo);
    @Select("select * from order_info where order_number = #{orderNumber}")
    List<OrderInfo> getOrdersByOrderNumber(String orderNumber);
    @Select("select * from order_info")
    List<OrderInfo> getAllOrderInfo();
    @Delete("delete from order_info where order_id=#{OrderInfoId}")
    void deleteOrderInfoByOrderId(int orderId);
    @Select("select count(*) from order_info")
    int getOrderCount();
    @Select("select count(*) from order_info where order_payment_status = 0 " +
            "and order_send_status = 0 and order_confirm_status = 0 and order_comment_status = 0")
    int getOrderWaitPaymentCount();
    @Select("select count(*) from order_info where order_payment_status = 1 " +
            "and order_send_status = 0 and order_confirm_status = 0 and order_comment_status = 0")
    int getOrderWaitSendCount();
    @Select("select count(*) from order_info where order_payment_status = 1 " +
            "and order_send_status = 1 and order_confirm_status = 0 and order_comment_status = 0")
    int getOrderWaitConfirmCount();
    @Select("select count(*) from order_info where order_payment_status = 1 " +
            "and order_send_status = 1 and order_confirm_status = 1 and order_comment_status = 0")
    int getOrderWaitCommentCount();
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '01-00' and '02-00' and order_trading_time_year=#{year}\n" +
            "and order_payment_status = 0")
    int getOneMonthPaymentOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '02-00' and '03-00' and order_trading_time_year=#{year}\n" +
            "and order_payment_status = 0")
    int getTwoMonthPaymentOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '03-00' and '04-00' and order_trading_time_year=#{year}\n" +
            "and order_payment_status = 0")
    int getThreeMonthPaymentOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '04-00' and '05-00' and order_trading_time_year=#{year}\n" +
            "and order_payment_status = 0")
    int getFourMonthPaymentOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '05-00' and '06-00' and order_trading_time_year=#{year}\n" +
            "and order_payment_status = 0")
    int getFiveMonthPaymentOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '06-00' and '07-00' and order_trading_time_year=#{year}\n" +
            "and order_payment_status = 0")
    int getSixMonthPaymentOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '07-00' and '08-00' and order_trading_time_year=#{year}\n" +
            "and order_payment_status = 0")
    int getSevenMonthPaymentOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '08-00' and '09-00' and order_trading_time_year=#{year}\n" +
            "and order_payment_status = 0")
    int getEightMonthPaymentOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '09-00' and '10-00' and order_trading_time_year=#{year}\n" +
            "and order_payment_status = 0")
    int getNineMonthPaymentOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '10-00' and '11-00' and order_trading_time_year=#{year}\n" +
            "and order_payment_status = 0")
    int getTenMonthPaymentOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '11-00' and '12-00' and order_trading_time_year=#{year}\n" +
            "and order_payment_status = 0")
    int getElevenMonthPaymentOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '12-00' and '00-00' and order_trading_time_year=#{year}\n" +
            "and order_payment_status = 0")
    int getTwelveMonthPaymentOrderCount(String year);

    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '01-00' and '02-00' and order_trading_time_year=#{year}\n" +
            "and order_send_status = 0")
    int getOneMonthSendOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '02-00' and '03-00' and order_trading_time_year=#{year}\n" +
            "and order_send_status = 0")
    int getTwoMonthSendOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '03-00' and '04-00' and order_trading_time_year=#{year}\n" +
            "and order_send_status = 0")
    int getThreeMonthSendOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '04-00' and '05-00' and order_trading_time_year=#{year}\n" +
            "and order_send_status = 0")
    int getFourMonthSendOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '05-00' and '06-00' and order_trading_time_year=#{year}\n" +
            "and order_send_status = 0")
    int getFiveMonthSendOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '06-00' and '07-00' and order_trading_time_year=#{year}\n" +
            "and order_send_status = 0")
    int getSixMonthSendOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '07-00' and '08-00' and order_trading_time_year=#{year}\n" +
            "and order_send_status = 0")
    int getSevenMonthSendOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '08-00' and '09-00' and order_trading_time_year=#{year}\n" +
            "and order_send_status = 0")
    int getEightMonthSendOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '09-00' and '10-00' and order_trading_time_year=#{year}\n" +
            "and order_send_status = 0")
    int getNineMonthSendOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '10-00' and '11-00' and order_trading_time_year=#{year}\n" +
            "and order_send_status = 0")
    int getTenMonthSendOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '11-00' and '12-00' and order_trading_time_year=#{year}\n" +
            "and order_send_status = 0")
    int getElevenMonthSendOrderCount(String year);
    @Select("SELECT count(*) from order_info where order_trading_time_detail BETWEEN '12-00' and '00-00' and order_trading_time_year=#{year}\n" +
            "and order_send_status = 0")
    int getTwelveMonthSendOrderCount(String year);

    @Select("SELECT count(*) from order_info where order_trading_time_year=#{year} ")
    int getOrderCountByYear(String year);

    @Select("SELECT count(*) from order_info where  order_payment_status = 1  and order_trading_time_year=#{year}")
    int getSuccessOrderCountByYear(String year);

    @Select("SELECT count(*) from order_info where  order_payment_status = 0 and order_trading_time_year=#{year}")
    int getFailOrderCountByYear(String year);

    @Select("select distinct(order_trading_time_year) from order_info")
    List<String> getOrderYear();

    @Select("select count(*) from order_info where order_province = #{province}")
    int getOrderCountByProvince(String province);
    @Select("select count(*) from order_info where order_product_type = #{type}")
    int getBallOrderCount(String type);
    @Select("select distinct(order_product_type) from order_info")
    List<String> getOrderProductType();

    @Update("update order_info set order_send_status = 1 where order_id = #{orderId}")
    void sendProduct(int orderId);
    @Select("<script>" +
            "select " +
            "*\n" +
            "FROM\n" +
            "order_info\n"
            + "<where> "
            + " order_payment_status = 1 and order_back_money_status= 1 and order_confirm_status = 0"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (order_number like '%${keyWord}%' or order_product_name like '%${keyWord}%'  or order_product_total_price like '%${keyWord}%' or order_product_type like '%${keyWord}%' ) "
            + "</if>"
            + "</where>"
            + "</script>")
    List<OrderInfo> getBackMoneyOrders(SearchVo searchVo);
    @Update("update order_info set order_status = 1 where order_id = #{orderId}")
    void backMoney(int orderId);

    @Insert("insert into shopping.order_info(order_product_numbers,order_product_name, order_product_total_price,  order_user_name, order_number, order_product_id, order_product_image, order_product_type, order_product_price, order_trading_time_detail, order_trading_time_year,order_phone_number)" +
            "values (#{orderProductNumbers},#{orderProductName},  #{orderProductTotalPrice}, #{orderUserName},  #{orderNumber}, #{orderProductId}, #{orderProductImage}, #{orderProductType}, #{orderProductPrice}, #{orderTradingTimeDetail}, #{orderTradingTimeYear},#{orderPhoneNumber})")
    void insertNotPaymentOrder(OrderInfo orderInfo);

    @Select("select * from order_info where order_number = #{orderNumber} and order_user_name = #{orderUserName} ")
    List<OrderInfo> getShoppingOrders(OrderInfo orderInfo);

    @Update("update order_info set order_payment_status = 1," +
            "order_province = #{orderProvince},order_address = #{orderAddress},order_receiver_name=" +
            "#{orderReceiverName}" +
            "where  order_number = #{orderNumber}")
    void updateOrderInfoByOrderNumber(OrderInfo orderInfo);

    @Select("select sum(order_product_total_price)order_product_total_price,sum(order_product_numbers) order_product_numbers,\n" +
            "order_product_type,order_number,order_product_name,order_product_image,\n" +
            "order_trading_time_detail,order_trading_time_year\n" +
            "from order_info where order_payment_status = 0 and order_user_name = #{keyWord}\n" +
            "group by order_number")
    List<OrderInfo> getWaitPaymentOrder(SearchVo searchVo);
    @Select("select * from order_info where order_payment_status = 1 and order_send_status = 0 and " +
            "order_user_name = #{keyWord}")
    List<OrderInfo> getWaitSendOrder(SearchVo searchVo);
    @Select("select * from order_info where order_payment_status = 1 and order_send_status = 1 and order_confirm_status = 0 and " +
            "order_user_name = #{keyWord}")
    List<OrderInfo> getWaitConfirmOrder(SearchVo searchVo);
    @Select("select * from order_info where order_payment_status = 1 and order_send_status = 1 and order_confirm_status = 1" +
            " and order_comment_status = 0 and " +
            "order_user_name = #{keyWord}")
    List<OrderInfo> getWaitCommentOrder(SearchVo searchVo);
}
