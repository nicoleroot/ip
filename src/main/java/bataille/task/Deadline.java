package bataille.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

	private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
	private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

	private final LocalDateTime by;
	private final String rawBy;

	/**
	 * Constructs a new Deadline task with the specified description and due date.
	 * Attempts to parse the time string into a LocalDateTime object using INPUT_FORMAT.
	 *
	 * @param description The description of the deadline task. Should not be null or empty.
	 * @param by The due date/time of the deadline as a string.
	 */
	public Deadline(String description, String by) {
		super(description);
		LocalDateTime parsed = null;
		try {
			parsed = LocalDateTime.parse(by.trim(), INPUT_FORMAT);
		} catch (DateTimeParseException e) {
		}
		this.by = parsed;
		this.rawBy = by;
	}

	public String getBy() {
		return rawBy;
	}

	@Override
	public String toString() {
		String displayBy = (by != null) ? by.format(OUTPUT_FORMAT) : rawBy;
		return "[D]" + super.toString() + " (by: " + displayBy + ")";
	}
}
