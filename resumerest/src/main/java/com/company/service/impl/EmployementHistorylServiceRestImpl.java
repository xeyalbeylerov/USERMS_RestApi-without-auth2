package com.company.service.impl;///*



import com.company.entity.EmployementHistory;
import com.company.service.inter.EmployementHistorylServiceInter;
import com.company.service.inter.EmployementHistorylServiceRestInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xeyal
 */

@Service(value = "employementHistoryServiceRest")
public class EmployementHistorylServiceRestImpl implements EmployementHistorylServiceRestInter {
    @Autowired
    @Qualifier(value = "employementHistoryService")
    private EmployementHistorylServiceInter employementHistory;

    @Override
    public List<EmployementHistory> getAllEmployementHistoryByUserId(int id) {
        return employementHistory.getAllEmployementHistoryByUserId(id);
    }

}
