package com.game.sdk;


import androidx.annotation.Keep;
import androidx.annotation.NonNull;

/**
 * CP需要传的支付参数
 */
@Keep
public class PayParams {
    public String appUserId; // 调用方用户id
    public String appUserName; // CP的用户昵称
    public String appRoleId; // CP角色名ID
    public String appServerId; //CP角色服务器ID
    public String productId; //产品id（联系运营核实）

    public PaySubType productType = PaySubType.INAPP;//商品类型（inapp是消耗型商品、subs是订阅型商品）
    public String appOrderId; //cp自有订单Id（不能重复）
    public String notifyUrl; //支付通知地址
    public String app_ext1; //其他字段
    public String app_ext2; //其他字段

    //SDK自身需要的参数，不需要CP传
    public final SdkPayParams sdkPayParams = new SdkPayParams();

    /**
     * SDK自身需要的参数，不需要CP传
     */
    @Keep
    public static final class SdkPayParams {
        public String sdkOrderId; //SDK的订单Id,通过获取订单接口获取的
        public String productPrice; //商品价格,cp不需要传,SDK自己获取
        public String productName;//商品名称,cp不需要传,SDK自己获取
        public String productDesc;//商品描述,cp不需要传,SDK自己获取
        public String currency;//货币类型,CNY,USD
        public String sign;//签名


        @NonNull
        @Override
        public String toString() {
            return "SdkPayParams{" +
                    "sdkOrderId='" + sdkOrderId + '\'' +
                    ", productPrice='" + productPrice + '\'' +
                    ", productName='" + productName + '\'' +
                    ", productDesc='" + productDesc + '\'' +
                    ", currency='" + currency + '\'' +
                    ", sign='" + sign + '\'' +
                    '}';
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "PayParams{" +
                "appUserId='" + appUserId + '\'' +
                ", appUserName='" + appUserName + '\'' +
                ", appRoleId='" + appRoleId + '\'' +
                ", appServerId='" + appServerId + '\'' +
                ", productId='" + productId + '\'' +
                ", productType=" + productType +
                ", appOrderId='" + appOrderId + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", app_ext1='" + app_ext1 + '\'' +
                ", app_ext2='" + app_ext2 + '\'' +
                ", sdkPayParams=" + sdkPayParams +
                '}';
    }
}
