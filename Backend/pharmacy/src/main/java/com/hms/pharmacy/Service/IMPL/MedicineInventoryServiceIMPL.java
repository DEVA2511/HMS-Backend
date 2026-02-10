package com.hms.pharmacy.Service.IMPL;

import com.hms.pharmacy.DTO.MedicineInventoryDTO;
import com.hms.pharmacy.DTO.StockStatus;
import com.hms.pharmacy.Exception.HMSException;
import com.hms.pharmacy.Modal.MedicineInventory;
import com.hms.pharmacy.Reposictory.MedicineInventoryRepository;
import com.hms.pharmacy.Service.MedicineInventoryService;
import com.hms.pharmacy.Service.MedicineService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicineInventoryServiceIMPL implements MedicineInventoryService {

    private final MedicineInventoryRepository medicineInventoryRepository;
    private final MedicineService medicineService;

    @Override
    public List<MedicineInventoryDTO> getAllMedicine() throws HMSException {

        List<MedicineInventory> inventory = (List<MedicineInventory>) medicineInventoryRepository.findAll();
        return inventory.stream()
                .map(MedicineInventory::toDTO)
                .toList();
    }

    @Override
    public MedicineInventoryDTO getMedicineById(Long id) throws HMSException {
        return medicineInventoryRepository.findById(id)
                .orElseThrow(() -> new HMSException("MEDICINE_NOT_FOUND"))
                .toDTO();
    }

    @Override
    public MedicineInventoryDTO addMedicine(MedicineInventoryDTO medicine) throws HMSException {
        medicine.setAddedDate(LocalDate.now());
        medicineService.addStock(medicine.getMedicineId(), medicine.getQuantity());
        medicine.setInitialQuantity(medicine.getQuantity());
        medicine.setStockStatus(StockStatus.ACTIVE);
        return medicineInventoryRepository.save(medicine.toEntity()).toDTO();
    }

    @Override
    public MedicineInventoryDTO updateMedicine(MedicineInventoryDTO medicineInventory) throws HMSException {
        MedicineInventory existingMedicine = medicineInventoryRepository.findById(medicineInventory.getId())
                .orElseThrow(() -> new HMSException("INVENTORY_NOT_FOUND"));

        if (existingMedicine.getInitialQuantity() < medicineInventory.getQuantity()) {
            medicineService.addStock(medicineInventory.getMedicineId(), medicineInventory.getQuantity() - existingMedicine.getInitialQuantity());
        } else if (existingMedicine.getInitialQuantity() > medicineInventory.getQuantity()) {
            medicineService.removeStock(medicineInventory.getMedicineId(),
                    existingMedicine.getInitialQuantity() - medicineInventory.getQuantity());
        }
        existingMedicine.setQuantity(medicineInventory.getQuantity());
        existingMedicine.setExpiryDate(medicineInventory.getExpiryDate());
        existingMedicine.setBatchNumber(medicineInventory.getBatchNumber());
        existingMedicine.setInitialQuantity(medicineInventory.getQuantity());

        return medicineInventoryRepository.save(existingMedicine).toDTO();
    }

    @Override
    public void deleteMedicine(Long id) throws HMSException {
        medicineInventoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public String sellStock(Long medicineId, Integer quantity) throws HMSException {

        List<MedicineInventory> inventories =
                medicineInventoryRepository
                        .findByMedicineIdAndExpiryDateAfterAndQuantityGreaterThanAndStockStatusEqualsOrderByExpiryDateAsc(
                                medicineId, LocalDate.now(), 0,StockStatus.ACTIVE);

        if (inventories.isEmpty()) {
            throw new HMSException("OUT_OF_STOCK");
        }

        StringBuilder batchDetails = new StringBuilder();
        int remainingQuantity = quantity;

        for (MedicineInventory inventory : inventories) {

            if (remainingQuantity <= 0) break;

            int availableQuantity = inventory.getQuantity();

            if (availableQuantity <= remainingQuantity) {
                batchDetails.append(
                        String.format("Batch %s : %d units\n",
                                inventory.getBatchNumber(), availableQuantity)
                );

                remainingQuantity -= availableQuantity;
                inventory.setQuantity(0);
                inventory.setStockStatus(StockStatus.EXPIRED);


            } else {
                batchDetails.append(
                        String.format("Batch %s : %d units\n",
                                inventory.getBatchNumber(), remainingQuantity)
                );

                inventory.setQuantity(availableQuantity - remainingQuantity);
//                medicineService.removeStock(medicineId, remainingQuantity);
                remainingQuantity = 0;
            }

        }
        if(remainingQuantity >0){
            throw new HMSException("INSUFFCIENT_STOCK");
        }
                    medicineService.removeStock(medicineId, quantity);
            medicineInventoryRepository.saveAll(inventories);



        return batchDetails.toString();
    }


    private void markExpired(List<MedicineInventory> inventories) throws HMSException {

        for (MedicineInventory medicineInventory : inventories) {

            medicineInventory.setStockStatus(StockStatus.EXPIRED);

        }
        medicineInventoryRepository.saveAll(inventories);
    }

    @Override
    @Scheduled(cron = "0 00 10 * * ?") // server time this used for the it scheduled the take ate every day
    public void deleteExpiredMedicines() throws HMSException {
        System.out.printf("Running expired medicine deletion task at %s%n", LocalDate.now());
        List<MedicineInventory> expiredMedicines = medicineInventoryRepository.findByExpiryDateBefore(LocalDate.now());
        for (MedicineInventory inventory : expiredMedicines) {
            medicineService.removeStock(inventory.getMedicine().getId(), inventory.getQuantity());

        }

        this.markExpired(expiredMedicines);
    }

    @Scheduled(cron = "0 00 10 * * ?")
    public void printMessage() {
        System.out.printf("Scheduled task executed at %s%n", LocalDate.now());
    }
}

//problem is deletion of expired medicines from inventory is not reducing stock in medicine table
//you need to override delete method to reduce stock before deletion of inventory record