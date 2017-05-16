package es.mabu.mr.restaurant.timetable;

import java.time.DayOfWeek;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Day {

	@Id
	@GenericGenerator(name = "day_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence", value = "day_id_seq")

	})
	@GeneratedValue(generator = "day_sequence")
	private Long id;

	@Enumerated(EnumType.ORDINAL)
	DayOfWeek day;
	boolean openMorning;

	Integer openingMorning;
	Integer lastHourToMorningBook;

	boolean openDinner;

	Integer openingDinner;
	Integer lastHourToDinnerBook;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "time_table_id")
	private TimeTable timeTable;

	public Day() {

	}

	public Day(DayOfWeek day, boolean openMorning, Integer openingMorning, Integer lastHourToMorningBook,
			boolean openDinner, Integer openingDinner, Integer lastHourToDinnerBook) {
		this.day = day;
		this.openMorning = openMorning;
		this.openingMorning = openingMorning;
		this.lastHourToMorningBook = lastHourToMorningBook;
		this.openDinner = openDinner;
		this.openingDinner = openingDinner;
		this.lastHourToDinnerBook = lastHourToDinnerBook;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TimeTable getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(TimeTable timeTable) {
		this.timeTable = timeTable;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public boolean isOpenMorning() {
		return openMorning;
	}

	public void setOpenMorning(boolean openMorning) {
		this.openMorning = openMorning;
	}

	public Integer getOpeningMorning() {
		return openingMorning;
	}

	public void setOpeningMorning(Integer openingMorning) {
		this.openingMorning = openingMorning;
	}

	public Integer getLastHourToMorningBook() {
		return lastHourToMorningBook;
	}

	public void setLastHourToMorningBook(Integer lastHourToMorningBook) {
		this.lastHourToMorningBook = lastHourToMorningBook;
	}

	public boolean isOpenDinner() {
		return openDinner;
	}

	public void setOpenDinner(boolean openDinner) {
		this.openDinner = openDinner;
	}

	public Integer getOpeningDinner() {
		return openingDinner;
	}

	public void setOpeningDinner(Integer openingDinner) {
		this.openingDinner = openingDinner;
	}

	public Integer getLastHourToDinnerBook() {
		return lastHourToDinnerBook;
	}

	public void setLastHourToDinnerBook(Integer lastHourToDinnerBook) {
		this.lastHourToDinnerBook = lastHourToDinnerBook;
	}

}
