package com.sungan.ad.controller.dict;

import com.hundsun.jresplus.base.dict.DictEntry;
import com.sungan.ad.dao.model.PlatProduct;
import com.sungan.ad.dao.model.adenum.EnumProductStatus;
import com.sungan.ad.service.plat.PlatProductService;
import com.sungan.ad.vo.PlatProductVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyf18255 on 2017/5/1.
 */
@Component
public class ProductDictHandler extends DictHandler {
    @Autowired
    private PlatProductService productService;
    @Override
    public String getDicName() {
        return "PUBLIC_PRODUCT";
    }

    @Override
    public DictEntry getDictEntry(String dictName, String keyName) {
        if(StringUtils.isBlank(keyName)){
            return null;
        }
       final PlatProductVo platProductVo = productService.find(keyName);
        if(platProductVo==null){
            return null;
        }
        DictEntry entry = new DictEntry() {
            @Override
            public String getValue() {
                return platProductVo.getProductId();
            }

            @Override
            public String getLabel() {
                return platProductVo.getProductName();
            }
        };
        return null;
    }

    @Override
    public List<DictEntry> getDicts(String dictName) {
        PlatProduct product= new PlatProduct();
        product.setProductStatus(EnumProductStatus.PUBLIC.getKey());
        List<PlatProductVo> platProductVos = productService.queryList(product);
        List<DictEntry> entryList = new ArrayList<DictEntry>();
        if(platProductVos==null){
            return entryList;
        }
        for(PlatProductVo vo:platProductVos){
            final String productId = vo.getProductId();
            final String productName = vo.getProductName();
            DictEntry entry = new DictEntry() {
                @Override
                public String getValue() {
                    return productId;
                }

                @Override
                public String getLabel() {
                    return productName;
                }
            };
        }
        return entryList;
    }
}
