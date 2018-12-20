package Stakhurskyi.Pr5;

import java.util.Comparator;

class Product implements Comparable<Product> {
    private String name;
    private double price;
    private int quantity;

    public Product(String name,double price,int quantity){
        this.quantity= quantity;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(Product o) {
        int price1 = this.getQuantity();
        int price2 = o.getQuantity();

        if(price1 > price2) {
            return 1;
        }
        else if(price1 < price2) {
            return -1;
        }
        else {
            return 0;
        }
    }
}


class SortedByName implements Comparator<Product> {

    public int compare(Product obj1, Product obj2) {

        String str1 = obj1.getName();
        String str2 = obj2.getName();

        return str1.compareTo(str2);
    }
}


class SortedByPrice implements Comparator<Product> {

    public int compare(Product obj1, Product obj2) {

        double price1 = obj1.getPrice();
        double price2 = obj2.getPrice();

        if(price1 > price2) {
            return 1;
        }
        else if(price1 < price2) {
            return -1;
        }
        else {
            return 0;
        }
    }
}


