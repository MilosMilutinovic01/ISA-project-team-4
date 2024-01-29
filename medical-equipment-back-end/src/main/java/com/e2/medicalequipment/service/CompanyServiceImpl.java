package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCompanyDTO;
import com.e2.medicalequipment.dto.UpdateCompanyDTO;
import com.e2.medicalequipment.model.*;
import com.e2.medicalequipment.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company Create(CreateCompanyDTO createCompanyDto) throws Exception {
        Address address = new Address(createCompanyDto.address);
        Company company = new Company(createCompanyDto);
        company.setAddress(address);
        company.setAverageRating(0L);
        LocalTime startTime = LocalTime.parse(createCompanyDto.startTime, formatter);
        company.setStartTime(startTime);
        LocalTime endTime = LocalTime.parse(createCompanyDto.endTime, formatter);
        company.setEndTime(endTime);

        if (company.getId() != null) {
            throw new Exception("ID must be null for a new entity.");
        }
        Company savedCompany = companyRepository.save(company);
        return savedCompany;
    }

    public Company Get(String id) {
        return companyRepository.findById(Long.valueOf(id)).get();
    }
    public Company Update(UpdateCompanyDTO companyDTO) throws Exception{
        Company company = new Company(companyDTO);
        Address address = new Address(companyDTO.address);
        if ((company.getId() == null) || (address.getId() == null)){
            throw new Exception("ID must not be null for updating entity.");
        }
        company.setAddress(address);
        LocalTime startTime = LocalTime.parse(companyDTO.startTime, formatter);
        company.setStartTime(startTime);
        LocalTime endTime = LocalTime.parse(companyDTO.endTime, formatter);
        company.setEndTime(endTime);
        Company savedCompany = companyRepository.save(company);
        return savedCompany;
    }

    @Override
    public List<Company> GetAll() throws Exception {
        return this.companyRepository.findAll();
    }

    @Override
    public List<Company> Search(String name, String street, String city, String country) throws Exception {
        List<Company> companies = this.companyRepository.findAll();
        List<Company> searchedCompanies = companies.stream()
                .filter(c -> (c.getName().toLowerCase().contains(name.toLowerCase()) || name.equals("empty")) &&
                        (c.getAddress().getStreet().toLowerCase().contains(street.toLowerCase()) || street.equals("empty")) &&
                        (c.getAddress().getCity().toLowerCase().contains(city.toLowerCase()) || city.equals("empty")) &&
                        (c.getAddress().getCountry().toLowerCase().contains(country.toLowerCase()) || country.equals("empty"))).collect(Collectors.toList());
        return searchedCompanies;
    }

    @Override
    public List<Company> Filter(String rate, List<Company> companies) throws Exception {
        Double rating = Double.parseDouble(rate);
        List<Company> filteredCompanies = companies.stream()
                .filter(c -> c.getAverageRating() >= (rating-1) && c.getAverageRating() <= rating).collect(Collectors.toList());
        return filteredCompanies;
    }
}
