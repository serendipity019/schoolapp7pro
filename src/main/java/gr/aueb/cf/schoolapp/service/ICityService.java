package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.model.City;

import java.sql.SQLException;
import java.util.List;

public interface ICityService {
    /*Also here if we want the user to have screen that can make CRUD in this parametric table
    then need to have and all others here, insert, update etc */
    List<City> getAllCities() throws SQLException;
}
