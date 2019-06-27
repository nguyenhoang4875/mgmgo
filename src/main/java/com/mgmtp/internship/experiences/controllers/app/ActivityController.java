package com.mgmtp.internship.experiences.controllers.app;

import com.mgmtp.internship.experiences.dto.ActivityDetailDTO;
import com.mgmtp.internship.experiences.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Activity Controller
 *
 * @author: vhduong
 */

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

    @GetMapping("/update/{activityId}")
    public String getUpdateActivity(Model model, @PathVariable("activityId") long activityId) {
        if (!model.containsAttribute("activityInfo")) {
            ActivityDetailDTO activityDetailDTO = activityService.findById(activityId);
            if (activityDetailDTO == null) {
                return "error";
            }
            model.addAttribute("activityInfo", activityDetailDTO);
        }
        return "activity/update";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/update/{activityId}")
    public String updateActivity(@ModelAttribute("activityInfo") @Valid ActivityDetailDTO activityDetailDTO,
                                 final BindingResult bindingResult,
                                 @PathVariable("activityId") long activityId,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult.getFieldError().getDefaultMessage());
            redirectAttributes.addFlashAttribute("activityInfo", activityDetailDTO);
            return "redirect:/activity/update/{activityId}";
        }

        int rowUpdate = 0;
        if (activityService.updateActivity(activityId, activityDetailDTO.getName(), activityDetailDTO.getDescription()) == rowUpdate) {
            redirectAttributes.addFlashAttribute("error", "Can't update Activity. Try again!");
            redirectAttributes.addFlashAttribute("activityInfo", activityDetailDTO);
            return "redirect:/activity/update/{activityId}";
        } else {
            return "redirect:/activity/{activityId}";
        }
    }
}
