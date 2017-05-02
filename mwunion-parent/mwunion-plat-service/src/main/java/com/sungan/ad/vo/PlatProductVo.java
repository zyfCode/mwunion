package com.sungan.ad.vo;

import com.sungan.ad.dao.model.PlatProduct;
import com.sungan.ad.dao.model.adenum.EnumProductPubicSources;
import org.apache.commons.lang.StringUtils;

/**
 * 说明:
 */
public class PlatProductVo  extends PlatProduct {
   private String productStatusCn;
    private String  productTypeCn;
    private String publicSourceCn;
    private String createTimeStr;
    private String updateTimeStr;

    public String getProductStatusCn() {
        return productStatusCn;
    }

    public void setProductStatusCn(String productStatusCn) {
        this.productStatusCn = productStatusCn;
    }

    public String getProductTypeCn() {
        return productTypeCn;
    }

    public void setProductTypeCn(String productTypeCn) {
        this.productTypeCn = productTypeCn;
    }

    public String getPublicSourceCn() {
        String publicSource = this.getPublicSource();
        if(StringUtils.isNotBlank(publicSource)){
            if(publicSource.contains(",")){
                String[] split = publicSource.split(",");
                for(String t:split){
                    EnumProductPubicSources match = EnumProductPubicSources.match(t);
                    if(match==null){
                        continue;
                    }
                    if(StringUtils.isBlank(this.publicSourceCn)){
                        this.publicSourceCn = match.getLabel();
                    }else{
                        this.publicSourceCn = this.publicSourceCn+","+match.getLabel();
                    }
                }
            }else{
                EnumProductPubicSources match = EnumProductPubicSources.match(this.getPublicSource());
                this.publicSourceCn = match.getLabel();
            }
        }
        return publicSourceCn;
    }

    public void setPublicSourceCn(String publicSourceCn) {
        this.publicSourceCn = publicSourceCn;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }
}