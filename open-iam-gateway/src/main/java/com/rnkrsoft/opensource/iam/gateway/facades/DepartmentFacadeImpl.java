package com.rnkrsoft.opensource.iam.gateway.facades;

import com.rnkrsoft.opensource.iam.gateway.department.domains.SearchDepartmentRequest;
import com.rnkrsoft.opensource.iam.gateway.department.domains.SearchDepartmentResponse;
import com.rnkrsoft.opensource.iam.gateway.department.facade.DepartmentFacade;
import org.springframework.stereotype.Service;

/**
 * Created by woate on 2019/7/14.
 */
@Service
public class DepartmentFacadeImpl implements DepartmentFacade {
    @Override
    public SearchDepartmentResponse searchDepartment(SearchDepartmentRequest request) {
        return null;
    }
}
