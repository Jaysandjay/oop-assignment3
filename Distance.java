//Jasmine Sanders

public class Distance {
    private String[] data1, data2;
    private int length;
    
    public Distance(){}
    
    public Distance(String[] data1, String[] data2){
        this.data1 = data1;
        this.data2 = data2;
        this.length = data1.length; 
    }
    
    public String[] getData1(){
        return data1;
    }
    
    public String[] getData2(){
        return data2;
    }
    
    public void setData1(String[] data){
        data1 = data;
    }
    
    public void setData2(String[] data){
        data2 = data;
    }
    
    public double calculateDistance(){
        double sum = 0;
        
//        Start at index 2 (Skip id and diagnosis)
        for(int i = 2; i < length ; i++){
//          Convert values into doubles  
            double value1 = Double.parseDouble(data1[i]);
            double value2 = Double.parseDouble(data2[i]);
            sum += Math.pow((value1 - value2), 2); 
        }
        
        return Math.sqrt(sum);
    }
    
        public double calculateDistance(String[] data1, String[] data2){
        double sum = 0;
        
//        Start at index 2 (Skip id and diagnosis)
        for(int i = 2; i < data1.length ; i++){
//          Convert values into doubles  
            double value1 = Double.parseDouble(data1[i]);
            double value2 = Double.parseDouble(data2[i]);
            sum += Math.pow((value1 - value2), 2); 
        }
        
        return Math.sqrt(sum);
    }
    
    
}
