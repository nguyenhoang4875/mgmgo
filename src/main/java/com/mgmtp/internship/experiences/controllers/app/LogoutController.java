package com.mgmtp.internship.experiences.controllers.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Logout controller.
 *
 * @author ntynguyen
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {
    @GetMapping()
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }
}