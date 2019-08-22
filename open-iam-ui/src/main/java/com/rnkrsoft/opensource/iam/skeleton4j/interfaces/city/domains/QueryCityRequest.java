package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.city.domains;

import com.rnkrsoft.opensource.iam.enums.CityStatus;
import lombok.Data;

import javax.web.doc.AbstractRequestPage;
import javax.web.doc.annotation.ApidocElement;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class QueryCityRequest extends AbstractRequestPage{
    @ApidocElement(value = "城市名称", maxLen = 36, required = false, placeholder = "请输入城市名称，支持模糊查询")
    String cityName;
    @ApidocElement(value = "城市状态", required = false, enumClass = CityStatus.class, defaults = "1")
    Integer cityStatus;
}
