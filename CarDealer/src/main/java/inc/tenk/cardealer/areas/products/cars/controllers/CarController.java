package inc.tenk.cardealer.areas.products.cars.controllers;

import inc.tenk.cardealer.areas.products.cars.models.CarDTO;
import inc.tenk.cardealer.areas.products.cars.models.PublishCarDTO;
import inc.tenk.cardealer.areas.products.parts.models.PartDTO;
import inc.tenk.cardealer.areas.products.parts.models.PublishPartDTO;
import inc.tenk.cardealer.areas.products.cars.services.CarServiceImpl;
import inc.tenk.cardealer.controllers.BaseController;
import inc.tenk.cardealer.exceptions.EntityNotFoundException;
import inc.tenk.cardealer.exceptions.PageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','ADMIN','MODERATOR')")
@RequestMapping("/cars")
public class CarController extends BaseController{
    private CarServiceImpl carService;

    @Autowired
    public CarController(CarServiceImpl carService) {
        this.carService = carService;
    }

    @GetMapping("")
    private ModelAndView cars(Model model, @PageableDefault(size = 10) Pageable pageable) {
        return this.view("cars/cars");
    }
    @GetMapping("/publish")
    public String publishCar() {
        return "redirect:/publish";
    }
    @PostMapping("/publish")
    public ModelAndView publishCar(@Valid @ModelAttribute("newCar") PublishCarDTO carDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("newPart",new PublishPartDTO());
            model.addAttribute("newCar",carDTO);
            return this.view("users/admin/publish");
        }
        this.carService.publish(carDTO);
        return this.redirect("/");
    }
    @GetMapping("/delete/{id}")
    public ModelAndView deletePartById(@PathVariable Long id) {
        if(this.carService.get(id)==null) {
            throw new EntityNotFoundException();
        }
        this.carService.delete(id);
        return this.redirect("/");
    }
    @GetMapping("/edit/{id}")
    public ModelAndView editPartById(@PathVariable Long id, Model model) {
        if(this.carService.get(id)==null) {
            throw new EntityNotFoundException();
        }
        CarDTO car = this.carService.get(id);
        model.addAttribute("id",id);
        model.addAttribute("car",car);
        return this.view("cars/car-edit");
    }
    @PostMapping("/edit/{id}")
    public ModelAndView editPartById(@PathVariable Long id,@Valid @ModelAttribute("car") PublishCarDTO car,BindingResult result) {
        if(result.hasErrors()) {
            return this.view("cars/car-edit");
        }
        this.carService.edit(id,car);
        return this.redirect("/");
    }
    @GetMapping("/{id}")
    @PreAuthorize(value = "permitAll()")
    public ModelAndView getCar(@PathVariable Long id, Model model) {
        CarDTO carDTO = this.carService.get(id);
        if(carDTO==null) {
            throw new PageNotFoundException();
        }
        model.addAttribute("id",id);
        model.addAttribute("car",carDTO);
        return this.view("cars/publication");
    }
}
