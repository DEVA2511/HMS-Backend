package com.hms.pharmacy.Controller;

import com.hms.pharmacy.DTO.MedicineInventoryDTO;
import com.hms.pharmacy.Exception.HMSException;
import com.hms.pharmacy.Service.MedicineInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacy/inventory")
@RequiredArgsConstructor
@CrossOrigin
public class MedicineInventoryController {

    private final MedicineInventoryService medicineInventoryService;

    @PostMapping("/add")
    public ResponseEntity<MedicineInventoryDTO> addMedicineInventory(@RequestBody MedicineInventoryDTO medicineInventoryDTO) throws HMSException {
        return new ResponseEntity<>(medicineInventoryService.addMedicine(medicineInventoryDTO), HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<MedicineInventoryDTO> updateMedicineInventory(@RequestBody MedicineInventoryDTO medicineInventoryDTO) throws HMSException {
        return new ResponseEntity<>(medicineInventoryService.updateMedicine(medicineInventoryDTO), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MedicineInventoryDTO> getMedicineInventoryById(@PathVariable Long id) throws HMSException {
        return new ResponseEntity<>(medicineInventoryService.getMedicineById(id), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MedicineInventoryDTO>> getAllMedicineInventory() throws HMSException {
        return new ResponseEntity<>(medicineInventoryService.getAllMedicine(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMedicineInventory(@PathVariable Long id) throws HMSException {
        medicineInventoryService.deleteMedicine(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
