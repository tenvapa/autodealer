package inc.tenk.cardealer.controllers;

import inc.tenk.cardealer.areas.products.cars.services.CarServiceImpl;
import inc.tenk.cardealer.areas.products.parts.services.PartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView index(Model model, @PageableDefault(size = 16) Pageable pageable) {
        model.addAttribute("cars",this.carService.listAllByPage(pageable));
        model.addAttribute("parts", this.partService.listAllByPage(pageable));
        return this.view("index");
    }
    @PreAuthorize(value = "permitAll()")
    @GetMapping("/search")
    public ModelAndView search(Model model, @RequestParam("search") String searchValue, @PageableDefault(size = 16) Pageable pageable) {
        model.addAttribute("cars",this.carService.search(pageable,searchValue));
        model.addAttribute("parts", this.partService.search(pageable,searchValue));
        return this.view("index");
    }
}
