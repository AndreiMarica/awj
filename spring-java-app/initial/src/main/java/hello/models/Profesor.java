package hello.models;

/**
 * Profesor POJO.
 */
public class Profesor {
    private int id;
    private String numeProfesor;
    private String materieProfesor;
    private int aniVechime;

    public Profesor(int id, String numeProfesor, String materieProfesor, int aniVechime) {
        this.id = id;
        this.numeProfesor = numeProfesor;
        this.materieProfesor = materieProfesor;
        this.aniVechime = aniVechime;
    }

    public int getAniVechime() {
        return aniVechime;
    }

    public String getMaterieProfesor() {
        return materieProfesor;
    }

    public String getNumeProfesor() {
        return numeProfesor;
    }

    public int getId() {
        return id;
    }
}
