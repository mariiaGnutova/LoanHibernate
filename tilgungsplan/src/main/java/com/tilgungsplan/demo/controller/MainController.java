package com.tilgungsplan.demo.controller;

import com.tilgungsplan.demo.dataTransferObject.RepaymentDTO;
import com.tilgungsplan.demo.entity.RepaymentDO;
import com.tilgungsplan.demo.mapper.RepaymentMapper;
import com.tilgungsplan.demo.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
//@RestController
//@EnableWebMvc
@RequestMapping("/api/v1")
public class MainController {


    private PaymentService paymentService;

    @Autowired(required = true)
    @Qualifier(value="paymentService")
    public void setPaymentService(PaymentService ps){
        this.paymentService = ps;
    }

    @ResponseBody
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        List<RepaymentDO> all = paymentService.findall();
        StringBuilder output = new StringBuilder();
        all.forEach(p -> output.append(p.getDate() + "<br>"));
        return output.toString();
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
//    public  List<RepaymentDTO> createRepayment(@Valid @RequestParam (value = "remainingdebt") double remainingDebt,
//                                               @RequestParam (value = "interest") double interest,
//                                               @RequestParam (value = "repayment")  double repayment,
//                                               @RequestParam (value = "rate")  double rate,
//                                               @RequestParam (value = "date") String startDate) throws EntityExistsException, ParseException {

    public  List<RepaymentDTO> createRepayment(@Valid @RequestParam (value = "loanValue") double loanValue) throws EntityExistsException, ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
       // LocalDateTime parsedDate = LocalDateTime.parse(startDate, formatter);
        return RepaymentMapper.makeRepaymentDTOList(paymentService.calculatePaymentPlan(loanValue));
                //parsedDate, remainingDebt, interest, repayment, rate));
    }
}
