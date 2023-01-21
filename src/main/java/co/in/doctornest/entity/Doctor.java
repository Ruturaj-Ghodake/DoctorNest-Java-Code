package co.in.doctornest.entity;

import co.in.doctornest.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Column(nullable = false , unique = true)
    private String email;

    private String password;
    //@Pattern(regexp="(^$|[0-9]{10})")
    @Column(nullable = false , unique = true)
    private String mobileNo;
    private Gender gender;
    private int hospitalId;
    @NotNull
    private String address1;
    @NotNull
    private String address2;
    private String address3;
    private String state;
    private String district;
    private String city;
    private String specialist;
    private String registrationNo;
    private String stateCouncil;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lasteModifiedDate;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy ="doctorSet")
    Set<Patient> patientSet;
}
