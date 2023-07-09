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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





//@RequestMapping( path = "api/user")


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
   // UserOrderRepository userOrderRepository ;
   // OrderItemRepository orderItemRepository ;
    @PersistenceContext private EntityManager entityManager;
    private final ValidatorBean validatorBean ;

    public UserController(
            //UserOrderRepository uoRepo,
                           UserRepository repo,
            //               OrderItemRepository oiRepo,
                           ValidatorBean validator )
    {
        userRepository = repo ;
        //userOrderRepository = uoRepo ;
        //orderItemRepository = oiRepo ;
        validatorBean = validator ;
    }
/*
    @GetMapping(value = "orders", produces = "application/json; charset=UTF-8" )
    List<UserOrder> getOrders()
    {
        List<UserOrder> orders = (List<UserOrder> )userOrderRepository.findAll() ;
        return orders ;
    }

    @GetMapping(value = "orders/{user_id}", produces = "application/json; charset=UTF-8")
    List<UserOrder>
    getUserOrders( @PathVariable Long user_id )
    {
        List<UserOrder> orders = (List<UserOrder> )userOrderRepository.getUserOrdersByUserId( user_id ) ;
        return orders ;
    }

    @PostMapping("orders/add")
    public UserOrder addOrder(@RequestBody UserOrder order )
    { return userOrderRepository.save( order ) ; }

    @GetMapping(value = "orderitems", produces = "application/json; charset=UTF-8")
    List<OrderItem>
    getAllOrderItems()
    {
        List<OrderItem> orderitems = (List<OrderItem> )orderItemRepository.findAll() ;
        return orderitems ;
    }

    @GetMapping(value = "orderitems/{order_id}", produces = "application/json; charset=UTF-8")
    List<OrderItem>
    getOrderItemsByOrderId( @PathVariable Long order_id )
    {
        return (List<OrderItem> )orderItemRepository.getOrderItemsByOrderId(order_id);
    }

    @PostMapping("orderitems/add")
    public OrderItem addOrderItem(@RequestBody OrderItem item )
    { return orderItemRepository.save( item ) ; }
*/
    @GetMapping(value = "listall"
    //        , produces = "application/json; charset=UTF-8"
    )
    List<User> listAllUsers( )
    {
        List<User> users = (List<User>)userRepository.findAll() ;
        return users ;
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
        logger.info("this is a info message");
        logger.warn("this is a warn message");
        logger.error("this is a error message");

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
        logger.info("this is a info message");
        logger.warn("this is a warn message");
        logger.error("this is a error message");

        return userRepository.save( user ) ;
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id)
    {
        userRepository.deleteById(id);
    }
}
