package com.marketplace.marketplacechallenge.controller;

import com.marketplace.marketplacechallenge.service.RankProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Disponibiliza as APIs de ranquiamento de produtos para consumo.
 */
@RestController
@RequestMapping(path = "/rank")
public class RankProductRestController {

    @Autowired
    private RankProductService rankProductService;

    @PostMapping(path = "/execute")
    public ResponseEntity rankProducts() {
        rankProductService.rankProductsByCategory();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
