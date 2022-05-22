package com.gf.milatrma.urlaliaser.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor  //   is not allowed here!

public class UrlAliaserDeleteDto {
    private String secretCode;

//    @JsonCreator
//    public UrlAliaserDeleteDto(@JsonProperty("secretCode") String secretCode) {
//        this.secretCode = secretCode;
//    }
}
