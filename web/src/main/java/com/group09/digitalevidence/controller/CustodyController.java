package com.group09.digitalevidence.controller;

import com.group09.digitalevidence.entity.CustodyEntry;
import com.group09.digitalevidence.service.CustodyService;
import com.group09.digitalevidence.service.EvidenceService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class CustodyController {

    private final CustodyService custodyService;
    private final EvidenceService evidenceService;

    public CustodyController(
            CustodyService custodyService,
            EvidenceService evidenceService) {
        this.custodyService = custodyService;
        this.evidenceService = evidenceService;
    }

    @GetMapping("/custody")
    public String showCustody(Model model) {

        model.addAttribute("custodyEntries",
                custodyService.getAllCustodyEntries());

        model.addAttribute("newEntry", new CustodyEntry());

        model.addAttribute("evidenceList",
                evidenceService.getAllEvidence());

        return "custody";
    }

    @PostMapping("/custody")
    public String addCustodyEntry(
            @ModelAttribute("newEntry") CustodyEntry entry,
            @RequestParam("evidenceId") Long evidenceId,
            RedirectAttributes redirectAttributes) {

        var evidence = evidenceService.getEvidenceById(evidenceId);

        if (evidence.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "Selected evidence was not found."
            );
            return "redirect:/custody";
        }

        entry.setEvidence(evidence.get());
        entry.setTransferDate(LocalDateTime.now());

        custodyService.saveCustodyEntry(entry);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Custody record added successfully!"
        );

        return "redirect:/custody";
    }

    @PostMapping("/custody/delete/{id}")
    public String deleteCustodyEntry(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        custodyService.deleteCustodyEntry(id);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Custody record deleted successfully!"
        );

        return "redirect:/custody";
    }
}
