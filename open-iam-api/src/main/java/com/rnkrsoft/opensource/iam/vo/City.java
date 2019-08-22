package com.rnkrsoft.opensource.iam.vo;

import lombok.Builder;
import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Data
@Builder
public class City implements Serializable{
    @ApidocElement("城市编号")
    Integer cityId;
    @ApidocElement("城市名称")
    String cityName;
}
