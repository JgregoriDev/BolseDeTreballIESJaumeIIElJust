package com.lpc.bolsedetreballiesjaumeiieljust.Entitat;

import java.util.ArrayList;

/**
 * Created by lpc on 4/05/17.
 */

public class ArrayOfertesTreballs {
    private ArrayList <OfertesTreball>ofertesTreballs;

    public ArrayOfertesTreballs() {
        this.ofertesTreballs = new ArrayList<>();
    }
    public ArrayOfertesTreballs(ArrayList<OfertesTreball> ofertesTreballs) {
        this.ofertesTreballs = ofertesTreballs;
    }

    public ArrayList<OfertesTreball> getOfertesTreballs() {
        return ofertesTreballs;
    }

    public void setOfertesTreballs(ArrayList<OfertesTreball> ofertesTreballs) {
        this.ofertesTreballs = ofertesTreballs;
    }
}
