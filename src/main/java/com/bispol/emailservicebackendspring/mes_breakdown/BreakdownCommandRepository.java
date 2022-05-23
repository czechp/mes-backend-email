package com.bispol.emailservicebackendspring.mes_breakdown;

import java.util.List;

interface BreakdownCommandRepository {
    void save(Breakdown breakdown);

    void deleteById(long id);

    boolean existsById(long id);

}
