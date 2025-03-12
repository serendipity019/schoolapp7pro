package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ICityDAO;
import gr.aueb.cf.schoolapp.model.City;

import java.sql.SQLException;
import java.util.List;

public class CitySetviceImpl implements ICityService {
    private final ICityDAO cityDao;

    public CitySetviceImpl(ICityDAO cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public List<City> getAllCities() throws SQLException {
        try {
            return cityDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
