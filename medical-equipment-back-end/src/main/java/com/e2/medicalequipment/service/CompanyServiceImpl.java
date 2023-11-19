package com.e2.medicalequipment.service;

import com.e2.medicalequipment.dto.CreateCompanyDTO;
import com.e2.medicalequipment.model.Address;
import com.e2.medicalequipment.model.Company;
import com.e2.medicalequipment.repository.AddressRepository;
import com.e2.medicalequipment.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    @Override
    public List<Company> GetAll() throws Exception {
        return this.companyRepository.findAll();
    }

    @Override
    public List<Company> Search(String name, String street, String city, String country) throws Exception {
        List<Company> companies = this.companyRepository.findAll();
        List<Company> searchedCompanies = companies.stream()
                .filter(c -> (c.getName().toLowerCase().contains(name) || name.equals("empty")) &&
                        (c.getAddress().getStreet().toLowerCase().contains(street) || street.equals("empty")) &&
                        (c.getAddress().getCity().toLowerCase().contains(city) || city.equals("empty")) &&
                        (c.getAddress().getCountry().toLowerCase().contains(country) || country.equals("empty"))).collect(Collectors.toList());
        return searchedCompanies;
    }

    @Override
    public List<Company> Filter(String rate, List<Company> companies) throws Exception {
        Double rating = Double.parseDouble(rate);
        List<Company> filteredCompanies = companies.stream()
                .filter(c -> c.getAverageRating() > (rating-1) && c.getAverageRating() <= rating).collect(Collectors.toList());
        return filteredCompanies;
    }
}
