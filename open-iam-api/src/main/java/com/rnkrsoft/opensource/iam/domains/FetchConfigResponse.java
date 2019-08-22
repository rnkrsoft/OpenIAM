package com.rnkrsoft.opensource.iam.domains;

import lombok.Data;

import javax.web.doc.AbstractResponse;
import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class FetchConfigResponse extends AbstractResponse{
    @ApidocElement("记录")
    final List<FetchConfigRecord> records = new ArrayList();

    public <T> T getConfig(String name, Class<T> javaType){
        for (FetchConfigRecord record : records){
            if (record.getName().equals(name)){
                return convert(record.getValue(), javaType);
            }
        }
        return null;
    }

    static  <T> T convert(Object value, Class<T> paramClass) {
        try {
            if (paramClass == String.class) {
                return (T) (value == null ? null : value.toString());
            }
            if (paramClass == Integer.class || paramClass == Integer.TYPE) {
                if (value == null) {
                    return null;
                } else {
                    return (T) new Integer(value.toString());
                }
            }
            if (paramClass == Long.class || paramClass == Long.TYPE) {
                if (value == null) {
                    return null;
                } else {
                    return (T) new Long(value.toString());
                }
            }
            if (paramClass == Double.class || paramClass == Double.TYPE) {
                if (value == null) {
                    return null;
                } else {
                    return (T) new Double(value.toString());
                }
            }
            if (paramClass == Boolean.class || paramClass == Boolean.TYPE) {
                if (value == null) {
                    return null;
                } else {
                    return (T) new Boolean(value.toString());
                }
            }
            throw new IllegalArgumentException("无效参数类型'" + paramClass + "'");
        } catch (Exception e) {
            return null;
        }
    }
}
