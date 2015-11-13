package hello.models;

/**
 * Elev POJO.
 */
public class Elev {
    private int id;
    private String nume;
    private String cnp;
    private int varsta;

    public Elev(int id,String nume, String cnp, int varsta) {
        this.id = id;
        this.nume = nume;
        this.cnp = cnp;
        this.varsta = varsta;
    }

    public String getNume() {
        return nume;
    }

    public String getCnp() {
        return cnp;
    }

    public int getVarsta() {
        return varsta;
    }

    public int getId() {
        return id;
    }
}
