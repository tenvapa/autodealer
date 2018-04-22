package inc.tenk.cardealer.areas.sales.controllers;

import inc.tenk.cardealer.areas.products.cars.models.CarDTO;
import inc.tenk.cardealer.areas.sales.services.SaleService;
import inc.tenk.cardealer.areas.sales.services.SaleServiceImpl;
import inc.tenk.cardealer.areas.sales.models.SaleReviewDTO;
import inc.tenk.cardealer.areas.products.cars.services.CarServiceImpl;
import inc.tenk.cardealer.areas.users.models.UserDTO;
import inc.tenk.cardealer.areas.users.services.UserService;
import inc.tenk.cardealer.areas.users.services.UserServiceImpl;
import inc.tenk.cardealer.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','ADMIN','MODERATOR','USER')")
@RequestMapping("/sales")
public class SaleController extends BaseController {
    private SaleService saleService;
    private UserService userService;

    @Autowired
    public SaleController(SaleService saleService, UserService userService) {
        this.saleService = saleService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView addSale(Model model, Principal principal) {
        model.addAttribute("allSales", this.saleService.all());
        model.addAttribute("userSales", this.saleService.salesByUser(principal.getName()));
        return this.view("sales/sales");
    }

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('USER')")
    public ModelAndView addSale(Principal principal) {
        UserDTO userDTO = this.userService.get(principal.getName());
        this.saleService.finalizeSales(userDTO.getCart(),userDTO);
        return this.redirect("/sales");
    }
}
