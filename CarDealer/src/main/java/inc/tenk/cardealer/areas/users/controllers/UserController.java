package inc.tenk.cardealer.areas.users.controllers;

import inc.tenk.cardealer.areas.products.cars.models.PublishCarDTO;
import inc.tenk.cardealer.areas.products.parts.models.PublishPartDTO;
import inc.tenk.cardealer.areas.users.models.UserDTO;
import inc.tenk.cardealer.areas.users.services.RoleServiceImpl;
import inc.tenk.cardealer.areas.users.services.UserServiceImpl;
import inc.tenk.cardealer.areas.users.models.UserEditDTO;
import inc.tenk.cardealer.areas.users.models.UserLoginDTO;
import inc.tenk.cardealer.areas.users.models.UserRegisterDTO;
import inc.tenk.cardealer.controllers.BaseController;
import inc.tenk.cardealer.utils.HTMLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController extends BaseController{
    private UserServiceImpl userService;
    private RoleServiceImpl roleService;


    @Autowired
    public UserController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PostMapping("/role/{id}")
    public ModelAndView role(@PathVariable Long id, @RequestParam("role") String role) {
        this.userService.updateRole(id,role);
        return this.redirect("/users");
    }
    @PreAuthorize(value = "isAnonymous()")
    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute UserRegisterDTO user, Model model, HttpServletRequest request) {
        model.addAttribute("user",user);
        return this.view("users/register");
    }
    @PostMapping("/register")
    @PreAuthorize(value = "isAnonymous()")
    public ModelAndView registerPost(@Valid @ModelAttribute("user") UserRegisterDTO user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("user",user);
            if(!user.getPassword().equals(user.getConfirmPassword())) {
                model.addAttribute("password_mismatch","Passwords mismatch!");
            }
            return this.view("users/register");
        }
        if(!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("user",user);
            model.addAttribute("password_mismatch","Passwords mismatch!");
            return this.view("users/register");
        }
        this.userService.register(user);
        return this.redirect("/login");
    }
    @PreAuthorize(value = "hasRole('USER')")
    @GetMapping("/cart")
    public ModelAndView cartProducts(Principal principal, Model model) {
        //TODO: get all parts and all cars from the cart
        String username = principal.getName();
        model.addAttribute("cars", this.userService.carsInCart(username));
        model.addAttribute("parts",this.userService.partsInCart(username));
        model.addAttribute("totalCartValue",this.userService.totalCartValue(username));
        return this.view("users/user/cart");
    }
    @PreAuthorize(value = "hasRole('USER')")
    @PostMapping("/cart/add/{id}")
    public ModelAndView cartProducts(@PathVariable Long id,@RequestParam("product") String productType,
                                     Principal principal) {
        this.userService.addToCart(id,productType,principal.getName());
        return this.redirect("/cart");
    }
    @PreAuthorize(value = "hasRole('USER')")
    @PostMapping("/cart/remove/{id}")
    public ModelAndView cartRemove(@PathVariable Long id,@RequestParam("product") String productType, Principal principal) {
        this.userService.removeFromCart(id,productType,principal.getName());
        return this.redirect("/cart");
    }
    @GetMapping("/login")
    @PreAuthorize(value = "isAnonymous()")
    public ModelAndView login(@ModelAttribute UserLoginDTO user, Model model) {
        model.addAttribute("user",user);
        model.addAttribute("htmlEncoder", new HTMLEncoder());
        return this.view("users/login");
    }
    @PreAuthorize(value = "isAnonymous()")
    @PostMapping("/login")
    public ModelAndView loginPost(@Valid @ModelAttribute("user") UserLoginDTO userDto,BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return this.view("users/login");
        }
        if(!userService.exist(userDto.getUsername())) {
            model.addAttribute("user",userDto);
            model.addAttribute("username_error","Username do not exist!");
            return this.view("users/login");
        }
        if(!userService.exist(userDto.getUsername(),userDto.getPassword())) {
            model.addAttribute("user",userDto);
            model.addAttribute("password_error","Password is incorrect!");
            return this.view("users/login");
        }
        return this.redirect("/login");
    }

    @GetMapping("/publish")
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','ADMIN','MODERATOR')")
    public ModelAndView addPart(Model model, HttpServletRequest request) {
        model.addAttribute("newPart",new PublishPartDTO());
        model.addAttribute("newCar",new PublishCarDTO());
        return this.view("users/admin/publish");
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','ADMIN','MODERATOR')")
    @GetMapping("/users")
    public ModelAndView users(Principal principal, Model model,HttpServletRequest request) {
        model.addAttribute("loggedInUser", this.userService.get(principal.getName()));
        model.addAttribute("users", this.userService.all());
        model.addAttribute("roles", this.roleService.all());
        model.addAttribute("userSuccess",request.getSession().getAttribute("userSuccess"));
        request.getSession().removeAttribute("userSuccess");
        return this.view("users/admin/users");
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping("/users/delete/{id}")
    public ModelAndView deleteUsers(@PathVariable Long id,HttpServletRequest request) {
        this.userService.delete(id);
        request.getSession().setAttribute("userSuccess","You have removed the user successfully!");
        return this.redirect("/users");
    }
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping("/edit/profile")
    public ModelAndView getProfile(Model model, Principal principal) {
        String username = principal.getName();
        UserDTO user = this.userService.get(username);
        model.addAttribute("user",user);

        return this.view("users/profile");
    }
    @PostMapping("/edit/profile")
    @PreAuthorize(value = "isAuthenticated()")
    public ModelAndView profilePost(@Valid @ModelAttribute("user") UserEditDTO user, BindingResult bindingResult, Model model) {
        if(!this.userService.exist(user.getUsername(),user.getCurrentPassword())) {
            model.addAttribute("incorrect_password", "This password do not match your current one!");
            return this.view("users/profile");
        }

        if (bindingResult.hasErrors() && (!user.getPassword().isEmpty() || !user.getConfirmPassword().isEmpty())) {
            model.addAttribute("user", user);
            if (!user.getPassword().equals(user.getConfirmPassword())) {
                model.addAttribute("password_mismatch", "Passwords mismatch!");
            }
            return this.view("users/profile");
        }
        if (!user.getPassword().equals(user.getConfirmPassword()) && (!user.getPassword().isEmpty() || !user.getConfirmPassword().isEmpty())) {
            model.addAttribute("user", user);
            model.addAttribute("password_mismatch", "Passwords mismatch!");
            return this.view("users/profile");
        }
        this.userService.edit(user);
        model.addAttribute("success","You have edited your account successfully!");
        return this.view("users/profile");
    }
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return this.redirect("/login?logout");
    }
}
