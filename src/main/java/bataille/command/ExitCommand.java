package bataille.command;

import bataille.storage.Storage;
import bataille.tasklist.TaskList;
import bataille.ui.Ui;

/**
 * Exit the app by setting isExit to 1
 */
public class ExitCommand extends Command {
	@Override
	public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
		ui.printGoodbye();
	}

	@Override
	public boolean isExit() {
		return true;
	}
}
