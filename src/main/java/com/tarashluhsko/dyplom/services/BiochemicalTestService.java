package com.tarashluhsko.dyplom.services;

import com.tarashluhsko.dyplom.model.BiochemicalTest;
import com.tarashluhsko.dyplom.repositories.BiochemicalTestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BiochemicalTestService {

    private final BiochemicalTestRepository biochemicalTestRepository;

    public BiochemicalTestService(BiochemicalTestRepository biochemicalTestRepository) {
        this.biochemicalTestRepository = biochemicalTestRepository;
    }

    public BiochemicalTest create(BiochemicalTest biochemicalTest) {
        double biologicalAge = 0.55 * biochemicalTest.getBmi() + 39.2 * biochemicalTest.getWaistHips() + 3.37 * biochemicalTest.getGtt()
                + 4.44 * biochemicalTest.getVldl() + 1.4 * biochemicalTest.getUrea() - 0.27 * biochemicalTest.getAlt()
                + 0.13 * biochemicalTest.getAlkaine() - 30;

        double error = 38.8 - 0.646 * (LocalDateTime.now().getYear() - biochemicalTest.getCustomer().getBirth_dt().getYear());

        double result = biologicalAge - error;

        biochemicalTest.setResults(Double.valueOf(result).intValue());
        return biochemicalTestRepository.save(biochemicalTest);
    }

    public BiochemicalTest update(BiochemicalTest biochemicalTest) {
        if (biochemicalTest.getId() > 0) {
            BiochemicalTest savedTest = biochemicalTestRepository.findById(biochemicalTest.getId()).orElse(null);
            biochemicalTest.setCreated(savedTest.getCreated());
            return biochemicalTestRepository.save(biochemicalTest);
        }
        return null;
    }

    public void delete(Long id) {
        if (id > 0) {
            biochemicalTestRepository.deleteById(id);
        }
    }


    public List<BiochemicalTest> getBiochemicalTests(Long id) {
        return biochemicalTestRepository.findAllByCustomerId(id);
    }
}
