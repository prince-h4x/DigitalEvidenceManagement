package com.group09.digitalevidence.controller;

import com.group09.digitalevidence.service.EvidenceService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    private final EvidenceService evidenceService;

    public SearchController(EvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }

    @GetMapping("/search")
    public String searchEvidence(
            @RequestParam(required = false, defaultValue = "") String keyword,
            Model model) {

        model.addAttribute("keyword", keyword);

        if (!keyword.isBlank()) {
            model.addAttribute(
                    "searchResults",
                    evidenceService.searchEvidence(keyword.trim())
            );
        }

        return "search";
    }
}
