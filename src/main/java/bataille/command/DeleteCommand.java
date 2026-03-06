package bataille.command;

import bataille.exception.BatailleException;
import bataille.storage.Storage;
import bataille.task.Task;
import bataille.tasklist.TaskList;
import bataille.ui.Ui;

public class DeleteCommand extends Command {
	private final int index;

	public DeleteCommand(int index) {
		this.index = index;
	}

	/**
	 * Executes the delete command by removing the specified task from the task list.
	 *
	 * @param tasks The current task list from which the task will be removed.
	 * @param ui The user interface for displaying the deletion confirmation.
	 * @param storage The storage handler for saving the updated task list.
	 * @throws BatailleException If the index is invalid or if there's an error during storage operations.
	 */
	@Override
	public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws BatailleException {
		Task removed = tasks.remove(index);
		ui.showTaskDeleted(removed, tasks.size());
		storage.saveData(tasks.getAll());
	}
}
