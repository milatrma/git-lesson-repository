package com.gf.milatrma.urlaliaser.repository;

import com.gf.milatrma.urlaliaser.entity.UrlAliaser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UrlAliaserRepository extends JpaRepository<UrlAliaser, Long> {

    Optional<UrlAliaser> findUrlAliaserByAlias(String alias);

}
