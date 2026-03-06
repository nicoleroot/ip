package bataille.command;

import bataille.storage.Storage;
import bataille.tasklist.TaskList;
import bataille.ui.Ui;

// ─────────────────────────────────────────────
// ExitCommand
// ─────────────────────────────────────────────
public class ExitCommand extends Command {
	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.printGoodbye();
	}

	@Override
	public boolean isExit() {
		return true;
	}
}
