# Bataille — Task Manager

> *"To live is to consume without return."*

**Bataille** is a command-line task manager with a Georges Bataille-inspired aesthetic. It manages your todos, deadlines, and events — stored locally and persisted between sessions.



## Quick Start

1. Ensure you have **Java 17** or later installed.
2. Download the latest release
3. Run it:
   ```
   java -jar bataille.jar
   ```


## Features

### Add a Todo
A simple task with no time constraint.
```
todo <description>
```
**Example:**
```
todo read Erotism by Bataille
```
```
____________________________________________________________
 Got it. I've added this taboo:
   [T][ ] read Erotism by Bataille
 Now you have 1 taboos in your ledger.
____________________________________________________________
```



### Add a Deadline
A task with a due date/time.
```
deadline <description> /by <date>
```
- Date format: `yyyy-MM-dd HHmm` (e.g. `2019-12-02 1800`)
- Plain text also accepted (e.g. `Sunday midnight`)

**Example:**
```
deadline submit essay /by 2025-06-15 2359
```
```
____________________________________________________________
 Got it. I've added this taboo:
   [D][ ] submit essay (by: Jun 15 2025, 11:59pm)
 Now you have 2 taboos in your ledger.
____________________________________________________________
```



### Add an Event
A task with a start and end time.
```
event <description> /from <start> /to <end>
```
- Date format: `yyyy-MM-dd HHmm` (e.g. `2025-06-01 0900`)
- Plain text also accepted

**Example:**
```
event conference /from 2025-06-01 0900 /to 2025-06-01 1700
```
```
____________________________________________________________
 Got it. I've added this taboo:
   [E][ ] conference (from: Jun 01 2025, 9:00am to: Jun 01 2025, 5:00pm)
 Now you have 3 taboos in your ledger.
____________________________________________________________
```



### List All Tasks
```
list
```
```
____________________________________________________________
 Sacred Taboos in your ledger:
 1.[T][ ] read Erotism by Bataille
 2.[D][ ] submit essay (by: Jun 15 2025, 11:59pm)
 3.[E][ ] conference (from: Jun 01 2025, 9:00am to: Jun 01 2025, 5:00pm)
____________________________________________________________
```



### Mark a Task as Done
```
profane <number>
```
**Example:** `profane 1`
```
____________________________________________________________
 The limit has been crossed! This truth is now profaned:
   [T][X] read Erotism by Bataille
 Another ritual completed. Another boundary crossed.
____________________________________________________________
```



### Unmark a Task
```
restore <number>
```
**Example:** `restore 1`



### Delete a Task
```
delete <number>
```
**Example:** `delete 2`
```
____________________________________________________________
 The void consumes it. Noted. I've removed this taboo:
   [D][ ] submit essay (by: Jun 15 2025, 11:59pm)
 Now you have 2 taboos in the list.
____________________________________________________________
```



### Find Tasks by Keyword
Search across all task descriptions (case-insensitive).
```
find <keyword>
```
**Example:** `find essay`
```
____________________________________________________________
 Here are the matching taboos in your ledger:
 1.[D][ ] submit essay (by: Jun 15 2025, 11:59pm)
____________________________________________________________
```



### Exit
```
bye
```



## Data Storage

Tasks are automatically saved to `data/bataille.txt` after every change. They are loaded automatically on startup. You do not need to save manually.



## Command Summary

| Command | Format |
|---------|--------|
| Add todo | `todo <description>` |
| Add deadline | `deadline <description> /by <date>` |
| Add event | `event <description> /from <start> /to <end>` |
| List | `list` |
| Find | `find <keyword>` |
| Mark done | `profane <number>` |
| Unmark | `restore <number>` |
| Delete | `delete <number>` |
| Exit | `bye` |