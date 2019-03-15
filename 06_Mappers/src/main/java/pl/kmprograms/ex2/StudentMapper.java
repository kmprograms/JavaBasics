package pl.kmprograms.ex2;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface StudentMapper {

    @Mappings({
            @Mapping(source = "id", target = "studentId", defaultExpression = "java(new java.util.Random().nextLong())"),
            @Mapping(source = "name", target = "studentName"),
            @Mapping(source = "age", target = "studentAge")
    })
    StudentDto studentToStudentDto(Student student);

    @Mappings({
            @Mapping(source = "studentId", target = "id", defaultExpression = "java(new java.util.Random().nextLong())"),
            @Mapping(source = "studentName", target = "name"),
            @Mapping(source = "studentAge", target = "age")
    })
    Student studentDtoToStudent(StudentDto studentDto);
}
