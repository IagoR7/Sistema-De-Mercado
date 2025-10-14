/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CategoriaDao;
import java.util.List;
import model.Categoria;

public class CategoriaController {
    private CategoriaDao dao = new CategoriaDao();

    public List<Categoria> buscarCategorias() {
        return dao.buscarTodas();
    }
}