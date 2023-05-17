/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 *
 * @author dell
 */
public interface Service {
    void createAProduct();
    void checkExist();
    void searchByName();
    void updateProduct();
    void deleteProduct();
    void saveToFile();
    void printAllProduct();
}
