package lbycpa2.module3;

import java.util.Scanner;
import java.util.Stack;

public class Palindrome {
    public static void main(String[] args) {

        System.out.print("Word in: ");

        Scanner scanner = new Scanner(System.in);
        String word = scanner.next();


        boolean status = true;
        String answer;
        Stack<Character> stack = new Stack<>();

        System.out.print("Is it a palindrome: ");

        char[] wordSeparated = word.toCharArray();

        for (char c: wordSeparated){
            stack.add(c);
        }

        //Checking time

        for (int i=0; i<stack.size()-1;i++){
            if (stack.pop() == wordSeparated[i]) {
                continue;
            } else {
                status = false;
                break;
            }
        }

        if (status){
            answer ="YES! Another palindrome found :)";
        } else {
            answer = "No, it's not. Sorry.";
        }
        System.out.print(answer);

    }
}
