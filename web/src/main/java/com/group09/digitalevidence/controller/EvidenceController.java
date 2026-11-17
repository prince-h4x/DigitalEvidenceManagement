package com.group09.digitalevidence.controller;

import com.group09.digitalevidence.entity.Evidence;
import com.group09.digitalevidence.service.EvidenceService;
import com.group09.digitalevidence.service.CaseService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class EvidenceController {

    private final EvidenceService evidenceService;
    private final CaseService caseService;

    public EvidenceController(
            EvidenceService evidenceService,
            CaseService caseService) {
        this.evidenceService = evidenceService;
        this.caseService = caseService;
    }

    @GetMapping("/evidence")
    public String showEvidence(Model model) {

        model.addAttribute("evidenceList",
                evidenceService.getAllEvidence());

        model.addAttribute("newEvidence", new Evidence());

        model.addAttribute("cases",
                caseService.getAllCases());

        return "evidence";
    }

    @PostMapping("/evidence")
    public String addEvidence(
            @ModelAttribute("newEvidence") Evidence evidence,
            @RequestParam("caseId") Long caseId,
            RedirectAttributes redirectAttributes) {

        var linkedCase = caseService.getCaseById(caseId);

        if (linkedCase.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Selected case was not found."
            );
            return "redirect:/evidence";
        }

        evidence.setLinkedCase(linkedCase.get());
        evidence.setSubmissionDate(LocalDate.now());

        evidenceService.saveEvidence(evidence);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Evidence added successfully!"
        );

        return "redirect:/evidence";
    }

    @PostMapping("/evidence/delete/{id}")
    public String deleteEvidence(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        evidenceService.deleteEvidence(id);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Evidence deleted successfully!"
        );

        return "redirect:/evidence";
    }
}
