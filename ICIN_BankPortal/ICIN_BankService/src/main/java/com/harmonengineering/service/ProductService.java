package com.harmonengineering.service;

import com.harmonengineering.beans.ReportCriteriaBean;
import com.harmonengineering.entity.OrderItem;
import com.harmonengineering.entity.Product;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ProductService
{
    //public static final String productResourceUrl = "http://localhost:8080/product" ;
    public static  String serviceUrl = "/product" ;
    public static String hostString = "http://localhost:" ;
    public static String productResourceUrl ;

    static final RestTemplate restTemplate = new RestTemplate() ;

    public ProductService( String serverPort )
    {
        productResourceUrl = hostString + serverPort + serviceUrl ;
    }

    public List<Product> getProductList( )
    {
        ResponseEntity<Product[]> productList =
                restTemplate.getForEntity(productResourceUrl + "/listall",
                        Product[].class);
        return Arrays.asList( productList.getBody() ) ;
    }
    public List<Product> getProductPurchaseList()
    {
        ResponseEntity<Product[]> productList =
                restTemplate.getForEntity(productResourceUrl + "/purchase",
                        Product[].class);
        return Arrays.asList( productList.getBody() ) ;
    }
    public List<OrderItem> getPurchases()
    {
        ResponseEntity<OrderItem[]> purchaseList =
                restTemplate.getForEntity(productResourceUrl + "/purchases",
                        OrderItem[].class);
        return Arrays.asList( purchaseList.getBody() ) ;
    }
    public List<Product> getSearchResultList( Product criteriaP )
    {
        StringBuilder sb = new StringBuilder() ;
        sb.append( productResourceUrl ).append("/search") ;
        /*sb.append( "/id/").append( criteriaP.getId() ) ;
        sb.append( "/name/").append( criteriaP.getDescription() ) ;
        sb.append("/season/").append( criteriaP.getSeason()) ;
        sb.append("/brand/").append( criteriaP.getBrand()) ;
        sb.append("/category/").append( criteriaP.getCategory()) ;
        sb.append("/price/").append(  criteriaP.getPrice()) ;
        sb.append("/color/").append( criteriaP.getColor()) ;
        sb.append("/date/").append( criteriaP.getDateAdded()) ;*/

        sb.append( "/").append( criteriaP.getId() ) ;
        sb.append( "/").append( criteriaP.getDescription() ) ;
        sb.append("/").append( criteriaP.getSeason()) ;
        sb.append("/").append( criteriaP.getBrand()) ;
        sb.append("/").append( criteriaP.getCategory()) ;
        sb.append("/").append(  criteriaP.getPrice()) ;
        sb.append("/").append( criteriaP.getColor()) ;
        sb.append("/").append( criteriaP.getDateAdded()) ;

        System.out.println("StringBuilder -> " + sb.toString()) ;
        ResponseEntity<Product[]> productList =
                restTemplate.getForEntity( sb.toString(), Product[].class );
        return Arrays.asList( productList.getBody() ) ;
    }
    public List<OrderItem> getSearchPurchasesResultList(OrderItem criteriaI )
    {
        ReportCriteriaBean reportCriteria = new ReportCriteriaBean() ;
        reportCriteria.setBeginDate( null ) ;
        reportCriteria.setEndDate( null );
        return getSearchPurchasesResultList( criteriaI, reportCriteria ) ;
    }
    public List<OrderItem> getSearchPurchasesResultList(OrderItem criteriaI, ReportCriteriaBean reportCriteria )
    {
        Product criteriaP = criteriaI.getProduct() ;
        StringBuilder sb = new StringBuilder() ;
        /*sb.append( productResourceUrl ).append("/search/purchases") ;
        sb.append( "/orderid/").append( criteriaI.getOrderId() ) ;
        sb.append( "/productid/").append( criteriaI.getProductId() ) ;
        sb.append( "/name/").append( criteriaP.getDescription() ) ;
        sb.append("/season/").append( criteriaP.getSeason()) ;
        sb.append("/brand/").append( criteriaP.getBrand()) ;
        sb.append("/category/").append( criteriaP.getCategory()) ;
        sb.append("/color/").append( criteriaP.getColor()) ;
        sb.append("/begin_date/").append( reportCriteria.getBeginDate()) ;
        sb.append("/end_date/").append( reportCriteria.getEndDate()) ;*/

        sb.append( productResourceUrl ).append("/search/purchases") ;
        sb.append( "/").append( criteriaI.getOrderId() ) ;
        sb.append( "/").append( criteriaI.getProductId() ) ;
        sb.append( "/").append( criteriaP.getDescription() ) ;
        sb.append("/").append( criteriaP.getSeason()) ;
        sb.append("/").append( criteriaP.getBrand()) ;
        sb.append("/").append( criteriaP.getCategory()) ;
        sb.append("/").append( criteriaP.getColor()) ;
        sb.append("/").append( reportCriteria.getBeginDate()) ;
        sb.append("/").append( reportCriteria.getEndDate()) ;
        System.out.println("StringBuilder -> " + sb.toString()) ;
        ResponseEntity<OrderItem[]> itemList =
                restTemplate.getForEntity( sb.toString(), OrderItem[].class );
        return Arrays.asList( itemList.getBody() ) ;
    }
    public Product getProductById( Long Id )
    {
        ResponseEntity<Product> product = restTemplate.getForEntity( productResourceUrl + "/" + Id ,Product.class  ) ;
        return product.getBody() ;
    }
    public void addProduct( Product p )
    {
        System.out.println("Description: " + p.getDescription() + " Price:" + p.getPrice().toString());
        HttpEntity<Product> req = new HttpEntity<>( p  );
        restTemplate.postForObject(productResourceUrl + "/add", req, Product.class);
    }
    public void updateProduct( Product p )
    {
        HttpEntity<Product> req = new HttpEntity<>( p );
        restTemplate.exchange( productResourceUrl + "/update/" + p.getId(), HttpMethod.PUT, req, Product.class ) ;
    }
    public void deleteProduct( Product p ) { deleteProduct( p.getId() ); }
    public void deleteProduct( Long productId )
    {
        restTemplate.delete(productResourceUrl + "/delete/" + productId ) ;
    }
}
