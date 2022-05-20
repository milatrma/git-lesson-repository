package com.gf.milatrma.urlaliaser.entity;

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
@NoArgsConstructor
@Entity
public class UrlAliaser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String alias;
    private String secretCode;
    private int hitCount;

    public UrlAliaser(String url, String alias) {
//    public UrlAliaser(String url, String alias, String secretCode) {
        this.url = url;
        this.alias = alias;
//        this.secretCode = secretCode;
        Random random = new Random();
        this.secretCode = String.format("%04d", random.nextInt(10000));

    }
}
