package com.group09.digitalevidence.service;

import com.group09.digitalevidence.entity.CustodyEntry;
import com.group09.digitalevidence.repository.CustodyRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustodyService {

    private final CustodyRepository custodyRepository;

    public CustodyService(CustodyRepository custodyRepository) {
        this.custodyRepository = custodyRepository;
    }

    public CustodyEntry saveCustodyEntry(CustodyEntry entry) {
        return custodyRepository.save(entry);
    }

    public List<CustodyEntry> getAllCustodyEntries() {
        return custodyRepository.findAll();
    }

    public List<CustodyEntry> getCustodyByEvidenceId(Long evidenceId) {
        return custodyRepository.findByEvidence_Id(evidenceId);
    }

    public void deleteCustodyEntry(Long id) {
        custodyRepository.deleteById(id);
    }

    public long getTotalCustodyEntries() {
        return custodyRepository.count();
    }
}
