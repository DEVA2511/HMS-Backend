package com.hms.pharmacy.Controller;

import com.hms.pharmacy.DTO.ResponseDTO;
import com.hms.pharmacy.DTO.SaleDTO;
import com.hms.pharmacy.DTO.SaleItemDTO;
import com.hms.pharmacy.DTO.SaleRequest;
import com.hms.pharmacy.Exception.HMSException;
import com.hms.pharmacy.Modal.Sale;
import com.hms.pharmacy.Service.SaleService;
import com.hms.pharmacy.Service.SalesItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacy/sales")
@RequiredArgsConstructor
@CrossOrigin
public class SaleController {
    private final SaleService saleService;
    private final SalesItemService salesItemService;

    @PostMapping("/create")
    public ResponseEntity<Long> createSale(@RequestBody SaleRequest saleDTO) throws HMSException {
       return new ResponseEntity<>(saleService.createSale(saleDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateSale(@RequestBody SaleDTO saleDTO) throws HMSException {
        saleService.updateSale(saleDTO);
        return new ResponseEntity<>(new ResponseDTO("Sale update successfully"), HttpStatus.OK);
    }

    @GetMapping("/getSaleItems/{saleId}")
    public ResponseEntity<List<SaleItemDTO>> getSaleItems(@PathVariable Long saleId) throws HMSException {
        return new ResponseEntity<>(salesItemService.getSaleItemsBySaleId(saleId), HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable Long id) throws HMSException {
        SaleDTO sale= saleService.getSaleById(id);
        return new ResponseEntity<>(sale, HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SaleDTO>> getAllSales() throws HMSException {
    List<SaleDTO> sale = saleService.getAllSales();
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

}
