package com.hms.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationCountDTO {
    private List<MonthlyRoleCount> doctorCounts;
    private List<MonthlyRoleCount> patientCounts;
}
