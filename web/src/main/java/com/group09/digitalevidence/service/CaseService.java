package com.group09.digitalevidence.service;

import com.group09.digitalevidence.entity.Case;
import com.group09.digitalevidence.repository.CaseRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaseService {

    private final CaseRepository caseRepository;

    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public Case saveCase(Case newCase) {
        return caseRepository.save(newCase);
    }

    public List<Case> getAllCases() {
        return caseRepository.findAll();
    }

    public Optional<Case> getCaseById(Long id) {
        return caseRepository.findById(id);
    }

    public void deleteCase(Long id) {
        caseRepository.deleteById(id);
    }

    public long getTotalCases() {
        return caseRepository.count();
    }

    public long getOpenCases() {
        return caseRepository.countByStatus("Open");
    }
}
