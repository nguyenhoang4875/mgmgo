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

    private static final String ACTIVITY_INFO_ATTRIBUTE = "activityInfo";
    private static final String ERROR_VIEW = "error";

    @Autowired
    private ActivityService activityService;

    @GetMapping("/{activityId}")
    public String getActivity(Model model, @PathVariable("activityId") long activityId) {
        ActivityDetailDTO activityDetailDTO = activityService.findById(activityId);
        if (activityDetailDTO != null) {
            model.addAttribute(ACTIVITY_INFO_ATTRIBUTE, activityDetailDTO);
            return "activity/detail";
        }
        return ERROR_VIEW;
    }

    @GetMapping("/update/{activityId}")
    public String getUpdateActivity(Model model, @PathVariable("activityId") long activityId) {
        if (!model.containsAttribute(ACTIVITY_INFO_ATTRIBUTE)) {
            ActivityDetailDTO activityDetailDTO = activityService.findById(activityId);
            if (activityDetailDTO == null) {
                return ERROR_VIEW;
            }
            model.addAttribute(ACTIVITY_INFO_ATTRIBUTE, activityDetailDTO);
        }
        return "activity/update";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/update/{activityId}")
    public String updateActivity(@ModelAttribute(ACTIVITY_INFO_ATTRIBUTE) @Valid ActivityDetailDTO activityDetailDTO,
                                 final BindingResult bindingResult,
                                 @PathVariable("activityId") long activityId,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(ERROR_VIEW, bindingResult.getFieldError().getDefaultMessage());
            redirectAttributes.addFlashAttribute(ACTIVITY_INFO_ATTRIBUTE, activityDetailDTO);
            return "redirect:/activity/update/{activityId}";
        }

        int rowUpdate = 0;
        if (activityService.updateActivity(activityId, activityDetailDTO.getName(), activityDetailDTO.getDescription()) == rowUpdate) {
            redirectAttributes.addFlashAttribute(ERROR_VIEW, "Can't update Activity. Try again!");
            redirectAttributes.addFlashAttribute(ACTIVITY_INFO_ATTRIBUTE, activityDetailDTO);
            return "redirect:/activity/update/{activityId}";
        } else {
            return "redirect:/activity/{activityId}";
        }
    }
}
