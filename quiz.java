import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class quiz{
    /* We thought that it would be most effective if we were to store the
       entire quiz, including the answer key, in a single multi-dimesonal
       arry. The rows are each for a different question, while each collumn
       is: the question, answer 1, answer 2, answer 3, and the correct answer.
       Then to have an arry to keep record of what questions were answered
       correctly by having an array the size of the # of questions with either
       a 0 for wrong or a 1 for right.*/
    public static final String[][] quiz = new String[10][5];
    public static final int[] record = new int[10];
    public static void main(String[] args) throws FileNotFoundException{
        populateQuiz();
        quizTime();
        results();
    }
    public static void populateQuiz() throws FileNotFoundException{
        /*Since we used one array for the entirety of the quiz, we also saw
           that is would be most effective to input those answers in an easier
           way. We did this by creating a txt file with all the vlaues and then
           just looped through untill the entire array was filled with the
           correct values.*/
        File input = new File("quiz.txt");
        Scanner read = new Scanner(input);
        for(int i = 0; i < quiz.length; i++){
            for(int q = 0; q < quiz[0].length; q++){
                //nextLine() was used as in the txt document everything was
                //seperated with a new line
                quiz[i][q] = read.nextLine();
            }
        }
    }
    public static void quizTime(){
        for(int i = 0; i < quiz.length; i++){
            /*This segment of the code from the first print to the prompt
               for a response, is just to print out each question with its
               possible answers for each question*/
            System.out.print((i+1) +"." );
            System.out.println(quiz[i][0]);
            for(int q = 1; q < quiz[0].length-1; q++){
                System.out.println("\t" + quiz[i][q]);
            }
            System.out.print("Enter Response: ");
            /*Once the user is prompted, another method is then used to check
               the response for user error, and then wether the answer is 
               correct based on the answer key we created. If that method deems
               the response correct, this method will tell the user that. f
               not, this methode will let the user know and then print the
               answer based off the answer key.*/
            if(correctAns(i)){
                record[i]++;
                System.out.println("Correct!");
            }else{
                System.out.println("The correct answer is " + quiz[i][quiz[0].length-1]);
            }
            System.out.println("");
        }
    }
    public static boolean correctAns(int a){
        /*Once the user inputs an answer it is taken in by this method and
           checked for error and then whether it is right. */
        Scanner input = new Scanner(System.in);
        String ans = "z";
        ans = input.next();
        //If the answer input is valid, the loop will break and then continue
        //to check and see if it is right
        while(true){
            if(ans.equalsIgnoreCase("a")){
                break;
            }else if(ans.equalsIgnoreCase("b")){
                break;
            }else if(ans.equalsIgnoreCase("c")){
                break;
            }
            System.out.print("Enter a valid answer: ");
            ans = input.next();
        }
        if(ans.equalsIgnoreCase(quiz[a][quiz[0].length-1])){
            return true;
        }else{
            return false;
        }
    }
    public static void results(){
        //Since all the results for each question is stored in a seperate array
        //using 1s and 0s, the results can easily be computed.
        int correct = 0;
        for(int i = 0; i < record.length; i++){
            correct += record[i];
        }
        System.out.println("Correct answers: " + correct);
        System.out.println("Incorrect answers: " + (10 - correct));
    }
}