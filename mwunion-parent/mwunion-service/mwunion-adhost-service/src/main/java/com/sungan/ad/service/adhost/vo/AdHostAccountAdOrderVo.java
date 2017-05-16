package com.sungan.ad.service.adhost.vo;

import com.sungan.ad.dao.model.AdHostAccountAdOrder;
import com.sungan.ad.dao.model.adenum.EnumAdHostAccountAdOrderPayType;
import com.sungan.ad.dao.model.adenum.EnumAdHostAccountAdOrderStatus;
import com.sungan.ad.dao.model.adenum.EnumAdHostAccountAdOrderType;
import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 说明:
 */
public class AdHostAccountAdOrderVo  extends AdHostAccountAdOrder{
    private String adOrderTypeCn;
    private String payTypeCn;
    private String adOrderStausCn;
    private String createTimeStr;

    public String getAdOrderTypeCn() {
        return adOrderTypeCn;
    }

    public void setAdOrderTypeCn(String adOrderTypeCn) {
        this.adOrderTypeCn = adOrderTypeCn;
    }

    public String getPayTypeCn() {
        return payTypeCn;
    }

    public void setPayTypeCn(String payTypeCn) {
        this.payTypeCn = payTypeCn;
    }

    public String getAdOrderStausCn() {
        return adOrderStausCn;
    }

    public void setAdOrderStausCn(String adOrderStausCn) {
        this.adOrderStausCn = adOrderStausCn;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}