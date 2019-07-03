package com.tilgungsplan.demo.controller;

import com.tilgungsplan.demo.dataTransferObject.RepaymentDTO;
import com.tilgungsplan.demo.entity.RepaymentDO;
import com.tilgungsplan.demo.mapper.RepaymentMapper;
import com.tilgungsplan.demo.services.PaymentService;
import com.tilgungsplan.demo.services.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
//@EnableWebMvc
@RequestMapping("/api/v1")

public class ControllerRepayment {


//   private PaymentService paymentService;
//
//    @Autowired(required = true)
//    @Qualifier(value="paymentService")
//    public void setPaymentService(PaymentService ps){
//        this.paymentService = ps;
//    }


    private PaymentService paymentService = new PaymentServiceImpl();

//    @ResponseBody
//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String index(){
//        List<RepaymentDO> all = paymentService.findall();
//        StringBuilder output = new StringBuilder();
//        all.forEach(p -> output.append(p.getDate() + "<br>"));
//        return output.toString();
//    }



    @RequestMapping(value = "/getPayments", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
//    public  List<RepaymentDTO> createRepayment(@Valid @RequestParam (value = "remainingdebt") double remainingDebt,
//                                               @RequestParam (value = "interest") double interest,
//                                               @RequestParam (value = "repayment")  double repayment,
//                                               @RequestParam (value = "rate")  double rate,
//                                               @RequestParam (value = "date") String startDate) throws EntityExistsException, ParseException {

    public  List<RepaymentDTO> createRepayment(@Valid @RequestParam (value = "loanValue") double loanValue) throws EntityExistsException, ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime parsedDate = LocalDateTime.parse("2015-10-12T08:15:16", formatter);
        return RepaymentMapper.makeRepaymentDTOList(paymentService.calculatePaymentPlan(loanValue));
      //  return null;
                //parsedDate, remainingDebt, interest, repayment, rate));
    }
}
