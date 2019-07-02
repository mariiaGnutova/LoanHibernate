package com.tilgungsplan.demo.init;

import com.tilgungsplan.demo.entity.RepaymentDO;
import com.tilgungsplan.demo.services.PaymentService;
import com.tilgungsplan.demo.services.PaymentServiceImpl;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;


@Component
public class DataInit {

    public void DataInit(){}
    private PaymentService paymentService;
    private double interest;
    private LocalDateTime date;
    private double rate;
    private double remainingDebt;
    private double repayment;
    private double debitInterest;


    public void createPaymentPlan(double loanValue) throws Exception {
        paymentService = new PaymentServiceImpl();
        debitInterest = 0.0212;  // 2.12%
        double initialRepayment = 0.02;  //2%
        loanValue = loanValue *-1;
        interest = 0;
        date = LocalDateTime.parse("2015-12-03T10:15:30");
        rate = Math.round(loanValue*-1*(initialRepayment + debitInterest)/12 * 100.0) / 100.0;
        remainingDebt = loanValue;
        repayment = loanValue;

        // 10 Years = 120 Month
       for (int month = 0; month <= 120; month++){
           tableEntry();
       }
    }

    public void tableEntry(){
        int a = 0;
        try {
            RepaymentDO repaymentDO = new RepaymentDO(date, remainingDebt, interest, repayment, rate);
            paymentService.saveRepaymentDO(repaymentDO);
            a = 1;
            interest = Math.round(remainingDebt*-1*debitInterest/12
                    * 100.0) / 100.0;
            a = 2;
            repayment = Math.round((rate - interest +1) * 100.0) / 100.0 ;
            a = 3;
            remainingDebt = Math.round((remainingDebt + repayment) * 100.0) / 100.0;
            a = 4;
            date = date.plusMonths(1);
        } catch (Exception e) {
            System.out.println("Crash on Step :" + a);
        }
    }
//    public double getLoanValue() {
//        Scanner in = new Scanner(System.in);
//        String s;
//        do {
//            System.out.print("\n\nDer Darlehensbetrag: ");
//            s = in.nextLine();
//        }
//        while (!s.matches("\\d*"));
//
//        return Double.parseDouble(s);
//    }

}
