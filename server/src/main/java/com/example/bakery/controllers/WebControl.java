package com.example.bakery.controllers;

import com.example.bakery.models.Admin;
import com.example.bakery.models.AllRole;
import com.example.bakery.models.Baker;
import com.example.bakery.models.Client;
import com.example.bakery.repositories.AdminRepository;
import com.example.bakery.repositories.AllRoleRepository;
import com.example.bakery.repositories.BakerRepository;
import com.example.bakery.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Null;
import java.util.Arrays;

@Controller
public class WebControl {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BakerRepository bakerRepository;

    @Autowired
    private AllRoleRepository allRoleRepository;

    @Autowired
    private AdminRepository adminRepository;


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/home";
    }



    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/registration/client")
    public String registrationClient() {
        return "registrationForClient";
    }

    @PostMapping("/registration/client")
    public String doRegistrationClient(@RequestParam String name, @RequestParam String password, @RequestParam String login, @RequestParam String surname, @RequestParam String email) {
        AllRole allRoles=allRoleRepository.findByUserlogin(login);

        if(allRoles!=null)
        {
            return "registrationForClient";
        }

        else {
            Client new_client = new Client();
            AllRole new_allRole=new AllRole();
            new_allRole.login=login;
            new_allRole.password=password;
            new_allRole.type="ROLE_CLIENT";

            new_client.name = name;
            new_client.login = login;
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            new_client.password = passwordEncoder.encode(password);
            new_client.surname = surname;
            new_client.yourBaker = "default";
            new_client.type="ROLE_CLIENT";
            new_client.email = email;

            ResponseEntity.ok(allRoleRepository.save(new_allRole));
            ResponseEntity.ok(clientRepository.save(new_client));
            return "login";}
    }

    @GetMapping("/registration/baker")
    public String registrationBaker() {
        return "registrationForBaker";
    }

    @PostMapping("/registration/baker")
    public String doRegistration_new_baker(@RequestParam String name, @RequestParam String password, @RequestParam String login, @RequestParam String specialisation, @RequestParam String surname, @RequestParam String email) {
        AllRole allRole=allRoleRepository.findByUserlogin(login);
        if(allRole!=null)
        {return "registrationForBaker";}
        else
        {
            AllRole new_allrole=new AllRole();
            new_allrole.login=login;
            new_allrole.password=password;
            new_allrole.type="ROLE_BAKER";

            Baker new_baker = new Baker();
            new_baker.name = name;
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            new_baker.password = passwordEncoder.encode(password);
            new_baker.login = login;
            new_baker.email = email;
            new_baker.specialisation = specialisation;
            new_baker.surname = surname;
            new_baker.yourClient = "default";
            new_baker.type="ROLE_BAKER";
            new_baker.ordersCount = 0;
            new_baker.finishedOrdersCount = 0;

            ResponseEntity.ok(allRoleRepository.save(new_allrole));
            ResponseEntity.ok(bakerRepository.save(new_baker));
            return "login";
        }
    }

    @GetMapping("/registration/admin")
    public String registrationAdmin() {
        return "registrationForAdmin";
    }

    @PostMapping("/registration/admin")
    public String doRegistration_new_admin(@RequestParam String name, @RequestParam String password, @RequestParam String link, @RequestParam String login, @RequestParam String position, @RequestParam String surname) {
        AllRole allrole=allRoleRepository.findByUserlogin(login);
        if(allrole!=null)
        {return "registrationForAdmin";}
        else
        {AllRole new_allrole=new AllRole();
            new_allrole.login=login;
            new_allrole.password=password;
            new_allrole.type="ROLE_ADMIN";
            Admin new_admin = new Admin();
            new_admin.name = name;
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            new_admin.password = passwordEncoder.encode(password);
            new_admin.link = link;
            new_admin.login = login;
            new_admin.position = position;
            new_admin.surname = surname;
            new_admin.type="ROLE_ADMIN";

            ResponseEntity.ok(allRoleRepository.save(new_allrole));
            ResponseEntity.ok(adminRepository.save(new_admin));
            return "login";
        }
    }


    @GetMapping("/home")
    public String home(@RequestParam(name = "type",required = false,defaultValue = "300") String name,Model model) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();

        if(allRoleRepository.findByUserlogin(auth1.getName()).type.equals("ROLE_CLIENT"))
        {
            model.addAttribute("type","/client_page");
            return "redirect:/client_page";

        }
        else if(allRoleRepository.findByUserlogin(auth1.getName()).type.equals("ROLE_BAKER"))
        {model.addAttribute("type","/baker_page");
        return "redirect:/baker_page";}
        else{
            model.addAttribute("type","/admin_page");
            return "redirect:/admin_page";
        }
    }

    @GetMapping("/mainPage")
    public String mainPage(){
        return "mainPage";
    }

    @GetMapping("/client_page")
    public String clientPage(@RequestParam(name = "name",required = false,defaultValue = "300") String name,Model model) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name",clientRepository.findByClientlogin(auth1.getName()).name);
        model.addAttribute("surname",clientRepository.findByClientlogin(auth1.getName()).surname);
        model.addAttribute("login",clientRepository.findByClientlogin(auth1.getName()).login);
        model.addAttribute("email",clientRepository.findByClientlogin(auth1.getName()).email);
        model.addAttribute("baker",clientRepository.findByClientlogin(auth1.getName()).yourBaker);
        return "clientPage";
    }
    @GetMapping("/baker_page")
    public String bakerPage(@RequestParam(name = "name",required = false,defaultValue = "300") String name,Model model) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name",bakerRepository.findByBakerlogin(auth1.getName()).name);
        model.addAttribute("surname",bakerRepository.findByBakerlogin(auth1.getName()).surname);
        model.addAttribute("login",bakerRepository.findByBakerlogin(auth1.getName()).login);
        model.addAttribute("specialisation",bakerRepository.findByBakerlogin(auth1.getName()).specialisation);
        model.addAttribute("client",bakerRepository.findByBakerlogin(auth1.getName()).yourClient);
        return "bakerPage";
    }

    @GetMapping("/admin_page")
    public String adminPage(@RequestParam(name = "name",required = false,defaultValue = "300") String name,Model model) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name",adminRepository.findByAdminlogin(auth1.getName()).name);
        model.addAttribute("surname",adminRepository.findByAdminlogin(auth1.getName()).surname);
        model.addAttribute("login",adminRepository.findByAdminlogin(auth1.getName()).login);
        model.addAttribute("link",adminRepository.findByAdminlogin(auth1.getName()).link);
        model.addAttribute("position",adminRepository.findByAdminlogin(auth1.getName()).position);
        return "adminPage";
    }

    @GetMapping("/my_baker")
    public String getBaker(@RequestParam(name = "name",required = false,defaultValue = "300") String name, Model model) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("client", clientRepository.findByClientBaker(auth1.getName()));
        model.addAttribute("baker",clientRepository.findByClientlogin(auth1.getName()).yourBaker);
        return "myBaker";
    }

    @GetMapping("/my_client")
    public String getClient(@RequestParam(name = "name",required = false,defaultValue = "300") String name, Model model) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("baker", bakerRepository.findByBakerClient(auth1.getName()));
        model.addAttribute("client",bakerRepository.findByBakerlogin(auth1.getName()).yourClient);
        return "myClient";
    }

    @GetMapping("/free_client")
    public String freeClient(@RequestParam(name = "name",required = false,defaultValue = "300") String name, Model model) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("client", clientRepository.findFreeClient());
        return "freeClient";
    }

    @GetMapping("/spec_cakes")
    public String cakesSpec(@RequestParam(name = "specialisation",required = false,defaultValue = "300") String specialisation, Model model) {
        model.addAttribute("baker_cake", bakerRepository.findSpecCakes());
        return "cakeSpec";
    }

    @GetMapping("/spec_cookies")
    public String cookiesSpec(@RequestParam(name = "specialisation",required = false,defaultValue = "300") String specialisation, Model model) {
        model.addAttribute("baker_cookie", bakerRepository.findSpecBiscuits());
        return "cookiesSpec";
    }

    @GetMapping("/spec_cupcakes")
    public String cupcakesSpec(@RequestParam(name = "specialisation",required = false,defaultValue = "300") String specialisation, Model model) {
        model.addAttribute("baker_cupcake", bakerRepository.findSpecCupcakes());
        return "cupcakesSpec";
    }

    @GetMapping("/spec_pies")
    public String piesSpec(@RequestParam(name = "specialisation",required = false,defaultValue = "300") String specialisation, Model model) {
        model.addAttribute("baker_pie", bakerRepository.findSpecPies());
        return "pieSpec";
    }

    @GetMapping("/spec_other")
    public String otherSpec(@RequestParam(name = "specialisation",required = false,defaultValue = "300") String specialisation, Model model) {
        model.addAttribute("baker_other", bakerRepository.findSpecOther());
        return "otherSpec";
    }

    @GetMapping("/target")
    public String target(Model model) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        if (clientRepository.findByClientlogin(auth1.getName()).yourBaker.equals("default")){
            return "target";
        }
        else {
            model.addAttribute("baker", clientRepository.findByClientlogin(auth1.getName()).yourBaker);
            return "changeBaker";
        }
    }

    @PostMapping("/target")
    public String target(@RequestParam(required = false) String yourBaker) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        String yourClient = clientRepository.findByClientlogin(auth1.getName()).login;
        String oldBakerLog = clientRepository.findByClientlogin(yourClient).yourBaker;
        String oldClientLog = bakerRepository.findByBakerlogin(yourBaker).yourClient;
        System.out.println(oldClientLog);
        if (oldBakerLog.equals("default") && oldClientLog.equals("default")) {
            bakerRepository.findByBakerlogin(yourBaker).yourClient = yourClient;
            bakerRepository.save(bakerRepository.findByBakerlogin(yourBaker));
            clientRepository.findByClientlogin(yourClient).yourBaker = yourBaker;
            clientRepository.save(clientRepository.findByClientlogin(yourClient));
            bakerRepository.findByBakerlogin(yourBaker).ordersCount += 1;
        }
        else if (oldClientLog.equals("default") == false){
            return "errorBaker";
        }
        else{
            Baker oldBaker = bakerRepository.findByBakerlogin(oldBakerLog);
            oldBaker.yourClient = "default";
            bakerRepository.findByBakerlogin(yourBaker).yourClient = yourClient;
            bakerRepository.save(bakerRepository.findByBakerlogin(yourBaker));
            clientRepository.findByClientlogin(yourClient).yourBaker = yourBaker;
            clientRepository.save(clientRepository.findByClientlogin(yourClient));
            bakerRepository.findByBakerlogin(yourBaker).ordersCount += 1;

        }
        return "redirect:/home";
    }

    @GetMapping("/finish_order")
    public String finishOrder(){
        return "finishOrder";
    }

    @PostMapping("/finish_order")
    public String doFinishOrder(){
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientRepository.findByClientlogin(auth1.getName());
        String oldBakerLog = client.yourBaker;
        if (oldBakerLog.equals("default")){
            return "errorBaker";
        }
        else {
            Baker oldBaker = bakerRepository.findByBakerlogin(oldBakerLog);
            System.out.println(oldBakerLog);
            oldBaker.yourClient = "default";
            oldBaker.finishedOrdersCount += 1;
            clientRepository.findByClientlogin(auth1.getName()).yourBaker = "default";
            bakerRepository.save(oldBaker);
            clientRepository.save(client);
            return "mainPage";
        }
    }

}
