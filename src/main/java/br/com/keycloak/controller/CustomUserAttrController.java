package br.com.keycloak.controller;


import java.security.Principal;
import java.util.Map;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomUserAttrController {

    @GetMapping(path = "/users")
    public String getUserInfo(Model model) {

        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext()
            .getAuthentication();

        final Principal principal = (Principal) authentication.getPrincipal();

        if (principal instanceof KeycloakPrincipal) {

            @SuppressWarnings("unchecked")
			KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            IDToken token = kPrincipal.getKeycloakSecurityContext().getIdToken();
            Map<String, Object> customClaims = token.getOtherClaims();
            
            model.addAttribute("nomeUsuario",token.getName()!=null?token.getName():principal.getName());
            model.addAttribute("email",token.getEmail());
            model.addAttribute("dataNascimento", String.valueOf(customClaims.get("nascimento")));
            model.addAttribute("idToken", kPrincipal.getKeycloakSecurityContext().getIdTokenString());
            model.addAttribute("token", kPrincipal.getKeycloakSecurityContext().getTokenString());
        }
        
        return "userInfo";
    }
  
}