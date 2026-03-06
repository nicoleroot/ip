package bataille.command;

import bataille.storage.Storage;
import bataille.task.Event;
import bataille.task.Task;
import bataille.tasklist.TaskList;
import bataille.ui.Ui;

/**
 * Creates an Event object and adds it to the task list
 */
public class AddEventCommand extends Command {
	private final String description;
	private final String from;
	private final String to;

	public AddEventCommand(String description, String from, String to) {
		this.description = description;
		this.from = from;
		this.to = to;
	}

	@Override
	public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
		Task task = new Event(description, from, to);
		tasks.add(task);
		ui.showTaskAdded(task, tasks.size());
		storage.saveData(tasks.getAll());
	}
}
