package com.tarashluhsko.dyplom.services;

import com.tarashluhsko.dyplom.model.MicrovesselsTest;
import com.tarashluhsko.dyplom.repositories.MicrovesselsTestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MicrovesselsTestService {
    private final MicrovesselsTestRepository microvesselsTestRepository;

    public MicrovesselsTestService(MicrovesselsTestRepository microvesselsTestRepository) {
        this.microvesselsTestRepository = microvesselsTestRepository;
    }

    public MicrovesselsTest create(MicrovesselsTest microvesselsTest) {
        double biologicalAge = 0.69 *  microvesselsTest.getBmi() + 50.58 * microvesselsTest.getWaistHips() - 0.25 * microvesselsTest.getBloodFlow() -
                0.072 * microvesselsTest.getRecoverySpeed()  + 0.75 * microvesselsTest.getPlatelets() + 0.696;

        double ERR = 48.15 - 0.82 * (LocalDateTime.now().getYear()  - microvesselsTest.getCustomer().getBirth_dt().getYear());
        double result = biologicalAge - ERR;

        microvesselsTest.setResults(Double.valueOf(result).intValue());

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

    public MicrovesselsTest getLatestTest(Long id) {
        return microvesselsTestRepository.findFirstByCustomerIdOrderByCreatedDesc(id);
    }
}
