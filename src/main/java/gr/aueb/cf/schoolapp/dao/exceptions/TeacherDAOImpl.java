package gr.aueb.cf.schoolapp.dao.exceptions;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public void delete(Integer id) throws TeacherDAOException {
        String sql = "DELETE FROM Teachers WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new TeacherDAOException("SQL Error. Teacher with id: " + id + " not deleted.");
        }
    }

    @Override
    public Teacher getById(Integer id) throws TeacherDAOException {
        String sql = "SELECT * FROM Teachers WHERE id = ?";
        Teacher teacher = null;
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                teacher = new Teacher(rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("vat"),
                        rs.getString("fatherName"),
                        rs.getString("phone_num"),
                        rs.getString("email"),
                        rs.getString("street"),
                        rs.getString("street_num"),
                        rs.getString("zipcode"),
                        rs.getInt("city_id"),
                        rs.getString("uuid"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("uodated_at").toLocalDateTime());
            }
            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();

            //logging
            throw new TeacherDAOException("SQL Error. Teacher with id: " + id + " error in finding.");
        }
    }

    @Override
    public List<Teacher> getAll() throws TeacherDAOException {
        String sql = "SELECT * FROM Teachers";
        Teacher teacher = null;
        List<Teacher> teachers = new ArrayList<>();
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            rs = ps.executeQuery();

            while (rs.next()) {
                teacher = new Teacher(rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("vat"),
                        rs.getString("fatherName"),
                        rs.getString("phone_num"),
                        rs.getString("email"),
                        rs.getString("street"),
                        rs.getString("street_num"),
                        rs.getString("zipcode"),
                        rs.getInt("city_id"),
                        rs.getString("uuid"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("uodated_at").toLocalDateTime());
                teachers.add(teacher);
            }
            return teachers;

        } catch (SQLException e) {
            e.printStackTrace();

            //logging
            throw new TeacherDAOException("SQL Error. Error in get all teachers.");
        }
    }

    @Override
    public Teacher getByUUID(String uuid) throws TeacherDAOException {
        String sql = "SELECT * FROM Teachers WHERE uuid = ?";
        Teacher teacher = null;
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, uuid);
            rs = ps.executeQuery();

            if (rs.next()) {
                teacher = new Teacher(rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("vat"),
                        rs.getString("fatherName"),
                        rs.getString("phone_num"),
                        rs.getString("email"),
                        rs.getString("street"),
                        rs.getString("street_num"),
                        rs.getString("zipcode"),
                        rs.getInt("city_id"),
                        rs.getString("uuid"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("uodated_at").toLocalDateTime());
            }
            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();

            //logging
            throw new TeacherDAOException("SQL Error. Teacher with uuid: " + uuid + " error in finding.");
        }
    }

    @Override
    public List<Teacher> getByLastname(String lastname) throws TeacherDAOException {
        String sql = "SELECT * FROM Teachers WHERE lastname LIKE ?";
        Teacher teacher = null;
        List<Teacher> teachers = new ArrayList<>();
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1,lastname + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                teacher = new Teacher(rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("vat"),
                        rs.getString("fatherName"),
                        rs.getString("phone_num"),
                        rs.getString("email"),
                        rs.getString("street"),
                        rs.getString("street_num"),
                        rs.getString("zipcode"),
                        rs.getInt("city_id"),
                        rs.getString("uuid"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("uodated_at").toLocalDateTime());
                teachers.add(teacher);
            }
            return teachers;

        } catch (SQLException e) {
            e.printStackTrace();

            //logging
            throw new TeacherDAOException("SQL Error. Error in get teachers by lastname.");
        }
    }

    @Override
    public Teacher getTeacherByVat(String vat) throws TeacherDAOException {
        String sql = "SELECT * FROM Teachers WHERE vat = ?";
        Teacher teacher = null;
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, vat);
            rs = ps.executeQuery();

            if (rs.next()) {
                teacher = new Teacher(rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("vat"),
                        rs.getString("fatherName"),
                        rs.getString("phone_num"),
                        rs.getString("email"),
                        rs.getString("street"),
                        rs.getString("street_num"),
                        rs.getString("zipcode"),
                        rs.getInt("city_id"),
                        rs.getString("uuid"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("uodated_at").toLocalDateTime());
            }
            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();

            //logging
            throw new TeacherDAOException("SQL Error. Teacher with vat: " + vat + " error in finding.");
        }
    }
}
