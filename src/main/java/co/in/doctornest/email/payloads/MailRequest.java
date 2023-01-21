package co.in.doctornest.email.payloads;

import lombok.Data;
@Data
public class MailRequest {
    private String to;
    private String subject;

}
