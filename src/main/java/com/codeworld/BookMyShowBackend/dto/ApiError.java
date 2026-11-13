package com.codeworld.BookMyShowBackend.dto;

import java.io.Serializable;
import java.time.Instant;

public record ApiError(Instant timestamp,int status, String error,String message,String path)
{

}
