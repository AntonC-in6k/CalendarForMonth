package monthcalendar;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by employee on 7/18/16.
 */
public class CommandHandler {

    public ButtonCommands handle(String command) throws IOException {
        return useCommand(buttonPress());
    }

    protected String buttonPress() {
        String result;
        Scanner scanner = new Scanner(System.in);
        result = scanner.next();
        scanner.close();
        return result;
    }

    private ButtonCommands useCommand(String command) throws IOException {
        switch (command) {
            case "d":
                return ButtonCommands.MOVE_RIGHT;

            case "a":
                return ButtonCommands.MOVE_LEFT;

            case "w":
                return  ButtonCommands.MOVE_UP;

            case "s":
                return  ButtonCommands.MOVE_DOWN;

            case "e":
                return ButtonCommands.EXIT;

            default:
                return ButtonCommands.WRONG_COMMAND;
        }
    }


}
