package gr.aueb.cf.schoolapp.dao.exceptions;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO {

    @Override
    public Teacher insert(Teacher teacher) throws TeacherDAOException {
        String sql = "INSERT INTO Teachers (firstname, lastname, vat, fatherName, phone_num, " +
               " email, street, street_num, zipcode, city_id, uuid, created_at, updated_at)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Teacher insertedTeacher = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, teacher.getFirstname());
            ps.setString(2, teacher.getLastname());
            ps.setString(3, teacher.getVat());
            ps.setString(4, teacher.getFatherName());
            ps.setString(5, teacher.getPhoneNum());
            ps.setString(6, teacher.getEmail());
            ps.setString(7, teacher.getStreet());
            ps.setString(8, teacher.getStreetNum());
            ps.setString(9, teacher.getZipCode());
            ps.setInt(10, teacher.getCityId());
            ps.setString(11, teacher.getUuid());
            ps.setTimestamp(12, Timestamp.valueOf(LocalDateTime.now())); // second way: ps.setTimestamp(12, Timestamp.valueOf(teacher.getCreatedAt()));
            ps.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now())); // second way: ps.setTimestamp(12, Timestamp.valueOf(teacher.getUpdatedAt()));

            ps.executeUpdate();

            ResultSet rsGeneratedKeys = ps.getGeneratedKeys();
            if (rsGeneratedKeys.next()) {
                int generatedKey = rsGeneratedKeys.getInt(1);
                insertedTeacher =getById(generatedKey);
            }

            //logging
            return insertedTeacher;
        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new TeacherDAOException("SQL Error. Teacher with vat: " + teacher.getVat() + " not inserted.");
        }
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDAOException {
        String sql = "UPDATE Teachers SET firstname = ?, SET lastname = ?, SET vat = ?, SET fatherName = ?, " +
                " SET phone_num = SET, SET email = ?, SET street = ?, SET street_num = ?, " +
                " SET zipcode = ?, SET city_id = ?, SET updated_at = ? WHERE id = ?" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Teacher updatedTeacher = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, teacher.getFirstname());
            ps.setString(2, teacher.getLastname());
            ps.setString(3, teacher.getVat());
            ps.setString(4, teacher.getFatherName());
            ps.setString(5, teacher.getPhoneNum());
            ps.setString(6, teacher.getEmail());
            ps.setString(7, teacher.getStreet());
            ps.setString(8, teacher.getStreetNum());
            ps.setString(9, teacher.getZipCode());
            ps.setInt(10, teacher.getCityId());
            ps.setTimestamp(11, Timestamp.valueOf(teacher.getUpdatedAt()));
            ps.setInt(12, teacher.getId());

            ps.executeUpdate();

            updatedTeacher = getById(teacher.getId());
            //logging
            return updatedTeacher;
        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new TeacherDAOException("SQL Error. Teacher with vat: " + teacher.getVat() + " not updated.");
        }
    }

    @Override
    public void delete(Teacher teacher) throws TeacherDAOException {

    }

    @Override
    public Teacher getById(Integer id) throws TeacherDAOException {
        return null;
    }

    @Override
    public List<Teacher> getAll() throws TeacherDAOException {
        return List.of();
    }

    @Override
    public Teacher getByUUID(String uuid) throws TeacherDAOException {
        return null;
    }

    @Override
    public Teacher getByLastname(String lastname) throws TeacherDAOException {
        return null;
    }

    @Override
    public Teacher getTeacherByVat(String vat) throws TeacherDAOException {
        return null;
    }
}
