package com.tarashluhsko.dyplom.controllers;

import com.tarashluhsko.dyplom.model.Doctor;
import com.tarashluhsko.dyplom.model.Roles;
import com.tarashluhsko.dyplom.services.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;


@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    private final PasswordEncoder passwordEncoder;

    public DoctorController(DoctorService doctorService, PasswordEncoder passwordEncoder) {
        this.doctorService = doctorService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody Doctor doctor) {
        ResponseEntity response = null;
        try {
            System.out.println("///////////////   " + doctor.getLastName());
            String hashPwd = passwordEncoder.encode(doctor.getPassword());
            doctor.setPassword(hashPwd);
            doctor.setRole("ROLE_DOCTOR");
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


    @GetMapping("/getDoctor")
    public Doctor getDoctor(@RequestParam Long id) {
        return doctorService.findById(id);
    }
}
