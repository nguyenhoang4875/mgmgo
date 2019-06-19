package com.mgmtp.internship.experiences.controllers.app;

import com.mgmtp.internship.experiences.dto.UserDTO;
import com.mgmtp.internship.experiences.exceptions.MD5Exception;
import com.mgmtp.internship.experiences.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Login controller.
 *
 * @author ntynguyen
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String login(Model model, HttpSession httpSession) {
        if (!userService.checkLogin(httpSession)) {
            model.addAttribute("user", new UserDTO());
            return "login";
        }
        return "redirect:/";
    }

    @PostMapping()
    public String login(@ModelAttribute("user") UserDTO userDTO, RedirectAttributes redirectAttributes, HttpSession session, BindingResult bindingResult) throws MD5Exception {
        if (bindingResult.hasErrors()) {
            return "redirect:/login";
        }
        if (userService.validateUser(userDTO.getUserName(), userDTO.getPassword())) {
            session.setAttribute("user", userDTO);
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("incorrect", true);
        return "redirect:/login";
    }
}
