//package monthcalendar;
//
//import java.io.IOException;
//import java.util.Scanner;
//
///**
// * Created by employee on 7/18/16.
// */
//public class HandleCommands {
//    private ButtonCommands buttonCommands;
//
//
//    public HandleCommands() {
//    }
//
//    public String handleCommand() throws IOException {
//        buttonPress();
//        useCommand();
//        return generateOutput(monthPeriod.getMonths());
//    }
//
//    private ButtonCommands useCommand(String command) throws IOException {
//        switch (command) {
//            case "d":
//            this.monthPeriod = monthPeriod.next();
//            break;
//
//            case A:
//                this.monthPeriod = monthPeriod.previous();
//                break;
//
//            case W: //увеличить период
//                this.monthPeriod = monthPeriod.increase();
//
//            case S:
//                this.monthPeriod = monthPeriod.decrease();
//                break;
//
//            default:
//                break;
//        }
//    }
//
//    protected String buttonPress() {
//        String result;
//        Scanner scanner = new Scanner(System.in);
//        result = scanner.next();
//        scanner.close();
//        return result;
//    }
//}
