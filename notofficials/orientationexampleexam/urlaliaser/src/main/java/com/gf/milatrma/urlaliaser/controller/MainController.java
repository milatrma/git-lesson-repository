package com.gf.milatrma.urlaliaser.controller;

import com.gf.milatrma.urlaliaser.service.UrlAliaserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String getUrlFromAlias(@PathVariable String alias
                                  ) {
//        String gainedUrl = urlAliaserService.getUrlFromAlias(alias);
//        if (Objects.nonNull(gainedUrl)) {
////            responseEntity.getStatusCodeValue()
//            return "redirect:" + gainedUrl;
//        } else {
////            responseEntity.getStatusCode(HTTP)
//            return "redirect:/";
//        }
                String gainedUrl = urlAliaserService.getUrlFromAlias(alias);
        if (Objects.nonNull(gainedUrl)) {
//            responseEntity.getStatusCodeValue()
            return "redirect:" + gainedUrl;
        } else {
//            responseEntity.getStatusCode(HTTP)
            return "redirect:/";
        }

//        public String getUrlFromAlias(@PathVariable String alias
//                                  ) {
//        public ResponseEntity<Assignee> replaceAssignee(@RequestBody Assignee assigneeInput,
//        @PathVariable long id) {
//            if (assigneeService.checkAssignee(id)) {
//                assigneeInput.setId(id);
//// assigneeService.addAssignee(assigneeInput);
//                return ResponseEntity.ok(assigneeInput);
//            } else {
//// 404
//                return ResponseEntity.notFound().build();
//            }


    }
}
