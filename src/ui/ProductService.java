/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import data.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Input;

/**
 *
 * @author dell
 */
public class ProductService implements Service{
    ArrayList<Product> productList = readFromFile();
    @Override
    public void createAProduct() {  
        String productName, productId, status;
        int quantity;
        double unitPrice;
        boolean cont = false;
        do {            
            productId = Input.getStringWithRegex("Enter id(PXXX): ", 
                    "Please enter again!", "^[P|p][0-9]{3}$");
            if (searchById(productId) != -1 ) {
                System.out.println("ProductID have already exist!");
                cont = true;
            }
            else
                cont = false;
        } while (cont);
        do {            
            productName = Input.getStringWithRegex("Enter product's name: ", 
                "Please enter again!", "^[A-Za-z0-9]{5,}$");
            if (checkExistByName(productName) != -1 ) {
                System.out.println("ProductID have already exist!");
                cont = true;
            }
            else
                cont = false;
        } while (cont);
        
        status = Input.getStringWithRegex("Available or Not Availabe", 
                "Please enter again!", "^Available|^available|^not available|^Not Availabe|^$");
        if (status.toLowerCase().equalsIgnoreCase("not available"))
            quantity = 0;
        else
            quantity = Input.getInteger("Enter quantity [0..1000]: ", "Please enter again!", 0, 1000);
        unitPrice = Input.getDouble("Enter unit price[0..1000]: ", "Please enter again!", 0, 1000);
        productList.add(new Product(productId, productName, status, unitPrice, quantity));
    }
    
    public int searchById(String sId) {
        int pos = -1;
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getProductID().equalsIgnoreCase(sId))
                    pos = i;
            }
        return pos;
    }
    
    @Override
    public void checkExist() {
        int count = 0;
        if (productList.size() == 0)
            System.out.println("There is no product actually. Please add a new product");
        else {
            String cName = Input.getString("Enter name you want to check: ", "Please enter again!");
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getProductName().equalsIgnoreCase(cName)) {
                    System.out.println("Exist product");
                    count++;
                }
            }
            if (count == 0) 
                System.out.println("No product found!");
        }
    }
    
    public int checkExistByName(String cName) {
        int pos = -1;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductName().equalsIgnoreCase(cName)) 
                pos = i;
        }
        return pos;

    }

    @Override
    public void searchByName() {
        if (productList.size() == 0)
            System.out.println("There is no product actually. Please add a new product");
        else {
            String sName = Input.getString("Enter product's name you want to search.", 
                    "Please enter again!");
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getProductName().contains(sName)) 
                    System.out.println(productList);
            }
        }
    }

    @Override
    public void updateProduct() {
        if (productList.size() == 0)
            System.out.println("There is no product actually. Please add a new product");
        else {
            Scanner sc = new Scanner(System.in);
            String uID = Input.getStringWithRegex("Enter product's ID you want to update new information:",
                    "Please enter again!", "^[P|p][0-9]{3}$");
            int pos = searchById(uID);
            String uName, uStatus, uQuantity = "", uPrice;
            int intUQuantity = -1;
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getProductID().equalsIgnoreCase(uID)) {
                    System.out.println("Enter product's name you want to update:");
                    uName = sc.nextLine();
                    System.out.println("Enter product's status you want to update:");
                    uStatus = sc.nextLine();
                    if (uStatus.toLowerCase().equalsIgnoreCase("not available")) {
                        intUQuantity = 0;
                        uQuantity = "0";
                    }
                    else {
                        System.out.println("Enter the number of product you want to update:");
                        uQuantity = sc.nextLine();
                        }
                    System.out.println("Enter product's price you want to update:");
                    uPrice = sc.nextLine();
                    if (uName.isEmpty()|| uStatus.isEmpty() || uStatus.matches
                            ("^Availabe|^available|^not available|^Not Available|$") == false ||
                            uQuantity.isEmpty() || Integer.parseInt(uQuantity) < 0 
                            || Integer.parseInt(uQuantity) > 1000 || 
                            Double.parseDouble(uPrice) < 0 || Double.parseDouble(uPrice) > 1000)
                        return;
                    else {
                        productList.get(pos).setProductName(uName);
                        productList.get(pos).setStatus(uStatus);
                        if (intUQuantity == 0)
                            productList.get(pos).setQuantity(intUQuantity);
                        else
                            productList.get(pos).setQuantity(Integer.parseInt(uQuantity));
                        productList.get(pos).setUnitPrice(Double.parseDouble(uPrice));
                    }
                }
            }
        }
    }

    @Override
    public void deleteProduct() {
        if (productList.size() == 0)
            System.out.println("There is no product actually. Please add a new product");
        else {
            String dID = Input.getStringWithRegex("Enter product's ID you want "
                    + "to delete:", "Please enter again!", "^[P|p][0-9]{3}$");
            int pos = searchById(dID);
            if (pos > -1)  {
                productList.get(pos).setStatus("Not available");
                productList.get(pos).setQuantity(0);
            }
            else
                System.out.println("Productname does not exist");
        }
    }
    
    public ArrayList<Product> readFromFile()  {
        ArrayList<Product> productList = new ArrayList<>();
        FileInputStream fi = null;
        ObjectInputStream oi = null;
        try {
            File f = new File("product.dat");
            if (!f.exists())
                return productList;
            else {
            fi = new FileInputStream("product.dat");
            oi = new ObjectInputStream(fi);
            Product p;
            while ((p = (Product)(oi.readObject())) != null)
                productList.add(p);
//            productList = (ArrayList<Product>)oi.readObject();
            fi.close();
            oi.close();
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public void saveToFile() {

        if (productList.size() == 0)
            System.out.println("Empty list!");
        else
            try {
                FileOutputStream f = new FileOutputStream("product.dat");
                ObjectOutputStream fo = new ObjectOutputStream(f);
                for (Product product : productList) {
                    fo.writeObject(product);
                }
                fo.close();
                f.close();
                System.out.println("Save to file success!");
            } catch (Exception e) {
                e.printStackTrace();
            } 
    }

    public void sortByQuantity() {
        for (int i = 0; i < productList.size() - 1; i++) {
            for (int j = i; j < productList.size(); j++) {
                if (productList.get(i).getQuantity() < productList.get(j).getQuantity()) {
                    Product tmp = productList.get(i);
                    productList.set(i, productList.get(j));
                    productList.set(j, tmp);
                }
            }
        }
    }
    
    @Override
    public void printAllProduct() {
        sortByQuantity();
        if (productList.size() == 0)
            System.out.println("There is no product actually. Please add a new product");
        else {
            System.out.printf("|%-5s|%-15s|%-13s|%-10s|%-8s|\n",
                                "ID", "Product Name", "Status", "Unit price", "Quantity");
            for (Product product : productList) {
                System.out.println(product);
            }
        }
    }
}
