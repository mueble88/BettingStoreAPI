package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.TokenUtils;
import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;



import java.security.Signature;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    public static String email;
    public static String password;
    public static String newPassword;

    @GetMapping("/login/{email}/{password}")
    public List<User> loginValidation(@PathVariable("email") String email, @PathVariable("password")String password){
        return userRepository.findByEmail(email);
    }

//    @GetMapping("/login")
//    public List<User> loginValidation(@PathVariable("email") String email, @PathVariable("password")String password){
//        return userRepository.findByEmail(email);
//    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getLogin(@RequestBody LoginDto loginDto){
        email = loginDto.getEmail();
        password = loginDto.getPassword();

        boolean emailValid = StringUtils.hasLength(email);
        if(emailValid) {
            passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            List<User> users = userRepository.findByEmail(email);
            if(users.size() > 0){
                boolean passwordValid = passwordEncoder.matches(password, users.get(0).getPassword());
                if (passwordValid) {
                    User user = users.get(0);
                    long id = user.getId();
                    String name = user.getName();
                    String lastName = user.getLastName();

                    // crear token
                    String token = TokenUtils.getJWTToken(email, id);
                    TokenDto tokenDto = new TokenDto();
                    tokenDto.setToken(token);
                    tokenDto.setName(name);
                    tokenDto.setLastName(lastName);
                    tokenDto.setEmail(email);

                    return new ResponseEntity<TokenDto>(tokenDto, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }



    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getChangePassword(@RequestBody ChangePasswordDto changePasswordDTO) {
        email = changePasswordDTO.getEmail();
        password = changePasswordDTO.getOldPassword();
        newPassword = changePasswordDTO.getNewPassword();

        boolean emailValid = StringUtils.hasLength(email);
        if(emailValid) {
            List<User> users = userRepository.findByEmail(email);
            if (users.size() > 0) {
                passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
                boolean passwordValid = passwordEncoder.matches(password, users.get(0).getPassword());
                if (passwordValid) {
                    if (newPassword != password) {
                        /* Create password encryption  */
                        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
                        String passwordEncrypted = passwordEncoder.encode(newPassword);
                        users.get(0).setPassword(passwordEncrypted);
                        userRepository.updatePasswordField(email, users.get(0).getPassword());
                        ResponseDto responseDto = new ResponseDto();
                        responseDto.setStatus(200);
                        responseDto.setMessage("OK");
                        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
                    } else {
                        System.out.println("el nuevo password debe ser diferente al password");
                    }
                } else {
                    System.out.println("El password no se encontró");
                }
            } else {
                System.out.println("Ingrese el correo");
            }
        }
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(401);
        responseDto.setMessage("UNAUTHORIZED");
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.UNAUTHORIZED);
    }
}

