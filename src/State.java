import java.util.ArrayList;
import java.util.List;

public enum State {
    ACTIVE(1,"Active"),
    DISCONTINUED(2,"Discontinued");
    private int id;
    private String value;

    private State(int id, String value){
        this.value = value;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static State getFromValue(String valor) {
        if(valor == null)
            return null;

        for(State d : values()) {
            if(d.getValue().equalsIgnoreCase(valor))
                return d;
        }
        throw new IllegalArgumentException(
                "No existe un EstadoAlumnoEnum con el valor '"+ valor +"'");
    }

    public static State getFromId(int id) {
        if(id == 0)
            return null;

        for(State d : values()) {
            if(d.getId() == id)
                return d;
        }
        throw new IllegalArgumentException(
                "No existe un EstadoAlumnoEnum con id '"+ id +"'");
    }

    public static String[] getStringValues() {
        List<String> lista = new ArrayList<>();

        for(State d : values())
            lista.add(d.getValue());

        return lista.toArray(new String[values().length]);
    }

    public static List<State> getValues() {

        List<State> res = new ArrayList<State>();
        for(State d : values())
            res.add(d);

        return res;
    }
}
