package com.company.dto;

import com.company.entity.EmployementHistory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class EmployementHistoryDTO {
    private Integer id;
    private String header;
    private Date beginDate;
    private Date endDate;
    private String jobDescription;

    public EmployementHistoryDTO(EmployementHistory eHistory) {
        this.id = eHistory.getId();
        this.header = eHistory.getHeader();
        this.beginDate = eHistory.getBeginDate();
        this.endDate = eHistory.getEndDate();
        this.jobDescription = eHistory.getJobDescription();
    }
}
