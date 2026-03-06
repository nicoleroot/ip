package bataille.command;

import bataille.exception.BatailleException;
import bataille.storage.Storage;
import bataille.tasklist.TaskList;
import bataille.ui.Ui;

public class UnknownCommand extends Command {

	/**
	 * Executes the unknown command by throwing a BatailleException.
	 *
	 * @param tasks The current task list (not used, but required by Command interface).
	 * @param ui The user interface (not used directly).
	 * @param storage The storage handler (not used by this command).
	 * @throws BatailleException Always thrown with a message indicating unknown command.
	 */
	@Override
	public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws BatailleException {
		throw new BatailleException("I don't understand this ritual. Speak clearly.");
	}
}