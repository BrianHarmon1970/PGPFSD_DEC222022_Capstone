package com.harmonengineering.controller;

import com.harmonengineering.beans.ValidatorBean;
import com.harmonengineering.entity.*;
import com.harmonengineering.icin_bankservice.IcinBankServiceApplication;
import org.apache.coyote.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.Metamodel;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "http://localhost:4200",
        methods = { RequestMethod.GET, RequestMethod.DELETE,
                RequestMethod.POST, RequestMethod.PUT } ,
        allowedHeaders = "*", maxAge = 3600 )
@RequestMapping( value = "${com.harmonengineering.icin_bank.user-root}")
@RestController
public class UserController
{
    private static final Logger logger = LoggerFactory.getLogger(IcinBankServiceApplication.class);
    @Autowired private UserRepository userRepository ;
    UserCartItemRepository cartItemRepository ;
    @PersistenceContext private EntityManager entityManager;
    private final ValidatorBean validatorBean ;

    public UserController(
            EntityManager em ,
            //UserOrderRepository uoRepo,
            UserRepository repo,
            UserCartItemRepository ucartItemRepo,
            //OrderItemRepository oiRepo,
            ValidatorBean validator )
    {
        userRepository = repo ;
        cartItemRepository = ucartItemRepo ;
        validatorBean = validator ;
        entityManager = em ;
    }

    // USER CRUD REST API
    //
    @GetMapping(value = "listall", produces = "application/json; charset=UTF-8")
    List<User> listAllUsers( )
    {
        List<User> users = (List<User>)userRepository.findAll() ;
        return users ;
    }

    @GetMapping( value="/getById/{id}", produces = "application/json; charset=UTF-8" )
    User getById( @PathVariable Long id )
    {
        Optional<User> optional = userRepository.findById( id ) ;
        return (User) optional.orElse(null);
    }
    @GetMapping( value="/{id}" )
    User getById_( @PathVariable Long id )
    {
        logger.info( "retrieving user[" + id + "]: ");
        Optional<User> optional = userRepository.findById( id ) ;
        return (User) optional.orElse(null) ;
    }
    @PostMapping( value="add" )
    public User addUser(@RequestBody User user )
    { return userRepository.save( user ) ; }

    @PutMapping( path="/{id}",
            produces = "application/json; charset=UTF-8; application/x-www-form-urlencoded",
            consumes = "application/json; charset=UTF-8; application/x-www-form-urlencoded")
    public User updateUser(@PathVariable Long id, @RequestBody User user)
    {
        user.setId( id ) ;
        logger.info( "saving user: " + user.getUserName());
        return userRepository.save( user ) ;
    }
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id)
    {
        userRepository.deleteById(id);
    }

    @GetMapping(value = "search/{id}/{user_name}/{first_name}/{last_name}", produces = "application/json; charset=UTF-8")
    List<User> userSearchQuery(@PathVariable String id,
                               @PathVariable String user_name,
                               @PathVariable String first_name,
                               @PathVariable String last_name )
    {
        Long longID = validatorBean.isValidLong(id) ? Long.parseLong(id) : null  ;
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder() ;

        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        ArrayList<Predicate> predicates = new ArrayList<>() ;
        criteriaQuery.select(userRoot);
        if( longID != null )
            predicates.add(  criteriaBuilder.equal( userRoot.get("ID"), longID )) ;

        if( !user_name.equals("null"))
            predicates.add( criteriaBuilder.equal( userRoot.get("userName"), user_name )) ;

        if( !first_name.equals("null"))
            predicates.add( criteriaBuilder.equal( userRoot.get("firstName"), first_name )) ;

        if( !last_name.equals("null"))
            predicates.add( criteriaBuilder.equal( userRoot.get("lastName"), last_name )) ;


        System.out.println( "predicates.size(): " + predicates.size() ) ;
        switch( predicates.size() )
        {
            case 0: //criteriaQuery.where( predicates.get(0) ,predicates.get(1)  ) ;
                break ;
            case 1: criteriaQuery.where( predicates.get(0) ) ;  break ;
            case 2: criteriaQuery.where( predicates.get(0) ,predicates.get(1)  ) ; break ;
            case 3: criteriaQuery.where( predicates.get(0) ,predicates.get(1), predicates.get(2)  ) ; break ;
            case 4: criteriaQuery.where( predicates.get(0) ,predicates.get(1), predicates.get(2), predicates.get(3) ) ; break ;
        }

        TypedQuery<User> typedQuery = entityManager.createQuery( criteriaQuery );
        List<User> list = typedQuery.getResultList() ;
        return list ;
    }


    // USER CART CRUD REST API
    //
    @GetMapping( value="cart", produces = "application/json; charset=UTF-8")
    List<UserCartItem> getCartItems()
    {
        return cartItemRepository.findAll() ;
    }
    @GetMapping( value="cart/{id}", produces = "application/json; charset=UTF-8")
    UserCartItem getCartItem(@PathVariable Long id )
    {
        return cartItemRepository.findById( id ).orElseThrow() ;
    }
    @PostMapping( value="cart", produces = "application/json; charset=UTF-8")
    UserCartItem addCartItem( @RequestBody UserCartItem item )
    {
        return cartItemRepository.save( item ) ;
    }
    @PutMapping( value="cart", produces = "application/json; charset=UTF-8")
    UserCartItem updateCartItem( @RequestBody UserCartItem item )
    {
        return cartItemRepository.save( item ) ;
    }
    @DeleteMapping( value="cart/{id}" )
    void deleteById( @PathVariable Long id )
    {
        cartItemRepository.deleteById( id ) ;
    }

}


