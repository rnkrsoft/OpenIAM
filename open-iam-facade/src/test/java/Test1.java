import com.rnkrsoft.opensource.iam.gateway.channel.facade.ChannelFacade;
import com.rnkrsoft.opensource.iam.gateway.department.facade.DepartmentFacade;
import com.rnkrsoft.opensource.iam.gateway.login.facade.LoginFacade;
import com.rnkrsoft.opensource.iam.gateway.logout.facade.LogoutFacade;
import com.rnkrsoft.opensource.iam.gateway.notice.facade.NoticeFacade;
import com.rnkrsoft.opensource.iam.gateway.sms.facade.SmsFacade;
import com.rnkrsoft.opensource.iam.gateway.user.facade.UserFacade;
import com.rnkrsoft.platform.compiler.*;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
public class Test1 {

    public static void main(String[] args) throws Exception {
        String[] serviceClasses = new String[]{
                LoginFacade.class.getName(),
                LogoutFacade.class.getName(),
                DepartmentFacade.class.getName(),
                ChannelFacade.class.getName(),
                SmsFacade.class.getName(),
                UserFacade.class.getName(),
                NoticeFacade.class.getName(),
        };
        InterfacePlatformCompilerCli.generate(DeviceType.Android, "com.rnkrsoft.opensource.iam.gateway.v10", "./target/", "com.rnkrsoft.opensource.iam.gateway", serviceClasses);
    }
}
