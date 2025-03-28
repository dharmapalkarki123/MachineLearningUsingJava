package com.spamdetector;



import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instance;
import weka.core.DenseInstance;
import weka.core.Attribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
@Service
public class SpamClassifier {

    private NaiveBayes nb;
    private Instances dataset;

    // Constructor to initialize and train the model
    public SpamClassifier() throws Exception {
        // Load the dataset
        DataSource source = new DataSource("src/main/resources/dataset/spam.csv");
        dataset = source.getDataSet();
        
        // Set the last column as the target class
        dataset.setClassIndex(dataset.numAttributes() - 1);

        // Build the NaiveBayes classifier
        nb = new NaiveBayes();
        nb.buildClassifier(dataset);

        // Optional: Evaluate the model
        Evaluation eval = new Evaluation(dataset);
        eval.crossValidateModel(nb, dataset, 10, new Random(1));
        System.out.println(eval.toSummaryString());
    }

    // Method to classify a single email (text)
    public boolean classify(String emailText) throws Exception {
        // Assuming the emailText corresponds to a single instance of attributes
        // You need to create a similar instance format as in your dataset

        // Assuming you have two attributes (one for text and one for category)
        List<Attribute> attributes = new ArrayList<>();
        
        // Assuming your CSV has these two attributes (text and category)
        attributes.add(new Attribute("text", (List<String>) null)); // Text attribute
        attributes.add(new Attribute("category", List.of("ham", "spam"))); // Category attribute

        // Create a new instance (feature vector) for the email
        Instance instance = new DenseInstance(2); // Assuming two attributes
        instance.setValue(attributes.get(0), emailText);  // Set the text
        instance.setDataset(dataset);  // Set the dataset for reference

        // Classify the instance using the model
        double result = nb.classifyInstance(instance);
        
        // Return true if the classification is "spam", false if "ham"
        return dataset.classAttribute().value((int) result).equals("spam");
    }
}

