package core.business;

import core.utilities.results.Result;
import core.utilities.results.SuccessResult;

import java.util.List;

public class BusinessRules {
    public static Result Run(List<Result> results) {
        for (Result result :
                results) {
            if (!result.isSuccess()) {
                return result;
            }
        }
        return new SuccessResult();
    }
}
