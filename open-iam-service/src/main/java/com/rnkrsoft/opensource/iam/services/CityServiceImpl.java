package com.rnkrsoft.opensource.iam.services;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.opensource.iam.enums.CityStatus;
import com.rnkrsoft.opensource.iam.enums.IAMResponseCode;
import com.rnkrsoft.opensource.iam.jdbc.dao.CityDAO;
import com.rnkrsoft.opensource.iam.jdbc.entity.CityEntity;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.city.domains.*;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.city.services.CityService;
import com.rnkrsoft.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.web.data.Node;
import javax.web.skeleton4j.utils.QueryUtils;
import java.util.Date;
import java.util.List;

/**
 * Created by rnkrsoft.com on 2019/7/9.
 */
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityDAO cityDAO;
    @Override
    public CreateCityResponse create(CreateCityRequest request) {
        CreateCityResponse response = new CreateCityResponse();
        cityDAO.insertSelective(CityEntity.builder()
                        .cityName(request.getCityName())
                        .build()
        );
        return response;
    }

    @Override
    public DeleteCityResponse delete(DeleteCityRequest request) {
        DeleteCityResponse response = new DeleteCityResponse();
        CityEntity cityEntity = CityEntity.builder()
                .cityId(request.getCityId())
                .cityStatus(CityStatus.DISABLED.getCode())
                .build();
        cityEntity.setLastUpdateDate(new Date());
        int updateCount = cityDAO.updateByPrimaryKeySelective(cityEntity);
        if (updateCount == 1) {
            return response;
        }else{
            response.setRspCode(IAMResponseCode.LOGIC_DELETE_FAILURE);
            return response;
        }
    }

    @Override
    public QueryCityResponse query(QueryCityRequest request) {
        QueryCityResponse response = new QueryCityResponse();
        Pagination<CityEntity> pagination = new Pagination<CityEntity>(request.getPageSize(), request.getPageNo());
        if (StringUtils.isNotBlank(request.getCityName())){
            pagination.getParams().put("cityName", request.getCityName());
        }
        if (StringUtils.isNotBlank(request.getCityStatus())){
            pagination.getParams().put("cityStatus", request.getCityStatus());
        }
        cityDAO.queryCity(pagination);
        QueryUtils.parameter(request, pagination.getTotal(), pagination.getPageNum(), response);
        for (CityEntity record : pagination.getRecords()) {
            response.addRecord(QueryCityRecord.builder()
                    .cityId(record.getCityId())
                    .cityName(record.getCityName())
                    .cityStatus(record.getCityStatus())
                    .build());
        }
        return response;
    }

    @Override
    public FetchCityResponse fetch(FetchCityRequest request) {
        FetchCityResponse response = new FetchCityResponse();
        List<CityEntity> cityEntities = cityDAO.selectAnd(CityEntity.builder().cityStatus(CityStatus.ENABLED.getCode()).build());
        for (CityEntity record : cityEntities){
            response.addNode(Node.builder()
                    .text(record.getCityName())
                    .value(Integer.toString(record.getCityId()))
                    .build()
            );
        }
        return response;
    }
}
