/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import ui.Menu;
import ui.ProductService;
import util.Input;

/**
 *
 * @author dell
 */
public class ProductManagement {
    
    public static void main(String[] args) {
        Menu menu = new Menu();
        ArrayList<String> menuList = new ArrayList();
        menu.addNewOption(menuList, "1- Print");
        menu.addNewOption(menuList, "2- Create a product");
        menu.addNewOption(menuList, "3- Check exists");
        menu.addNewOption(menuList, "4- Search product' information by Name");
        menu.addNewOption(menuList, "5- Update product");
        menu.addNewOption(menuList, "   5.1- Update product");
        menu.addNewOption(menuList, "   5.2- Delete product");
        menu.addNewOption(menuList, "6- Save products to file");
        menu.addNewOption(menuList, "Others- Quit");
        ProductService ps = new ProductService();
        int choice;
        do {   
            menu.printMenu(menuList);
            choice = menu.getChoice(menuList);
            switch (choice) {
                case 1:
                    ps.printAllProduct();
                    break;
                case 2:
                    ps.createAProduct();
                    break;
                case 3:
                    ps.checkExist();
                    break;
                case 4:
                    ps.searchByName();
                    break;
                case 5:
                    System.out.println("1- Update product");
                    System.out.println("2- Delete product");
                    int updateChoice = Input.getInteger("Enter choice[1..2]: ", "Please enter again!", 1, 2);
                    if (updateChoice == 1)
                        ps.updateProduct();
                    else if (updateChoice == 2)
                        ps.deleteProduct();
                    break;
                case 6:
                    ps.saveToFile();
                    break;
                default:
                    System.out.println("Goodbye!");
            }
        } while (choice > 0 && choice < 7);
    }
    
    
}
