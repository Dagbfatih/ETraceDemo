package business.abstracts;

import core.utilities.results.Result;

import java.io.IOException;

public interface EmailService {
    Result sendConfirmationMail(String receiverName, String toAddress);
    Result receiveMail(String receiverName, String toAddress);
}
