package bataille.command;

import bataille.exception.BatailleException;
import bataille.storage.Storage;
import bataille.tasklist.TaskList;
import bataille.ui.Ui;

// ─────────────────────────────────────────────
// UnknownCommand
// ─────────────────────────────────────────────
public class UnknownCommand extends Command {
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws BatailleException {
		throw new BatailleException("I don't understand this ritual. Speak clearly.");
	}
}