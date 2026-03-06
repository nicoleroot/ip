package bataille.command;

import bataille.storage.Storage;
import bataille.task.Task;
import bataille.task.ToDo;
import bataille.tasklist.TaskList;
import bataille.ui.Ui;

public class AddToDoCommand extends Command {
	private final String description;

	public AddToDoCommand(String description) {
		this.description = description;
	}

	@Override
	public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
		Task task = new ToDo(description);
		tasks.add(task);
		ui.showTaskAdded(task, tasks.size());
		storage.saveData(tasks.getAll());
	}
}
