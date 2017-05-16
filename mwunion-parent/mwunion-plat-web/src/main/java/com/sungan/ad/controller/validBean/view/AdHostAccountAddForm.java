package com.sungan.ad.controller.validBean.view;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *  #combox({"label":"订单类型","name":"adOrderType","value":"","headItem":{"label":"","value":"请选择 ..."},  "dictName": "ADHOSTACCOUNTPAYORDER_TYPE"})
 #combox({"label":"计费方式","name":"payType","value":"","headItem":{"label":"","value":"请选择 ..."},  "dictName": "ADHOSTACCOUNTPAYORDERPAY_TYPE"})
 #textfield({"label":"计费上限", "name":"adAmount","value":"","defValue":"","check":"decmal1"})
 #textfield({"label":"计费单价", "name":"price","value":"","defValue":"","check":"required;decmal1"})

 * Created by zhangyf18255 on 2017/5/7.
 */
public class AdHostAccountAddForm {
    @NotEmpty(message = "广告主")
    private String adhostId;
    @NotEmpty(message = "支付类型")
    private String payType;
    @NotEmpty(message = "请选择产品")
    private String productId;

    private String anaxNames;

    private String adAmount;

    public String getAnaxNames() {
        return anaxNames;
    }

    public void setAnaxNames(String anaxNames) {
        this.anaxNames = anaxNames;
    }

    public String getAdAmount() {
        return adAmount;
    }

    public void setAdAmount(String adAmount) {
        this.adAmount = adAmount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAdhostId() {
        return adhostId;
    }

    public void setAdhostId(String adhostId) {
        this.adhostId = adhostId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

}
