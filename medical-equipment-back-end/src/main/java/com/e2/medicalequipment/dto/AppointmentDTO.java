package com.e2.medicalequipment.dto;

public class AppointmentDTO {
    public Long id;
    public String start;
    public String end;
    public CompanyAdministratorDTO companyAdministratorDTO;
    public UpdateCustomerDTO customerDTO;

    public AppointmentDTO(Long id, String start, String end, CompanyAdministratorDTO companyAdministratorDTO, UpdateCustomerDTO customerDTO) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.companyAdministratorDTO = companyAdministratorDTO;
        this.customerDTO = customerDTO;
    }
}
