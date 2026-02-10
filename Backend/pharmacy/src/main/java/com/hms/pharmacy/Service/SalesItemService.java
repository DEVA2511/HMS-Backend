package com.hms.pharmacy.Service;

import com.hms.pharmacy.DTO.SaleItemDTO;
import com.hms.pharmacy.Exception.HMSException;

import java.util.List;

public interface SalesItemService {

    Long createSaleItem(SaleItemDTO saleItemDTO) throws HMSException;
    void createSaleItems(Long saleId,List<SaleItemDTO> saleItemDTOs) throws HMSException;
    void createMultipleSaleItems(Long saleId,Long medicineId ,List<SaleItemDTO> saleItemDTOs) throws HMSException;

    void updateSaleItem(SaleItemDTO saleItemDTO) throws HMSException;
    void deleteSaleItem(Long id) throws HMSException;

     List<SaleItemDTO> getSaleItemsBySaleId( Long saleId) throws HMSException;
    SaleItemDTO getSaleItem(Long id) throws HMSException;
}
