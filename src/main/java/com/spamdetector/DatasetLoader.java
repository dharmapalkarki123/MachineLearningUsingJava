package com.spamdetector;

import java.io.IOException;
import java.io.File;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class DatasetLoader {
	 public Instances loadDataset(String filePath) throws IOException {
	        // Load the CSV file
	        CSVLoader loader = new CSVLoader();
	        loader.setSource(new File(filePath));

	        // Convert to WEKA dataset format
	        Instances data = loader.getDataSet();

	        // Set the class index (the last column is usually the label)
	        data.setClassIndex(data.numAttributes() - 1);

	        return data;
	    }

	    public static void main(String[] args) {
	        try {
	            DatasetLoader loader = new DatasetLoader();
	            Instances dataset = loader.loadDataset("src/main/resources/dataset/spam.csv");
	            System.out.println("Dataset loaded successfully!");
	            System.out.println("Number of Instances: " + dataset.numInstances());
	            System.out.println("Number of Attributes: " + dataset.numAttributes());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
