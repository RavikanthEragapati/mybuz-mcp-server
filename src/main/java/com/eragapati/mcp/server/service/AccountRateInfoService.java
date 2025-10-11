package com.eragapati.mcp.server.service;

import com.eragapati.mcp.server.repository.AccountRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountRateInfoService {

    private final AccountRateRepository accountRateRepository;


    @Tool(description = "Provides Card Account member Annual Percentage Rate (APR) for each Type of Rate Purchase, Cach, Balance Transfer.Input account_id")
    public String getAllAccountRates(String acctId) {
        return accountRateRepository.findByAccountId(acctId)
                .stream()
                .map(acctRate ->

                        String.format(" ACCOUNT_ID: %s | RATE_TYPE: %s | APR: %s",
                                acctRate.getAccountId(),
                                acctRate.getRateType(),
                                acctRate.getAprPercentage())


                )
                .collect(Collectors.joining("\n"));
    }

}
