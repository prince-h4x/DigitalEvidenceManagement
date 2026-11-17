package com.group09.digitalevidence.repository;

import com.group09.digitalevidence.entity.CustodyEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

@Repository
public interface CustodyRepository
        extends JpaRepository<CustodyEntry, Long> {

    @Override
    @EntityGraph(attributePaths = "evidence")
    List<CustodyEntry> findAll();

    @EntityGraph(attributePaths = "evidence")
    List<CustodyEntry> findByEvidence_Id(Long evidenceId);

    long countByEvidence_Id(Long evidenceId);
}
