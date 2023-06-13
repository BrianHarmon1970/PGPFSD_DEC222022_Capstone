package com.harmonengineering.controller;

import com.harmonengineering.beans.ConfiguredPortNumberBean;
import com.harmonengineering.beans.ReportCriteriaBean;
import com.harmonengineering.beans.ValidatorBean;
import com.harmonengineering.entity.*;
import com.harmonengineering.service.ProductService;
import com.harmonengineering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


//@RestController
@Controller
public class MainController {

    //@Value(value = "${server.port}")
    public static String serverPort  ;


    private static final UserCart userCart = new UserCart();
    //private static ProductService productService ; // = new ProductService();
    //private Environment environment ;
    private static UserService userService ;//= new UserService( ) ;

    
    private final ValidatorBean validatorBean; //= new ValidatorBean() ;
    private static ConfiguredPortNumberBean portBean ;

/*
    @Autowired private ValidatorBean validatorBean; //= new ValidatorBean() ;
    @Autowired private ReportCriteriaBean reportCriteriaBean;
    @Autowired private ConfiguredPortNumberBean portBean ;
*/

    MainController(
            ValidatorBean vb,
            ReportCriteriaBean rcb, ConfiguredPortNumberBean portBean
    ) {
        validatorBean = vb;
        serverPort = portBean.getServerPort() ;
        //this.portBean = portBean ;
        System.out.println( "Port Number: " + serverPort ) ;
        userService = new UserService( portBean /*serverPort*/ ) ;
      //  productService = new ProductService( this.serverPort ) ;
    }

    @RequestMapping("/main")
    public String main(Model model) {
        //model.addAttribute("books", bookService.getBooks());
        System.out.println( "Hello. Port = "+ serverPort ) ;
        return "home";
    }

    @RequestMapping("addProductForm")
    String AddProductForm() {
        return "ProductForm";
    }

//    @RequestMapping("viewProducts")
//    String viewProducts(Model model) {
//        List<Product> list = productService.getProductList();
//        model.addAttribute("list", list);
//        return "listProducts";
//
//    }
//
//    @RequestMapping("getProductList")
//    String getProducts(Model model) {
//        List<Product> list = productService.getProductList();
//        model.addAttribute("list", list);
//        return "listProducts";
//    }
//
//
//    @RequestMapping("viewUsers")
//    String ViewUsers(Model model) {
//        List<User> list = userService.getUserList();;
//       list = list.subList( 0, 1) ; // initially show only the first user
//        model.addAttribute("list", list);
//        return "UsersView";
//    }
//
//    @RequestMapping("searchUsers")
//    String searchUsers(Model model, HttpServletRequest request) {
//        User u = new User();
//
//        String str;
//        str = request.getParameter("user_id");
//        Long user_id = validatorBean.isValidLong(str) ? Long.parseLong(str) : null;
//
//        str = request.getParameter("user_name");
//        String user_name = validatorBean.isValidText(str) ? str : null;
//
//        str = request.getParameter("first_name");
//        String first_name = validatorBean.isValidText(str) ? str : null;
//
//        str = request.getParameter("last_name");
//        String last_name = validatorBean.isValidText(str) ? str : null;
//
//        u.setId(user_id);
//        u.setUserName( user_name );
//        u.setFirstName(first_name);
//        u.setLastName(last_name);
//
//        List<User> list = userService.getSearchResultList(u);
//        model.addAttribute("list", list);
//        return "UsersView";
//    }
//
//    @RequestMapping("searchProduct")
//    String searchProduct(Model model, HttpServletRequest request)
//            throws ParseException {
//        Product p = new Product();
//
//        String str;
//        str = request.getParameter("product_id");
//        Long product_id = validatorBean.isValidLong(str) ? Long.parseLong(str) : null;
//
//        str = request.getParameter("product_price");
//        Double product_price = validatorBean.isValidDouble(str) ? Double.parseDouble(str) : null;
//
//        str = request.getParameter("product_description");
//        String product_description = validatorBean.isValidText(str) ? str : null;
//
//        str = request.getParameter("product_category");
//        String product_category = validatorBean.isValidText(str) ? str : null;
//
//        str = request.getParameter("product_season");
//        String product_season = validatorBean.isValidText(str) ? str : null;
//
//        str = request.getParameter("product_brand");
//        String product_brand = validatorBean.isValidText(str) ? str : null;
//
//        str = request.getParameter("product_color");
//        String product_color = validatorBean.isValidText(str) ? str : null;
//
//        str = request.getParameter("product_date_added");
//        Date product_date_added = null;
//        if (validatorBean.isValidText(str)) {
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//            product_date_added = formatter.parse(request.getParameter("product_date_added"));
//        }
//
//        p.setId(product_id);
//        p.setDescription(product_description);
//        p.setPrice(product_price);
//        p.setCategory(product_category);
//        p.setSeason(product_season);
//        p.setBrand(product_brand);
//        p.setColor(product_color);
//        p.setDateAdded(product_date_added);
//
//        List<Product> list = productService.getSearchResultList(p);
//        model.addAttribute("list", list);
//        return "listProducts";
//    }
//
//    @PostMapping("addProduct")
//    String createProduct(Model model, javax.servlet.http.HttpServletRequest request) {
//        String str = request.getParameter("product_discount");
//        Double discount = validatorBean.isValidDouble(str) ? Double.parseDouble(str) : 0.00d;
//
//        Product p = new Product();
//        p.setDescription(request.getParameter("product_description"));
//        p.setPrice(Double.parseDouble(request.getParameter("product_price")));
//        p.setCategory(request.getParameter("product_category"));
//        p.setSeason(request.getParameter("product_season"));
//        p.setBrand(request.getParameter("product_brand"));
//        p.setColor(request.getParameter("product_color"));
//        p.setDiscount(discount);
//
//        //p.setCategory("CAT5") ;
//        p.setDateAdded(new Date());
//        p.setDateModified(new Date());
//
//        productService.addProduct(p);
//
//        model.addAttribute("Message", "<h1>Product created.</h1>");
//        return "Message";
//    }
//
//    @PostMapping("updateProduct")
//    String updateProduct(Model model, HttpServletRequest request)
//            throws ParseException, IOException {
//        Product p = new Product();
//        Long Id = Long.parseLong(request.getParameter("product_id"));
//        String Message = null;
//        String str;
//        str = request.getParameter("product_discount_" + Id);
//        Double discount = validatorBean.isValidDouble(str) ? Double.parseDouble(str) : 0.00d;
//        str = request.getParameter("product_price_" + Id);
//        Double doublePrice = null;
//        if (validatorBean.isValidDouble(str))
//            doublePrice = Double.parseDouble(str);
//        else {
//            Message = "Data invalid or out of range - <br>price: " + doublePrice;
//            model.addAttribute("Message", Message);
//            return "Message";
//        }
//        if (!validatorBean.isValidDecimal(discount, 3, 2) ||
//                !validatorBean.isValidDecimal(doublePrice, 10, 2)) {
//            Message = "Data invalid or out of range - <br>price: " + doublePrice + "<br>discount: " + discount;
//            model.addAttribute("Message", Message);
//            return "Message";
//        }
//
//        p.setId(Id);
//        p.setDescription(request.getParameter("product_description_" + Id));
//        p.setPrice(Double.parseDouble(request.getParameter("product_price_" + Id)));
//        p.setCategory(request.getParameter("product_category_" + Id));
//        p.setSeason(request.getParameter("product_season_" + Id));
//        p.setBrand(request.getParameter("product_brand_" + Id));
//        p.setDiscount(discount);
//        p.setColor(request.getParameter("product_color_" + Id));
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//        p.setDateAdded(formatter.parse(request.getParameter("product_date_added_" + Id)));
//        p.setDateModified(new Date());
//
//        productService.updateProduct(p);
//
//        List<Product> list = productService.getProductList();
//        model.addAttribute("list", list);
//        return "listProducts";
//    }
//
//    @RequestMapping("deleteProduct")
//    String deleteProduct(Model model, javax.servlet.http.HttpServletRequest request)
//    {
//        Long Id = Long.parseLong(request.getParameter("del_product_id"));
//        productService.deleteProduct(Id);
//
//        /*
//            Product p = new Product() ;
//            // Alternate method ( Assuming a product is already created )
//            p.setId( Id ); ;
//            productService.deleteProduct( p );
//        */
//
//        List<Product> list = productService.getProductList();
//        model.addAttribute("list", list);
//        return "listProducts";
//    }
//
//    @RequestMapping("addCartItem")
//    String AddToCart(Model model, HttpServletRequest req) {
//        String strPID = req.getParameter("add_button");
//
//        Product p = productService.getProductById(Long.parseLong(strPID));
//        userCart.add(p, 1L);
//        model.addAttribute("cartItems", userCart.getItems());
//        return "CartView";
//    }
//
//    @RequestMapping("submitCart")
//    String SubmitCart(Model model) {
//        if (!userCart.getItems().isEmpty()) {
//            UserOrder newOrder = new UserOrder();
//            newOrder.setUserId(userCart.getUserId());
//            newOrder.setOrderDate(new Date());
//            UserOrder responseOrder = userService.saveUserOrder(newOrder);
//            OrderItem lineItem = new OrderItem();
//            Iterator<CartItem> iterator = userCart.getItems().iterator();
//            while (iterator.hasNext()) {
//                CartItem cartItem = iterator.next();
//                lineItem.setOrderId(responseOrder.getID());
//                lineItem.setProductId(cartItem.getProduct().getId());
//                lineItem.setQuantity(cartItem.getQuantity());
//                userService.saveOrderItem(lineItem);
//            }
//            userCart.getItems().clear();
//        }
//        return "CartView";
//    }
//
//    @RequestMapping("emptyCart")
//    String EmptyCart(Model model) {
//        userCart.getItems().clear();
//        return "CartView";
//    }
//
//    @RequestMapping("viewCart")
//    String ViewCart(Model model) {
//        model.addAttribute("cartItems", userCart.getItems());
//        return "CartView";
//    }

//    @RequestMapping("updateUserPassword")
//    String updatePass(Model model, HttpServletRequest request) {
//        String Message;
//        String password = request.getParameter("password");
//        long userId;
//        String str = request.getParameter("user_id");
//        if (validatorBean.isValidLong(str) && validatorBean.isValidText(password)) {
//            userId = Long.parseLong(str);
//            User u = userService.getUserById(userId);
//            u.setUserPass(password);
//            userService.updateUser(u);
//            Message = "User: " + u.getUserName() + " updated successfully.";
//        } else Message = "Error validating user for update.";
//        model.addAttribute("Message", Message);
//        return "Message";
//    }
//
//    @RequestMapping("viewCatalog")
//    String ViewCatalog(Model model) {
//        List<Product> products = productService.getProductList();
//        model.addAttribute("productList", products);
//
//        return "CatalogView";
//    }

    @RequestMapping("viewOrders")
    String ViewOrders(Model model) {

        //List<UserOrder> orderList = userService.getOrders() ;
        //model.addAttribute( "orderlist", orderList ) ;

        //return "OrdersView" ;
        return "ReportCriteriaView";
    }
//
//    @RequestMapping("dispatchReport")
//    String Report(Model model, HttpServletRequest request) {
//        // set Session Attributes
//        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH) ;
//        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//        Date beginDate;
//        Date endDate;
//        String strDate = null ;
//        try {
//            strDate = request.getParameter("begin_date");
//            System.out.println(strDate);
//            if (validatorBean.isValidText(strDate))
//                beginDate = formatter.parse(strDate);
//            else beginDate = null;
//        } catch (ParseException pe) {
//            try { beginDate = format2.parse(strDate); }
//            catch (ParseException pe2 ) { beginDate = null; }
//        }
//        try {
//            strDate = request.getParameter("end_date");
//            System.out.println(strDate);
//            if (validatorBean.isValidText(strDate))
//                endDate = formatter.parse(strDate);
//            else endDate = new Date(); // now
//        } catch (ParseException pe) {
//            try { endDate = format2.parse(strDate); }
//            catch (ParseException pe2 ) { endDate = new Date(); }
//        }
//        OrderItem I = new OrderItem();
//
//        String str;
//        str = request.getParameter("order_id");
//        Long order_id = validatorBean.isValidLong(str) ? Long.parseLong(str) : null;
//
//        str = request.getParameter("product_id");
//        Long product_id = validatorBean.isValidLong(str) ? Long.parseLong(str) : null;
//
//        str = request.getParameter("product_description");
//        String product_description = validatorBean.isValidText(str) ? str : null;
//
//        str = request.getParameter("product_category");
//        String product_category = validatorBean.isValidText(str) ? str : null;
//
//        str = request.getParameter("product_season");
//        String product_season = validatorBean.isValidText(str) ? str : null;
//
//        str = request.getParameter("product_brand");
//        String product_brand = validatorBean.isValidText(str) ? str : null;
//
//        str = request.getParameter("product_color");
//        String product_color = validatorBean.isValidText(str) ? str : null;
//
//        UserOrder o = new UserOrder();
//        Product p = new Product();
//        I.setOrderId(order_id);
//        I.setProductId(product_id);
//
//        p.setDescription(product_description);
//
//        p.setCategory(product_category);
//        p.setSeason(product_season);
//        p.setBrand(product_brand);
//        p.setColor(product_color);
//        I.setProduct(p);
//        I.setOrder(o);
//
//        String strReportType = request.getParameter("report_type");
//        System.out.println("Radio value: " + strReportType);
//
//        reportCriteriaBean.setReportType(strReportType);
//        reportCriteriaBean.setBeginDate(beginDate);
//        reportCriteriaBean.setEndDate(endDate);
//        model.addAttribute("reportCriteriaBean", reportCriteriaBean ) ;
//
//        if (strReportType.equals("purchase_order")) {
//            ArrayList<PurchaseOrderContainer> purchaseOrders = new ArrayList<>();
//            List<UserOrder> orderList = userService.getOrders();
//            Iterator<UserOrder> iter = orderList.iterator();
//            while (iter.hasNext()) {
//                UserOrder userOrder = iter.next();
//                I.setOrderId( userOrder.getID()) ;
//                List<OrderItem> list = productService.getSearchPurchasesResultList(I, reportCriteriaBean);
//                if( list.size() > 0 )
//                    purchaseOrders.add( new PurchaseOrderContainer( userOrder, list) ) ;
//            }
//            model.addAttribute("orderlist", purchaseOrders);
//            return "OrdersView";
//        } else if (strReportType.equals("product_purchase"))
//        {
//            List<OrderItem> list = productService.getSearchPurchasesResultList(I, reportCriteriaBean);
//            model.addAttribute("purchaseList", list);
//            return "PurchasesView" ;
//        } else return "home";
//    }
}