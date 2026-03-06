package bataille.task;

public class ToDo extends Task {

	/**
	 * Constructs a new ToDo task with the specified description.
	 *
	 * @param description The description of the to-do task. Should not be null or empty.
	 */
	public ToDo(String description) {
		super(description);
	}

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}
