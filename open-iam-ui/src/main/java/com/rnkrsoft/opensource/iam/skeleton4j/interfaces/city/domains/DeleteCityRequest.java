package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.city.domains;

import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
public class DeleteCityRequest implements Serializable{
    @ApidocElement(value = "城市编号", maxLen = 36, unique = true)
    Integer cityId;
}
