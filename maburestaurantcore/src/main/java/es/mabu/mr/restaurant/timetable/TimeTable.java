package es.mabu.mr.restaurant.timetable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "time_table")
public class TimeTable {

	@Id
	@GenericGenerator(name = "time_table_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence", value = "time_table_id_seq")

	})
	@GeneratedValue(generator = "time_table_sequence")
	private Long id;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "timeTable")
	@OrderBy("day ASC")
	private List<Day> days;

	public TimeTable() {

	}

	public TimeTable(List<Day> days) {
		setDays(days);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		for (Day day : days) {
			day.setTimeTable(this);
		}
		this.days = days;
	}

}
