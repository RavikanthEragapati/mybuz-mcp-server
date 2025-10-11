package com.eragapati.mcp.server;

import com.eragapati.mcp.server.entity.AccountRate;
import com.eragapati.mcp.server.repository.AccountRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    public CommandLineRunner initDatabase(AccountRateRepository repository) {
        return (args) -> {
            log.info("Preloading database with sample data...");

            repository.save(new AccountRate("100", "BT", 15.99));
            repository.save(new AccountRate("100", "P", 18.99));
            repository.save(new AccountRate("100", "C", 22.99));

            repository.save(new AccountRate("101", "P", 13.50));
            repository.save(new AccountRate("101", "C", 19.99));

            log.info("Data load successfully.");

            // Optional: Print all records to the console to confirm insertion
            log.info("--- All Account Rates Retrieved ---");
            repository.findAll().forEach(rate -> {
                log.info("ID: {}, Account: {}, Type: {}, APR: {}%",
                        rate.getId(),
                        rate.getAccountId(),
                        rate.getRateType(),
                        rate.getAprPercentage());
            });
            log.info("-----------------------------------");
        };
    }

}
