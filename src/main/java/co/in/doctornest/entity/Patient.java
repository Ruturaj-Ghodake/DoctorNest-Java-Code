package co.in.doctornest.entity;

import co.in.doctornest.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    @Column(nullable = false , unique = true)
    private String email;
    @NotNull
    private String password;
    @NotNull
    @Column(nullable = false , unique = true)
    private String mobileNo;
    @NotNull
    private Gender gender;
    @NotNull
    private String address;
    @NotNull
    private String state;
    @NotNull
    private String district;
    @NotNull
    private String city;
    private String appointmentId;
    @CreatedDate
    private Date createdDate;
    private boolean patientAttended;
    @ManyToMany()
    @JoinTable(name = "doctor_patient",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    )
    Set<Doctor> doctorSet;
    @LastModifiedDate
    private Date lasteModifiedDate;
}
