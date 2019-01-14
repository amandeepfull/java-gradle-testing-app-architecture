package com.httphunt.exception;


import com.httphunt.model.response.HttpHuntApiResp;

public class HttpHuntApiException extends Exception{
   private HttpHuntApiResp apiResp;

   public HttpHuntApiException(String message){
       super(message);
   }

   public HttpHuntApiException(String message, int statusCode){
       this(message);
       this.apiResp = new HttpHuntApiResp(message, statusCode);
   }

   public HttpHuntApiException(String message, Throwable cause){
       super(message,cause);
       this.apiResp = new HttpHuntApiResp(message);
   }

   public HttpHuntApiException(String message, int statusCode, Throwable cause){
       super(message, cause);
       this.apiResp = new HttpHuntApiResp(message, statusCode);
   }

   public HttpHuntApiResp getApiResp(){
       return apiResp;
   }

}
