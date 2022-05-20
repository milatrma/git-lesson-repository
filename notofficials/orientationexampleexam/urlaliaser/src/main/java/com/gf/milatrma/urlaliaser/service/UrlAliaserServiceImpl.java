package com.gf.milatrma.urlaliaser.service;

import com.gf.milatrma.urlaliaser.entity.UrlAliaser;
import com.gf.milatrma.urlaliaser.repository.UrlAliaserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlAliaserServiceImpl implements UrlAliaserService {
    private final UrlAliaserRepository urlAliaserRepository;

    @Override
    public String addAliasGetSecrCode (String url,String alias) {
        if (urlAliaserRepository.findUrlAliaserByAlias(alias).isPresent()) {
            return null;
        } else {
            UrlAliaser urlAliaser = new UrlAliaser(url,alias);
            urlAliaserRepository.save(urlAliaser);
            return urlAliaser.getSecretCode();
        }
    }

    @Override
    public String getUrlFromAlias(String alias) {
        var urlAliaser = urlAliaserRepository.findUrlAliaserByAlias(alias);
        if (urlAliaser.isPresent()) {
            urlAliaser.get().setHitCount(urlAliaser.get().getHitCount()+1);
            urlAliaserRepository.save(urlAliaser.get());
            return urlAliaser.get().getUrl();
        } else {
            return null;
        }
    }
}
