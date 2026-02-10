package com.hms.pharmacy.Reposictory;

import com.hms.pharmacy.Modal.Medicine;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    Optional<Medicine> findByNameIgnoreCaseAndDosageIgnoreCase(String name, String dosage);

    Optional<Integer> findStockById(Long id);
}
