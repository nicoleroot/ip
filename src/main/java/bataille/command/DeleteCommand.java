package bataille.command;

import bataille.exception.BatailleException;
import bataille.storage.Storage;
import bataille.task.Task;
import bataille.tasklist.TaskList;
import bataille.ui.Ui;

// ─────────────────────────────────────────────
// DeleteCommand
// ─────────────────────────────────────────────
public class DeleteCommand extends Command {
	private final int index;

	public DeleteCommand(int index) {
		this.index = index;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws BatailleException {
		Task removed = tasks.remove(index);
		ui.showTaskDeleted(removed, tasks.size());
		storage.saveData(tasks.getAll());
	}
}
