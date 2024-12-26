package org.example;

import org.example.helper.DAO;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        DAO dao = DAO.getDao();
        String response = dao.report("EG.CFT.ACCS.ZS");
        System.out.println(response);

    }
}