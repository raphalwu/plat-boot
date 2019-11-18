package com.xxx.plat.base.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

public interface Queryable<Criteria> extends Serializable {
    default String queryType() {
        return this.getClass().toString();
    }

    Criteria toCriteria();

    default Map<String, ? extends Object> toParamterMap() {
        HashMap result = new HashMap();

        Map beanMap;
        try {
            beanMap = BeanUtils.describe(this);
        } catch (Exception var5) {
            return result;
        }

        Iterator var3 = beanMap.entrySet().iterator();

        while(var3.hasNext()) {
            Entry<String, String> entry = (Entry)var3.next();
            if (StringUtils.isNoneBlank(new CharSequence[]{(CharSequence)entry.getValue()}) && !((String)entry.getKey()).equals("class") && !((String)entry.getKey()).equals("serialVersionUID")) {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }
}