package com.gf.milatrma.urlaliaser.service;

import com.gf.milatrma.urlaliaser.entity.UrlAliaser;
import com.gf.milatrma.urlaliaser.entity.UrlAliaserDeleteDto;
import com.gf.milatrma.urlaliaser.entity.UrlAliaserDto;
import com.gf.milatrma.urlaliaser.repository.UrlAliaserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UrlAliaserServiceImpl implements UrlAliaserService {
    private final UrlAliaserRepository urlAliaserRepository;

    @Override
    public String addAliasGetSecrCode(String url, String alias) {
        if (urlAliaserRepository.findUrlAliaserByAlias(alias).isPresent()) {
            return null;
        } else {
            UrlAliaser urlAliaser = new UrlAliaser(url, alias);
            urlAliaserRepository.save(urlAliaser);
            return urlAliaser.getSecretCode();
        }
    }

    @Override
    public String getUrlFromAlias(String alias) {
        Optional<UrlAliaser> urlAliaser = urlAliaserRepository.findUrlAliaserByAlias(alias);
        if (urlAliaser.isPresent()) {
            urlAliaser.get().setHitCount(urlAliaser.get().getHitCount() + 1);
            urlAliaserRepository.save(urlAliaser.get());
            return urlAliaser.get().getUrl();
        } else {
            return null;
        }
    }

    @Override
    public List<UrlAliaserDto> getAllAliasisDto() {
        List<UrlAliaser> urlAliasers;
        urlAliasers = urlAliaserRepository.findAll();
        return urlAliasers.stream()
                .map(x -> new UrlAliaserDto(x.getId(), x.getUrl(),
                        x.getAlias(), x.getHitCount()))
                .collect(Collectors.toList());
    }

    @Override
    public int getDeleteStatus(Long id, UrlAliaserDeleteDto urlAliaserDeleteDto) {
        if (urlAliaserRepository.findById(id).isEmpty()) {
            return 404;
        }
        Optional<UrlAliaser> urlAliaser = urlAliaserRepository.findById(id);
        if (!urlAliaser.get().getSecretCode().equals(urlAliaserDeleteDto.getSecretCode())||
                Objects.isNull(urlAliaserDeleteDto.getSecretCode())) {
            return 403;
        } else {
            urlAliaserRepository.deleteById(id);
            return 202;
        }
    }
}
