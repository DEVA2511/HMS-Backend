package com.hms.pharmacy.Reposictory;

import com.hms.pharmacy.Modal.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    Boolean existsByPrescriptionId(Long PrescriptionId);
    Optional<Sale> findByPrescriptionId(Long PrescriptionId);
}
