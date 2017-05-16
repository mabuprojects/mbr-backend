package es.mabu.mr.restaurant.timetable;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class TimeTableUtil {

	public static TimeTable getTimeTable() {

		List<Day> days = new ArrayList<Day>();
		Integer openMorningTime = 7 * 60;
		Integer lastMorningOpenTime = 14 * 60;
		Integer openDinnerTime = 16 * 60;
		Integer lastDinnerOpenTime = 23 * 60;
		days.add(new Day(DayOfWeek.MONDAY, true, openMorningTime, lastMorningOpenTime, true, openDinnerTime,
				lastDinnerOpenTime));
		days.add(new Day(DayOfWeek.TUESDAY, true, openMorningTime, lastMorningOpenTime, true, openDinnerTime,
				lastDinnerOpenTime));
		days.add(new Day(DayOfWeek.WEDNESDAY, true, openMorningTime, lastMorningOpenTime, true, openDinnerTime,
				lastDinnerOpenTime));
		days.add(new Day(DayOfWeek.THURSDAY, true, openMorningTime, lastMorningOpenTime, true, openDinnerTime,
				lastDinnerOpenTime));
		days.add(new Day(DayOfWeek.FRIDAY, true, openMorningTime, lastMorningOpenTime, true, openDinnerTime,
				lastDinnerOpenTime));
		days.add(new Day(DayOfWeek.SATURDAY, true, openMorningTime, lastMorningOpenTime, true, openDinnerTime,
				lastDinnerOpenTime));
		days.add(new Day(DayOfWeek.SUNDAY, true, openMorningTime, lastMorningOpenTime, true, openDinnerTime,
				lastDinnerOpenTime));

		TimeTable timeTable = new TimeTable(days);

		return timeTable;

	}
}
