package bataille.command;

import bataille.storage.Storage;
import bataille.tasklist.TaskList;
import bataille.ui.Ui;

public class ListCommand extends Command {
	@Override
	public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
		ui.showTaskList(tasks.getAll());
	}
}
