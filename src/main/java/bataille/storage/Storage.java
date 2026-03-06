package bataille.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import bataille.exception.BatailleException;
import bataille.task.Deadline;
import bataille.task.Event;
import bataille.task.Task;
import bataille.task.ToDo;

public class Storage {

	private final Path filePath;
	private final Path dirPath;

	public Storage(String filePathStr) {
		this.filePath = Paths.get(filePathStr);
		this.dirPath = this.filePath.getParent();
	}

	public List<Task> loadData() throws BatailleException {
		List<Task> tasks = new ArrayList<>();

		if (Files.notExists(filePath)) {
			return tasks;
		}

		try {
			List<String> lines = Files.readAllLines(filePath);
			for (String line : lines) {
				try {
					tasks.add(parseLineToTask(line));
				} catch (Exception e) {
					System.err.println(" A corrupted taboo was found in the ledger. It has been purged.");
				}
			}
		} catch (IOException e) {
			throw new BatailleException("The void resisted reading. The sacred ledger is inaccessible.");
		}

		return tasks;
	}

	public void saveData(List<Task> tasks) {
		try {
			if (dirPath != null && Files.notExists(dirPath)) {
				Files.createDirectories(dirPath);
			}
			List<String> lines = new ArrayList<>();
			for (Task t : tasks) {
				lines.add(formatTaskForFile(t));
			}
			Files.write(filePath, lines);
		} catch (IOException e) {
			System.err.println("!! Failed to inscribe truths to the stone. Check disk permissions.");
		}
	}

	private Task parseLineToTask(String line) throws BatailleException {
		String[] parts = line.split(" \\| ");
		String type = parts[0];
		boolean isDone = parts[1].equals("1");
		String description = parts[2];

		Task task;
		switch (type) {
		case "T":
			task = new ToDo(description);
			break;
		case "D":
			task = new Deadline(description, parts[3]);
			break;
		case "E":
			task = new Event(description, parts[3], parts[4]);
			break;
		default:
			throw new BatailleException("Unknown ritual type.");
		}

		if (isDone) {
			task.markAsDone();
		}
		return task;
	}

	private String formatTaskForFile(Task t) {
		String type = (t instanceof ToDo) ? "T" : (t instanceof Deadline) ? "D" : "E";
		String status = t.isDone() ? "1" : "0";
		String fileInput = type + " | " + status + " | " + t.getDescription();

		if (t instanceof Deadline) {
			fileInput += " | " + ((Deadline) t).getBy();
		} else if (t instanceof Event) {
			fileInput += " | " + ((Event) t).getFrom() + " | " + ((Event) t).getTo();
		}
		return fileInput;
	}
}