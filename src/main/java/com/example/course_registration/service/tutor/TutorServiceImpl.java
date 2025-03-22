package com.example.course_registration.service.tutor;

import com.example.course_registration.common.CoreEntityServiceImpl;
import com.example.course_registration.model.entity.Course;
import com.example.course_registration.model.entity.Tutor;
import com.example.course_registration.repository.course.CourseRepository;
import com.example.course_registration.repository.tutor.TutorRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("tutorService")
public class TutorServiceImpl
        extends CoreEntityServiceImpl<Tutor>
        implements TutorService
{
    private final CourseRepository courseRepository;

    private final TutorRepository tutorRepository;

    public TutorServiceImpl(TutorRepository tutorRepository, CourseRepository courseRepository) {
        super(tutorRepository);
        this.tutorRepository = tutorRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void registerAvailability(Long tutorId, LocalDateTime availableTime, int duration) {
        Tutor tutor = findById(tutorId)
                .orElseThrow(() -> new IllegalArgumentException("해당 튜터가 존재하지 않습니다: " + tutorId));

        //30분 혹은 정각 검증증
        int minute = availableTime.getMinute();
        if (minute != 0 && minute != 30) {
            throw new IllegalArgumentException("수업은 정각 또는 30분에만 시작할 수 있습니다.");
        }

        //시간 겹침 검증
        LocalDateTime newStart = availableTime;
        LocalDateTime newEnd = availableTime.plusMinutes(duration);

        List<Course> existingCourses = courseRepository.findByTutorId(tutorId);

        boolean hasConflict = existingCourses.stream().anyMatch(course -> {
            LocalDateTime existingStart = course.getStartTime();
            LocalDateTime existingEnd = existingStart.plusMinutes(course.getDuration());
            return !(newEnd.isEqual(existingStart) || newEnd.isBefore(existingStart) || newStart.isEqual(existingEnd) || newStart.isAfter(existingEnd));
        });

        if (hasConflict) {
            throw new IllegalStateException("해당 시간대에 이미 등록된 수업이 존재합니다.");
        }
        Course availability = new Course();
        availability.setTutor(tutor);
        availability.setStartTime(availableTime);
        availability.setDuration(duration);

        courseRepository.save(availability);
    }


    @Override
    public void removeAvailability(Long tutorId, LocalDateTime availableTime) {
        findById(tutorId)
                .orElseThrow(() -> new IllegalArgumentException("해당 튜터가 존재하지 않습니다."));

        Course course = courseRepository.findByStartTime(availableTime).stream()
                .filter(c -> c.getTutor().getId().equals(tutorId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 시간의 수업이 존재하지 않습니다."));

        if (course.getStudent() != null) {
            throw new IllegalStateException("이미 수강 신청된 수업은 삭제할 수 없습니다.");
        }

        courseRepository.delete(course);
    }
}
