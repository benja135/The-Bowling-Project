package mdl.dcll.types;

import mdl.dcll.types.Frame;

/**
 * Created by benja135 on 09/03/16.
 */
public class Strike extends Frame {

    public Strike() {}

    public int score() {
        return 10;
    }
    
    public boolean isValid() {
        return true;
    }

    public String toString() {
        return "[Strike!]";
    }
}

