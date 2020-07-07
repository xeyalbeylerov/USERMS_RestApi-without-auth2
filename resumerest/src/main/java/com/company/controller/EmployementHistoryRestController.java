package com.company.controller;

import com.company.dto.EmployementHistoryDto;
import com.company.dto.ResponseDto;
import com.company.entity.EmployementHistory;
import com.company.service.inter.EmployementHistorylServiceRestInter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployementHistoryRestController {

    private final EmployementHistorylServiceRestInter employementHistoryService;

    @GetMapping("/ehistories/{id}")
    public ResponseEntity<ResponseDto> getEmployementHistoriesByUserId(@PathVariable("id") int id) {
        List<EmployementHistory> employementHistory = employementHistoryService.getAllEmployementHistoryByUserId(id);

        List<EmployementHistoryDto> eHistoryDTOS = new ArrayList<>();
        //skill-i skillDto ya cevirir
        for (int i = 0; i < employementHistory.size(); i++) {
            EmployementHistory s = employementHistory.get(i);
            eHistoryDTOS.add(new EmployementHistoryDto(s));
        }
        return ResponseEntity.ok(ResponseDto.of(eHistoryDTOS));
    }
}
