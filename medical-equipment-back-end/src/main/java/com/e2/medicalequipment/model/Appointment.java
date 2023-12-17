package com.e2.medicalequipment.model;

import com.e2.medicalequipment.dto.CreateAppointmentDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;

import java.time.LocalDateTime;

@Entity
@Table(schema = "stakeholders", name = "appointments")
public class Appointment {
    @Id
    @SequenceGenerator(name = "appointmentSeq", sequenceName = "appointmentSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointmentSeq")
    private Long id;

    @Column(name = "startTime")
    private LocalDateTime startTime;

    @Column(name = "endTime")
    private LocalDateTime endTime;

    @Column(name = "companyAdministratorId")
    private Long companyAdministratorId;

    @Column(name = "customerId")
    private Long customerId;


    public Appointment() {

    }

    public Appointment(CreateAppointmentDTO appointmentDTO) {
        this.startTime = LocalDateTime.parse(appointmentDTO.startTime);
        this.endTime = LocalDateTime.parse(appointmentDTO.endTime);
        this.companyAdministratorId = appointmentDTO.companyAdministratorId;
        this.customerId = appointmentDTO.customerId;
    }

    public Appointment(Long id, LocalDateTime startTime, LocalDateTime endTime, Long companyAdministratorId, Long customerId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.companyAdministratorId = companyAdministratorId;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getcompanyAdministratorId() { return companyAdministratorId; }

    public void setcompanyAdministratorId(Long companyAdministratorId) { this.companyAdministratorId = companyAdministratorId; }

    public Long getCustomerId() { return customerId; }

    public void setCustomerId(Long customerId) { this.customerId = customerId; }
}
