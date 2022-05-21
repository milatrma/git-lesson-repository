package com.gf.milatrma.urlaliaser.service;

import com.gf.milatrma.urlaliaser.entity.UrlAliaserDto;

import java.util.List;

public interface UrlAliaserService {
    String addAliasGetSecrCode (String url,String alias);
    String getUrlFromAlias(String alias);
    List<UrlAliaserDto> getAllAliasisDto();
    int getDeleteStatus(Long id, String secretCode);
}
