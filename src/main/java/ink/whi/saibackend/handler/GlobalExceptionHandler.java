package ink.whi.saibackend.handler;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {


//    @Order
//    @ExceptionHandler(ConstraintViolationException.class)
//    public Map<String, List<String>> defaultExceptionHandler(ConstraintViolationException ex) {
//        Map<String, List<String>> validationErrors = Maps.newHashMap();
//
//        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
//        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
//            for (Path.Node node : constraintViolation.getPropertyPath()) {
//                String fieldName = node.getName();
//                List<String> lst = validationErrors.get(fieldName);
//                if (lst == null) {
//                    lst = Lists.newArrayList();
//                }
//                lst.add(constraintViolation.getMessage());
//                validationErrors.put(node.getName(), lst);
//            }
//        }
//        return validationErrors;
//    }

    @ExceptionHandler(value = Exception.class)
    public String unauthorizedHandler(HttpServletRequest request, Exception exception) throws Exception {
        return "Server exception";
    }
}