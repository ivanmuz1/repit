package com.example.repit.repositories;

import com.example.repit.entities.Student;
import com.example.repit.entities.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorRepository  extends JpaRepository<Tutor, Integer> {

    @Query(value = "select round(avg(lesson.marklesson),1) as medianMark from lesson\n" +
            "join advertisement a on a.id_advertisement = lesson.id_advertisement\n" +
            "join tutor t on a.tutor_id_tutor = t.id_tutor\n" +
            "where t.id_tutor = ?", nativeQuery = true)
    Double MedianMark(int id);

    @Query(value = "select s.fname, s.yearsold, a.subject, date(l.startlesson) as date, l.marklesson, l.comment from tutor\n" +
            "             join advertisement a on tutor.id_tutor = a.tutor_id_tutor\n" +
            "            join lesson l on a.id_advertisement = l.id_advertisement\n" +
            "            join student s on s.id_student = l.id_student\n" +
            "            where l.marklesson is not null and tutor.id_tutor = ?", nativeQuery = true)
    List<Object[]> CompletedLessonForTutor(int id);


    @Query(value = "select idlesson, s.fname, s.mobile, s.yearsold, s.city, a.subject from lesson\n" +
            "    join advertisement a on a.id_advertisement = lesson.id_advertisement\n" +
            "    join student s on s.id_student = lesson.id_student\n" +
            "    where lesson.accept='no'", nativeQuery = true)
    List<Object[]> Applications();

}
