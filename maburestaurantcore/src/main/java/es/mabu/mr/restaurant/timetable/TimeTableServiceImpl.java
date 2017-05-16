package es.mabu.mr.restaurant.timetable;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeTableServiceImpl implements TimeTableService {

	@Autowired
	TimeTableRepository timeTableRepository;

	@Autowired
	DayRepository dayRepository;

	@Override
	public TimeTable save(TimeTable timeTable) throws TimeTableErrorException {
		List<Day> days = timeTable.getDays();
		if (days.size() != 7) {
			throw new TimeTableErrorException("week of " + days.size() + " days");
		}
		if (areDuplicates(days)) {
			throw new TimeTableErrorException("there are weeks of the day repeated");
		}

		timeTableRepository.save(timeTable);
		timeTable.getDays().stream().forEach(e -> dayRepository.save(e));
		return timeTable;

	}

	public boolean areDuplicates(List<Day> listContainingDuplicates) {
		final Set<DayOfWeek> set1 = new HashSet<>();

		for (Day day : listContainingDuplicates) {
			if (!set1.add(day.getDay())) {
				return true;
			}
		}
		return false;
	}

}
