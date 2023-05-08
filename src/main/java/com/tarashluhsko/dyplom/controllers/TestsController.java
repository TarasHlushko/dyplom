package com.tarashluhsko.dyplom.controllers;

import com.tarashluhsko.dyplom.model.ArteriesTest;
import com.tarashluhsko.dyplom.model.BiochemicalTest;
import com.tarashluhsko.dyplom.model.MicrovesselsTest;
import com.tarashluhsko.dyplom.services.ArteriesTestService;
import com.tarashluhsko.dyplom.services.BiochemicalTestService;
import com.tarashluhsko.dyplom.services.MicrovesselsTestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestsController {

    private final BiochemicalTestService biochemicalTestService;

    private final ArteriesTestService arteriesTestService;

    private final MicrovesselsTestService microvesselsTestService;

    public TestsController(BiochemicalTestService biochemicalTestService, ArteriesTestService arteriesTestService, MicrovesselsTestService microvesselsTestService) {
        this.biochemicalTestService = biochemicalTestService;
        this.arteriesTestService = arteriesTestService;
        this.microvesselsTestService = microvesselsTestService;
    }

    @GetMapping("/biochemical/getAll/{id}")
    public List<BiochemicalTest> getBiochemicalTests(@PathVariable Long id) {
        return biochemicalTestService.getBiochemicalTests(id);
    }


    @PostMapping("biochemical/create")
    public ResponseEntity<String> createBiochemicalTest(@RequestBody BiochemicalTest biochemicalTest) {
        ResponseEntity response = null;
        try {
            biochemicalTest.setCreated(LocalDateTime.now());
            System.out.println(biochemicalTest.getId() + ": " + biochemicalTest.getCustomer().getId());
            BiochemicalTest savedTest = biochemicalTestService.create(biochemicalTest);
            if (savedTest.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given test is successfully created");
            }
        } catch (Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }

        return response;
    }

    @PutMapping("biochemical/update")
    public ResponseEntity<String> updateBiochemicalTest(@RequestBody BiochemicalTest biochemicalTest) {
        ResponseEntity<String> response = null;
        try {
            biochemicalTestService.update(biochemicalTest);
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Given biochemical test details are successfully updated");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }
        return response;
    }

    @DeleteMapping("biochemical/delete/{id}")
    public ResponseEntity<String> deleteBiochemicalTest(@PathVariable Long id) {
        ResponseEntity<String> response = null;
        try {
            biochemicalTestService.delete(id);
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Given test is successfully deleted");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }
        return response;
    }


    @GetMapping("/arteries/getAll/{id}")
    public List<ArteriesTest> getArteriesTests(@PathVariable Long id) {
        return arteriesTestService.getTests(id);
    }

    @PostMapping("arteries/create")
    public ResponseEntity<String> createArteriesTest(@RequestBody ArteriesTest arteriesTest) {
        System.out.println(arteriesTest.getPi());
        ResponseEntity response = null;
        try {
            arteriesTest.setCreatedDt(LocalDateTime.now());
            System.out.println(arteriesTest.getId() + ": " + arteriesTest.getCustomer().getId());
            ArteriesTest savedTest = arteriesTestService.create(arteriesTest);
            if (savedTest.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given test is successfully created");
            }
        } catch (Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }

        return response;
    }

    @PutMapping("arteries/update")
    public ResponseEntity<String> updateArteriesTest(@RequestBody ArteriesTest arteriesTest) {
        ResponseEntity<String> response = null;
        try {
            arteriesTestService.update(arteriesTest);
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Given biochemical test details are successfully updated");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }
        return response;
    }

    @DeleteMapping("arteries/delete/{id}")
    public ResponseEntity<String> deleteArteriesTest(@PathVariable Long id) {
        ResponseEntity<String> response = null;
        try {
            arteriesTestService.delete(id);
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Given test is successfully deleted");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }
        return response;
    }

    @GetMapping("/microvessels/getAll/{id}")
    public List<MicrovesselsTest> getMicrovesselsTests(@PathVariable Long id) {
        return microvesselsTestService.getTests(id);
    }

    @PostMapping("microvessels/create")
    public ResponseEntity<String> createMicrovesselsTest(@RequestBody MicrovesselsTest microvesselsTest) {
        ResponseEntity response = null;
        try {
            microvesselsTest.setCreated(LocalDateTime.now());
            System.out.println(microvesselsTest.getId() + ": " + microvesselsTest.getCustomer().getId());
            MicrovesselsTest savedTest = microvesselsTestService.create(microvesselsTest);
            if (savedTest.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given test is successfully created");
            }
        } catch (Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }

        return response;
    }

    @PutMapping("microvessels/update")
    public ResponseEntity<String> updateMicrovesselsTest(@RequestBody MicrovesselsTest microvesselsTest) {
        ResponseEntity<String> response = null;
        try {
            microvesselsTestService.update(microvesselsTest);
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Given biochemical test details are successfully updated");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }
        return response;
    }

    @DeleteMapping("microvessels/delete/{id}")
    public ResponseEntity<String> deleteMicrovesselsTest(@PathVariable Long id) {
        ResponseEntity<String> response = null;
        try {
            microvesselsTestService.delete(id);
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Given test is successfully deleted");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }
        return response;
    }




}
