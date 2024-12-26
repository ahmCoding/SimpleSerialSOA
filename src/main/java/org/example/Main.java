package org.example;

import org.example.dataStructures.WDI;
import org.example.helper.DAO;
import org.example.helper.DatasetLoader;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        DAO dao = DAO.getDao();
        String response = dao.query("IRI", "EG.CFT.ACCS.ZS");
        System.out.println(response);

    }
}