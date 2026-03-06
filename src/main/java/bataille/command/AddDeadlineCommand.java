package bataille.command;

import bataille.storage.Storage;
import bataille.task.Deadline;
import bataille.task.Task;
import bataille.tasklist.TaskList;
import bataille.ui.Ui;

public class AddDeadlineCommand extends Command {
	private final String description;
	private final String by;

	public AddDeadlineCommand(String description, String by) {
		this.description = description;
		this.by = by;
	}

	@Override
	public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
		Task task = new Deadline(description, by);
		tasks.add(task);
		ui.showTaskAdded(task, tasks.size());
		storage.saveData(tasks.getAll());
	}
}
