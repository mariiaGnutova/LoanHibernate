package com.tilgungsplan.demo.output;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TableGeneration{
//
//
//
//
//    public void createTable() {
//        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
//        Date c;
//        String date = new String();
//        System.out.printf("\n%s %27s %11s %30s %7s %n", "Date", "Restschuld", "Zinsen", "Tilgung(+)/Auszahlung(-)",
//                "Rate");
//        for (long i = 1; i < 4; i++) {
//
//            c = repaymentDAOold.findById(i).get().getDate();
//            date = dateFormat.format(c);
//            System.out.printf("\n%7s %20s %13s %20s %17s %n", date,
//                    repaymentDAOold.findById(i).get().getRemainingDebt(), repaymentDAOold.findById(i).get().getInterest(),
//                    repaymentDAOold.findById(i).get().getRepayment(), repaymentDAOold.findById(i).get().getRate());
//        }
//        System.out.printf("%40s %170s", "...", "");
//
//        for (long y = repaymentDAOold.count() - 1; y < repaymentDAOold.count() + 1; y++) {
//            c = repaymentDAOold.findById(y).get().getDate();
//            date = dateFormat.format(c);
//            System.out.printf("\n%7s %20s %13s %20s %17s %n", date,
//                    repaymentDAOold.findById(y).get().getRemainingDebt(), repaymentDAOold.findById(y).get().getInterest(),
//                    repaymentDAOold.findById(y).get().getRepayment(), repaymentDAOold.findById(y).get().getRate());
//        }
//        System.out.printf("\n%7s %14s %14s %20s %17s %n", "Zinsbindungsende", repaymentDAOold.findById(
//                repaymentDAOold.count()).get().getRemainingDebt(), repaymentDAOold.sumInterest(), repaymentDAOold.sumRepayment(),
//                repaymentDAOold.sumRate());
//    }

}
