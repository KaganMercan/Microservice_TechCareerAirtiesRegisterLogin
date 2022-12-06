package com.kaganmercan.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kaganmercan.exception.ResourceNotFoundException;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//lombok
@Data

//Backendten FrontEnt'e veri göndermek için kullanacağım

//jackson: objeyi json çevirir.
//validationError: null ise dönemsini istemeyebiliriz.

//Eğer null dönerse ekranda nu null olanı gösterme.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult {

    private String timestamp = nowDate();
    private int status;
    private ResourceNotFoundException error;
    private String message;
    private String path;

    //Now Date
    private String nowDate() {
        Locale locale = new Locale("tr", "TR");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MMMM.yyyy HH:mm:ss", locale);
        Date date = new Date();
        String change = simpleDateFormat.format(date);
        System.out.println(change);
        return change;
    }

    //parametreli constructor
    public ApiResult(int status,  String message, String path) {
        this.timestamp = nowDate();
        this.status = status;
        this.message = message;
        this.path = path;
    }


    public ApiResult(int status, ResourceNotFoundException error, String message, String path) {
        this.timestamp = nowDate();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
