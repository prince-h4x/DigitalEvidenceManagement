package com.group09.digitalevidence.repository;

import com.group09.digitalevidence.entity.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvidenceRepository extends JpaRepository<Evidence, Long> {

    long countByType(String type);

    List<Evidence> findByNameContainingIgnoreCaseOrTypeContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String name,
            String type,
            String description
    );
}
