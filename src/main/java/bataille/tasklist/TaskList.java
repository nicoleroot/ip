package bataille.tasklist;

import java.util.ArrayList;
import java.util.List;

import bataille.exception.BatailleException;
import bataille.task.Task;

public class TaskList {

	private final ArrayList<Task> tasks;

	/**
	 * Constructs an empty TaskList.
	 * Creates a new ArrayList with no initial tasks.
	 */
	public TaskList() {
		this.tasks = new ArrayList<>();
	}

	/**
	 * Constructs a TaskList initialized with an existing list of tasks.
	 * Creates a new ArrayList containing all tasks from the provided list.
	 *
	 * @param initialTasks A list of tasks to initialize the TaskList with.
	 */
	public TaskList(List<Task> initialTasks) {
		this.tasks = new ArrayList<>(initialTasks);
	}

	public void add(Task task) {
		tasks.add(task);
	}

	public Task remove(int index) throws BatailleException {
		validateIndex(index);
		return tasks.remove(index - 1);
	}

	public Task get(int index) throws BatailleException {
		validateIndex(index);
		return tasks.get(index - 1);
	}

	public int size() {
		return tasks.size();
	}

	public boolean isEmpty() {
		return tasks.isEmpty();
	}

	public List<Task> getAll() {
		return tasks;
	}

	/**
	 * Marks or unmarks a task at the specified index as done.
	 * Uses 1-based indexing.
	 *
	 * @param index The position of the task to modify.
	 * @param isDone true to mark as done, false to mark as not done.
	 * @throws BatailleException If the index is out of bounds.
	 */
	public void markTask(int index, boolean isDone) throws BatailleException {
		Task task = get(index);
		if (isDone) {
			task.markAsDone();
		} else {
			task.markAsUndone();
		}
	}

	/**
	 * Validates that the given index is within the valid range.
	 *
	 * @param index The index to validate.
	 * @throws BatailleException If the index is out of bounds.
	 */
	private void validateIndex(int index) throws BatailleException {
		if (index < 1 || index > tasks.size()) {
			throw new BatailleException(
					"No such taboo exists. The number must be between 1 and " + tasks.size() + "."
			);
		}
	}
}