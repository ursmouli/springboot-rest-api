package com.app.restapi.initializer;

import com.app.restapi.jpa.entity.Country;
import com.app.restapi.jpa.entity.District;
import com.app.restapi.jpa.entity.State;
import com.app.restapi.jpa.repo.CountryRepository;
import com.app.restapi.jpa.repo.DistrictRepository;
import com.app.restapi.jpa.repo.StateRepository;
import com.app.restapi.jpa.repo.TalukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressInitializer implements CommandLineRunner {


    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private TalukRepository talukRepository;

    @Override
    public void run(String... args) throws Exception {

        Country country = countryRepository.save(new Country().setName("India").setCode("IND"));

        State inAP = new State().setName("Andhra Pradesh").setCode("IN-AP").setCountry(country);
        State inKA = new State().setName("Karnataka").setCode("IN-KA").setCountry(country);
        State inKL = new State().setName("Kerala").setCode("IN-KL").setCountry(country);
        State inTN = new State().setName("Tamil Nadu").setCode("IN-TN").setCountry(country);
        State inTS = new State().setName("Telangana").setCode("IN-TS").setCountry(country);
        List<State> states = List.of(inAP, inKA, inKL, inTN, inTS);

        stateRepository.saveAll(states);

        // districts for karnataka
        // https://en.wikipedia.org/wiki/List_of_districts_of_Karnataka
        new District().setName("Bagalkote").setCode(inKA.getCode() + "-BAG").setState(inKA);
        new District().setName("Bengaluru Urban").setCode(inKA.getCode() + "-BEU").setState(inKA);
        new District().setName("Bengaluru North").setCode(inKA.getCode() + "-BEN").setState(inKA);
        new District().setName("Bengaluru South").setCode(inKA.getCode() + "-BES").setState(inKA);
        new District().setName("Belagavi").setCode(inKA.getCode() + "-BEL").setState(inKA);
        new District().setName("Ballari").setCode(inKA.getCode() + "-BAL").setState(inKA);
        new District().setName("Bidar").setCode(inKA.getCode() + "-BID").setState(inKA);
        new District().setName("Bijapur").setCode(inKA.getCode() + "-VIJ").setState(inKA);
        new District().setName("Chamarajanagara").setCode(inKA.getCode() + "-CHA").setState(inKA);
        new District().setName("Chikkaballapura").setCode(inKA.getCode() + "-CHI").setState(inKA);
        new District().setName("Chikmagalur").setCode(inKA.getCode() + "-CHK").setState(inKA);
        new District().setName("Chitradurga").setCode(inKA.getCode() + "-CHT").setState(inKA);
        new District().setName("Dakshina Kannada").setCode(inKA.getCode() + "-DAK").setState(inKA);
        new District().setName("Davanagere").setCode(inKA.getCode() + "-DAV").setState(inKA);
        new District().setName("Dharawaḍa").setCode(inKA.getCode() + "-DHA").setState(inKA);
        new District().setName("Gadag").setCode(inKA.getCode() + "-GAD").setState(inKA);
        new District().setName("Kalaburagi").setCode(inKA.getCode() + "-KAL").setState(inKA);
        new District().setName("Hassan").setCode(inKA.getCode() + "-HAS").setState(inKA);
        new District().setName("Haveri").setCode(inKA.getCode() + "-HAV").setState(inKA);
        new District().setName("Kodagu").setCode(inKA.getCode() + "-KOD").setState(inKA);
        new District().setName("Kolara").setCode(inKA.getCode() + "-KL").setState(inKA);
        new District().setName("Koppal").setCode(inKA.getCode() + "-KOP").setState(inKA);
        new District().setName("Mandya").setCode(inKA.getCode() + "-MAN").setState(inKA);
        new District().setName("Mysuru").setCode(inKA.getCode() + "-MYS").setState(inKA);
        new District().setName("Raichur").setCode(inKA.getCode() + "-RAI").setState(inKA);
        new District().setName("Shivamogga").setCode(inKA.getCode() + "-SHI").setState(inKA);
        new District().setName("Tumakuru").setCode(inKA.getCode() + "-TUM").setState(inKA);
        new District().setName("Udupi").setCode(inKA.getCode() + "-UDU").setState(inKA);
        new District().setName("Uttara Kannada").setCode(inKA.getCode() + "-UTK").setState(inKA);
        new District().setName("Vijayanagara").setCode(inKA.getCode() + "-VIJ").setState(inKA);
        new District().setName("Yadgiri").setCode(inKA.getCode() + "-YAD").setState(inKA);

        // taluks for karnataka
    }
}
