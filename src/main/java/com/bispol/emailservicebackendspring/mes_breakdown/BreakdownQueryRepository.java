package com.bispol.emailservicebackendspring.mes_breakdown;

import java.util.List;

interface BreakdownQueryRepository {
    List<Breakdown> findBy();

    List<Breakdown> findByBreakdownType(BreakdownType breakdownType);
}
