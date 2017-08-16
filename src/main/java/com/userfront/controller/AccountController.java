package com.userfront.controller;

import com.userfront.domain.PrimaryAccount;
import com.userfront.domain.SavingsAccount;
import com.userfront.domain.User;
import com.userfront.service.AccountService;
import com.userfront.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @RequestMapping("/primaryAccount")
    public String primaryAccount(Principal principal, Model model) {

        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        model.addAttribute("primaryAccount", primaryAccount);
        return "primaryAccount";
    }

    @RequestMapping("/savingsAccount")
    public String savingsAccount(Principal principal, Model model) {

        User user = userService.findByUsername(principal.getName());
        SavingsAccount savingsAccount = user.getSavingsAccount();
        model.addAttribute("savingsAccount", savingsAccount);
        return "savingsAccount";
    }

    @RequestMapping("/deposit")
    public String deposit(Model model) {

        model.addAttribute("accountType", "");
        model.addAttribute("amount", "");
        return "deposit";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String depositPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal) {

        accountService.deposit(accountType, Double.parseDouble(amount), principal);
        return "redirect:/userFront";
    }

    @RequestMapping("/withdraw")
    public String withdraw(Model model) {

        model.addAttribute("accountType", "");
        model.addAttribute("amount", "");
        return "withdraw";
    }
}
