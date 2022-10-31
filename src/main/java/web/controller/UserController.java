package web.controller;
import web.models.User;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAll(ModelMap m) {
        m.addAttribute("users", userService.users());
        return "user/index";
    }


    @GetMapping("/new")
    public String newUser(ModelMap m) {
        m.addAttribute("user", new User());
        return "user/new";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap m,  @PathVariable("id") int id ) {
        m.addAttribute("user", userService.showUser(id));
        return "user/edit";

    }

    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/";
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.remove(id);
        return "redirect:/";
    }


}
