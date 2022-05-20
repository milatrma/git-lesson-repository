package com.gf.milatrma.urlaliaser;

import com.gf.milatrma.urlaliaser.entity.UrlAliaser;
import com.gf.milatrma.urlaliaser.repository.UrlAliaserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class UrlaliaserApplication implements CommandLineRunner {
    private final UrlAliaserRepository urlAliaserRepository;

    public static void main(String[] args) {
        SpringApplication.run(UrlaliaserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UrlAliaser urlAliaser = new UrlAliaser();
        urlAliaser.setUrl("myUrl");
        urlAliaser.setAlias("myAlias");
//        urlAliaserRepository.save(urlAliaser);

    }
}
