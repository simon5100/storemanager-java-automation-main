package org.example.tests;

import org.example.db.DbUtils;
import org.junit.jupiter.api.AfterAll;

public abstract class BaseTest {

    // Константы для использования в API запросах
    public static final String BASE_URL = "http://localhost:8001/";
    public static final String REGISTER_ENDPOINT = "register/";

    @AfterAll
    static void rearDown() {
        DbUtils.closeConnection();
    }
}
