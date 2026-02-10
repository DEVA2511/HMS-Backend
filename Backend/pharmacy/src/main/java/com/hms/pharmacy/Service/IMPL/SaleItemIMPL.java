package com.hms.pharmacy.Service.IMPL;

import com.hms.pharmacy.DTO.SaleItemDTO;
import com.hms.pharmacy.Exception.HMSException;
import com.hms.pharmacy.Modal.SaleItem;
import com.hms.pharmacy.Reposictory.SaleItemRepository;
import com.hms.pharmacy.Service.MedicineInventoryService;
import com.hms.pharmacy.Service.SalesItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleItemIMPL implements SalesItemService {
    private final SaleItemRepository saleItemRepository;
    private final MedicineInventoryService medicineInventoryService;

    @Override
    public Long createSaleItem(SaleItemDTO saleItemDTO) throws HMSException {

        return saleItemRepository.save(saleItemDTO.toEntity()).getId();
    }

    @Override
    public void createSaleItems(Long saleId, List<SaleItemDTO> saleItemDTOs) throws HMSException {
//        for (SaleItemDTO saleItem : saleItemDTOs) {
//          saleItem.setBatchNumber(medicineInventoryService.sellStock(saleItem.getMedicineId(), saleItem.getQuantity()));
//        }
    saleItemDTOs.stream().map((x)->{
        x.setSaleId(saleId);
        return x.toEntity();
    }).forEach(saleItemRepository::save);
    }

    @Override
    public void createMultipleSaleItems(Long saleId, Long medicineId, List<SaleItemDTO> saleItemDTOs) throws HMSException {
        saleItemDTOs.stream().map((x) -> {
            x.setSaleId(saleId);
            x.setMedicineId(medicineId);
            return x.toEntity();
        }).forEach(saleItemRepository::save);
    }

    @Override
    public void updateSaleItem(SaleItemDTO saleItemDTO) throws HMSException {
        SaleItem existingSaleItem = saleItemRepository.findById(saleItemDTO.getId())
                .orElseThrow(() -> new HMSException("SALE_ITEM_NOT_FOUND"));

//        existingSaleItem.setBatchNumber(saleItemDTO.getBatchNumber());
        existingSaleItem.setQuantity(saleItemDTO.getQuantity());
        existingSaleItem.setUnitPrice(saleItemDTO.getUnitPrice());

        saleItemRepository.save(existingSaleItem);
    }

    @Override
    public void deleteSaleItem(Long id) throws HMSException {

    }

    @Override
    public List<SaleItemDTO> getSaleItemsBySaleId(Long saleId) throws HMSException {
        return saleItemRepository.findBySaleId(saleId).stream().
                map(SaleItem::toDTO).toList();
    }

    @Override
    public SaleItemDTO getSaleItem(Long id) throws HMSException {
        return saleItemRepository.findById(id).map(SaleItem::toDTO)
                .orElseThrow(() -> new HMSException("SALE_ITEM_NOT_FOUND"));
    }
}
