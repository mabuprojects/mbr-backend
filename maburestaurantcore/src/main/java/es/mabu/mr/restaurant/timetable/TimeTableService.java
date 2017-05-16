package es.mabu.mr.restaurant.timetable;

public interface TimeTableService {

	TimeTable save(TimeTable timeTable) throws TimeTableErrorException;

}
