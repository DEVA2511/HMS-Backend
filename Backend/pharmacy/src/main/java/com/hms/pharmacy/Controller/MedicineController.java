package com.hms.pharmacy.Controller;

import com.hms.pharmacy.DTO.MedicineDTO;
import com.hms.pharmacy.DTO.ResponseDTO;
import com.hms.pharmacy.Exception.HMSException;
import com.hms.pharmacy.Service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacy/medicines")
@RequiredArgsConstructor
@CrossOrigin
public class MedicineController {

    private final MedicineService medicineService;

    @PostMapping("/add")
    public ResponseEntity<Long> addMedicine(@RequestBody MedicineDTO medicineDto) throws HMSException {
        return new ResponseEntity<>(medicineService.addMedicine(medicineDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MedicineDTO> getMedicine(@PathVariable Long id) throws HMSException {
        return new ResponseEntity<>(medicineService.getMedicineById(id), HttpStatus.OK);

    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateMedicine(@RequestBody MedicineDTO medicineDTO) throws HMSException {
        medicineService.updateMedicine(medicineDTO);
        return new ResponseEntity<>(new ResponseDTO("Medicine updated"), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MedicineDTO>> getAllMedicines() throws HMSException {
        return new ResponseEntity<>(medicineService.getAllMedicines(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long id) throws HMSException {
        medicineService.deleteMedicine(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
