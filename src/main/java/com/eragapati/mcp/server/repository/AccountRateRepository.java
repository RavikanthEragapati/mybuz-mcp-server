package com.eragapati.mcp.server.repository;

import com.eragapati.mcp.server.entity.AccountRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRateRepository extends JpaRepository<AccountRate, Long> {

    List<AccountRate> findByAccountId(String accountId);
}