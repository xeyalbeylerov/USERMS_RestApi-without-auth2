package com.company.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ResponseDTO {
    private Integer errorCode;
    private String errorMessage;
    private String successMessage;
    private Object obj;

    public static ResponseDTO of(Object obj) {
        ResponseDTO resp = new ResponseDTO();
        resp.setObj(obj);
        return resp;
    }
    public static ResponseDTO of(Object obj,String successMessage) {
        ResponseDTO resp = new ResponseDTO();
        resp.setObj(obj);
        resp.setSuccessMessage(successMessage);
        return resp;
    }
    public static ResponseDTO of(Integer errorCode, String errorMessage) {
        ResponseDTO resp = new ResponseDTO();
        resp.setErrorCode(errorCode);
        resp.setErrorMessage(errorMessage);
        return resp;
    }

    public static ResponseDTO of(Object obj,Integer errorCode,String errorMessage) {
        ResponseDTO resp = new ResponseDTO();
        resp.setObj(obj);
        resp.setErrorCode(errorCode);
        resp.setErrorMessage(errorMessage);
        return resp;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", successMessage='" + successMessage + '\'' +
                ", obj=" + obj +
                '}';
    }
}
