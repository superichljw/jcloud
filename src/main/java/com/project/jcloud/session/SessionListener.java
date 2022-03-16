package com.project.jcloud.session;

import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener  implements HttpSessionListener {

    @Value("${server.servlet.session.timeout}")
    private int sessionTime;

    public void sessionCreated(HttpSessionEvent se){
        System.out.println("session created ");
        System.out.println("session last accessed time :: " + se.getSession().getLastAccessedTime());
        System.out.println("session max interval :: " + sessionTime);
        se.getSession().setMaxInactiveInterval(sessionTime);
    }

    public void sessionDestroyed(HttpSessionEvent se){
        se.getSession().invalidate();
        System.out.println("session destroyed");
    }
}
