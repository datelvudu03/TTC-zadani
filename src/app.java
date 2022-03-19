import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class app {
    public static void main(String[] args) {
     Scanner scannerM = new Scanner(System.in);
     System.out.println("Press 1 to insert the numbers by yourself \nor press 2 to enter the path of the file that you want to read.");
     String choice,choice2,input,result,path = "";

     while (true){
         choice = scannerM.nextLine();
         if (choice.equals("1") || choice.equals("2")){
             break;
         }
         System.out.println("Please enter 1 or 2");
     }
        System.out.println("Press enter if you want the result to be shown on the console or \ntype 1 and enter the path where the output will be stored.");
     while (true){
            choice2 = scannerM.nextLine();
            if (choice2.equals("1") || choice2.isEmpty()){
                break;
            }
            System.out.println("Please press enter or type 1");
        }

     if (choice.equals("1")){
         input = userNumberInput(scannerM);
     }else {
         System.out.println("Please enter path of the input file:");
         path = scannerM.nextLine();
         input = getNumbers(path);
     }
        System.out.println("This input will be process: " + input);
        result = doNumbers(input);

        if (choice2.equals("1")){
            System.out.println("Please enter path where the result will be stored:");
            path = scannerM.nextLine();
            saveResult(result,path);
        }else {
            System.out.println(result);
        }
    }

    public static String userNumberInput(Scanner scanner){
        System.out.println("Enter numbers pls: ");
        String input = "";
        while (true){
            String temp = scanner.nextLine();

            boolean isNumeric = true;
            for (int i = 0; i < temp.length(); i++) {
                if (!Character.isDigit(temp.charAt(i))) {
                    isNumeric = false;
                }
            }
            if (!isNumeric && !temp.equalsIgnoreCase("done") || temp.isEmpty()){
                System.out.println("Input is not a number. Try again.");
                continue;
            }
            if(temp.equalsIgnoreCase("done")){
                break;
            }else {
                if (input.equals("")){
                    input += temp;
                }else {
                    input+= "," + temp;
                }
                System.out.println("Enter 'done' to finish input.\nYour input:" + input);
            }
        }
        return input;
    }

    public static String getNumbers(String path){
        String numberList = "";
        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                numberList += data;
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return  numberList;
    }

    public static void saveResult(String result,String path){
        try {
            FileWriter myWriter = new FileWriter(path + "output.txt");
            myWriter.write(result);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static String doNumbers(String numbers){
        String[] numberList = numbers.split(",");
        String result = "";
        ArrayList<Integer> nums = new ArrayList<>();
        for (String num: numberList){
            nums.add(Integer.valueOf(num));
        }
        int totalNumberSize = numberList.length;
        int decider = (totalNumberSize % 2 == 0) ? 0 : 1;
        for (int num: nums){
            if (num % 2 == decider){
                if (result.equals("")){
                    result+=num;
                }else {
                    result+="," + num;
                }

            }
        }

        return result;
    }

}
