package com.hms.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyRoleCount {
    private String month;
    private Long count;
//    private Long patientCount;
}
