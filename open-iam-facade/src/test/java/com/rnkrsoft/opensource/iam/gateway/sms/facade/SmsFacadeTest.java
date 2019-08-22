package com.rnkrsoft.opensource.iam.gateway.sms.facade;

import com.rnkrsoft.opensource.iam.gateway.login.facade.LoginFacade;
import com.rnkrsoft.opensource.iam.gateway.sms.domains.SendLoginSmsRequest;
import com.rnkrsoft.opensource.iam.gateway.sms.domains.SendLoginSmsResponse;
import com.rnkrsoft.platform.client.Location;
import com.rnkrsoft.platform.client.LocationProvider;
import com.rnkrsoft.platform.client.LocationStore;
import com.rnkrsoft.platform.client.ServiceFactory;
import com.rnkrsoft.platform.client.connector.http.HttpInterfaceConnector;
import com.rnkrsoft.platform.client.logger.LoggerFactory;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by woate on 2019/7/12.
 */
public class SmsFacadeTest {

    @Test
    public void testSendLoginSms() throws Exception {
        ServiceFactory serviceFactory = ServiceFactory.newInstance();
//        serviceFactory.settingConfigure(false, "gateway-configure.xxx.com", 80, "/configure");
//设置接口连接器实现

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
        serviceFactory.addServiceClasses(SmsFacade.class);
        //初始化
        serviceFactory.init();
        //如果需要在安卓平台上处理初始化错误则需要调用另一个init
//        serviceFactory.init(true, new AsyncHandler() {
//            @Override
//            public void fail(String code, String desc, String detail) {
//                System.out.println(desc);
//            }
//
//            @Override
//            public void success(Object response) {
//                System.out.println(response);
//            }
//        });

        SmsFacade smsFacade = serviceFactory.get(SmsFacade.class);
        SendLoginSmsResponse response = smsFacade.sendLoginSms(SendLoginSmsRequest.builder().mobileNo("19923352630").build());
        System.out.println(response);
    }
}