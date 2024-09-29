package com.api.webapi.service;

import com.api.webapi.entity.Registration;
import com.api.webapi.exception.ResourceNotFoundException;
import com.api.webapi.payload.RegistrationDto;
import com.api.webapi.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;

        this.modelMapper = modelMapper;
    }

    public List<RegistrationDto> getRegistrations() {

        List<Registration> registrations = registrationRepository.findAll();
       List<RegistrationDto> dtos =  registrations.stream().map(r->mapToDto(r)).collect(Collectors.toList());
        return dtos;
    }

    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        //copy dto to entity
       Registration registration= mapToEntity(registrationDto);
        Registration saveEntity = registrationRepository.save(registration);


        //copy entity to dto
      RegistrationDto dto = mapToDto(saveEntity);
        return dto;
    }

    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }

    public Registration updateRegistration(long id, Registration registration) {
       Registration r= registrationRepository.findById(id).get();
      
        r.setName(registration.getName());
        r.setEmail(registration.getEmail());
        r.setMobile(registration.getMobile());
        Registration saveEntity= registrationRepository.save(r);
        return saveEntity;
    }
    Registration mapToEntity(RegistrationDto registrationDto){
      Registration registration =  modelMapper.map(registrationDto ,Registration.class);
//        Registration registration=new Registration();
//        registration.setName(registrationDto.getName());
//        registration.setEmail(registrationDto.getEmail());
//        registration.setMobile(registrationDto.getMobile());
        return registration;
    }
   RegistrationDto  mapToDto(Registration registration){
       RegistrationDto  dto = modelMapper.map(registration,RegistrationDto.class);
//       RegistrationDto dto=new RegistrationDto();
//       dto.setName( registration.getName());
//       dto.setEmail( registration.getEmail());
//       dto.setMobile( registration.getMobile());
       return dto;
   }

    public RegistrationDto getRegistrationsById(long id) {
       Registration registration =  registrationRepository.findById(id)
               .orElseThrow(
                       ()->new ResourceNotFoundException("Record not found")
               );
       return mapToDto(registration);
    }
}
