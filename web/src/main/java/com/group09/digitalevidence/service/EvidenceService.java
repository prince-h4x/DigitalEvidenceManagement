package com.group09.digitalevidence.service;

import com.group09.digitalevidence.entity.Evidence;
import com.group09.digitalevidence.repository.EvidenceRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvidenceService {

    private final EvidenceRepository evidenceRepository;

    public EvidenceService(EvidenceRepository evidenceRepository) {
        this.evidenceRepository = evidenceRepository;
    }

    public Evidence saveEvidence(Evidence evidence) {
        return evidenceRepository.save(evidence);
    }

    public List<Evidence> getAllEvidence() {
        return evidenceRepository.findAll();
    }

    public Optional<Evidence> getEvidenceById(Long id) {
        return evidenceRepository.findById(id);
    }

    public void deleteEvidence(Long id) {
        evidenceRepository.deleteById(id);
    }

    public long getTotalEvidence() {
        return evidenceRepository.count();
    }

    public List<Evidence> searchEvidence(String keyword) {

        return evidenceRepository
                .findByNameContainingIgnoreCaseOrTypeContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword
                );
    }
}
