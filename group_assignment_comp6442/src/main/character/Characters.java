package main.character;

import main.character.Inventory;

/**
 * @author BoYang
 */
public abstract class Characters {
    protected String name;
    protected Inventory inventory;

    public Characters() {
    }

    public Characters(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
