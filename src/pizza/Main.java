package pizza;

import java.sql.SQLException;

public class Main {

    /* Here starts the execution of our program.
     * We create a CommandLine object and in order to interact
     * with the user first of all we initialize it (startSession),
     * then we activate it so as to be able to handle input from the user (execute)
     * and lastly, if everything goes fine and the user wants to exit
     * our application we terminate the command line session (endSession).
     */
	public static void main(String[] args) throws SQLException {
		CommandLine commandLine = new CommandLine();
		if (commandLine.startSession() && commandLine.execute()) {
			commandLine.endSession();
		}

	}

}
