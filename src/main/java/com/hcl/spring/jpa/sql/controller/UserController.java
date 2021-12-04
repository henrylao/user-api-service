package com.hcl.spring.jpa.sql.controller;

import com.hcl.spring.jpa.sql.model.Destination;
import com.hcl.spring.jpa.sql.model.Recommendation;
import com.hcl.spring.jpa.sql.model.Review;
import com.hcl.spring.jpa.sql.model.User;
import com.hcl.spring.jpa.sql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {
    boolean DEBUG = true;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String title) {
        try {
            List<User> users = new ArrayList<User>();

            if (title == null)
                userRepository.findAll().forEach(users::add);
//            else
//                userRepository.findByTitleContaining(title).forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable("user_id") long user_id) {
        Optional<User> userData = userRepository.findById(user_id);

        return userData.map(
                user -> new ResponseEntity<>(user, HttpStatus.OK)
        ).orElseGet(
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    /**
     * CREATE method for a User requiring an EMAIL and PASSWORD
     * @return
     */
    @PostMapping("/users/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            if (userRepository.existsByEmail(user.getEmail())) {
                return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
            }

            if (!isValidPassword(user.getPassword()) && !DEBUG) {
                return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
            }
            User newUser =                             new User(
                    user.getEmail(),
                    user.getPassword(),
                    user.isAdmin(),
                    user.getUsername(),
                    user.getFirstName(),
                    user.getLastName());
            System.out.println(newUser);
            User _user = userRepository
                    .save(
                            new User(
                                    user.getEmail(),
                                    user.getPassword(),
                                    user.isAdmin(),
                                    user.getUsername(),
                                    user.getFirstName(),
                                    user.getLastName()
                            ));

            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * UPDATE method for USER entities
     * TODO: add DESTINATION, REVIEW, RECOMMENDATION
     *
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);
        System.out.println(userData);
        if (userData.isPresent()) {
            User _user = userData.get();
            // MINIMUM
            _user.setAdmin(user.isAdmin());
            _user.setEmail(user.getEmail());
            _user.setPassword(user.getPassword());
            _user.setUsername(user.getUsername());

            // STRETCH
            System.out.println(user.getFirstName());
            _user.setFirstName(user.getFirstName());
            _user.setLastName(user.getLastName());
//            System.out.println(_user);
//			_user.setDestination(user.getDestination());
//			_user.setReviews(user.getReviews());
//			_user.setRecommendations(user.getRecommendations());

//			_user.setTitle(user.getTitle());
//			_user.setDescription(user.getDescription());
//			_user.setPublished(user.isPublished());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users?={id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Method for ping a particular service at a specific port for testing
     * @param port
     * @param resourceName
     * @return
     */
    private HttpEntity<? extends Object> pingHelper(Integer port, String resourceName) {
        try {
            // TODO: NOTE endpoint formatting is subject to change, this is for basic integration testing
            String uri = String.format("http://localhost:%d/api/%s", port, resourceName);
            System.out.println("Pinged: " + uri);
            RestTemplate restTemplate = new RestTemplate();
            Object result = null;
            if (resourceName.equals("review")) {
                result = (Review) restTemplate.getForObject(uri, Review.class);
            } else if (resourceName.equals("destination")) {
                result = (Destination) restTemplate.getForObject(uri, Destination.class);
            } else if (resourceName.equals("recommendation")) {
                result = (Recommendation) restTemplate.getForObject(uri, Recommendation.class);
            }

            if (result == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // TODO: add REST API call to REVIEW, RECOMMENDATION, DESTINATION for aggregation
    // TODO: migrate the following test pings to a JUNIT test
    // TODO: perform local testing with the `uscities.csv` file
    // TODO: perform local testing using `beans.xml` when defining classes instead of statically typed classes in model
    // TODO: add tests for USER class
    // TODO: migrate to a MYSQL/POSTGRESQL database from H2

    @GetMapping("/users/reviews/test")

    public ResponseEntity<HttpStatus> pingReviewService() {
        // TODO: add REVIEW endpoint
        return (ResponseEntity<HttpStatus>) pingHelper(8081, "review");
    }

    @GetMapping("/users/destinations/test")
    public ResponseEntity<HttpStatus> pingDestinationService() {
        // TODO: add DESTINATION endpoint
        return (ResponseEntity<HttpStatus>) pingHelper(8082, "destination");
    }

    @GetMapping("/users/recommendations/test")
    public ResponseEntity<HttpStatus> pingRecommendService() {
        // TODO: add RECOMMENDATION endpoint
        return (ResponseEntity<HttpStatus>) pingHelper(8083, "recommendation");
    }

}
