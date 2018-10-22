package org.superbiz.struts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam long id,
                          @RequestParam String firstName,
                          @RequestParam String lastName) {
        userRepository.save(new User(id,firstName,lastName));
        return "addedUser";
    }

    @GetMapping("/addUser")
    public String addUserForm() {
        return "addUserForm";
    }

    @GetMapping("/findUser")
    public String findUserForm() {
        return "findUserForm";
    }

    @PostMapping("/findUser")
    public String findUser(@RequestParam long id, Model model) {
        User user = userRepository.findOne(id);

        if (user == null) {
            model.addAttribute("errorMessage", "User not found.");
            return "findUserForm";
        }

        model.addAttribute("user", user);
        return "displayUser";
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
//        model.addAttribute("users", userRepository.findAll())
        model.addAttribute("users", userRepository.findAll());
        return "displayUsers";
    }
}
