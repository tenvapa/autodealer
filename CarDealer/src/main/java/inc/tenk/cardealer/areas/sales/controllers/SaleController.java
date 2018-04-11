package inc.tenk.cardealer.areas.sales.controllers;

import inc.tenk.cardealer.areas.cars.entities.Car;
import inc.tenk.cardealer.areas.cars.models.CarDTO;
import inc.tenk.cardealer.areas.cars.models.PublishCarDTO;
import inc.tenk.cardealer.areas.sales.services.SaleServiceImpl;
import inc.tenk.cardealer.areas.sales.models.SaleReviewDTO;
import inc.tenk.cardealer.areas.cars.services.CarServiceImpl;
import inc.tenk.cardealer.areas.users.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/sales")
public class SaleController {
    private SaleServiceImpl saleService;
    private UserServiceImpl userService;
    private CarServiceImpl carService;
    private SaleReviewDTO saleReviewDto;
    @Autowired
    public SaleController(SaleServiceImpl saleService, UserServiceImpl userService, CarServiceImpl carService) {
        this.saleService = saleService;
        this.userService = userService;
        this.carService = carService;
    }
    private boolean isLoggedIn(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        return username!=null;
    }
    @GetMapping
    @PreAuthorize(value = "isAuthenticated()")
    public String allSales(Model model) {
        //model.addAttribute("sales", this.saleService.getAllSalesInfo());
        return "sales/sales";
    }
    @GetMapping("/{id}")
    public String saleId(@PathVariable Long id, Model model) {
        //model.addAttribute("sales", this.saleService.getSaleById(id));
        return "sales/sales";
    }
    @GetMapping("/discounted")
    public String discountedSales(Model model, HttpServletRequest request) {
        //model.addAttribute("sales", this.saleService.getDiscountedSales());
        return "sales/sales";
    }
    @GetMapping("/discounted/{percent}")
    public String saleByPercent(@PathVariable int percent, Model model) {
        //model.addAttribute("sales", this.saleService.getDiscountedSalesByPercent(percent));
        return "sales/sales";
    }
    @GetMapping("/add")
    public String addSale(Model model, HttpServletRequest request) {

        if(!this.isLoggedIn(request)) return "redirect:/login";
        List<CarDTO> cars = this.carService.all();
        model.addAttribute("customers", this.userService.all());
        model.addAttribute("cars", cars);
        return "sales/sale-add";
    }


//    @PostMapping("/add")
//    public String addSale(@RequestParam("customers") String customerName, @RequestParam("cars") String car,
//                          @RequestParam("discount") String discount, Model model,HttpServletRequest request) {
//        if(!this.isLoggedIn(request)) return "redirect:/login";
//        User user = this.userService.get(customerName);
//        String carMake = car.split(":")[0];
//        String carModel = car.split(":")[1];
//        WholeCar carEntity = this.carService.getCarByMakeAndModel(carMake, carModel);
//        int finalDiscount=Integer.parseInt(discount);
//        double carPrice= this.carService.getCarPrice(carEntity.getId());
//        double finalCarPrice = carPrice-((carPrice*finalDiscount)/100);
//        this.saleReviewDto=new SaleReviewDTO();
//        this.saleReviewDto.setCar(carEntity);
//        this.saleReviewDto.setCustomer(user);
//        this.saleReviewDto.setDiscount(finalDiscount);
//        this.saleReviewDto.setCarPrice(carPrice);
//        this.saleReviewDto.setFinalCarPrice(finalCarPrice);
//        model.addAttribute("saleReview", saleReviewDto);
//        return "sales/sale-review";
//    }
    @GetMapping("/review")
    public String authorizeReviewSale() {
        return "redirect:/sales";
    }
    @PostMapping("/review")
    public String finalizeSale( HttpServletRequest request) {
        if(!this.isLoggedIn(request)) return "redirect:/login";
        if(this.saleReviewDto!=null) {
          //  this.saleService.addSale(saleReviewDto);
        }
        return "redirect:/sales";
    }
}
