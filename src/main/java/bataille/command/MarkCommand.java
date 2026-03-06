package bataille.command;

import bataille.exception.BatailleException;
import bataille.storage.Storage;
import bataille.tasklist.TaskList;
import bataille.ui.Ui;

// ─────────────────────────────────────────────
// MarkCommand  (profane / restore)
// ─────────────────────────────────────────────
public class MarkCommand extends Command {
	private final int index;
	private final boolean isDone;

	public MarkCommand(int index, boolean isDone) {
		this.index = index;
		this.isDone = isDone;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws BatailleException {
		tasks.markTask(index, isDone);
		ui.showTaskMarked(tasks.get(index), isDone);
		storage.saveData(tasks.getAll());
	}
}
