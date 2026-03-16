package org.example.tests;

import org.example.db.DbUtils;
import org.junit.jupiter.api.AfterAll;

public abstract class BaseTest {

    public static final String APP_UI_URL = "http://localhost:8001/";

    @AfterAll
    static void tearDown() {
        DbUtils.closeConnection();
    }
}
