package bataille;

import bataille.command.Command;
import bataille.exception.BatailleException;
import bataille.parser.Parser;
import bataille.storage.Storage;
import bataille.tasklist.TaskList;
import bataille.ui.Ui;

public class Bataille {

	private Storage storage;
	private TaskList tasks;
	private Ui ui;

	public Bataille(String filePath) {
		ui = new Ui();
		storage = new Storage(filePath);
		try {
			tasks = new TaskList(storage.loadData());
		} catch (BatailleException e) {
			ui.printLoadingError();
			tasks = new TaskList();
		}
	}

	public void run() {
		ui.printWelcome();
		boolean isExit = false;
		while (!isExit) {
			try {
				String fullCommand = ui.readCommand();
				ui.printLine();
				Command c = Parser.parse(fullCommand);
				c.execute(tasks, ui, storage);
				isExit = c.isExit();
			} catch (BatailleException e) {
				ui.printError(e.getMessage());
			} finally {
				ui.printLine();
			}
		}
	}

	public static void main(String[] args) {
		new Bataille("data/bataille.txt").run();
	}
}
