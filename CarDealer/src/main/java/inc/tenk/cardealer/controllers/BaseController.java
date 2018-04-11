package inc.tenk.cardealer.controllers;

import org.springframework.web.servlet.ModelAndView;

public class BaseController {
    public ModelAndView view(String templateName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(templateName);
        return modelAndView;
    }
    public ModelAndView redirect(String route) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:"+route);
        return modelAndView;
    }
}
