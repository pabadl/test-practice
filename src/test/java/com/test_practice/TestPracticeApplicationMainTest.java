package com.test_practice;

import org.junit.jupiter.api.Test;

class TestPracticeApplicationMainTest {

    @Test
    void main_runsWithoutWebServer() {
        final String key = "spring.main.web-application-type";
        String previous = System.getProperty(key);
        System.setProperty(key, "none");
        try {
            TestPracticeApplication.main(new String[] {});
        } finally {
            if (previous == null) {
                System.clearProperty(key);
            } else {
                System.setProperty(key, previous);
            }
        }
    }
}
