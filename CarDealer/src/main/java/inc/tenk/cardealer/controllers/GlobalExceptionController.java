package inc.tenk.cardealer.controllers;

import inc.tenk.cardealer.exceptions.EntityNotFoundException;
import inc.tenk.cardealer.exceptions.PageNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController extends BaseController{
    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView getException(){
        return this.view("exceptions/notFound").addObject("failureMessage",EntityNotFoundException.class.getAnnotation(ResponseStatus.class).reason());
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView unauthorized(){
        return this.view("error/403");
    }
    @ExceptionHandler(PageNotFoundException.class)
    public ModelAndView pageNotFound(){
        return this.view("error/404");
    }
}
