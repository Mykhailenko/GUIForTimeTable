package time_table.objects;

public class DayComputerClass {
	private int numberLess;
	private String auditory;
	private int dayNumber;
	@SuppressWarnings("unused")
	private WeekDays dayName;

	public DayComputerClass() {
		numberLess = -1;
		auditory = new String("");
		dayNumber = -1;
		dayName = WeekDays.Unknown;
	}

	public int getNumberLess() {
		return numberLess;
	}

	public void setNumberLess(int numberLess) {
		this.numberLess = numberLess;
	}

	public String getAuditory() {
		return auditory;
	}

	public void setAuditory(String auditory) {
		this.auditory = auditory;
	}

	public int getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
		switch (dayNumber) {
		case 1:
			dayName = WeekDays.Monday;
			break;
		case 2:
			dayName = WeekDays.Tuesday;
			break;
		case 3:
			dayName = WeekDays.Wednesday;
			break;
		case 4:
			dayName = WeekDays.Thursday;
			break;
		case 5:
			dayName = WeekDays.Friday;
			break;
		case 6:
			dayName = WeekDays.Saturday;
			break;
		case 7:
			dayName = WeekDays.Sunday;
			break;
		}
	}

	public String getDayName() {
		switch (dayNumber) {
		case 1:
			return "ом";
		case 2:
			return "бр";
		case 3:
			return "яп";
		case 4:
			return "вр";
		case 5:
			return "ор";
		case 6:
			return "яа";
		case 7:
			return "бя";
		default:
			return "";
		}
	}
}
