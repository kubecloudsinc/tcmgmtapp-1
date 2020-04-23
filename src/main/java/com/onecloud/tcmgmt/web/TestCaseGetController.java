
package com.onecloud.tcmgmt.web;

import com.onecloud.tcmgmt.dao.TestCaseDao;
import com.onecloud.tcmgmt.domain.appdb.TestCase;
import com.onecloud.tcmgmt.domain.appdb.TestStep;
import com.onecloud.tcmgmt.semantic.utils.SortByStepOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

public class TestCaseGetController extends ParameterizableViewController {

    protected TestCaseDao dao;

    // mask the super's Apache Commons Logging by SLF4J
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Required
    public void setDao(TestCaseDao dao) {
        this.dao = dao;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
        TestCase result = this.dao.getById(id);
        this.logger.debug("Got {} by id {}", result, id);
        Collections.sort(result.getTestSteps(),new SortByStepOrder());
//        logger.debug("AFTER SORT");
//        for (TestStep aStep: result.getTestSteps()){
//            logger.debug("Step order: "+aStep.getTestStepOrder());
//        }

        return new ModelAndView(this.getViewName()).addObject(result);
    }
}
