package com.cmr.ad;

import com.cmr.ad.model.test.Fare;
import com.cmr.ad.model.test.TaxiRide;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxiFareCalculatorService {

    @Autowired
    private KieContainer kieContainer;

    public Long calculateFare(TaxiRide taxiRide, Fare fare) {
        //KieSession is created using KieContainer instance. A KieSession instance is a place where input data can be
        // inserted. The KieSession interacts with the engine to process the actual business logic defined in rule based
        // on inserted Facts. This kieContainer is inserted using @Autowire and the KieContainer Bean defined in
        // TaxiFareConfiguration
        KieSession kieSession = kieContainer.newKieSession();

        //Global (just like a global variable) is used to pass information into the engine. We can set the Global using
        //setGlobal(“key”, value); in this example, we have set Fare object as Global to store the calculated taxi fare.
        kieSession.setGlobal("rideFare", fare);

        //a Rule requires data to operate on. We're inserting the Fact into session using kieSession.insert(taxiRide);
        kieSession.insert(taxiRide);

        //Once we are done with setting up the input Fact, we can request engine to execute the business logic by calling fireAllRules().
        kieSession.fireAllRules();

        //Finally, we need to clean up the session to avoid memory leak by calling the dispose() method.
        kieSession.dispose();

        return fare.getRideFare();
    }
}
