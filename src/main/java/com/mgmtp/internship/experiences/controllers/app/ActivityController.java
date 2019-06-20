package com.mgmtp.internship.experiences.controllers.app;

import com.mgmtp.internship.experiences.dto.ActivityDetailDTO;
import com.mgmtp.internship.experiences.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/{activityId}")
    public String getActivity(Model model, @PathVariable("activityId") long activityId) {
        ActivityDetailDTO activityDetailDTO = activityService.findById(activityId);
        if (activityDetailDTO != null) {
            model.addAttribute("activityInfo", activityDetailDTO);
            return "activity/detail";
        }

        return "error";
    }
}
