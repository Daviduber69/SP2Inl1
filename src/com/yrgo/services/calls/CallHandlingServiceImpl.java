package com.yrgo.services.calls;

import com.yrgo.domain.Action;
import com.yrgo.domain.Call;
import com.yrgo.services.customers.CustomerManagementMockImpl;
import com.yrgo.services.customers.CustomerManagementService;
import com.yrgo.services.customers.CustomerNotFoundException;
import com.yrgo.services.diary.DiaryManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
@Service("callHandlingService")
@Transactional
public class CallHandlingServiceImpl implements CallHandlingService {
    CustomerManagementService customerManagementService;

    DiaryManagementService diaryManagementService;
    @Autowired
    public CallHandlingServiceImpl(CustomerManagementService customerManagementService, DiaryManagementService diaryManagementService) {
        this.customerManagementService = customerManagementService;
        this.diaryManagementService = diaryManagementService;
    }

    @Override
    public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException {
        try {
            customerManagementService.recordCall(customerId, newCall);
            for (Action action : actions) {
                diaryManagementService.recordAction(action);
            }
        } catch (NullPointerException e) {
            throw new CustomerNotFoundException();
        }

    }
}