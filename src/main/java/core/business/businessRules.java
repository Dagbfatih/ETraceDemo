package core.business;

import core.utilities.results.Result;
import core.utilities.results.SuccessResult;

public class businessRules {
    public static Result Run(Result results[]) {
        for (Result result :
                results) {
            if (!result.isSuccess()) {
                return result;
            }
        }
        return new SuccessResult();
    }
}
