import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
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
        
//        System.out.println(Arrays.toString(data.get(3)));
//        System.out.println(Arrays.toString(data.get(4)));
//        CalculateDistance distance = new CalculateDistance(data.get(3), data.get(4));
//        
//        System.out.println("Distance: " + distance.calculateDistance());

           Score s = new Score(data);
           
           s.displayAccuracies();
    }
    
}
