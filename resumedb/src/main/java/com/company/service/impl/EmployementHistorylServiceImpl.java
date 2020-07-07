/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.impl;

import com.company.repo.EmployementHistoryRepository;
import com.company.entity.EmployementHistory;
import com.company.service.inter.EmployementHistorylServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Khayal Baylarov
 */
@Transactional
@Service(value = "employementHistoryService")
@RequiredArgsConstructor
public class EmployementHistorylServiceImpl implements EmployementHistorylServiceInter {

    private final EmployementHistoryRepository employementHistory;

    @Override
    public List<EmployementHistory> getAllEmployementHistoryByUserId(int id) {
        return employementHistory.findByUser_Id(id);
    }
    @Override
    public boolean isIdExists(Integer id) {
        return employementHistory.existsEmployementHistoryById(id);
    }
}
