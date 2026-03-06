package bataille.tasklist;

import java.util.ArrayList;
import java.util.List;

import bataille.exception.BatailleException;
import bataille.task.Task;

public class TaskList {

	private final ArrayList<Task> tasks;

	public TaskList() {
		this.tasks = new ArrayList<>();
	}

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

	public void markTask(int index, boolean isDone) throws BatailleException {
		Task task = get(index);
		if (isDone) {
			task.markAsDone();
		} else {
			task.markAsUndone();
		}
	}

	private void validateIndex(int index) throws BatailleException {
		if (index < 1 || index > tasks.size()) {
			throw new BatailleException(
					"No such taboo exists. The number must be between 1 and " + tasks.size() + "."
			);
		}
	}
}