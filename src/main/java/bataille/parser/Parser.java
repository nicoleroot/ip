package bataille.parser;

import bataille.command.AddDeadlineCommand;
import bataille.command.AddEventCommand;
import bataille.command.AddToDoCommand;
import bataille.command.Command;
import bataille.command.DeleteCommand;
import bataille.command.ExitCommand;
import bataille.command.FindCommand;
import bataille.command.ListCommand;
import bataille.command.MarkCommand;
import bataille.command.UnknownCommand;
import bataille.exception.BatailleException;

public class Parser {

	/**
	 * Parses the user's input string and returns the appropriate Command object.
	*
	 * @param input The raw user input string to be parsed.
	 * @return A Command object corresponding to the user's input.
	 * @throws BatailleException If the input is invalid or cannot be parsed properly.
	 */
	public static Command parse(String input) throws BatailleException {
		String lower = input.toLowerCase();

		if (lower.equals("bye")) {
			return new ExitCommand();
		} else if (lower.equals("list")) {
			return new ListCommand();
		} else if (lower.startsWith("todo")) {
			return parseToDo(input);
		} else if (lower.startsWith("deadline ")) {
			return parseDeadline(input);
		} else if (lower.startsWith("event ")) {
			return parseEvent(input);
		} else if (lower.startsWith("profane ")) {
			return parseMark(input, 8, true);
		} else if (lower.startsWith("restore ")) {
			return parseMark(input, 8, false);
		} else if (lower.startsWith("delete ")) {
			return parseDelete(input);
		} else if (lower.startsWith("find")) {
			return parseFind(input);
		} else {
			return new UnknownCommand();
		}
	}

	/**
	 * Parses a "todo" command and creates an AddToDoCommand.
	 *
	 * @param input The raw user input starting with "todo".
	 * @return An AddToDoCommand containing the parsed description.
	 * @throws BatailleException If the description is missing or empty.
	 */
	private static Command parseToDo(String input) throws BatailleException {
		int todoLength = 5;
		if (input.length() <= todoLength) {
			throw new BatailleException(
					"Taboo cannot be empty. Speak the unspeakable.\n" +
					"Usage: todo <description>"
			);
		}
		String description = input.substring(todoLength).trim();
		if (description.isEmpty()) {
			throw new BatailleException(
					"Taboo cannot be empty. Speak the unspeakable.\n" +
					"Usage: todo <description>"
			);
		}
		return new AddToDoCommand(description);
	}

	/**
	 * Parses a "deadline" command and creates an AddDeadlineCommand.
	 * Validates the presence of "/by".
	 *
	 * @param input The raw user input starting with "deadline".
	 * @return An AddDeadlineCommand containing the parsed description and due date.
	 * @throws BatailleException If the format is invalid or required fields are missing.
	 */
	private static Command parseDeadline(String input) throws BatailleException {
		int deadlineLength = 9;
		int byLength = 4;
		if (!input.contains("/by")) {
			throw new BatailleException(
					"A deadline requires both a taboo and a moment of reckoning.\n" +
					"Usage: deadline <description> /by <time>"
			);
		}
		int byIndex = input.indexOf("/by");
		if (input.length() <= deadlineLength) {
			throw new BatailleException(
					"The description cannot be empty.\n" +
					"Usage: deadline <description> /by <time>"
			);
		}
		String description = input.substring(deadlineLength, byIndex).trim();
		String by = input.substring(byIndex + byLength).trim();

		if (description.isEmpty() || by.isEmpty()) {
			throw new BatailleException(
					"Both the taboo and its deadline must be spoken.\n" +
					"Usage: deadline <description> /by <time>"
			);
		}
		return new AddDeadlineCommand(description, by);
	}

	/**
	 * Parses an "event" command and creates an AddEventCommand.
	 * Validates the presence of both "/from" and "/to".
	 *
	 * @param input The raw user input starting with "event".
	 * @return An AddEventCommand containing the parsed description, start time, and end time.
	 * @throws BatailleException If the format is invalid or required fields are missing.
	 */
	private static Command parseEvent(String input) throws BatailleException {
		int eventLength = 6;
		int fromLength = 6;
		int toLength = 4;
		if (!input.contains("/from") || !input.contains("/to")) {
			throw new BatailleException(
					"An event requires a taboo, a beginning, and an end.\n" +
					"Usage: event <description> /from <start> /to <end>"
			);
		}
		int fromIndex = input.indexOf("/from");
		int toIndex = input.indexOf("/to");

		if (toIndex <= fromIndex) {
			throw new BatailleException(
					"An event requires a taboo, a beginning, and an end.\n" +
					"Usage: event <description> /from <start> /to <end>"
			);
		}

		String description = input.substring(eventLength, fromIndex).trim();

		int fromContentStart = fromIndex + fromLength;
		String from = (fromContentStart <= toIndex) ? input.substring(fromContentStart, toIndex).trim() : "";

		int toContentStart = toIndex + toLength;
		String to = (toContentStart <= input.length()) ? input.substring(toContentStart).trim() : "";

		if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
			throw new BatailleException(
					"The event, its beginning, and its end must all be spoken.\n" +
					"Usage: event <description> /from <start> /to <end>"
			);
		}
		return new AddEventCommand(description, from, to);
	}

	/**
	 * Parses "profane" and "restore" commands and creates a MarkCommand.
	 * Extracts the task index and determines whether to mark as done or not done.
	 *
	 * @param input The raw user input starting with "profane" or "restore".
	 * @param prefixLen The length of the command prefix.
	 * @param isDone true for "profane" (mark as done), false for "restore" (mark as not done).
	 * @return A MarkCommand containing the task index and completion status.
	 * @throws BatailleException If the index is missing or not a valid number.
	 */
	private static Command parseMark(String input, int prefixLen, boolean isDone)
			throws BatailleException {
		if (input.length() <= prefixLen) {
			throw new BatailleException(isDone ? "You must specify which taboo to profane.\n" + "Usage: profane <number>"
					: "You must specify which taboo to restore.\n" + "Usage: restore <number>"
			);
		}
		try {
			int index = Integer.parseInt(input.substring(prefixLen).trim());
			return new MarkCommand(index, isDone);
		} catch (NumberFormatException e) {
			throw new BatailleException(isDone ? "Invalid number format for profane command.\n" + "Usage: profane <number>"
					: "Invalid number format for restore command.\n" + "Usage: restore <number>"
			);
		}
	}

	/**
	 * Parses a "delete" command and creates a DeleteCommand.
	 * Extracts the task index of the item to be deleted.
	 *
	 * @param input The raw user input starting with "delete".
	 * @return A DeleteCommand containing the task index to delete.
	 * @throws BatailleException If the index is missing, invalid, or out of bounds.
	 */
	private static Command parseDelete(String input) throws BatailleException {
		int deletionLength = 7;
		try {
			int index = Integer.parseInt(input.substring(deletionLength).trim());
			return new DeleteCommand(index);
		} catch (NumberFormatException | StringIndexOutOfBoundsException e) {
			throw new BatailleException("Identify the taboo by its number to delete it.");
		}
	}

	/**
	 * Parses a "find" command and creates a FindCommand.
	 * Extracts the keyword to search for in task descriptions.
	 *
	 * @param input The raw user input starting with "find".
	 * @return A FindCommand containing the keyword to search for.
	 * @throws BatailleException If the keyword is missing or empty.
	 */
	private static Command parseFind(String input) throws BatailleException {
		int findLength = 4;
		if (input.length() <= findLength) {
			throw new BatailleException(
					"You must specify a taboo to search for.\n" +
					"Usage: find <keyword>"
			);
		}
		String keyword = input.substring(findLength).trim();
		if (keyword.isEmpty()) {
			throw new BatailleException(
					"You must specify a taboo to search for.\n" +
					"Usage: find <keyword>"
			);
		}
		return new FindCommand(keyword);
	}
}