package ru.nsu.ccfit.doronin.minesweeper.main.textUI.Events;

public interface Observer {
    public void update(String command);
    public void update(String command, String arg);
}
