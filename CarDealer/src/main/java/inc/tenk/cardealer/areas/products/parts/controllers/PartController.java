package inc.tenk.cardealer.areas.parts.controllers;

import inc.tenk.cardealer.areas.cars.models.PublishCarDTO;
import inc.tenk.cardealer.areas.parts.models.PublishPartDTO;
import inc.tenk.cardealer.controllers.BaseController;
import inc.tenk.cardealer.exceptions.EntityNotFoundException;
import inc.tenk.cardealer.areas.parts.services.PartServiceImpl;
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
@RequestMapping("/parts")
public class PartController extends BaseController{
    private PartServiceImpl partService;


    @Autowired
    public PartController(PartServiceImpl partService) {
        this.partService = partService;
    }

    @GetMapping
    private ModelAndView cars(Model model, @PageableDefault(size = 10) Pageable pageable) {
        return this.view("parts/parts");
    }

//    @GetMapping("/all")
//    public ModelAndView allParts(Model model) {
//        model.addAttribute("parts", this.partService.all());
//        return this.view("parts/parts");
//    }
//    @GetMapping("/publish")
//    public String publishCar() {
//        return "redirect:/publish";
//    }

    @PostMapping("/publish")
    public ModelAndView publishPart(@Valid @ModelAttribute("newPart") PublishPartDTO part,BindingResult bindingResult,
                          Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("newPart",part);
            model.addAttribute("newCar",new PublishCarDTO());
            return this.view("users/admin/publish");
        }
        this.partService.publish(part);
        return this.redirect("/");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deletePartById(@PathVariable Long id) {
        if(this.partService.get(id)==null) {
            throw new EntityNotFoundException();
        }
        this.partService.delete(id);
        return this.redirect("/");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPartById(@PathVariable Long id, Model model)  {
        if(this.partService.get(id)==null) {
            throw new EntityNotFoundException();
        }
        PublishPartDTO part = this.partService.get(id);

        model.addAttribute("id",id);
        model.addAttribute("part",part);
        return this.view("parts/part-edit");
    }
    @PostMapping("/edit/{id}")
    public ModelAndView editPartById(@PathVariable Long id,@Valid @ModelAttribute("part") PublishPartDTO part, BindingResult result) {
        if(result.hasErrors()) {
            return this.view("parts/part-edit");
        }
        this.partService.edit(id,part);
        return this.redirect("/");
    }
    @GetMapping(value = "/{id}")
    @PreAuthorize(value = "permitAll()")
    public ModelAndView getCar(@PathVariable Long id) {
        PublishPartDTO publishPartDTO = this.partService.get(id);
        if(publishPartDTO==null) {
            throw new PageNotFoundException();
        }
        return this.view("parts/publication");
    }
}
