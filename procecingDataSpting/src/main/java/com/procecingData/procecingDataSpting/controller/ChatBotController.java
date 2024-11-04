package com.procecingData.procecingDataSpting.controller;

import com.procecingData.procecingDataSpting.controller.dto.UsuarioDTO;
import com.procecingData.procecingDataSpting.entity.PersonEntity;
import com.procecingData.procecingDataSpting.entity.RoleEntity;
import com.procecingData.procecingDataSpting.service.serviceImpl.readFileServiceImpl;
import com.procecingData.procecingDataSpting.service.serviceInterface.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/application")
public class ChatBotController {

    @Autowired
    private readFileServiceImpl serviceRead;

    @Autowired
    private PersonService personService;
    @GetMapping("/log")
    public String viewMain(Model model){
        model.addAttribute("userDto",new UsuarioDTO());
        return "login";
    }
    @GetMapping("/home")
    public String homeUser(Authentication authentication , Model model){

        List<String> roles = authentication.getAuthorities().stream()
                        .map(role->{
                            return role.getAuthority();
                        }).toList();

        if(roles.contains("ROLE_ADMIN")){

            model.addAttribute("roleAdmin",roles);
            model.addAttribute("userName_Admin",authentication.getName());

            return "redirect:/application/home_admin";
        }

        model.addAttribute("roleUser",roles);
        model.addAttribute("userName_User",authentication.getName());

        return "/home/home_user";
    }

    @GetMapping("/home_admin")
    public String homeAdmin(){
        return "/home/home_admin";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute("userName") UsuarioDTO user,Model model){

        Optional<PersonEntity> person = personService.findByUsername(user.getEmail());

        if (person.isPresent()){
            return "redirect:/application/log?alreadyExist";
        }

        PersonEntity userEntity = PersonEntity.builder()
                    .name(user.getName())
                    .username(user.getEmail())
                    .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                    .roles(Set.of(RoleEntity.builder().id(2L).build()))
                    .build();

        personService.saveUser(userEntity);


        return "redirect:/application/log?success";
    }

}

