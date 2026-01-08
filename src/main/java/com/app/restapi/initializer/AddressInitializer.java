package com.app.restapi.initializer;

import com.app.restapi.jpa.entity.Country;
import com.app.restapi.jpa.entity.District;
import com.app.restapi.jpa.entity.State;
import com.app.restapi.jpa.entity.Taluk;
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
        District kaBag = new District().setName("Bagalkote").setCode(inKA.getCode() + "-BAG").setState(inKA);
        District kaBeu = new District().setName("Bengaluru Urban").setCode(inKA.getCode() + "-BEU").setState(inKA);
        District kaBen = new District().setName("Bengaluru North").setCode(inKA.getCode() + "-BEN").setState(inKA);
        District kaBes = new District().setName("Bengaluru South").setCode(inKA.getCode() + "-BES").setState(inKA);
        District kaBel = new District().setName("Belagavi").setCode(inKA.getCode() + "-BEL").setState(inKA);
        District kaBal = new District().setName("Ballari").setCode(inKA.getCode() + "-BAL").setState(inKA);
        District kaBid = new District().setName("Bidar").setCode(inKA.getCode() + "-BID").setState(inKA);
        District kaBij = new District().setName("Bijapur").setCode(inKA.getCode() + "-BIJ").setState(inKA);
        District kaCha = new District().setName("Chamarajanagara").setCode(inKA.getCode() + "-CHA").setState(inKA);
        District kaChi = new District().setName("Chikkaballapura").setCode(inKA.getCode() + "-CHI").setState(inKA);
        District kaChk = new District().setName("Chikmagalur").setCode(inKA.getCode() + "-CHK").setState(inKA);
        District kaCht = new District().setName("Chitradurga").setCode(inKA.getCode() + "-CHT").setState(inKA);
        District kaDak = new District().setName("Dakshina Kannada").setCode(inKA.getCode() + "-DAK").setState(inKA);
        District kaDav = new District().setName("Davanagere").setCode(inKA.getCode() + "-DAV").setState(inKA);
        District kaDha = new District().setName("Dharawaḍa").setCode(inKA.getCode() + "-DHA").setState(inKA);
        District kaGad = new District().setName("Gadag").setCode(inKA.getCode() + "-GAD").setState(inKA);
        District kaKal = new District().setName("Kalaburagi").setCode(inKA.getCode() + "-KAL").setState(inKA);
        District kaHas = new District().setName("Hassan").setCode(inKA.getCode() + "-HAS").setState(inKA);
        District kaHav = new District().setName("Haveri").setCode(inKA.getCode() + "-HAV").setState(inKA);
        District kaKod = new District().setName("Kodagu").setCode(inKA.getCode() + "-KOD").setState(inKA);
        District kaKl = new District().setName("Kolara").setCode(inKA.getCode() + "-KL").setState(inKA);
        District kaKop = new District().setName("Koppal").setCode(inKA.getCode() + "-KOP").setState(inKA);
        District kaMan = new District().setName("Mandya").setCode(inKA.getCode() + "-MAN").setState(inKA);
        District kaMys = new District().setName("Mysuru").setCode(inKA.getCode() + "-MYS").setState(inKA);
        District kaRai = new District().setName("Raichur").setCode(inKA.getCode() + "-RAI").setState(inKA);
        District kaShi = new District().setName("Shivamogga").setCode(inKA.getCode() + "-SHI").setState(inKA);
        District kaTum = new District().setName("Tumakuru").setCode(inKA.getCode() + "-TUM").setState(inKA);
        District kaUdu = new District().setName("Udupi").setCode(inKA.getCode() + "-UDU").setState(inKA);
        District kaUtk = new District().setName("Uttara Kannada").setCode(inKA.getCode() + "-UTK").setState(inKA);
        District kaVij = new District().setName("Vijayanagara").setCode(inKA.getCode() + "-VIJ").setState(inKA);
        District kaYad = new District().setName("Yadgiri").setCode(inKA.getCode() + "-YAD").setState(inKA);

        List<District> karnatakaDistricts = List.of(
                kaBag, kaBeu, kaBen, kaBes, kaBel, kaBal, kaBid, kaBij,
                kaCha, kaChi, kaChk, kaCht, kaDak, kaDav, kaDha, kaGad,
                kaKal, kaHas, kaHav, kaKod, kaKl, kaKop, kaMan, kaMys,
                kaRai, kaShi, kaTum, kaUdu, kaUtk, kaVij, kaYad
        );

        districtRepository.saveAll(karnatakaDistricts);

        // taluks for karnataka raichur district
        Taluk kaRaiRai = new Taluk().setName("Raichuru").setDistrict(kaRai);
        Taluk kaRaiDev = new Taluk().setName("Devadurga").setDistrict(kaRai);
        Taluk kaRaiSin = new Taluk().setName("Sindhanur").setDistrict(kaRai);
        Taluk kaRaiMan = new Taluk().setName("Manvi").setDistrict(kaRai);
        Taluk kaRaiMas = new Taluk().setName("Maski").setDistrict(kaRai);
        Taluk kaRaiSir = new Taluk().setName("Sirwar").setDistrict(kaRai);
        Taluk kaRaiLin = new Taluk().setName("Lingsuguru").setDistrict(kaRai);

        List<Taluk> raichurTaluks = List.of(
                kaRaiRai,
                kaRaiDev,
                kaRaiSin,
                kaRaiMan,
                kaRaiMas,
                kaRaiSir,
                kaRaiLin
        );

        talukRepository.saveAll(raichurTaluks);
    }
}
