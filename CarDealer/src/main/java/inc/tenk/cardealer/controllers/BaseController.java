package inc.tenk.cardealer.controllers;

import org.springframework.web.servlet.ModelAndView;

public class BaseController {
    protected ModelAndView view(String templateName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(templateName);
        return modelAndView;
    }
    protected ModelAndView redirect(String route) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:"+route);
        return modelAndView;
    }
}
