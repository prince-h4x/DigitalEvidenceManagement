package com.group09.digitalevidence.controller;

import com.group09.digitalevidence.entity.Case;
import com.group09.digitalevidence.service.CaseService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class CaseController {

    private final CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @GetMapping("/cases")
    public String showCases(Model model) {

        model.addAttribute("cases", caseService.getAllCases());
        model.addAttribute("newCase", new Case());

        return "cases";
    }

    @PostMapping("/cases")
    public String registerCase(
            @ModelAttribute("newCase") Case newCase,
            RedirectAttributes redirectAttributes) {

        newCase.setRegistrationDate(LocalDate.now());

        if (newCase.getStatus() == null ||
                newCase.getStatus().isBlank()) {
            newCase.setStatus("Open");
        }

        caseService.saveCase(newCase);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Case registered successfully!"
        );

        return "redirect:/cases";
    }

    @PostMapping("/cases/delete/{id}")
    public String deleteCase(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        caseService.deleteCase(id);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Case deleted successfully!"
        );

        return "redirect:/cases";
    }
}
