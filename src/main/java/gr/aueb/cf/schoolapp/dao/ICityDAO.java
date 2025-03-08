package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.model.City;

import java.sql.SQLException;
import java.util.List;

public interface ICityDAO {
    //all CRUD methods need to identified
    List<City> getAll() throws SQLException;
}
