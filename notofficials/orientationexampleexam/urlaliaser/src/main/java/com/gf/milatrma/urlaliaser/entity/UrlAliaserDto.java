package com.gf.milatrma.urlaliaser.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
public class UrlAliaserDto {
    private Long id;
    private String url;
    private String alias;
    private int hitCount;

}
