package com.tarashluhsko.dyplom.services;

import com.tarashluhsko.dyplom.model.ArteriesTest;
import com.tarashluhsko.dyplom.model.BiochemicalTest;
import com.tarashluhsko.dyplom.repositories.ArteriesTestRepository;
import com.tarashluhsko.dyplom.repositories.BiochemicalTestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArteriesTestService {

    private final ArteriesTestRepository arteriesTestRepository;

    public ArteriesTestService(ArteriesTestRepository arteriesTestRepository) {
        this.arteriesTestRepository = arteriesTestRepository;
    }

    public ArteriesTest create(ArteriesTest arteriesTest) {

        double biologicalAge = 10.39 * arteriesTest.getImc_bif() + 20.81 * arteriesTest.getImc()
                - 14.33 * arteriesTest.getPi() - 0.743 * arteriesTest.getVed() + 33.85 * arteriesTest.getRi() + 41.09;
        double error = 19.696 - 0.368 * (LocalDateTime.now().getYear() - arteriesTest.getCustomer().getBirth_dt().getYear());
        double result = biologicalAge - error;
        arteriesTest.setResults(Double.valueOf(result).intValue());

        return arteriesTestRepository.save(arteriesTest);
    }

    public ArteriesTest update(ArteriesTest arteriesTest) {
        if (arteriesTest.getId() > 0) {
            ArteriesTest dbArteriesTest = arteriesTestRepository.findById(arteriesTest.getId()).orElse(null);
            arteriesTest.setCreatedDt(dbArteriesTest.getCreatedDt());
            return arteriesTestRepository.save(arteriesTest);
        }
        return null;
    }

    public void delete(Long id) {
        if (id > 0) {
            arteriesTestRepository.deleteById(id);
        }
    }

    public List<ArteriesTest> getTests(Long id) {
        if (id > 0) {
            return arteriesTestRepository.findAllByCustomerId(id);
        }
        return null;
    }
}
