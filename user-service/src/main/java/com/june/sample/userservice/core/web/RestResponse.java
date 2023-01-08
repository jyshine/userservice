package com.june.sample.userservice.core.web;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
public class RestResponse<T> implements Serializable {

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    | Private Variables
    |-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
    private static final long serialVersionUID = -7857710275656843711L;

    // Data
    @Getter
    private T data;

    @Getter
    private RestResponseMeta meta = new RestResponseMeta();

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    | Constructor
    |-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

    /**
     * 
     */
    public RestResponse() {
    }

    /**
     * 
     */
    @SuppressWarnings("rawtypes")
    public RestResponse(T data) {
        this.data = data;
    }

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    | Getter & Setter Method
    |-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
    /**
     * useMessage
     */
    public void setUserMessage(String message) {
        this.meta.setUserMessage(message);
    }

    /**
     * systemMessage
     */
    public void setSystemMessage(String systemMessage) {
        this.meta.setSystemMessage(systemMessage);
    }

    /**
     * code
     */
    public void setCode(String code) {
        this.meta.setCode(code);
    }

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    | Public Method for chaining
    |-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
    public RestResponse userMessage(String userMessage) {
        this.setUserMessage(userMessage);
        return this;
    }

    public RestResponse systemMessage(String systemMessage) {
        this.setSystemMessage(systemMessage);
        return this;
    }

    public RestResponse code(String code) {
        this.setCode(code);
        return this;
    }

    /*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    | Inner Class
    |-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/
    @Getter
    @Setter
    private class RestResponseMeta implements Serializable {
        private static final long serialVersionUID = 144476231638185217L;
        
        private String userMessage = "";
        private String systemMessage = "";
        private String code = "";
    }
}
