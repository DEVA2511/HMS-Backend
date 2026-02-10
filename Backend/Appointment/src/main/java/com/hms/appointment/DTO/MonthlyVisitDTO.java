package com.hms.appointment.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MonthlyVisitDTO {
    private String month;
    private Long visitCount;
}
