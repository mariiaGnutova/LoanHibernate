package com.tilgungsplan.demo.mapper;


import com.tilgungsplan.demo.dataTransferObject.RepaymentDTO;
import com.tilgungsplan.demo.entity.RepaymentDO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RepaymentMapper {

    public static RepaymentDO makeRepaymentDO(RepaymentDTO repaymentDTO)
    {
        return new RepaymentDO(repaymentDTO.getDate(),
                repaymentDTO.getRemainingDebt(),
                repaymentDTO.getInterest(),
                repaymentDTO.getRepayment(),
                repaymentDTO.getRate()
        );
    }

    public static RepaymentDTO makeRepaymentDTO(RepaymentDO repaymentDO) {
        RepaymentDTO.RepaymentDTOBuilder repaymentDTOBuilder = RepaymentDTO.newBuilder();
              repaymentDTOBuilder.setId(repaymentDO.getId())
                .setDate(repaymentDO.getDate())
                .setRepayment(repaymentDO.getRepayment());
              repaymentDTOBuilder.setInterest(repaymentDO.getInterest());
        repaymentDTOBuilder.setRemainingDebt(repaymentDO.getRemainingDebt());
        repaymentDTOBuilder.setRate(repaymentDO.getRate());
        return repaymentDTOBuilder.createRepaymentDTO();
    }


    public static List<RepaymentDTO> makeRepaymentDTOList(Collection<RepaymentDO> payments)
    {
        return payments.stream()
                .map(RepaymentMapper::makeRepaymentDTO)
                .collect(Collectors.toList());
    }
}
