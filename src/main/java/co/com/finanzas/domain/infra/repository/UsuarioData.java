package co.com.finanzas.domain.infra.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Usuario")
public class UsuarioData {

    @Id
    protected String uid;
    protected String nombre;
    protected String email;

    public UsuarioData(String uid, String nombre, String email){
        this.uid = uid;
        this.nombre = nombre;
        this.email = email;
    }

    public UsuarioData(){
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
