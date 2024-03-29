package com.e2.medicalequipment.model;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.e2.medicalequipment.dto.CreateAppointmentDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    @Column(name = "isPredefined")
    private boolean isPredefined;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "company_administrator_id", referencedColumnName = "company_administrator_id")
    private CompanyAdministrator companyAdministrator;

    public Appointment() {

    }


    /*public Appointment(CreateAppointmentDTO appointmentDTO) {
        String start = appointmentDTO.startTime;
        String end = appointmentDTO.endTime;
        System.out.println("\n\n\n");
        System.out.println("\nstart" + start);
        System.out.println("\n\n\n");

        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzzz)");

        // Define the input pattern to match the provided string
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (zzzz)", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

        try {
            Date inputDateA = inputFormat.parse(start);
            String outputDateStringA = outputFormat.format(inputDateA);
            System.out.println("\ninputDateA " + inputDateA);
            System.out.println("\nFormatted Date: " + outputDateStringA);

            Date inputDateB = inputFormat.parse(end);
            String outputDateStringB = outputFormat.format(inputDateB);

            // Parse the string using the defined pattern
            LocalDateTime parsedDateTimeA = LocalDateTime.parse(outputDateStringA, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            System.out.println("Parsed LocalDateTimeA: " + parsedDateTimeA);
            LocalDateTime parsedDateTimeB = LocalDateTime.parse(outputDateStringB, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            System.out.println("Parsed LocalDateTimeB: " + parsedDateTimeB);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/


    public Appointment(Long id, LocalDateTime start, LocalDateTime end,boolean isPredefined, CompanyAdministrator companyAdministrator) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isPredefined = isPredefined;
        this.companyAdministrator = companyAdministrator;
    }

    public Appointment(Appointment appointment) {
        this.id = appointment.id;
        this.startTime = appointment.startTime;
        this.endTime = appointment.endTime;
        this.isPredefined = appointment.isPredefined;
        // Copying company administrator
        if (appointment.companyAdministrator != null) {
            this.companyAdministrator = new CompanyAdministrator(appointment.companyAdministrator);
        }
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

    public boolean getIsPredefined() {
        return isPredefined;
    }

    public void setIsPredefined(boolean isPredefined) {
        this.isPredefined = isPredefined;
    }

    public CompanyAdministrator getCompanyAdministrator() {
        return companyAdministrator;
    }

    public void setCompanyAdministrator(CompanyAdministrator companyAdministrator) {
        this.companyAdministrator = companyAdministrator;
    }

}
