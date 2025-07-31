import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
        // Menu class to handle user interaction
        private Scanner scanner = new Scanner(System.in);
        private int choice;
        public static List<String[]> data = part1LoadFile();
        
        public Menu() {}

        public void displayMenu() {
            System.out.println("\n------------------------------------------------");
            System.out.println("Welcome to the Sample Analysis Program");
            System.out.println("Please select an option:");
            System.out.println("1. Calculate Distance Between 2 Samples");
            System.out.println("2. Display Scores Between All Samples");
            System.out.println("3. Display Closest Samples");
            System.out.println("4. Display Accuracies of Diagnosis Prediction");
            System.out.println("0. Exit");
            System.out.println("------------------------------------------------\n");
            choice = scanner.nextInt();
            executeChoice();
        }

        public void executeChoice(){
            switch (choice){
                case 1:
                    // Call method to calculate distance
                    System.out.print("Enter Sample A index: ");
                    int sampleA = scanner.nextInt();
                    System.out.print("Enter Sample B index: ");
                    int sampleB = scanner.nextInt();
                    part2CalculateDistance(sampleA, sampleB);
                    displayMenu();                  
                    break;
                case 2:
                    // Call method to display scores
                    part3DisplayScores();
                    displayMenu();
                    break;
                case 3: 
                    // Call method to display closest samples
                    System.out.print("Enter number of closest samples to display (3, 5, 7, 11): ");
                    int numClosest = scanner.nextInt();
                    part4DisplayClosestSamples(numClosest);
                    displayMenu();
                    break;
                case 4:
                    // Call method to display accuracies
                    part4DisplayAccuracies();
                    displayMenu();
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    displayMenu();
                    break;
            }
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

    public static void part2CalculateDistance( int sampleA, int sampleB){
        System.out.println("Part 1 Calculating distance between Sample " + sampleA + " and Sample " + sampleB);
        System.out.println("--------------------------------------------------------");
        Distance distance = new Distance(data.get(sampleA), data.get(sampleB));
        System.out.println("Distance: " + distance.calculateDistance());
        System.out.println("-------------------------------------------------------- ");
    }

    public static void part3DisplayScores(){
        System.out.println("Part 2 Displaying scores between all samples");
        System.out.println("--------------------------------------------------------");
        Score score = new Score(data);
        score.displayScore();
        System.out.println("--------------------------------------------------------");
    }

    public static void part4DisplayClosestSamples(int num){
        Scanner input = new Scanner(System.in);
        // Check if num is 3, 5, 7, 11 or 13
        List<Integer> validNumbers = Arrays.asList(3, 5, 7, 11, 13);
        while (!validNumbers.contains(num)) {
            System.out.print("Invalid number. Please enter one of the following: 3, 5, 7, 11, 13: ");
            num = input.nextInt();
        }
        System.out.println("Part 3 Displaying closest samples with " + num + " samples");
        System.out.println("--------------------------------------------------------");
        Score score = new Score(data);
        score.displayClosest(num);
        System.out.println("--------------------------------------------------------");
    }

    public static void part4DisplayAccuracies(){
        System.out.println("Part 4 Displaying accuracies of diagnosis prediction with N = [3, 5, 7, 11, 13] samples");
        System.out.println("--------------------------------------------------------");
        Score score = new Score(data);  
        score.displayAccuracies();
        System.out.println("--------------------------------------------------------");
    }
}
