package com.bispol.emailservicebackendspring.mes_breakdown;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface BreakdownRepository extends JpaRepository<Breakdown, Long> {
    List<Breakdown> findByBreakdownType(BreakdownType breakdownType);
}
