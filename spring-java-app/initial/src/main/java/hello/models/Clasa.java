package hello.models;

import java.util.List;

/**
 * Clasa POJO
 */
public class Clasa {
    private int id;
    private String numeClasa;
    private List<Elev> elevi;
    private List<Profesor> profesori;

    public Clasa(int id, String numeClasa, List<Elev> elevi, List<Profesor> profesori) {
        this.id = id;
        this.numeClasa = numeClasa;
        this.elevi = elevi;
        this.profesori = profesori;
    }

    public int getId() {
        return id;
    }

    public String getNumeClasa() {
        return numeClasa;
    }

    public List<Elev> getElevi() {
        return elevi;
    }

    public List<Profesor> getProfesori() {
        return profesori;
    }
}
