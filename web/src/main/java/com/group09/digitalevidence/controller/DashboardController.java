package com.group09.digitalevidence.controller;

import com.group09.digitalevidence.service.CaseService;
import com.group09.digitalevidence.service.EvidenceService;
import com.group09.digitalevidence.service.CustodyService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final CaseService caseService;
    private final EvidenceService evidenceService;
    private final CustodyService custodyService;

    public DashboardController(
            CaseService caseService,
            EvidenceService evidenceService,
            CustodyService custodyService) {

        this.caseService = caseService;
        this.evidenceService = evidenceService;
        this.custodyService = custodyService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {

        model.addAttribute("totalCases",
                caseService.getTotalCases());

        model.addAttribute("openCases",
                caseService.getOpenCases());

        model.addAttribute("totalEvidence",
                evidenceService.getTotalEvidence());

        model.addAttribute("totalCustody",
                custodyService.getTotalCustodyEntries());

        model.addAttribute("recentCases",
                caseService.getAllCases());

        return "dashboard";
    }
}
