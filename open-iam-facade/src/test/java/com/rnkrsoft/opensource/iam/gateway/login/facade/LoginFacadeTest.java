package com.rnkrsoft.opensource.iam.gateway.login.facade;

import com.rnkrsoft.opensource.iam.enums.ChannelType;
import com.rnkrsoft.opensource.iam.gateway.login.domains.BindLoginRequest;
import com.rnkrsoft.opensource.iam.gateway.login.domains.BindLoginResponse;
import com.rnkrsoft.opensource.iam.gateway.login.domains.SmsLoginRequest;
import com.rnkrsoft.opensource.iam.gateway.login.domains.SmsLoginResponse;
import com.rnkrsoft.opensource.iam.gateway.user.facade.UserFacade;
import com.rnkrsoft.platform.client.Location;
import com.rnkrsoft.platform.client.LocationProvider;
import com.rnkrsoft.platform.client.LocationStore;
import com.rnkrsoft.platform.client.ServiceFactory;
import com.rnkrsoft.platform.client.connector.http.HttpInterfaceConnector;
import com.rnkrsoft.platform.protocol.AsyncHandler;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by woate on 2019/7/12.
 */
public class LoginFacadeTest {

    /**
     * 5a89cb68-beb9-4204-bd84-1cbd49f45b5a
     * @throws Exception
     */
    @Test
    public void testSmsLogin() throws Exception {
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
        //向客户端中注册用户服务类, 安卓环境下只能使用该方法注册服务
        serviceFactory.addServiceClasses(LoginFacade.class);
        //初始化
        serviceFactory.init();

        LoginFacade loginFacade = serviceFactory.get(LoginFacade.class);
        SmsLoginResponse response = loginFacade.smsLogin(SmsLoginRequest.builder().mobileNo("19923352630").smsCode("626049").build());
        System.out.println(response.getRspDesc());
    }

    @Test
    public void testBindLogin() throws Exception {
        ServiceFactory serviceFactory = ServiceFactory.newInstance();
        //设置接口连接器实现

        serviceFactory.getServiceConfigure().setInterfaceConnectorClass(HttpInterfaceConnector.class);
        //设置远程配置获取失败后的退回接口服务器信息
        serviceFactory.settingFallback("iam", false, "localhost", 8001, "/api");
        serviceFactory.settingFallback("public", false, "localhost", 8001, "/api");
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
        serviceFactory.getServiceConfigure().setToken("723d0876-e3e7-473f-a8d3-b4d2267a67e8");
        //向客户端中注册用户服务类, 安卓环境下只能使用该方法注册服务
        serviceFactory.addServiceClasses(LoginFacade.class);
        //初始化
        serviceFactory.init();
        //如果需要在安卓平台上处理初始化错误则需要调用另一个init
        LoginFacade loginFacade = serviceFactory.get(LoginFacade.class);
        BindLoginResponse response = loginFacade.bindLogin(BindLoginRequest.builder().uuid("18500000001").channel(ChannelType.WEB.getCode()).build());
        System.out.println(response.getRspDesc());
    }
}