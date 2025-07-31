//Jasmine Sanders
import java.util.*;
import java.util.Collections;

public class Score extends Distance {
    private List<String[]> data; //Data set with rows of samples
    private List<double[]> scoreList = new ArrayList<>(); //Stores [sampleA, sampleB, distance] rows
    
    public Score(List<String[]> data){
        super();
        this.data = data;
        calculateScoreList(); //Sets scorelist
    }
    
//  Calculate the distances berwwne samples and store in scoreList [SampleA, SampleB, diatance]  
    public void calculateScoreList(){
        for(int initalSample = 1; initalSample < data.size(); initalSample++){
            for(int i = 1; i < data.size() ; i++){
//              Skip if comparing the same sample  
                if(initalSample == i) continue;
                
                double dist = calculateDistance(data.get(initalSample), data.get(i));               
                double[] row = {initalSample, i, dist};
                this.scoreList.add(row);
            }       
        } 
    }
    
//  Print out distances   
    public void displayScore(){
        System.out.printf("%-12s %-12s %-25s \n", "Sample A", "Sample B", "Distance");
        System.out.println("------------------------------------------------");
        
        for(double[] row : scoreList){
            int sampleA = (int) row[0];
            int sampleB = (int) row[1];     
            double distance = row[2];
            
            System.out.printf("%-12s %-12s %-25s \n", "Sample " + sampleA, "Sample " + sampleB, distance);
        }                 
    }
    
//  Print out N closest samples for each sample 
    public void displayClosest(int num){
        System.out.printf("%-12s %-12s %-25s %-10s \n", "Sample A", "Sample B", "Distance", "Diagnosis");
        System.out.println("--------------------------------------------------------------------------------");        
        List<String[]> sortedList = calculateSortedList(num);
            for(String[] row : sortedList){
                String sampleA = row[0];
                String sampleB = row[1];
                String distance = row[2];
                String diagnosis = row[3];
        
                System.out.printf("%-12s %-12s %-25s %-10s \n", "Sample " + sampleA, "Sample " + sampleB, distance, diagnosis);
        }   
        
    }
    
//  Calculate and return list of top N closest samples for each sample  
    public List<String[]> calculateSortedList(int num){
        List<String[]> sortedList = new ArrayList<>();
        
//      Iterate through the score list  
        for(int sampleAIndex = 1; sampleAIndex < data.size(); sampleAIndex++){
//          Initiate list for specific samples  
            List<String[]> sampleADistances = new ArrayList<>();
            
//          Iterate through the row in the score list  
            for(double[] row: scoreList){
//              Initiate variable for Sample A  
                int currentSample = (int) row[0];
                
//              Check if the current Sample is the same as the Sample A index  
                if(sampleAIndex == currentSample){
//                  Find SampleB diagnosis                   
                    int sampleBIndex = (int) row[1];
                    String diagnosis = data.get(sampleBIndex)[1];
                    
                    String[] rowToAdd = {
                        String.valueOf((int) row[0]), //Sample A Index
                        String.valueOf((int) row[1]), //Sample B Index
                        String.valueOf(row[2]), // Distance
                        diagnosis               //Diagnosis of Sample B
                    };
                            
//                  Add row to List if Sample A is the same
                    sampleADistances.add(rowToAdd);                                      
                }                
            }
            
//          Sort array by distances (closest first)
            Collections.sort(sampleADistances, (a, b) -> Double.compare(Double.parseDouble(a[2]), Double.parseDouble(b[2])));
            
//          Add top N closes samles to sorted list  
            for(int i = 0 ; i < num; i++){
                sortedList.add(sampleADistances.get(i));                           
            }            
        }       
        return sortedList;                
    }
    
//  Calculate prediction accuracy for N closets samples  
    public double calculateAccuracy(int num){
        List<String[]> sortedList = calculateSortedList(num);
        int correctlyPredicted=0;
        
//      Itereate through list grouped by N amount of samples          
        for(int sample = 0; sample < sortedList.size(); sample += num){
            List<String> diagnosisList = new ArrayList<>();
            
//          Create diagnosis list for sample
            for(int i = 0; i< num; i++){
//              Add the N rows of samples and add to list  
                String[] row = sortedList.get(sample + i);
                String diagnosis = row[3];
                diagnosisList.add(diagnosis);                
            }
            
//          find most repeated diagnosis
            String predictedDiagnosis = calculatedPredictedDiagnosis(diagnosisList);
//          Find actual diagnosis of sample A  
            int sampleAIndex = (int) Double.parseDouble(sortedList.get(sample)[0]);
            String actualDiagnosis =  data.get(sampleAIndex)[1];
            
//          Check if prediction is correct  
            if(predictedDiagnosis.equals(actualDiagnosis)){
                correctlyPredicted++;
            }           
        }     
//      Calculate the accuracy score  
        double accuracyScore = ((double)correctlyPredicted / (data.size() - 1)) * 100;
        return accuracyScore;
        
            
    }
    
//    Find most frequent diagnosis
    private String calculatedPredictedDiagnosis(List<String> diagnosisList){
        
        int diagnosisB=0;
        int diagnosisM=0;
        
        for(String diagnosis : diagnosisList){
            if(diagnosis.equals("B")){
                diagnosisB++;
            }else{
                diagnosisM++;
            }
        }         
        return(diagnosisB > diagnosisM ? "B" : "M");
    }
    
//  Display accuracy for N values
    public void displayAccuracies(){
        int[] nValues = {3, 5, 7, 11, 13};
        
        System.out.printf("%-5s %-25s \n", "N", "Accuracy%");
        System.out.println("-------------------------");
        
        for(int n: nValues){
            System.out.printf("%-5s %-25s \n", n, calculateAccuracy(n));
        }

        System.out.println("-------------------------");
        
    }
    
}
