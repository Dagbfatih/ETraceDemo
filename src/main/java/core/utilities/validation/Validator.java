package core.utilities.validation;

import core.utilities.results.ErrorResult;
import core.utilities.results.Result;
import core.utilities.results.SuccessResult;

import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public abstract class Validator<T> {

    // Must continue
//    RuleBuilderInitial<T, Type> ruleFor(Function<T, Type> filter) {
//        return new RuleBuilderClass<T, Type>();
//    }

    public abstract List<Result> validate(T entity);

    public Result createValidationMessages(List<Result> results) {
        Supplier<Stream<Result>> validationErrors = () -> results.stream().filter(v -> !v.isSuccess());
        var resultMessage = "İşlem başarılı";

        if (validationErrors.get().count() > 0) {
            resultMessage = String.join(",", validationErrors.get().map(Result::getMessage).toList());
            return new ErrorResult(resultMessage);
        }

        return new SuccessResult(resultMessage);
    }

    public Result mustGreaterThan(String propertyName, String data, int limit) {
        boolean isSuccess = data.length() > limit;
        String message = isSuccess ? "başarılı" : propertyName + " en az " + limit + " karakterden oluşmalıdır.";
        return new Result(isSuccess, message);
    }

    public Result mustGreaterThanOrEqualTo(String propertyName, String data, int limit) {
        boolean isSuccess = data.length() > limit;
        String message = isSuccess ? "başarılı" : propertyName + " en az " + (limit - 1) + " karakterden oluşmalıdır.";
        return new Result(isSuccess, message);
    }

    public Result mustCompatibleWithEmailFormat(String email) {
        String regexPattern = "^(.+)@(\\S+)$";
        boolean isSuccess = Pattern.compile(regexPattern).matcher(email).matches();
        String message = isSuccess ? "başarılı" : "E-posta formatına uygun olmalıdır.";

        return new Result(isSuccess, message);
    }
}
