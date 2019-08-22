package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.city.services;

import com.rnkrsoft.opensource.iam.enums.CityStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.city.domains.*;

import javax.web.data.Node;
import javax.web.skeleton4j.utils.QueryUtils;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
public class CityServiceImpl implements CityService{
    @Override
    public CreateCityResponse create(CreateCityRequest request) {
        CreateCityResponse response = new CreateCityResponse();
        return response;
    }

    @Override
    public DeleteCityResponse delete(DeleteCityRequest request) {
        DeleteCityResponse response = new DeleteCityResponse();
        return response;
    }

    @Override
    public QueryCityResponse query(QueryCityRequest request) {
        QueryCityResponse response = new QueryCityResponse();
        QueryUtils.parameter(request, request.getPageSize(), 1, response);
        for (int i = 0; i < request.getPageSize() ; i++) {
            response.addRecord(QueryCityRecord.builder()
                    .cityId(i)
                    .cityName("测试城市" + i)
                    .cityStatus(CityStatus.ENABLED.getCode())
                    .build());
        }
        return response;
    }

    @Override
    public FetchCityResponse fetch(FetchCityRequest request) {
        FetchCityResponse response = new FetchCityResponse();
        response.addNode(Node.builder().text("重庆").value("023").build());
        response.addNode(Node.builder().text("北京").value("010").build());
        return response;
    }
}
