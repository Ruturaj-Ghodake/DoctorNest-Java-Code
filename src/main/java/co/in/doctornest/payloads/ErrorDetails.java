package co.in.doctornest.payloads;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class ErrorDetails {
    private LocalDateTime dateTime;
    private String errorMessage;
    private String URL;
}
