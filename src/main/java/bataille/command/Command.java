package bataille.command;

import bataille.exception.BatailleException;
import bataille.storage.Storage;
import bataille.tasklist.TaskList;
import bataille.ui.Ui;

public abstract class Command {

	public abstract void executeCommand(TaskList tasks, Ui ui, Storage storage) throws BatailleException;

	public boolean isExit() {
		return false;
	}
}