package com.harmonengineering.controller;

import com.harmonengineering.beans.ValidatorBean;
import com.harmonengineering.entity.OrderItem;
//import com.harmonengineering.entity.OrderItemRepository;
import com.harmonengineering.entity.Product;
//import com.harmonengineering.entity.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.EntityManager ;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value="product" )
public class ProductController
{
    //@Autowired
   /* ProductRepository productRepository ;
    OrderItemRepository orderItemRepository ;
    @PersistenceContext private EntityManager entityManager;
    private final ValidatorBean validatorBean ;
    public ProductController( ProductRepository repo,
                              OrderItemRepository oiRepo,
                              ValidatorBean validator)
    {
        productRepository = repo ;
        orderItemRepository = oiRepo ;
        validatorBean = validator ;
    }

    @GetMapping(value = "listall", produces = "application/json; charset=UTF-8" )
    List<Product> listAll( )
    {
        return (List<Product>)productRepository.findAll();
    }

    @GetMapping( value="getById/{id}", produces = "application/json; charset=UTF-8" )
    Product getById( @PathVariable Long id )
    {
        Optional<Product> optional = productRepository.findById( id ) ;
        return optional.orElse(null);
    }
    @GetMapping( value="/{id}", produces = "application/json; charset=UTF-8" )
    Product getById_( @PathVariable Long id )
    {
        Optional<Product> optional = productRepository.findById( id ) ;
        return optional.orElse(null);
    }

//    @GetMapping( value="key/{key_name}/{key_value}")
//    List<Product> getByKeyValue(@PathVariable String key_name, @PathVariable String key_value )
//    {
//        System.out.println( "getByKeyValue() -> Key:(" + key_name + ", " + key_value + ") ");
//        return null ;
//    }
    //@GetMapping( value="search/id/{id}/name/{name}/season/{season}/brand/{brand}/category/{category}/price/{price}/color/{color}/date/{date}")
@GetMapping( value="search/{id}/{name}/{season}/{brand}/{category}/{price}/{color}/{date}", produces = "application/json; charset=UTF-8")
    List<Product> criteriaSelect(@PathVariable String id,
                                 @PathVariable String name,
                                 @PathVariable String season,
                                 @PathVariable String brand,
                                 @PathVariable String category,
                                 @PathVariable String price,
                                 @PathVariable String color,
                                 @PathVariable String date )
    {
        System.out.println("criteriaSelect(): -> "  ) ;
        System.out.println("criteriaSelect(): id -> " +  id ) ;
        System.out.println("criteriaSelect(): name -> " + name ) ;
        System.out.println("criteriaSelect(): season -> " + season ) ;
        System.out.println("criteriaSelect(): brand -> " + brand ) ;
        System.out.println("criteriaSelect(): category -> " + category ) ;
        System.out.println("criteriaSelect(): price -> " + price ) ;
        System.out.println("criteriaSelect(): color -> " + color ) ;
        System.out.println("criteriaSelect(): date -> " + date ) ;

        Long longID = validatorBean.isValidLong(id) ? Long.parseLong(id) : null  ;
        Double doublePrice = validatorBean.isValidDouble(price) ? Double.parseDouble(price) : null ;
        Date dateDate ;
        if(validatorBean.isValidText(date))
        {
            SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH) ;
            try { dateDate = formatter.parse( date ); } catch ( ParseException pe ) { dateDate = null ; }
        }
        else dateDate = null ;
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder() ;

        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        ArrayList<Predicate> predicates = new ArrayList<>() ;
        criteriaQuery.select(productRoot);
        if( longID != null )
            predicates.add(  criteriaBuilder.equal( productRoot.get("id"), longID )) ;

        if( dateDate != null )
            predicates.add(  criteriaBuilder.greaterThan( productRoot.get("dateAdded"), dateDate )) ;

        if( doublePrice != null )
            predicates.add(  criteriaBuilder.lessThanOrEqualTo( productRoot.get("price"),doublePrice)) ;

        if( !name.equals("null"))     predicates.add( criteriaBuilder.equal( productRoot.get("description"), name )) ;
        if( !season.equals("null"))   predicates.add( criteriaBuilder.equal( productRoot.get("season"), season )) ;
        if( !brand.equals("null"))    predicates.add( criteriaBuilder.equal( productRoot.get("brand"), brand )) ;
        if( !category.equals("null")) predicates.add( criteriaBuilder.equal( productRoot.get("category"), category )) ;
        if( !color.equals("null"))    predicates.add( criteriaBuilder.equal( productRoot.get("color"), color )) ;


        System.out.println( "predicates.size(): " + predicates.size() ) ;
        switch( predicates.size() )
        {
            case 0: //criteriaQuery.where( predicates.get(0) ,predicates.get(1)  ) ;
                break ;
            case 1: criteriaQuery.where( predicates.get(0) ) ;  break ;
            case 2: criteriaQuery.where( predicates.get(0) ,predicates.get(1)  ) ; break ;
            case 3: criteriaQuery.where( predicates.get(0) ,predicates.get(1), predicates.get(2)  ) ; break ;
            case 4: criteriaQuery.where( predicates.get(0) ,predicates.get(1), predicates.get(2), predicates.get(3) ) ; break ;
            case 5:  criteriaQuery.where( predicates.get(0) ,predicates.get(1), predicates.get(2),
                    predicates.get(3),
                    predicates.get(4)) ;
            break ;
            case 6: criteriaQuery.where( predicates.get(0) ,predicates.get(1), predicates.get(2), predicates.get(3),
                    predicates.get(4),
                    predicates.get(5)) ;
            break ;
            case 7: criteriaQuery.where( predicates.get(0) ,predicates.get(1), predicates.get(2), predicates.get(3),
                    predicates.get(4), predicates.get(5),
                    predicates.get(6)) ;
            break ;
            case 8: criteriaQuery.where( predicates.get(0) ,predicates.get(1), predicates.get(2),
                    predicates.get(3), predicates.get(4), predicates.get(5), predicates.get(6),
                    predicates.get(7)) ;
            break ;
        }

        TypedQuery<Product> typedQuery = entityManager.createQuery( criteriaQuery );
        return typedQuery.getResultList() ;
        //return list ;

    }
    @GetMapping(value="purchase" ,  produces = "application/json; charset=UTF-8")
    List<Product> purchaseOrder()
    {
        return productRepository.getProductPurchases();
    }

    @GetMapping(value = "purchases", produces = "application/json; charset=UTF-8")
    List<OrderItem> ProductPurchases()
    {
        return (List<OrderItem>)orderItemRepository.findAll();
    }

    //@GetMapping(
    //        "search/purchases/orderid/{orderid}/productid/{productid}/name/{name}/season/{season}"+
    //                "/brand/{brand}/category/{category}/color/{color}/begin_date/{begin_date}/end_date/{end_date}")
    @GetMapping( value="search/purchases/{orderid}/{productid}/{name}/{season}"+
            "/{brand}/{category}/{color}/{begin_date}/{end_date}", produces = "application/json; charset=UTF-8")

            List<OrderItem> searchPurchases(
                                @PathVariable String orderid,
                                @PathVariable String productid,
                                @PathVariable String name ,
                                @PathVariable String season,
                                @PathVariable String brand,
                                @PathVariable String category,
                                @PathVariable String color ,
                                @PathVariable String begin_date,
                                @PathVariable String end_date )
    {
        System.out.println("criteriaSelect(): -> "  ) ;
        System.out.println("criteriaSelect(): orderid -> " +  orderid ) ;
        System.out.println("criteriaSelect(): productid -> " +  productid ) ;
        System.out.println("criteriaSelect(): name -> " + name ) ;
        System.out.println("criteriaSelect(): season -> " + season ) ;
        System.out.println("criteriaSelect(): brand -> " + brand ) ;
        System.out.println("criteriaSelect(): category -> " + category ) ;
        System.out.println("criteriaSelect(): color -> " + color ) ;
        System.out.println("criteriaSelect(): begin_date -> " + begin_date ) ;
        System.out.println("criteriaSelect(): end_date -> " + end_date ) ;


        Long longOrderID = validatorBean.isValidLong(orderid) ? Long.parseLong(orderid) : null  ;
        Long longProductID = validatorBean.isValidLong(productid) ? Long.parseLong(productid) : null  ;


        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH) ;
        Date dateBeginDate ; Date dateEndDate ;
        if(validatorBean.isValidText(begin_date))
        {
            try { dateBeginDate = formatter.parse( begin_date ); } catch ( ParseException pe ) { dateBeginDate = null ; }
        } else dateBeginDate = null ;

        if(validatorBean.isValidText(end_date))
        {
            try { dateEndDate = formatter.parse( end_date ); } catch ( ParseException pe ) { dateEndDate = null ; }
        } else dateEndDate = null ;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder() ;

        CriteriaQuery<OrderItem> criteriaQuery = criteriaBuilder.createQuery(OrderItem.class);
        Root<OrderItem> itemRoot = criteriaQuery.from(OrderItem.class);
        ArrayList<Predicate> predicates = new ArrayList<>() ;
        criteriaQuery.select(itemRoot);//.where( criteriaBuilder.isNotNull( productRoot )) ;
        if( longOrderID != null )
            predicates.add(  criteriaBuilder.equal( itemRoot.get("orderId"), longOrderID )) ;

        if( longProductID != null )
            predicates.add(  criteriaBuilder.equal( itemRoot.get("productId"), longProductID )) ;
        if( dateBeginDate != null )
            predicates.add(  criteriaBuilder.greaterThanOrEqualTo( itemRoot.get("order").get("orderDate"), dateBeginDate )) ;
        if( dateBeginDate != null )
            predicates.add(  criteriaBuilder.lessThanOrEqualTo( itemRoot.get("order").get("orderDate"), dateEndDate)) ;


        if( !name.equals("null"))     predicates.add( criteriaBuilder.equal( itemRoot.get("product").get("description"), name )) ;
        if( !season.equals("null"))   predicates.add( criteriaBuilder.equal( itemRoot.get("product").get("season"), season )) ;
        if( !brand.equals("null"))    predicates.add( criteriaBuilder.equal( itemRoot.get("product").get("brand"), brand )) ;
        if( !category.equals("null")) predicates.add( criteriaBuilder.equal( itemRoot.get("product").get("category"), category )) ;
        if( !color.equals("null"))    predicates.add( criteriaBuilder.equal( itemRoot.get("product").get("color"), color )) ;


        System.out.println( "predicates.size(): " + predicates.size() ) ;
        switch( predicates.size() ) {
            case 0: //criteriaQuery.where( predicates.get(0) ,predicates.get(1)  ) ;
                break;
            case 1:
                criteriaQuery.where(predicates.get(0));
                break;
            case 2:
                criteriaQuery.where(predicates.get(0), predicates.get(1));
                break;
            case 3:
                criteriaQuery.where(predicates.get(0), predicates.get(1), predicates.get(2));
                break;
            case 4:
                criteriaQuery.where(predicates.get(0), predicates.get(1), predicates.get(2), predicates.get(3));
                break;
            case 5:
                criteriaQuery.where(predicates.get(0), predicates.get(1), predicates.get(2), predicates.get(3),
                        predicates.get(4));
                break;
            case 6:
                criteriaQuery.where(predicates.get(0), predicates.get(1), predicates.get(2), predicates.get(3),
                        predicates.get(4), predicates.get(5));
                break;
            case 7:
                criteriaQuery.where(predicates.get(0), predicates.get(1), predicates.get(2), predicates.get(3),
                        predicates.get(4), predicates.get(5), predicates.get(6));
                break;
            case 8:
                criteriaQuery.where(predicates.get(0), predicates.get(1), predicates.get(2), predicates.get(3),
                        predicates.get(4), predicates.get(5), predicates.get(6), predicates.get(7));
                break;
            case 9:
                criteriaQuery.where(predicates.get(0), predicates.get(1), predicates.get(2), predicates.get(3),
                        predicates.get(4), predicates.get(5), predicates.get(6), predicates.get(7),predicates.get(8));
                break;
        }
        TypedQuery<OrderItem> typedQuery = entityManager.createQuery( criteriaQuery );
        //List<OrderItem> list = typedQuery.getResultList() ;
        //return list ;
        return typedQuery.getResultList() ;
    }

    @PostMapping( value="add")
    public Product addProduct(@RequestBody Product product )
    { return productRepository.save( product ) ; }

    @PutMapping(value = "update/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product )
    {
        product.setId( id );
        return productRepository.save( product );
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteProduct(@PathVariable Long id)
    {
        productRepository.deleteById(id);
    }
    */
}
