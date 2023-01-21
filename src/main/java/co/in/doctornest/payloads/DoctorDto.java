package co.in.doctornest.payloads;

import co.in.doctornest.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class DoctorDto {
    private long id;
    @NotNull(message = "First name should not be null or blank")
    @Size(min = 2 , message = "First name contains atleast 2 characters")
    private String firstName;
    @NotBlank(message = "Last name should not be null or blank")
    @Size(min = 2 , message = "Last name contains atleast 2 characters")
    private String lastName;
    @Email(message = "Enter correct email")
    @NotBlank(message = "Email should not be null or blank")
    private String email;
    @NotBlank(message = "Password should not be null or blank")
    private String password;
    @NotBlank(message = "Mobile number should not be null or blank")
    private String mobileNo;
    private Gender gender;
    private int hospitalId;
    @NotBlank(message = "Address1 name should not be null or blank")
    private String address1;
    @NotBlank(message = "Address2 should not be null or blank")
    private String address2;
    private String address3;
    @NotBlank(message = "State should not be null or blank")
    private String state;
    @NotBlank(message = "District should not be null or blank")
    private String district;
    @NotBlank(message = "City should not be null or blank")
    private String city;
    @NotBlank(message = "Specialist should not be null or blank")
    private String specialist;
    @NotBlank(message = "RegistrationNo should not be null or blank")
    private String registrationNo;
    @NotBlank(message = "StateCouncil should not be null or blank")
    private String stateCouncil;
    private Date createdDate;
    private Date lasteModifiedDate;
}
