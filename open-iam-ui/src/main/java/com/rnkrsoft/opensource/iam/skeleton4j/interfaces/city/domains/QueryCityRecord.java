package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.city.domains;

import com.rnkrsoft.opensource.iam.enums.CityStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.city.services.CityService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.annotation.WebCascadeInterface;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryCityRecord implements Serializable{
    @ApidocElement(value = "城市编号", maxLen = 10, unique = true, hidden = true, interfaces = {
            @WebCascadeInterface(serviceClass = CityService.class, value = "delete", confirm = true)
    })
    Integer cityId;
    @ApidocElement(value = "城市名称", maxLen = 36)
    String cityName;
    @ApidocElement(value = "状态", maxLen = 10, enumClass = CityStatus.class)
    Integer cityStatus;
}
