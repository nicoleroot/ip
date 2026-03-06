package bataille.command;

import bataille.exception.BatailleException;
import bataille.storage.Storage;
import bataille.task.Task;
import bataille.tasklist.TaskList;
import bataille.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {

	private final String keyword;

	public FindCommand(String keyword) {
		this.keyword = keyword.toLowerCase();
	}

	/**
	 * Executes the find command by searching through all tasks and displaying matches.
	 *
	 * @param tasks The current task list to search through.
	 * @param ui The user interface for displaying the search results.
	 * @param storage The storage handler.
	 * @throws BatailleException This command does not throw exceptions under normal operation.
	 */
	@Override
	public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws BatailleException {
		List<Task> matches = new ArrayList<>();
		for (Task t : tasks.getAll()) {
			if (t.getDescription().toLowerCase().contains(keyword)) {
				matches.add(t);
			}
		}
		ui.showFound(matches);
	}
}