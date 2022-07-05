package business.concretes;

import business.abstracts.EmailService;
import core.utilities.results.ErrorResult;
import core.utilities.results.Result;
import core.utilities.results.SuccessResult;

import java.util.Random;
import java.util.Scanner;

public class EmailManager implements EmailService {
    int confirmationCode;

    @Override
    public Result sendConfirmationMail(String receiverName, String toAddress) {
        confirmationCode = generateRandomCode();
        System.out.println("Confirmation Email\n" + receiverName + " doğrulama kodunuz: " + confirmationCode);
        return new SuccessResult();
    }

    @Override
    public Result receiveMail(String senderName, String toAddress) {
        System.out.println("Please enter the code:");
        Scanner in = new Scanner(System.in);
        var result = Integer.parseInt(in.nextLine());

        if (result != confirmationCode) {
            return new ErrorResult("Code wrong");
        }
        System.out.println("Entered code is correct, welcome!");
        return new SuccessResult("Entered code is correct, welcome!");
    }

    private static int generateRandomCode() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return Integer.parseInt(String.format("%06d", number));
    }
}
