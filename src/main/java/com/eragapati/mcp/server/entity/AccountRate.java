package com.eragapati.mcp.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "ACCOUNT_RATE")
@NoArgsConstructor
@AllArgsConstructor
public class AccountRate {

    public AccountRate(String accountId, String rateType, Double aprPercentage) {
        this.accountId = accountId;
        this.rateType = rateType;
        this.aprPercentage = aprPercentage;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountId;
    private String rateType;
    private Double aprPercentage;
}

