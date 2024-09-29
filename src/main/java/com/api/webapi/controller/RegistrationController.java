package com.api.webapi.controller;

import com.api.webapi.entity.Registration;
import com.api.webapi.payload.RegistrationDto;
import com.api.webapi.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations() {
        List<RegistrationDto> dtos = registrationService.getRegistrations();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RegistrationDto> createRegistration(
@RequestBody RegistrationDto registrationDto
    ) {
        RegistrationDto regDto = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(regDto,HttpStatus.CREATED);
    }
@DeleteMapping
    public ResponseEntity<String>deleteRegistration(
    @RequestParam long id
){
    registrationService.deleteRegistration(id);
    return new ResponseEntity<>("Deleted",HttpStatus.OK);
}
@PutMapping("/{id}")
public ResponseEntity<Registration>updateRegistration(
        @PathVariable long id,
        @RequestBody Registration registration
) {
   Registration updateReg= registrationService.updateRegistration(id,registration);
   return new ResponseEntity<>(updateReg,HttpStatus.OK);
}
@GetMapping("/{id}")
public ResponseEntity<RegistrationDto> getRegistrationById(
        @PathVariable long id
){
    RegistrationDto dto =  registrationService.getRegistrationsById(id);
    return new ResponseEntity<>(dto,HttpStatus.OK);
}
}
