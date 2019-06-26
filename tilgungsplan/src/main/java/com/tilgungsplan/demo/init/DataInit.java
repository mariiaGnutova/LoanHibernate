package com.tilgungsplan.demo.init;

import com.tilgungsplan.demo.entity.RepaymentDO;
import com.tilgungsplan.demo.output.TableGeneration;
import com.tilgungsplan.demo.services.PaymentService;
import com.tilgungsplan.demo.services.PaymentServiceImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class DataInit<forPrinting> implements ApplicationRunner {

    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private Calendar calendar;
    public void DataInit(){}
    private PaymentService paymentService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        paymentService = new PaymentServiceImpl();
        double debitInterest = 0.0212;  // 2.12%
        double initialRepayment = 0.02;  //2%
        int monthInYear = 12;
        double interest = 0;
        Date date = dateFormat.parse("30.11.2015");
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        double loanValue = getLoanValue()*-1;
        double rate = Math.round(loanValue*-1*(initialRepayment + debitInterest)/monthInYear * 100.0) / 100.0;
        double remainingDebt = loanValue;
        double til = loanValue;

        // 10 Years = 120 Month
       for (int month = 0; month <= 120; month++){

     /* for test
            for (int month = 1; month <= 5; month++){

            RepaymentDO repaymentDO = new RepaymentDO(date, loanValue, interest, loanValue, loanValue);
           paymentService.saveRepaymentDO(repaymentDO);
           System.out.println("Las id: " + paymentService.getLastInsertedId());
           loanValue++; */
            int a = 0;
           try {
               RepaymentDO repaymentDO = new RepaymentDO(date, remainingDebt, interest, til, rate);
               a = 1;
               paymentService.saveRepaymentDO(repaymentDO);
               a = 2;
               Long lastInsertedID = paymentService.getLastInsertedId();
               a = 3;
               RepaymentDO repaymentDO1 = paymentService.findById(lastInsertedID);
               a = 4;
               double remainingDedt = repaymentDO1.getRemainingDebt();
               a = 5;

               interest = Math.round(remainingDedt*-1*debitInterest/12
                        * 100.0) / 100.0;
               a = 6;
               til = Math.round((rate - interest +1) * 100.0) / 100.0 ;
               a = 7;
               remainingDebt = Math.round((remainingDedt + til) * 100.0) / 100.0;
               a = 8;
               date = nextMonth(repaymentDO);
           } catch (Exception e) {
               System.out.println("Crash on Step :" + a);
           }
       }
    //For test
        List<RepaymentDO> forPrinting = paymentService.findall();
            for (RepaymentDO repaymentDO : forPrinting ){
                System.out.println("Object: " + repaymentDO.getId());
            }
        System.out.println("URL DB " + paymentService.getURLDB());

//        TableGeneration test = new TableGeneration(repaymentDAOold);
//        test.createTable();

    }

    public double getLoanValue() {
        Scanner in = new Scanner(System.in);
        String s;
        do {
            System.out.print("\n\nDer Darlehensbetrag: ");
            s = in.nextLine();
        }
        while (!s.matches("\\d*"));

        return Double.parseDouble(s);
    }

    public Date nextMonth (RepaymentDO repaymentDO){
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        paymentService.saveRepaymentDO(repaymentDO);
        return calendar.getTime();
    }
}
