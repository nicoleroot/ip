package bataille.command;

import bataille.exception.BatailleException;
import bataille.storage.Storage;
import bataille.tasklist.TaskList;
import bataille.ui.Ui;

public abstract class Command {

	/**
	 * Executes the command's specific operation.
	 * This method must be implemented by all concrete command classes.
	 *
	 * @param tasks The current task list that the command will operate on.
	 * @param ui The user interface for displaying messages to the user.
	 * @param storage The storage handler for persisting changes to disk.
	 * @throws BatailleException If an error occurs during command execution.
	 */
	public abstract void executeCommand(TaskList tasks, Ui ui, Storage storage) throws BatailleException;

	/**
	 * Determines whether this command should cause the application to exit.
	 *
	 * @return true if the application should terminate after this command,
	 *         false otherwise. Default implementation returns false.
	 */
	public boolean isExit() {
		return false;
	}
}