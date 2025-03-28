package com.spamdetector;



import java.io.IOException;

import org.springframework.stereotype.Service;

import java.io.File;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

@Service
public class DatasetLoader {

    public Instances loadDataset(String filePath) throws IOException {
        // Load the CSV file
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(filePath));

        // Convert to WEKA dataset format
        Instances data = loader.getDataSet();

        // Check if dataset has attributes and set class index
        if (data.numAttributes() > 0) {
            // Assuming the last column is the class label (spam or not spam)
            data.setClassIndex(data.numAttributes() - 1);
        } else {
            System.out.println("Dataset has no attributes");
            return null;
        }

        return data;
    }

    public static void main(String[] args) {
        try {
            DatasetLoader loader = new DatasetLoader();
            Instances dataset = loader.loadDataset("src/main/resources/dataset/spam.csv");

            if (dataset != null) {
                System.out.println("Dataset loaded successfully!");
                System.out.println("Number of Instances: " + dataset.numInstances());
                System.out.println("Number of Attributes: " + dataset.numAttributes());

                // Print attributes to verify class index setup
                for (int i = 0; i < dataset.numAttributes(); i++) {
                    System.out.println("Attribute " + i + ": " + dataset.attribute(i).name());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
