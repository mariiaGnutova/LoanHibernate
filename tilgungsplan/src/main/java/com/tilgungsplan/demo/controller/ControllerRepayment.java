package com.tilgungsplan.demo.controller;

import com.tilgungsplan.demo.dataTransferObject.RepaymentDTO;
import com.tilgungsplan.demo.entity.RepaymentDO;
import com.tilgungsplan.demo.mapper.RepaymentMapper;
import com.tilgungsplan.demo.services.PaymentService;
import com.tilgungsplan.demo.services.PaymentServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityExistsException;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
//@EnableWebMvc
@RequestMapping("/api/v1")

public class ControllerRepayment {


    private PaymentService paymentService = new PaymentServiceImpl();

    @ResponseBody
    @RequestMapping(value = "/paymentsTable", method = RequestMethod.GET)
    public List<RepaymentDTO> index(@RequestParam (value = "userId") long userId){
        return RepaymentMapper.makeRepaymentDTOList(paymentService.findallForUser(userId));
    }

//    @RequestMapping(value = "/getPaymentsInCertainMonth", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public RepaymentDO getPaymentsInCertainMonth (@RequestParam (value = "Date ") String date){

    @GetMapping("/getPaymentsInCertainMonth/{date}")
    public RepaymentDO getPaymentsInCertainMonth (@PathVariable(value = "date") String date, @RequestParam (value = "userId") long userId){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime parsedDate = LocalDateTime.parse(date, formatter);
        return paymentService.findByDate(parsedDate, userId);
    }

    @RequestMapping(value = "/getPayments", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
//    public  List<RepaymentDTO> createRepayment(@Valid @RequestParam (value = "remainingdebt") double remainingDebt,
//                                               @RequestParam (value = "interest") double interest,
//                                               @RequestParam (value = "repayment")  double repayment,
//                                               @RequestParam (value = "rate")  double rate,
//                                               @RequestParam (value = "date") String startDate) throws EntityExistsException, ParseException {

    public List<RepaymentDTO>  createRepayment(@Valid @RequestParam (value = "loanValue") double loanValue,
                                               @RequestParam (value = "date") String startDate) throws EntityExistsException, ParseException {
        //"2015-10-12T08:15:16"

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime parsedDate = LocalDateTime.parse(startDate, formatter);
        long userId = paymentService.getLastUserId() + 1;
      return RepaymentMapper.makeRepaymentDTOList(paymentService.calculatePaymentPlan(loanValue, parsedDate, userId));
      //parsedDate, remainingDebt, interest, repayment, rate));
    }

    @RequestMapping(value = "/deleteOldCalculations", method = RequestMethod.DELETE)
    public boolean deleteOldCalculations(){
        return paymentService.deletOldCulculations();
    }
}
