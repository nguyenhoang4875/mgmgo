package com.mgmtp.internship.experiences.controllers.app;

import com.mgmtp.internship.experiences.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping()
    public String home(Model model) {
        model.addAttribute("placeForm", placeService.findById(1));

        return "index";
    }
}
