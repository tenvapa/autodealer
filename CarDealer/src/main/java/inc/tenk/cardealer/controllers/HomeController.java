package inc.tenk.cardealer.controllers;

import inc.tenk.cardealer.areas.cars.services.CarServiceImpl;
import inc.tenk.cardealer.areas.parts.services.PartServiceImpl;
import inc.tenk.cardealer.exceptions.PageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@Controller

public class HomeController extends BaseController{
    private CarServiceImpl carService;
    private PartServiceImpl partService;

    @Autowired
    public HomeController(CarServiceImpl carService, PartServiceImpl partService) {
        this.carService = carService;
        this.partService = partService;
    }

    @PreAuthorize(value = "permitAll()")
    @GetMapping("/")
    public ModelAndView index(Model model) {
        model.addAttribute("cars",this.carService.all());
        model.addAttribute("parts", this.partService.all());
        return this.view("index");
    }
}
