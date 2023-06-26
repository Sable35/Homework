package com.example.controllers;

import com.example.model.Fellow;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

  private Fellow fellow = new Fellow();
  private boolean isAuthorized = false;

  public boolean isAuthorized() {
    return isAuthorized;
  }

  @GetMapping("/OnlyFellows")
  public String viewAuthorized(Model model) {
    model.addAttribute("isAuthorized", isAuthorized=false);
    return "OnlyFellows.html";
  }

  @PostMapping("/OnlyFellows")
  public String authorizeFellow(
          @RequestParam String login,
          @RequestParam String password,
          Model model
  ) {
    if (!login.isBlank() && !password.isBlank()) {
      fellow.setLogin(login);
      fellow.setPassword(password);
      model.addAttribute("isAuthorized", isAuthorized=true);
      model.addAttribute("login", fellow.getLogin());
      model.addAttribute("password", fellow.getPassword());
    } else {model.addAttribute("isAuthorized", isAuthorized);}
    return "OnlyFellows.html";
  }

  @PostMapping("/OnlyFellowsSignedOut")
  public String signOutFellow(Model model) {
  model.addAttribute("isAuthorized", isAuthorized=false);
    return "OnlyFellows.html";
  }
}
