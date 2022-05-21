package com.gf.milatrma.urlaliaser.controller;

import com.gf.milatrma.urlaliaser.service.UrlAliaserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MainController {
    public final UrlAliaserService urlAliaserService;

    @GetMapping
    public String getIndex(
            Model model,
            @RequestParam(required = false) String url,
            @RequestParam(required = false) String alias
//            @RequestParam (required = false) String success
    ) {
//        model.addAttribute("success", success);


        return "index";
    }

    //    POST /save-link
//    If the alias is already in use redirect to the main page with the error scenario
//    Else
//    Generate a secret code which is just a random 4-digit string consisting of numbers
//    Store the entry in the database
//    Redirect to the main page with the success scenario
    @PostMapping("/save-link")
    public String saveLink(RedirectAttributes redirectAttributes,
                           @RequestParam(required = false) String url,
                           @RequestParam(required = false) String alias) {
        String newSecretCode = urlAliaserService.addAliasGetSecrCode(url, alias);
        if (Objects.nonNull(newSecretCode)) {
            redirectAttributes.addFlashAttribute("success",
                    String.format("Your URL is aliased to %s and your secret code is %s", alias, newSecretCode));
        } else {
            redirectAttributes.addFlashAttribute("error", "Your alias is already in use!");
            redirectAttributes.addFlashAttribute("url", url);
            redirectAttributes.addFlashAttribute("alias", alias);
        }
        return "redirect:/";
    }

    //    GET /a/{alias}
//    If the alias exists it should increment the hit count and redirect to the URL otherwise respond with 404 status code
    @GetMapping("/a/{alias}")
//    @ResponseBody
    public String getUrlFromAlias(@PathVariable String alias,
                                  RedirectAttributes redirectAttributes
    ) {
        String gainedUrl = urlAliaserService.getUrlFromAlias(alias);
        if (Objects.nonNull(gainedUrl)) {
//            responseEntity.getStatusCodeValue()
            return "redirect:" + gainedUrl;
        } else {
//            responseEntity.getStatusCode(HTTP)
            redirectAttributes.addFlashAttribute("error", "404 status code: Not found");
            return "redirect:/";
        }


    }
}
