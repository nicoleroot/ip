package bataille.command;

import bataille.exception.BatailleException;
import bataille.storage.Storage;
import bataille.tasklist.TaskList;
import bataille.ui.Ui;

public class MarkCommand extends Command {
	private final int index;
	private final boolean isDone;

	public MarkCommand(int index, boolean isDone) {
		this.index = index;
		this.isDone = isDone;
	}

	/**
	 * Executes the mark command by changing a task's completion status.
	 *
	 * @param tasks The current task list containing the task to modify.
	 * @param ui The user interface.
	 * @param storage The storage handler for saving the updated task list.
	 * @throws BatailleException If the index is invalid or if there's an error during storage operations.
	 */
	@Override
	public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws BatailleException {
		tasks.markTask(index, isDone);
		ui.showTaskMarked(tasks.get(index), isDone);
		storage.saveData(tasks.getAll());
	}
}
