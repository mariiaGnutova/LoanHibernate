package com.tilgungsplan.demo.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RepaymentDTO {
    @JsonIgnore
    private Long id;

    @NotNull(message = "Date can not be null!")
    private LocalDateTime date;

    @NotNull(message = "remainingDebt can not be null!")
    private double remainingDebt;  // Restschuld

    @NotNull(message = "interest can not be null!")
    private double interest;  // Zinsen

    @NotNull(message = "repayment can not be null!")
    private double repayment;  // Tildung

    @NotNull(message = "rate can not be null!")
    private double rate;

    private RepaymentDTO(){}

    public RepaymentDTO(Long id, LocalDateTime date, double remainingDebt, double interest, double repayment, double rate) {
        this.id = id;
        this.date = date;
        this.remainingDebt = remainingDebt;
        this.interest = interest;
        this.repayment = repayment;
        this.rate = rate;
    }

    public static RepaymentDTOBuilder newBuilder()
    {
        return new RepaymentDTOBuilder();
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime getDate() {
        return date;
    }

    public double getRemainingDebt() {
        return remainingDebt;
    }

    public double getInterest() {
        return interest;
    }

    public double getRepayment() {
        return repayment;
    }

    public double getRate() {
        return rate;
    }

    public static class RepaymentDTOBuilder{
        private long id;
        private LocalDateTime date;
        private double remainingDebt;
        private double interest;
        private double repayment;
        private double rate;

        public RepaymentDTOBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public RepaymentDTOBuilder setDate(LocalDateTime date){
            this.date = date;
            return this;
        }

        public void setRemainingDebt(double remainingDebt) {
            this.remainingDebt = remainingDebt;
        }

        public void setInterest(double interest) {
            this.interest = interest;
        }

        public void setRepayment(double repayment) {
            this.repayment = repayment;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public RepaymentDTO createRepaymentDTO()
        {
            return new RepaymentDTO(id, date,remainingDebt,interest, repayment, rate);
        }

    }
}