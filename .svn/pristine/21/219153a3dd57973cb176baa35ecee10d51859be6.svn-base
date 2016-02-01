
package com.cisco.cstg.autotools.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Conventions;
import org.springframework.web.servlet.ModelAndView;

import com.cisco.cstg.autotools.domain.appdb.IdentifiableAppEntity;

public class IdentifiableAppEntityListController extends AbstractIdentifiableAppEntityController {
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        List<IdentifiableAppEntity> result = super.dao.getAllInFactdb();
        super.logger.debug("Got {} entities", result.size());
        ModelAndView mav = new ModelAndView(super.getViewName());
        mav.addObject(result);
        super.logger.debug("The name of the result object is: "+ Conventions.getVariableName(result));
        return mav;
    }
}
