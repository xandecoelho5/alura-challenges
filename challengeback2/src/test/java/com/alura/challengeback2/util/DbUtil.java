package com.alura.challengeback2.util;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DbUtil {

    public static void populaBanco(DataSource dataSource) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("data.sql"));
        }
    }

    public static void salvarListaNoBanco(TestEntityManager em, List<Object> lista) {
        lista.forEach(em::persist);
    }
}
