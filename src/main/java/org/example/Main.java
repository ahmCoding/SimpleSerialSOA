package org.example;

import org.example.dataStructures.WDI;
import org.example.helper.DatasetLoader;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DatasetLoader datasetLoader = new DatasetLoader();
        List<WDI> dataset;
        dataset = datasetLoader.loadDataset("./data/WDICSV.csv");
        System.out.println(dataset.size());

    }
}