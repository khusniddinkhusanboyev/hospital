package uz.hospital.response_api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseApi  {
    private boolean success;
    private String message;
    private Object data;


}
