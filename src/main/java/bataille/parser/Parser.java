package bataille.parser;

import bataille.command.AddDeadlineCommand;
import bataille.command.AddEventCommand;
import bataille.command.AddToDoCommand;
import bataille.command.Command;
import bataille.command.DeleteCommand;
import bataille.command.ExitCommand;
import bataille.command.ListCommand;
import bataille.command.MarkCommand;
import bataille.command.UnknownCommand;
import bataille.exception.BatailleException;

public class Parser {

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
		} else {
			return new UnknownCommand();
		}
	}

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
		String from = (fromContentStart <= toIndex)
				? input.substring(fromContentStart, toIndex).trim()
				: "";

		int toContentStart = toIndex + toLength;
		String to = (toContentStart <= input.length())
				? input.substring(toContentStart).trim()
				: "";

		if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
			throw new BatailleException(
					"The event, its beginning, and its end must all be spoken.\n" +
					"Usage: event <description> /from <start> /to <end>"
			);
		}
		return new AddEventCommand(description, from, to);
	}

	private static Command parseMark(String input, int prefixLen, boolean isDone)
			throws BatailleException {
		if (input.length() <= prefixLen) {
			throw new BatailleException(isDone
					? "You must specify which taboo to profane.\n" +
					"Usage: profane <number>"
					: "You must specify which taboo to restore.\n" +
					"Usage: restore <number>"
			);
		}
		try {
			int index = Integer.parseInt(input.substring(prefixLen).trim());
			return new MarkCommand(index, isDone);
		} catch (NumberFormatException e) {
			throw new BatailleException(isDone
					? "Invalid number format for profane command.\n" +
					"Usage: profane <number>"
					: "Invalid number format for restore command.\n" +
					"Usage: restore <number>"
			);
		}
	}

	private static Command parseDelete(String input) throws BatailleException {
		int deletionLength = 7;
		try {
			int index = Integer.parseInt(input.substring(deletionLength).trim());
			return new DeleteCommand(index);
		} catch (NumberFormatException | StringIndexOutOfBoundsException e) {
			throw new BatailleException("Identify the taboo by its number to delete it.");
		}
	}
}