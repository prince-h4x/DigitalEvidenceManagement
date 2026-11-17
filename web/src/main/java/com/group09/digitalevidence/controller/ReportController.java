package com.group09.digitalevidence.controller;

import com.group09.digitalevidence.service.CaseService;
import com.group09.digitalevidence.service.EvidenceService;
import com.group09.digitalevidence.service.CustodyService;

import com.group09.digitalevidence.entity.Case;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReportController {

    private final CaseService caseService;
    private final EvidenceService evidenceService;
    private final CustodyService custodyService;

    public ReportController(
            CaseService caseService,
            EvidenceService evidenceService,
            CustodyService custodyService) {

        this.caseService = caseService;
        this.evidenceService = evidenceService;
        this.custodyService = custodyService;
    }

    @GetMapping("/reports")
    public String showReports(Model model) {

        List<Case> cases = caseService.getAllCases();

        model.addAttribute("totalCases", cases.size());

        model.addAttribute("openCases",
                cases.stream()
                        .filter(c -> "Open".equalsIgnoreCase(c.getStatus()))
                        .count());

        model.addAttribute("investigationCases",
                cases.stream()
                        .filter(c -> "Under Investigation".equalsIgnoreCase(c.getStatus()))
                        .count());

        model.addAttribute("closedCases",
                cases.stream()
                        .filter(c -> "Closed".equalsIgnoreCase(c.getStatus()))
                        .count());

        model.addAttribute("totalEvidence",
                evidenceService.getTotalEvidence());

        model.addAttribute("totalCustody",
                custodyService.getTotalCustodyEntries());

        model.addAttribute("cases", cases);

        model.addAttribute("evidenceList",
                evidenceService.getAllEvidence());

        model.addAttribute("custodyEntries",
                custodyService.getAllCustodyEntries());

        return "reports";
    }
}
