package com.tarashluhsko.dyplom.services;

import com.tarashluhsko.dyplom.model.ArteriesTest;
import com.tarashluhsko.dyplom.model.BiochemicalTest;
import com.tarashluhsko.dyplom.repositories.ArteriesTestRepository;
import com.tarashluhsko.dyplom.repositories.BiochemicalTestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArteriesTestService {

    private final ArteriesTestRepository arteriesTestRepository;

    public ArteriesTestService(ArteriesTestRepository arteriesTestRepository) {
        this.arteriesTestRepository = arteriesTestRepository;
    }

    public ArteriesTest create(ArteriesTest arteriesTest) {
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
