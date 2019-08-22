package com.rnkrsoft.opensource.iam.gateway;

import com.rnkrsoft.embedded.boot.EmbeddedApplicationLoader;
import com.rnkrsoft.embedded.boot.annotation.EmbeddedBootApplication;
import com.rnkrsoft.embedded.boot.annotation.EmbeddedRemoteConfigure;
import com.rnkrsoft.framework.config.v1.RuntimeMode;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
@EmbeddedBootApplication(
        remoteConfigure = @EmbeddedRemoteConfigure(
                host = "localhost",
                port = 8001,
                groupId = "com.rnkrsoft.opensource.iam.gateway",
                artifactId = "open-iam-gateway",
                version = "1.0.0",
                env = "DEV",
                runtimeMode = RuntimeMode.LOCAL
        ),
        hostName = "localhost",
        port = 8001
)
public class Main {

    public static void main(String[] args) throws Exception {
        EmbeddedApplicationLoader.runWith(Main.class, args);
    }
}
