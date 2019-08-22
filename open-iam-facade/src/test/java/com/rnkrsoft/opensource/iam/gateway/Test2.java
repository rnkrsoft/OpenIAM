package com.rnkrsoft.opensource.iam.gateway;

import com.rnkrsoft.platform.compiler.InterfacePlatformSQLGenerator;

import java.io.File;
import java.io.IOException;

/**
 * Created by woate on 2019/7/14.
 */
public class Test2 {
    public static void main(String[] args) throws IOException {
        InterfacePlatformSQLGenerator.generate(new File("./target/init.sql"), "com.rnkrsoft.opensource.iam.gateway");
    }
}
