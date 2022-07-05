package business.concretes;

import business.abstracts.EmailService;
import core.utilities.results.ErrorResult;
import core.utilities.results.Result;
import core.utilities.results.SuccessResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class EmailManager implements EmailService {
    int confirmationCode;

    @Override
    public Result sendConfirmationMail(String receiverName, String toAddress) {
        confirmationCode = 123456;
        System.out.println("confirmation email sent, please enter the code");
        return new SuccessResult("email sent, please enter the code");
    }

    @Override
    public Result receiveMail(String senderName, String toAddress) {
        Scanner in = new Scanner(System.in);
        var result = Integer.parseInt(in.nextLine());

        if (result != confirmationCode) {
            return new ErrorResult("code wrong");
        }
        System.out.println("email received");
        return new SuccessResult("email received");
    }
}
