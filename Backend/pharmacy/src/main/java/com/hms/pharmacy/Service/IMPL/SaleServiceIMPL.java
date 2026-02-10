package com.hms.pharmacy.Service.IMPL;

import com.hms.pharmacy.DTO.SaleDTO;
import com.hms.pharmacy.DTO.SaleItemDTO;
import com.hms.pharmacy.DTO.SaleRequest;
import com.hms.pharmacy.Exception.HMSException;
import com.hms.pharmacy.Modal.Sale;
import com.hms.pharmacy.Reposictory.SaleRepository;
import com.hms.pharmacy.Service.MedicineInventoryService;
import com.hms.pharmacy.Service.SaleService;
import com.hms.pharmacy.Service.SalesItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceIMPL implements SaleService {
    private final SaleRepository saleRepository;
    private final SalesItemService salesItemService;
    private final MedicineInventoryService medicineInventoryService;

    @Override
    @Transactional
    public Long createSale(SaleRequest saleDTO) throws HMSException {
        if (saleDTO.getPrescriptionId() != null && saleRepository.existsByPrescriptionId(saleDTO.getPrescriptionId())) {
            throw new HMSException("SALE_ALREADY_EXISTS_FOR_PRESCRIPTION");
        }
        for (SaleItemDTO saleItem : saleDTO.getSaleItems()) {
           saleItem.setBatchNumber( medicineInventoryService.sellStock(saleItem.getMedicineId(), saleItem.getQuantity()));
        }
        Sale sale = new Sale(null, saleDTO.getPrescriptionId(),saleDTO.getBuyerName(),saleDTO.getBuyerContact(), LocalDateTime.now(), saleDTO.getTotalAmount());
        sale = saleRepository.save(sale);
        salesItemService.createSaleItems(sale.getId(), saleDTO.getSaleItems());

        return sale.getId();
    }

    @Override
    public void updateSale(SaleDTO saleDTO) throws HMSException {
        Sale sale = saleRepository.findByPrescriptionId(saleDTO.getPrescriptionId()).orElseThrow(() -> new HMSException("SALE_NOT_FOUND"));
        sale.setSaleDate(LocalDateTime.now());
        sale.setTotalAmount(saleDTO.getTotalAmount());
        saleRepository.save(sale);
    }

    @Override
    public SaleDTO getSaleById(Long id) throws HMSException {
        return saleRepository.findById(id)
                .orElseThrow(() -> new HMSException("SALE_NOT_FOUND"))
                .toDTO();
    }

    @Override
    public SaleDTO getPrescriptionId(Long prescriptionId) throws HMSException {
        return saleRepository.findByPrescriptionId(prescriptionId).orElseThrow(() -> new HMSException("SALE_NOT_FOUND")).toDTO();
    }

    @Override
    public void deleteSale(Long id) throws HMSException {

    }

    @Override
    public List<SaleDTO> getAllSales() throws HMSException {
       return ((List<Sale>) saleRepository.findAll()).stream().map(Sale::toDTO).toList();
    }


}
