
package com.onecloud.tcmgmt.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onecloud.tcmgmt.domain.appdb.User;
import com.onecloud.tcmgmt.service.UserContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserContextInterceptor extends HandlerInterceptorAdapter {
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
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            User user = this.userContextService.getUserFromContext();
            if (user != null) {
                modelAndView.addObject(this.authUserName, user);
            }
        }
    }
}
