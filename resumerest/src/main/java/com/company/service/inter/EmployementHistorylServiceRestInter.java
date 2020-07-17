/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.entity.EmployementHistory;

import java.util.List;

/**
 *
 * @author xeyal
 */
public interface EmployementHistorylServiceRestInter {
     List<EmployementHistory> getAllEmployementHistoryByUserId(int userId);
}
