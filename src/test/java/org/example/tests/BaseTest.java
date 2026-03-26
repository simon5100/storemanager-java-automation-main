package org.example.tests;

import org.example.backend.models.RegisterRequest;
import org.example.db.DbUtils;
import org.example.frontend.models.User;
import org.junit.jupiter.api.AfterAll;
import static org.example.backend.requests.AuthServiceRequest.executeGetRegister;
import static org.example.config.test_envproperties.TestConfigReaderImpl.getAppUiUrl;

public abstract class BaseTest {

    public static final String APP_UI_URL = getAppUiUrl();

    @AfterAll
    static void tearDown() {
        DbUtils.closeConnection();
    }

    protected User registerTestUser() {
        RegisterRequest request = RegisterRequest.generate();
        executeGetRegister(request);
        return User.builder().email(request.getEmail()).password(request.getPassword()).build();
    }


}
