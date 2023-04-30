package com.tarashluhsko.dyplom.services;

import com.tarashluhsko.dyplom.model.BiochemicalTest;
import com.tarashluhsko.dyplom.repositories.BiochemicalTestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiochemicalTestService {

    private final BiochemicalTestRepository biochemicalTestRepository;

    public BiochemicalTestService(BiochemicalTestRepository biochemicalTestRepository) {
        this.biochemicalTestRepository = biochemicalTestRepository;
    }

    public BiochemicalTest create(BiochemicalTest biochemicalTest) {
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
