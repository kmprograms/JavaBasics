package pl.kmprograms;

import java.util.Objects;

public class Player {

    private String name;
    private int wins;

    public Player() {
    }

    public Player(String name, int wins) {
        this.name = name;
        this.wins = wins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return wins == player.wins &&
                Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, wins);
    }

    @Override
    public String toString() {
        return name + " : " + wins;
    }
}
