package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.city.services;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.city.domains.*;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@ApidocService("城市服务")
public interface CityService {
    @ApidocInterface("创建")
    CreateCityResponse create(CreateCityRequest request);
    @ApidocInterface("删除")
    DeleteCityResponse delete(DeleteCityRequest request);
    @ApidocInterface("查询")
    QueryCityResponse query(QueryCityRequest request);
    @ApidocInterface("拉取")
    FetchCityResponse fetch(FetchCityRequest request);
}
