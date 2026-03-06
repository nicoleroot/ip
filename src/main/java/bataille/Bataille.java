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

	/**
	 * Constructs a new Bataille application instance.
	 * Initializes the UI, storage, and attempts to load existing tasks.
	 * If loading fails, starts with an empty task list.
	 *
	 * @param filePath The path to the data file where tasks are stored.
	 *                 Example: "data/bataille.txt"
	 */
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

	/**
	 * Runs the main application loop.
	 * This method:
	 * 1. Displays the welcome message
	 * 2. Enters a command processing loop until exit command is received
	 * 3. Reads user input, parses it into commands, and executes them
	 * 4. Handles any exceptions that occur during command execution
	 * 5. Ensures proper cleanup when the application exits
	 */
	public void run() {
		ui.printWelcome();
		boolean isExit = false;
		try {
			while (!isExit) {
				try {
					String fullCommand = ui.readCommand();
					ui.printLine();
					Command c = Parser.parse(fullCommand);
					c.executeCommand(tasks, ui, storage);
					isExit = c.isExit();
				} catch (BatailleException e) {
					ui.printError(e.getMessage());
				} finally {
					ui.printLine();
				}
			}
		} finally {
			ui.close();
		}
	}

	/**
	 * The entry point of the Bataille application.
	 * Creates a new Bataille instance with the default data file path.
	 *
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		new Bataille("data/bataille.txt").run();
	}
}
