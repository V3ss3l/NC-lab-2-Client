
import controller.Controller;
import view.*;

import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {
        //View view = new ConsoleView();
        View view = new JavaFXView();
        Controller controller = new Controller(view);
        view.setController(controller);
        new Thread(controller).start();
        view.run();
    }
}
