package com.kodluyoruz.playlist.exception;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
class ExceptionDetail {

    private String message;
    private Date timeStamp;
}