
package com.cisco.cstg.autotools.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.cisco.cstg.autotools.domain.appdb.IdentifiableAppEntity;

public class IdentifiableAppEntityGetController extends AbstractIdentifiableAppEntityController {
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
        IdentifiableAppEntity result = super.dao.getByIdInFactdb(id);
        super.logger.debug("Got {} by id {}", result, id);
        return new ModelAndView(super.getViewName()).addObject(result);
    }
}
