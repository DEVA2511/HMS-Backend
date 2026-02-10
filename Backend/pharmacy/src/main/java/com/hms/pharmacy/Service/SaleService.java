package com.hms.pharmacy.Service;

import com.hms.pharmacy.DTO.SaleDTO;
import com.hms.pharmacy.DTO.SaleRequest;
import com.hms.pharmacy.Exception.HMSException;

import java.util.List;

public interface SaleService {
    Long createSale(SaleRequest saleDTO) throws HMSException;

    void updateSale(SaleDTO saleDTO) throws HMSException;

    SaleDTO getSaleById(Long id) throws HMSException;

    SaleDTO getPrescriptionId(Long prescriptionId) throws HMSException;
    void deleteSale(Long id) throws HMSException;

    List<SaleDTO> getAllSales() throws HMSException;

}
