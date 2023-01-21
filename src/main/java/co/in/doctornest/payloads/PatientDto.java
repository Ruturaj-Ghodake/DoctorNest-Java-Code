package co.in.doctornest.payloads;

import co.in.doctornest.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class PatientDto {
    private long id;
    @NotNull(message = "First name should not be null or blank")
    @Size(min = 2 , message = "First name contains atleast 2 characters")
    private String firstName;
    @NotNull(message = "Last name should not be null or blank")
    @Size(min = 2 , message = "Last name contains atleast 2 characters")
    private String lastName;
    @Email(message = "Enter correct email")
    @NotBlank(message = "Email should not be null or blank")
    private String email;
    @NotBlank(message = "Password should not be null or blank")
    private String password;
    @NotBlank(message = "Mobile number should not be null or blank")
    @Size(min = 10 , message = "Mobile number should be 10 digit")
    private String mobileNo;
    private Gender gender;
    @NotBlank(message = "Address number should not be null or blank")
    private String address;
    @NotBlank(message = "State number should not be null or blank")
    private String state;
    @NotBlank(message = "District number should not be null or blank")
    private String district;
    @NotBlank(message = "City number should not be null or blank")
    private String city;
    private String appointmentId;
    private Set<DoctorDto> doctorDto;
    private Date createdDate;
    private Date lasteModifiedDate;
}
