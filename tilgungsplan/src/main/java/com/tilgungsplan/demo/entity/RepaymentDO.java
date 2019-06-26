package com.tilgungsplan.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Loan")  // TILGUNGSPLAN
public class RepaymentDO {  // TildungDO

    @Id
    @GeneratedValue //(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;

//    @Temporal(TemporalType.DATE)
//    @Column(nullable = false)
//    private Date date;  // datum
 @Basic
 @Temporal(TemporalType.DATE)
  private Date date;

    @Column(name="Debt")
    private double remainingDebt;  // Restschuld

    @Column(name="interest")
    private double interest;  // Zinsen

    @Column(name="repayment")
    private double repayment;  // Tildung

    @Column(name="rate")
    private double rate;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }  // getDatum

    public void setDate(Date date) {
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

    public RepaymentDO(Date date, double remainingDebt, double interest, double repayment, double rate) {
        this.date = date;
        this.remainingDebt = remainingDebt;
        this.interest = interest;
        this.repayment = repayment;
        this.rate = rate;
    }

    public RepaymentDO(){}
}


