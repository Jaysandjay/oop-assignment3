import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
//      Part 1 load file and create data set
        List<String[]> data = part1LoadFile();

//      Part 2 calculate distance between two samples
        part2CalculateDistance(data, 3, 4);

//      Part 3 display scores between all samples
        //part3DisplayScores(data);

//      Part 4 display closest samples with N samples
        part4DisplayClosestSamples(data, 3);

//      Part 4 ii display accuracies of diagnosis prediction with N samples
        part4DisplayAccuracies(data);
    }
    
    public static List<String[]> part1LoadFile(){
        //      Create list of arrays  
        List<String[]> data = new ArrayList<>();
        
//      open and read the file  
        try(BufferedReader br = new BufferedReader(new FileReader("./Experiment.csv"))){
//           Skip header
            br.readLine();
            
//          Create line variable and read the next line  
            String line = br.readLine();
           
//          if line is not empty  
            while(line != null){
//              Create array of line splitting the data by ','  
                String[] set = line.split(",");
//              Add array to data array  
                data.add(set);
//              Read the next line  
                line = br.readLine();
            } 
//        If error, print stack trace    
        } catch(IOException e){
            e.printStackTrace();
        }
        return data;
    }

    public static void part2CalculateDistance(List<String[]> data, int sampleA, int sampleB){
        System.out.println("Part 1 Calculating distance between Sample " + sampleA + " and Sample " + sampleB);
        System.out.println("--------------------------------------------------------");
        Distance distance = new Distance(data.get(sampleA), data.get(sampleB));
        System.out.println("Distance: " + distance.calculateDistance());
        System.out.println("-------------------------------------------------------- \n");
    }

    public static void part3DisplayScores(List<String[]> data){
        System.out.println("Part 2 Displaying scores between all samples");
        System.out.println("--------------------------------------------------------");
        Score score = new Score(data);
        score.displayScore();
        System.out.println("-------------------------------------------------------- \n");
    }

    public static void part4DisplayClosestSamples(List<String[]> data, int num){
        System.out.println("Part 3 Displaying closest samples with " + num + " samples");
        System.out.println("--------------------------------------------------------");
        Score score = new Score(data);
        score.displayClosest(num);
        System.out.println("-------------------------------------------------------- \n");
    }

    public static void part4DisplayAccuracies(List<String[]> data){
        System.out.println("Part 4 Displaying accuracies of diagnosis prediction with N = [3, 5, 7, 11, 13] samples");
        System.out.println("--------------------------------------------------------");
        Score score = new Score(data);  
        score.displayAccuracies();
        System.out.println("-------------------------------------------------------- \n");
    }

}
