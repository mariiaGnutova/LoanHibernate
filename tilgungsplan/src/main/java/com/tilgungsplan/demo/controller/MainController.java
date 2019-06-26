package com.tilgungsplan.demo.controller;

import com.tilgungsplan.demo.entity.RepaymentDO;
import com.tilgungsplan.demo.services.PaymentService;
import com.tilgungsplan.demo.services.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {


    private PaymentService paymentService = new PaymentServiceImpl();

    @ResponseBody
    @RequestMapping("/")
    public String index(){
        List<RepaymentDO> all = paymentService.findall();
        StringBuilder output = new StringBuilder();
        all.forEach(p -> output.append(p.getDate() + "<br>"));
        return output.toString();
    }
}
