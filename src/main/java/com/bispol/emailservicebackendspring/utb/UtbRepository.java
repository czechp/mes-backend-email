package com.bispol.emailservicebackendspring.utb;

import com.bispol.emailservicebackendspring.utb.dto.UtbDtoQueryInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository()
interface UtbRepository extends org.springframework.data.repository.Repository<Utb, Long> {
    void save(Utb utb);

    Optional<Utb> findById(long id);

    @Query("SELECT u from Utb u ")
    List<UtbDtoQueryInfo> findAll();

    void delete(Utb utb);

    List<Utb> findBySystem(UtbSystem system);

    boolean existsByEmailAndSystem(String email, UtbSystem system);
}
