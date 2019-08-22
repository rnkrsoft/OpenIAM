package com.rnkrsoft.opensource.iam.gateway.user.facade;

import com.rnkrsoft.opensource.iam.gateway.login.domains.SmsLoginRequest;
import com.rnkrsoft.opensource.iam.gateway.login.domains.SmsLoginResponse;
import com.rnkrsoft.opensource.iam.gateway.login.facade.LoginFacade;
import com.rnkrsoft.opensource.iam.gateway.user.domains.GetMyInfoRequest;
import com.rnkrsoft.opensource.iam.gateway.user.domains.GetMyInfoResponse;
import com.rnkrsoft.platform.client.Location;
import com.rnkrsoft.platform.client.LocationProvider;
import com.rnkrsoft.platform.client.LocationStore;
import com.rnkrsoft.platform.client.ServiceFactory;
import com.rnkrsoft.platform.client.connector.http.HttpInterfaceConnector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by woate on 2019/7/23.
 */
public class UserFacadeTest {

    @Test
    public void testGetMyInfo() throws Exception {
        ServiceFactory serviceFactory = ServiceFactory.newInstance();
        serviceFactory.getServiceConfigure().setInterfaceConnectorClass(HttpInterfaceConnector.class);
        //设置远程配置获取失败后的退回接口服务器信息
        serviceFactory.settingFallback("iam", false, "iam-gateway.rnkrsoft.com", 8002, "/api");
        serviceFactory.settingFallback("public", false, "iam-gateway.rnkrsoft.com", 8002, "/api");
        //如果不使用TOKEN作为密码时的固定密码
        serviceFactory.setPassword("1234567890654321");
        //加密时或者解密时的秘钥向量 默认配置，在configure无法成功获取时使用
        serviceFactory.setKeyVector("1234567890654321");
        //用户版本号
        serviceFactory.setAppVersion("4.0.0");
        //注册定位信息提供者
        serviceFactory.registerLocationProvider(new LocationProvider() {
            @Override
            public void locate(LocationStore locationStore) {
                locationStore.refreshLocation(new Location(1, 2));
            }
        });
        //是否自动获取定位信息
        serviceFactory.getServiceConfigure().setAutoLocate(true);
        serviceFactory.getServiceConfigure().setMacAddress("44-45-53-54-00-00");
        //用户标识
        serviceFactory.getServiceConfigure().setUid("sssss");
        serviceFactory.getServiceConfigure().setUic("2542563b-a153-48af-84d4-d40542c8bc3b");
        serviceFactory.getServiceConfigure().setToken("5a89cb68-beb9-4204-bd84-1cbd49f45b5a");
        //向客户端中注册用户服务类, 安卓环境下只能使用该方法注册服务
        serviceFactory.addServiceClasses(UserFacade.class);
        //初始化
        serviceFactory.init();

        UserFacade userFacade = serviceFactory.get(UserFacade.class);
        GetMyInfoResponse response = userFacade.getMyInfo(new GetMyInfoRequest());
        System.out.println(response.getRspDesc());
    }
}