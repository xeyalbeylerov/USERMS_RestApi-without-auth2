package com.company.service.impl;///*



import com.company.entity.EmployementHistory;
import com.company.service.inter.EmployementHistorylServiceInter;
import com.company.service.inter.EmployementHistorylServiceRestInter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xeyal
 */

@Service(value = "employementHistoryServiceRest")
@RequiredArgsConstructor
public class EmployementHistorylServiceRestImpl implements EmployementHistorylServiceRestInter {

    private final EmployementHistorylServiceInter employementHistory;

    @Override
    public List<EmployementHistory> getAllEmployementHistoryByUserId(int id) {
        return employementHistory.getAllEmployementHistoryByUserId(id);
    }

}
