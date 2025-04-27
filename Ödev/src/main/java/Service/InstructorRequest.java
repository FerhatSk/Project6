package Service;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InstructorRequest {
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationalIdentity;
    private String email;
    private String password;
    private String companyName;


}