package com.harmonengineering.service;

import com.harmonengineering.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductList
{
    private List<Product> products ;
    public ProductList() { products = new ArrayList<>() ; }
    public List<Product> getProducts() { return products ; }
    public void setProducts( List<Product> list ) { products = list ; }
}