package bataille.task;

public class Task {

	protected String description;
	protected boolean isDone;

	/**
	 * Constructs a new Task with the specified description.
	 * New tasks are initially marked as not done.
	 *
	 * @param description The description of the task. Should not be null or empty.
	 */
	public Task(String description) {
		this.description = description;
		this.isDone = false;
	}

	/**
	 * Returns the description of the task.
	 *
	 * @return The task description string.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Returns the completion status of the task.
	 *
	 * @return true if the task is marked as done, false otherwise.
	 */
	public boolean isDone() {
		return this.isDone;
	}

	/**
	 * Returns a status icon representing the task's completion state.
	 *
	 * @return "X" if the task is done, otherwise " ".
	 */
	public String getStatusIcon() {
		return (isDone ? "X" : " ");
	}

	/**
	 * Marks the task as done.
	 */
	public void markAsDone() {
		this.isDone = true;
	}

	/**
	 * Marks the task as not done.
	 */
	public void markAsUndone() {
		this.isDone = false;
	}

	/**
	 * Returns a string representation of the task for display to the user.
	 *
	 * @return A formatted string representing the task.
	 */
	@Override
	public String toString() {
		return "[" + getStatusIcon() + "] " + description;
	}
}