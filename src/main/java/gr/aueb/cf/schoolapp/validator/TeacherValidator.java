package gr.aueb.cf.schoolapp.validator;

import gr.aueb.cf.schoolapp.dto.BaseDTO;

import java.util.HashMap;
import java.util.Map;

public class TeacherValidator<T> {
    private TeacherValidator(){}

    public static <T extends BaseDTO> Map<String, String> validate(T dto) {
        Map<String, String> errors = new HashMap<>();

        if (dto.getFirstname().length() < 2 || dto.getFirstname().length() > 32) {
            errors.put("firstname", "Firstname must be between 2 and 32 characters.");
        }

        if (dto.getLastname().length() < 2 || dto.getFirstname().length() > 32) {
            errors.put("lastname", "Lastname must be between 2 and 32 characters.");
        }

        //To do validation for length for all form fields

        if (dto.getFirstname().matches("^.*\\s+.*$")) {
            errors.put("firstname", "Firstname must not include spaces");
        }

        if (dto.getLastname().matches("^.*\\s+.*$")) {
            errors.put("lastname", "Lastname must not include spaces");
        }

        //To do validation for whitespaces for all form fields

        return errors;
    }
}
