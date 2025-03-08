package gr.aueb.cf.schoolapp.dao.exceptions;

import java.io.Serial;

public class TeacherDAOException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public TeacherDAOException(String message) {
        super(message);
    }
}
