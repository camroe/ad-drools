package com.cmr.ad;

import com.cmr.ad.model.test.Fare;
import com.cmr.ad.model.test.TaxiRide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = {TaxiFareConfiguration.class})
//Dont need , SpringBootContextLoader.class
class TaxiFareCalculatorServiceTest {
    Logger logger = LoggerFactory.getLogger(TaxiFareCalculatorServiceTest.class);

    @Autowired
    private TaxiFareCalculatorService taxiFareCalculatorService;

    @BeforeEach
    void setUp() {
//        taxiFareCalculatorService = new TaxiFareCalculatorService();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calculateFare() {
    }

    @Test
    public void whenNightSurchargeFalseAndDistLessThan10_thenFixWithoutNightSurcharge() {
        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setIsNightSurcharge(false);
        taxiRide.setDistanceMile(9L);
        Fare rideFare = new Fare();
        Long totalCharge = taxiFareCalculatorService.calculateFare(taxiRide, rideFare);
        logger.info("Total Charge: $" + totalCharge);
        assertNotNull(totalCharge);
        assertEquals(Long.valueOf(70), totalCharge);
    }
}