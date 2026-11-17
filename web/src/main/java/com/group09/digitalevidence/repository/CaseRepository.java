package com.group09.digitalevidence.repository;

import com.group09.digitalevidence.entity.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {

    long countByStatus(String status);

}
