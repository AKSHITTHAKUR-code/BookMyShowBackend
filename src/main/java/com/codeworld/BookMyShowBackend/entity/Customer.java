package com.codeworld.BookMyShowBackend.entity;


import jakarta.persistence.*;

@Entity
@Table(name="customers",uniqueConstraints ={ @UniqueConstraint(name=" uk_customer_phone",columnNames = "phone"),@UniqueConstraint(name="uk_customer_email",columnNames = "email")
})
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String name;
    @Column(nullable = false,length = 200)
    private String email;
    @Column(nullable = false,length = 20)
    private String phoneNumber;

    public Customer()
    {

    }

    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
