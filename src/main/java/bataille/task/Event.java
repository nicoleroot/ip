package bataille.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

	private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
	private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

	private final LocalDateTime from;
	private final LocalDateTime to;
	private final String rawFrom;
	private final String rawTo;

	/**
	 * Constructs a new Event task with the specified description, start time, and end time.
	 * Attempts to parse the time strings into LocalDateTime objects using INPUT_FORMAT.
	 *
	 * @param description The description of the event. Should not be null or empty.
	 * @param from The start time of the event as a string.
	 * @param to The end time of the event as a string.
	 */
	public Event(String description, String from, String to) {
		super(description);

		LocalDateTime parsedFrom = null;
		LocalDateTime parsedTo = null;
		try {
			parsedFrom = LocalDateTime.parse(from.trim(), INPUT_FORMAT);
		} catch (DateTimeParseException e) {
		}
		try {
			parsedTo = LocalDateTime.parse(to.trim(), INPUT_FORMAT);
		} catch (DateTimeParseException e) {
		}

		this.from = parsedFrom;
		this.to = parsedTo;
		this.rawFrom = from;
		this.rawTo = to;
	}

	public String getFrom() {
		return rawFrom;
	}

	public String getTo() {
		return rawTo;
	}

	@Override
	public String toString() {
		String displayFrom = (from != null) ? from.format(OUTPUT_FORMAT) : rawFrom;
		String displayTo = (to != null) ? to.format(OUTPUT_FORMAT) : rawTo;
		return "[E]" + super.toString() + " (from: " + displayFrom + " to: " + displayTo + ")";
	}}
