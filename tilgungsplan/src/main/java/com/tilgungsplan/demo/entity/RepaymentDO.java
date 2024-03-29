package com.tilgungsplan.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Loan")  // TILGUNGSPLAN
public class RepaymentDO {  // TildungDO

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    private LocalDateTime date;

    private double remainingDebt;  // Restschuld

    private double interest;  // Zinsen

    private double repayment;  // Tildung

    private double rate;

    private long userId;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }  // getDatum

    public void setDate(LocalDateTime date) {
        this.date = date;
    }  // setDatum

    public double getRemainingDebt() {
        return remainingDebt;
    }

    public void setRemainingDebt(double remainingDebt) {
        this.remainingDebt = remainingDebt;
    }  // setRestschuld

    public double getInterest() {
        return interest;
    }  // getZinsen

    public void setInterest(double interest) {
        this.interest = interest;
    }  // setZinsen

    public double getRepayment() {
        return repayment;
    }  // getTildung

    public void setRepayment(double repayment) {
        this.repayment = repayment;
    }  // setTildung

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public RepaymentDO(long userId, LocalDateTime date, double remainingDebt, double interest, double repayment, double rate) {
        this.date = date;
        this.remainingDebt = remainingDebt;
        this.interest = interest;
        this.repayment = repayment;
        this.rate = rate;
        this.userId = userId;
    }

    public RepaymentDO(){}

    @Override
    public String toString() {
        return "RepaymentDO{" +
                "id=" + id +
                ", date=" + date +
                ", remainingDebt=" + remainingDebt +
                ", interest=" + interest +
                ", repayment=" + repayment +
                ", rate=" + rate + ", userId=" + userId +
                '}';
    }
}


