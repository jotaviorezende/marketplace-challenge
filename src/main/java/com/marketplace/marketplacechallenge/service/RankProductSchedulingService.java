package com.marketplace.marketplacechallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Executa o ranquiamento de produtos todos os dias a 1:00h.
 */
@Component
@EnableScheduling
public class RankProductSchedulingService {

    @Autowired
    private RankProductService rankProductService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void RankingPeriodicProducts() {
        rankProductService.rankProductsByCategory();
    }
}
