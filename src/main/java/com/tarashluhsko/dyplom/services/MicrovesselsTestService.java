package com.tarashluhsko.dyplom.services;

import com.tarashluhsko.dyplom.model.MicrovesselsTest;
import com.tarashluhsko.dyplom.repositories.MicrovesselsTestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MicrovesselsTestService {
    private final MicrovesselsTestRepository microvesselsTestRepository;

    public MicrovesselsTestService(MicrovesselsTestRepository microvesselsTestRepository) {
        this.microvesselsTestRepository = microvesselsTestRepository;
    }

    public MicrovesselsTest create(MicrovesselsTest microvesselsTest) {
        return microvesselsTestRepository.save(microvesselsTest);
    }

    public MicrovesselsTest update(MicrovesselsTest microvesselsTest) {
        if (microvesselsTest.getId() > 0) {
            MicrovesselsTest savedTest = microvesselsTestRepository.findById(microvesselsTest.getId())
                    .orElse(null);
            microvesselsTest.setCreated(savedTest.getCreated());
            return microvesselsTestRepository.save(microvesselsTest);
        }
        return null;
    }

    public void delete(Long id) {
        if (id > 0) {
            microvesselsTestRepository.deleteById(id);
        }
    }


    public List<MicrovesselsTest> getTests(Long id) {
        if (id > 0) {
            return microvesselsTestRepository.findAllByCustomerId(id);
        }
        return null;
    }
}
