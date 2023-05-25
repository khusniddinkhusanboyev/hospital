package uz.hospital.response_api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ResponseApi  {

    private boolean success;
    private String message;
    private Object data;


}
