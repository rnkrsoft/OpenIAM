package com.rnkrsoft.opensource.iam;

import com.rnkrsoft.framework.orm.jdbc.NameMode;
import com.rnkrsoft.framework.orm.jdbc.WordMode;
import com.rnkrsoft.framework.orm.jdbc.mysql.DataEngineType;
import com.rnkrsoft.framework.orm.untils.SqlScriptUtils;
import org.junit.Test;

import java.io.IOException;

public class SqlTest {
    @Test
    public void test2() throws IOException {
        SqlScriptUtils.generate("./target/sql.sql", NameMode.customize, "", NameMode.customize, "tb", NameMode.entity, "", DataEngineType.InnoDB, WordMode.lowerCase, WordMode.lowerCase, true, true, "com.rnkrsoft.opensource.iam.jdbc");
    }
}
