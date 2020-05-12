
package com.onecloud.tcmgmt.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onecloud.tcmgmt.domain.appdb.User;
import com.onecloud.tcmgmt.service.DefaultUserContextService;
import com.onecloud.tcmgmt.service.UserContextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserContextInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(UserContextInterceptor.class);

    private final UserContextService userContextService;

    private String authUserName = "authUser";

    @Autowired
    public UserContextInterceptor(UserContextService userContextService) {
        this.userContextService = userContextService;
    }

    public void setAuthUserName(String authUserName) {
        this.authUserName = authUserName;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        User user = this.userContextService.getUserFromContext();
        if (user != null) {
            logger.debug("About to add user to request");
            request.setAttribute(this.authUserName, user);
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("Inside the postHandle method");
        if (modelAndView != null) {
            User user = this.userContextService.getUserFromContext();
            if (user != null) {
                logger.debug("About to add user to modelView");
                modelAndView.addObject(this.authUserName, user);
            }
        }
    }
}
