/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.ArrayList;
import util.Input;

/**
 *
 * @author dell
 */
public class Menu {
    public void addNewOption(ArrayList menuList, String s) {
        menuList.add(s);
    }
    
    public void printMenu(ArrayList menuList) {
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println(menuList.get(i));
        }
    }
    
    public int getChoice(ArrayList menuList) {
        int choice;
        choice = Input.getInteger("Enter choice [1.." + (menuList.size() - 3) + "]: ", "Please enter again!");
        return choice;
    }
}
