package com.sungan.ad.service.adhost.vo;

import com.sungan.ad.dao.model.AdHostAccountAdOrderAttri;
import com.sungan.ad.dao.model.adenum.EnumProductPubicSources;
import org.apache.commons.lang.StringUtils;

/**
 * 说明:
 */
public class AdHostAccountAdOrderAttriVo  extends AdHostAccountAdOrderAttri{
    private String publicSourceCn;
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
}