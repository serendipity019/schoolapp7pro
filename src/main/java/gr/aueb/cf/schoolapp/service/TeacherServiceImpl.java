package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.exceptions.TeacherAlreadyExistsException;
import gr.aueb.cf.schoolapp.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.mapper.Mapper;
import gr.aueb.cf.schoolapp.model.Teacher;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TeacherServiceImpl implements ITeacherService {

    private final ITeacherDAO teacherDAO;
    //Constructor injection
    public TeacherServiceImpl(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public TeacherReadOnlyDTO insertTeacher(TeacherInsertDTO dto) throws
            TeacherDAOException, TeacherAlreadyExistsException {
        Teacher teacher;
        Teacher insertedTeacher;


        try {
            teacher = Mapper.mapTeacherInsertToModel(dto);

            if (teacherDAO.getTeacherByVat(dto.getVat()) != null)
                throw new TeacherAlreadyExistsException("Teacher with vat: " + dto.getVat() + " already exists");

            insertedTeacher = teacherDAO.insert(teacher);
            //logging
            return Mapper
                    .mapTeacherToReadOnlyDTO(insertedTeacher)
                    .orElseThrow(() -> new TeacherDAOException("Error in Mapping"));
        } catch (TeacherDAOException | TeacherAlreadyExistsException e) {
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public TeacherReadOnlyDTO updateTeacher(Integer id, TeacherUpdateDTO dto) throws TeacherDAOException, TeacherAlreadyExistsException, TeacherNotFoundException {
        Teacher teacher;
        Teacher fetchedTeacher;
        Teacher updatedTeacher;


        try {
            //First check for reason errors
            if (teacherDAO.getById(id) == null) {
                throw new TeacherNotFoundException("Teacher with id: " + id + " was not found");
            }

            //Here we check if the vat is not anyone else vat.
            fetchedTeacher = teacherDAO.getTeacherByVat(dto.getVat());
            if (fetchedTeacher != null && !fetchedTeacher.getId().equals(dto.getId())) {
                throw new TeacherAlreadyExistsException("Teacher with id: " + dto.getId() + " already exists");
            }

            // If not exist reason errors then continue the process. Here make the dto to model
            teacher = Mapper.mapTeacherUpdateToModel(dto);
            updatedTeacher = teacherDAO.update(teacher);
            //logging. again the model been dto
            return Mapper.mapTeacherToReadOnlyDTO(updatedTeacher)
                    .orElseThrow(() -> new TeacherDAOException("Error during mapping"));


        } catch (TeacherDAOException | TeacherNotFoundException |TeacherAlreadyExistsException e) {
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public void deleteTeacher(Integer id) throws TeacherDAOException, TeacherNotFoundException {
        try {
            if (teacherDAO.getById(id) == null) {
                throw new TeacherNotFoundException("Teacher with id: " + id + " not found");
            }
            //logging
            teacherDAO.delete(id);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public TeacherReadOnlyDTO getTeacherById(Integer id) throws TeacherDAOException, TeacherNotFoundException {
        Teacher teacher;
        try {
            teacher = teacherDAO.getById(id);

            return Mapper.mapTeacherToReadOnlyDTO(teacher)
                    .orElseThrow(() -> new TeacherNotFoundException("Teacher with id: " + id + " not found"));
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public List<TeacherReadOnlyDTO> getAllTeachers() throws TeacherDAOException {
        List<Teacher> teachers;
        try {
            teachers = teacherDAO.getAll();

            return teachers.stream()
                    .map(Mapper::mapTeacherToReadOnlyDTO)
                    .flatMap(Optional::stream)// Here take off all the empty dtos
                    .collect(Collectors.toList()); //this return the clear list of dtos

            //Second way
//            return teachers.stream()
//                    .map(t -> Mapper.mapTeacherToReadOnlyDTO(t).orElse(null))
//                    .collect(Collectors.toList());
        } catch (TeacherDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    //We don't make this, because in service make everything that return to user and this used for internals processes.
    @Override
    public TeacherReadOnlyDTO getTeacherByUuid(String uuid) throws TeacherDAOException, TeacherNotFoundException {
        return null;
    }

    //We don't make this, because in service make everything that return to user and this used for internals processes.
    @Override
    public TeacherReadOnlyDTO getTeacherByVat(String vat) throws TeacherDAOException, TeacherNotFoundException {
        return null;
    }

    @Override
    public List<TeacherReadOnlyDTO> getTeacherByLastname(String lastname) throws TeacherDAOException {
        List<Teacher> teachers;
        try {
            teachers = teacherDAO.getByLastname(lastname);

            return teachers.stream()
                    .map(Mapper::mapTeacherToReadOnlyDTO)
                    .flatMap(Optional::stream)// Here take off all the empty dtos
                    .collect(Collectors.toList()); //this return the clear list of dtos

            //Second way
//            return teachers.stream()
//                    .map(t -> Mapper.mapTeacherToReadOnlyDTO(t).orElse(null))
//                    .collect(Collectors.toList());
        } catch (TeacherDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
