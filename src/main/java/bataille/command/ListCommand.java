package bataille.command;

import bataille.storage.Storage;
import bataille.tasklist.TaskList;
import bataille.ui.Ui;

public class ListCommand extends Command {

	/**
	 * Executes the list command by displaying all tasks to the user.
	 * @param tasks The current task list containing all tasks to be displayed.
	 * @param ui The user interface responsible for formatting and displaying the task list to the user.
	 * @param storage The storage handler
	 */
	@Override
	public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
		ui.showTaskList(tasks.getAll());
	}
}
