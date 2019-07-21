package com.mayi.springbootsession;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class SessionInitialier extends AbstractHttpSessionApplicationInitializer {
    public SessionInitialier() {
        super(SessionConfig.class);
    }
}
