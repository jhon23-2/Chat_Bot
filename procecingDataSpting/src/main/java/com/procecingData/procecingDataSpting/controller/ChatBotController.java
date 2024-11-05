package com.procecingData.procecingDataSpting.controller;

import com.procecingData.procecingDataSpting.controller.dto.UsuarioDTO;
import com.procecingData.procecingDataSpting.entity.PersonEntity;
import com.procecingData.procecingDataSpting.entity.QuestionEntity;
import com.procecingData.procecingDataSpting.entity.RoleEntity;
import com.procecingData.procecingDataSpting.service.serviceImpl.readFileServiceImpl;
import com.procecingData.procecingDataSpting.service.serviceInterface.PersonService;
import com.procecingData.procecingDataSpting.service.serviceInterface.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/application")
public class ChatBotController {

    @Autowired
    private readFileServiceImpl readFileService;
    @Autowired
    private PersonService personService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/log")
    public String viewMain(Model model){
        model.addAttribute("userDto",new UsuarioDTO());
        return "login";
    }
    @GetMapping("/home")
    public String homeUser(Authentication authentication , Model model){

        List<String> roles = new ArrayList<>();

        authentication.getAuthorities().stream()
                .forEach(role->{
                    roles.add(role.getAuthority());
                });

        if(roles.contains("ROLE_ADMIN")){
            return "redirect:/application/home_admin";
        }

        model.addAttribute("roleUser",roles);
        model.addAttribute("userName_User",authentication.getName());
        return "/home/home_user";
    }

    @GetMapping("/home_admin")
    public String homeAdmin(Model model , Authentication authentication){

        List<String> roles = new ArrayList<>();

        authentication.getAuthorities().stream()
                        .forEach(role->{
                            roles.add(role.getAuthority());
                        });

        model.addAttribute("userName_Admin",authentication.getName());
        model.addAttribute("roleAdmin",roles);

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

    @PostMapping("/read")
    public ResponseEntity<?> readFile(@RequestParam("file") MultipartFile file){
        List<Map<String,String>> data =  readFileService.readFile(file);

        List<QuestionEntity> conversacion = data.stream()
                .map(pregunta->
                    QuestionEntity.builder()
                            .dateTimeQuestion(LocalDateTime.now().toString())
                            .question(pregunta.get("Pregunta"))
                            .responseEntity(com.procecingData.procecingDataSpting.entity.ResponseEntity.builder()
                                    .dateTimeResponse(LocalDateTime.now().toString())
                                    .response(pregunta.get("Respuesta"))
                                    .build())
                            .build()
                ).toList();

        questionService.saveAllQuestion(conversacion);

        return new ResponseEntity<>(Map.of("Success","data save Successfully"), HttpStatus.OK);
    }

    @PostMapping("/message")
    public ResponseEntity<?> sendMessage(@RequestParam("message") String message){
        //TODO... Consulta y devolver respuesta asociada para pintarla en dicho Dom

        System.out.println(message);

        return new ResponseEntity<>(Map.of("Value",message),HttpStatus.OK);
    }

}

