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
    public String doRegistrationClient(@RequestParam String name, @RequestParam String password, @RequestParam String login, @RequestParam String surname) {
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

            ResponseEntity.ok(allRoleRepository.save(new_allRole));
            ResponseEntity.ok(clientRepository.save(new_client));
            return "login";}
    }

    @GetMapping("/registration/baker")
    public String registrationBaker() {
        return "registrationForBaker";
    }

    @PostMapping("/registration/baker")
    public String doRegistration_new_baker(@RequestParam String name, @RequestParam String password, @RequestParam String login, @RequestParam String specialisation, @RequestParam String surname) {
        AllRole allRole=allRoleRepository.findByUserlogin(login);
        if(allRole!=null)
        {return "registrationForBaker";}
        else
        {AllRole new_allrole=new AllRole();
            new_allrole.login=login;
            new_allrole.password=password;
            new_allrole.type="ROLE_BAKER";
            Baker new_baker = new Baker();
            new_baker.name = name;
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            new_baker.password = passwordEncoder.encode(password);
            new_baker.login = login;
            new_baker.specialisation = specialisation;
            new_baker.surname = surname;
            new_baker.type="ROLE_BAKER";

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
        return "bakerPage";}
        else{
            model.addAttribute("type","/admin_page");
            return "adminPage";
        }
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
    public String teacherPage(@RequestParam(name = "name",required = false,defaultValue = "300") String name,Model model) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name",bakerRepository.findByTeacherlogin(auth1.getName()).name);
        model.addAttribute("surname",bakerRepository.findByTeacherlogin(auth1.getName()).surname);
        model.addAttribute("login",bakerRepository.findByTeacherlogin(auth1.getName()).login);
        model.addAttribute("specialisation",bakerRepository.findByTeacherlogin(auth1.getName()).specialisation);
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

    @GetMapping("/my_client")
    public String getStudent(@RequestParam(name = "name",required = false,defaultValue = "300") String name, Model model) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("client", clientRepository.findByClientBaker(auth1.getName()));
        return "myClient";
    }

    @GetMapping("/free_client")
    public String freeStudent(@RequestParam(name = "name",required = false,defaultValue = "300") String name, Model model) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("client", clientRepository.findFreeClient());
        return "freeClient";
    }


    @GetMapping("/target")
    public String target() {
        return "target";
    }

    @PostMapping("/target")
    public String target(@RequestParam String yourBaker,@RequestParam String client) {
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        clientRepository.findByClientlogin(client).yourBaker=yourBaker;
        clientRepository.save(clientRepository.findByClientlogin(client));
        return "redirect:/home";
    }




}
