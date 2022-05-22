package com.gf.milatrma.urlaliaser.controller;

import com.gf.milatrma.urlaliaser.entity.UrlAliaserDeleteDto;
import com.gf.milatrma.urlaliaser.entity.UrlAliaserDto;
import com.gf.milatrma.urlaliaser.service.UrlAliaserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class UrlAliaserApiController {
    //    GET /api/links
//    It should respond with the stored entries in the following JSON format
//    Note that the secret code is not included
//            [
//    {
//        "id": 0,
//            "url": "http://reddit.com",
//            "alias": "bye-bye-time",
//            "hitCount": 0
//    },
//    {
//        "id": 1,
//            "url": "http://youtube.com",
//            "alias": "watch-videos",
//            "hitCount": 4
//    }
//]
    private final UrlAliaserService urlAliaserService;

    @GetMapping("/api/links")
    public List<UrlAliaserDto> getApis() {
        return urlAliaserService.getAllAliasisDto();
    }

    //DELETE /api/links/{id}
//        The secret code should be in the request's body in JSON format
//        {
//        "secretCode": "0483"
//        }
//        If it doesn't exists respond with 404 status code
//        If it exists but the provided secret code doesn't match respond with 403 status code
//        If it exists and the provided secret code matches delete the entry from the database and respond with 204 status code
    @DeleteMapping("/api/links/{id}")
    public ResponseEntity<Long> deleteAlias(@PathVariable("id") Long id, @RequestBody (required = false) UrlAliaserDeleteDto urlAliaserDeleteDto) {
        int status = urlAliaserService.getDeleteStatus(id, urlAliaserDeleteDto);
        return ResponseEntity.status(status).build();

    }
}

