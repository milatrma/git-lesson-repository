package com.gf.milatrma.urlaliaser.service;

public interface UrlAliaserService {
    String addAliasGetSecrCode (String url,String alias);
    String getUrlFromAlias(String alias);
}
