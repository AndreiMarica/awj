package hello.models;

/**
 * Catalog Pojo.
 */
public class Catalog {
    private int id;
    private String codCatalog;
    private Clasa clasa;

    public Catalog(int id, String codCatalog, Clasa clasa) {
        this.id = id;
        this.codCatalog = codCatalog;
        this.clasa = clasa;
    }

    public int getId() {
        return id;
    }

    public String getCodCatalog() {
        return codCatalog;
    }

    public Clasa getClasa() {
        return clasa;
    }
}
