package start;

import presentation.Controller;
import presentation.View;

import java.util.logging.Logger;


/**
 * @Author: Ostafie Stanca
 * @Since: May 05, 2021
 */
public class Start {
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    /**
     * Functia Main a aplicatiei
     * @param args
     */

    public static void main(String[] args) {

        View view = new View();
        Controller controller = new Controller(view);
        view.setVisible(true);
    }

}
