
package com.onecloud.tcmgmt.web;

import com.onecloud.tcmgmt.dao.*;
import com.onecloud.tcmgmt.domain.appdb.Defect;
import com.onecloud.tcmgmt.domain.appdb.TestCase;
import com.onecloud.tcmgmt.semantic.constants.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/defects.html")
public class DefectListController extends BaseFormController{

    private static final Logger logger =
            LoggerFactory.getLogger(DefectListController.class);

    private DefectDao defectDao;
    private ComponentDao componentDao;
    private DefectStatusDao defectStatusDao;
    private PriorityDao priorityDao;
    private ProductDao productDao;
    private SeverityDao severityDao;

    @Autowired
    public DefectListController(DefectDao defectDao, ComponentDao componentDao,
                                DefectStatusDao defectStatusDao, PriorityDao priorityDao,
                                ProductDao productDao, SeverityDao severityDao) {
        this.defectDao = defectDao;
        this.componentDao = componentDao;
        this.defectStatusDao = defectStatusDao;
        this.priorityDao = priorityDao;
        this.productDao = productDao;
        this.severityDao = severityDao;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView displayList(@RequestParam(value = "navPage", required = false) String navPage,
            HttpServletRequest request, HttpServletResponse response) {

        logger.debug("INSIDE THE PAGINATION METHOD");

        PagedListHolder<Defect> defectList;
        List<Defect> defects=null;
        if(navPage == null) {
            defectList = new PagedListHolder<Defect>();
            defects = defectDao.getAll();
            // Setting the source for PagedListHolder
            defectList.setSource(defects);
            defectList.setPageSize(ApplicationConstants.UI_PAGINATION_PAGE_SIZE);
            // Setting PagedListHolder instance to session
            request.getSession().setAttribute("defectList", defectList);
        }else if(navPage.equals("prev")) {
            // get the user list from session
            defectList = (PagedListHolder<Defect>)request.getSession().getAttribute("defectList");
            // switch to previous page
            defectList.previousPage();
        }else if(navPage.equals("next")) {
            defectList = (PagedListHolder<Defect>)request.getSession().getAttribute("defectList");
            // switch to next page
            defectList.nextPage();
        }else {
            int pageNum = Integer.parseInt(navPage);
            defectList = (PagedListHolder<Defect>)request.getSession().getAttribute("defectList");
            // set the current page number
            // page number starts from zero in PagedListHolder that's why subtracting 1
            defectList.setPage(pageNum - 1);
        }

        return new ModelAndView("defectList")
                .addObject("statusMap", getDefectStatusMap(this.defectStatusDao))
                .addObject("severityMap", getSeverityMap(this.severityDao))
                .addObject("productMap", getProductMap(this.productDao))
                .addObject("priorityMap", getPriorityMap(this.priorityDao))
                .addObject("componentMap", getComponentMap(this.componentDao));
    }
}
