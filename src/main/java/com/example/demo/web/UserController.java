package com.example.demo.web;

import com.example.demo.entity.User;
import com.example.demo.model.CreateUserRequest;
import com.example.demo.model.Result;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by BBG on 2018/5/24.
 */
@RestController
@RequestMapping("/api/userController")
public class UserController {
    final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        log.info("getAllUser");
        return userRepository.findAll();
    }

    @PostMapping("/saveOneUser")
    private void saveOneUser(@RequestParam(name = "name", required = true) String name,
                             @RequestParam("age") Integer age,
                             @RequestParam("phone") String phone) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setPhone(phone);
        userRepository.save(user);
    }

    @PostMapping("/createUser")
    public Result<User> createUser(@Valid CreateUserRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error(bindingResult.getFieldError().getDefaultMessage());
            return ResultUtils.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        User user = new User();
        BeanUtils.copyProperties(request, user);
        userRepository.save(user);
        return ResultUtils.success(user);
    }

    @GetMapping("/getUserById/{id}")
    private User getUserById(@PathVariable("id") String id) {
        log.info("getUserById {}", id);
        User user = userRepository.getOne(id);
        return user;
    }

    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable("id") String id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/getUserByAge/{age}")
    public List<User> getUserByAge(@PathVariable("age") Integer age) {
        return userRepository.findUserByAge(age);
    }


    @GetMapping("/saveTwoUser")
    public void saveTwoUser() {
        userService.insertTwoUser();
    }

    @PostMapping("/saveUser")
    public User saveUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
        return userRepository.save(user);
    }

    @GetMapping("/getAge/{id}")
    public void getAge(@PathVariable String id) throws Exception {
        userService.getAge(id);
    }

    @GetMapping("/lambda")
    public void lambda() {
        List<User> users = createUsers(10);
        users.forEach(user -> {
            log.info(user.toString());
        });
        Map<String, User> userMap = users.stream().collect(Collectors.toMap(User::getId, user -> user));
        log.info(userMap.toString());
        Collections.sort(users, (User user1, User user2) -> (
                user1.getAge().compareTo(user2.getAge())
        ));
        users.forEach(user -> {
            log.info(user.toString());
        });
        Collections.sort(users, (User user1, User user2) -> (
                user2.getAge().compareTo(user1.getAge())
        ));
        users.forEach(user -> {
            log.info(user.toString());
        });
    }

    private List<User> createUsers(int num) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            User user = new User();
            user.setId(i + "");
            user.setName(getRandomHan(3));
            user.setAge(i % 3);
            user.setPhone("121121212");
            users.add(user);
        }
        return users;
    }

    private Random ran = new Random();
    private final static int delta = 0x9fa5 - 0x4e00 + 1;

    public String getRandomHan(int num) {
        String name = "";
        for (int i = 0; i < num; i++) {
            name += (char) (0x4e00 + ran.nextInt(delta));
        }
        return name;
    }


    public static void main(String[] args) {
        Map<String, String> phoneMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String input;
        while (scanner.hasNext() && !Objects.equals(input = scanner.nextLine(), "noname")) {
            String[] temps = input.split(" ");
            phoneMap.put(temps[0], temps[1]);
        }
        while (scanner.hasNext()) {
            String name = scanner.next();
            if (phoneMap.containsKey(name)) {
                System.out.println(phoneMap.get(scanner.next()));
            } else {
                System.out.println("Not fould");
            }
        }
    }


}
