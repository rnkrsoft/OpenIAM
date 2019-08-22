package com.rnkrsoft.opensource.iam;

import com.rnkrsoft.embedded.boot.EmbeddedApplicationLoader;
import com.rnkrsoft.embedded.boot.annotation.EmbeddedBootApplication;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@EmbeddedBootApplication(
        hostName = "localhost",
        port = 80
)
public class Main {
    public static void main(String[] args) throws Exception {
        EmbeddedApplicationLoader.runWith(Main.class, args);
    }
}