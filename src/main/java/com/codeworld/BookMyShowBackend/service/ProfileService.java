package com.codeworld.BookMyShowBackend.service;


import com.codeworld.BookMyShowBackend.dto.CreateProfileRequest;
import com.codeworld.BookMyShowBackend.dto.ProfileResponse;
import com.codeworld.BookMyShowBackend.entity.Customer;
import com.codeworld.BookMyShowBackend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProfileService {

    public final CustomerRepository customerRepository;


    public ProfileService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

public ProfileResponse create( CreateProfileRequest  request)
{
    String email= request.Email().trim().toLowerCase(Locale.ROOT);
    String phone=normalizePhone(request.phone());
    if(customerRepository.existsByEmail(email))
    {
        throw new ProfileConflictException("Email already exist with this email");
    }

    if(customerRepository.existsByPhoneNumber(phone))
    {
        throw new ProfileConflictException("Phone number already exist with this email");
    }
    return ProfileResponse.from(customerRepository.save(new Customer(request.Name().trim(),email,phone)));
}



public ProfileResponse login (String identifier) {
    String value = identifier == null ? "" : identifier.trim();

    String phone = value.replaceAll("\\D", "");
    Customer customer = value.contains("@")
            ? customerRepository.findByEmail(value.toLowerCase(Locale.ROOT)).orElse(null)
            : customerRepository.findByPhoneNumber(phone).orElse(null);
    if (customer == null) {
        throw new ProfileConflictException("no profile found for this  email or phone number");
    }
    return ProfileResponse.from(customer);
}



  private String normalizePhone( String phone){
        String normalize=phone==null ? "" : phone.replaceAll("\\D","");
        if(normalize.length()<10 || normalize.length()>15)
        {
            throw new IllegalArgumentException("enter valid phone number");
        }
        return normalize;
    }

}

