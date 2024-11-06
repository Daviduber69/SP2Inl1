package com.yrgo.client;

import com.yrgo.domain.Action;
import com.yrgo.domain.Call;
import com.yrgo.domain.Customer;
import com.yrgo.services.calls.CallHandlingService;
import com.yrgo.services.customers.CustomerManagementService;
import com.yrgo.services.customers.CustomerNotFoundException;
import com.yrgo.services.diary.DiaryManagementService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

public class SimpleClient {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
        DiaryManagementService diaryService = container.getBean(DiaryManagementService.class);
        CallHandlingService callService = container.getBean(CallHandlingService.class);

        Call call = new Call("Kebabpizza femton minuter kvart");
        Action action1 = new Action("Hämta kebabpizza om femton minuter kvart",new GregorianCalendar(2024,11,9)
                , "David");
        Action action2 = new Action("HÄMTA PIZZA FORT DET ÄR BROTTOM!!", new GregorianCalendar(2024, 11,10)
                ,"David");

        List<Action>actions = new ArrayList();
        actions.add(action1);
        actions.add(action2);

        try {
            callService.recordCall("NV10", call, actions);
        }catch(CustomerNotFoundException e) {
            System.err.println("This customer does not exist.");
        }
        System.out.println("Here are the actions:");
        Collection<Action> incompleteActions = diaryService.getAllIncompleteActions("David");
        for(Action action:incompleteActions) {
            System.out.println(action);
        }
        container.close();
    }
}
