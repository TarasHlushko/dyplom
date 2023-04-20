package com.tarashluhsko.dyplom.controllers;

import com.tarashluhsko.dyplom.model.Doctor;
import com.tarashluhsko.dyplom.services.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Doctor doctor) {
        ResponseEntity response = null;
        try {
            System.out.println("///////////////   " + doctor.getLastName());
            Doctor savedDoctor = doctorService.create(doctor);
            if (savedDoctor.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        } catch (Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }

        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Doctor doctor) {
        ResponseEntity response;

        try {
            doctorService.update(doctor);

                response = ResponseEntity.status(HttpStatus.OK)
                        .body("Given doctor's details were successfully updated");

        } catch (Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred due to " + e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        ResponseEntity response = null;

        try {
            doctorService.delete(id);
            response = ResponseEntity.status(HttpStatus.OK)
                    .body("Given user was successfully deleted");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }
        return response;
    }
}
