package co.com.finanzas.domain.infra.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Movimiento")
public class MovimientoData {

    @Id
    protected String movimientoId;

    protected String tipo;
    protected Integer saldo;
    protected String bolsilloId;
    protected String uid;
    protected LocalDateTime fecha;

    public MovimientoData(String movimientoId, String tipo, Integer saldo,LocalDateTime fecha, String bolsilloId, String uid){
        this.movimientoId = movimientoId;
        this.tipo = tipo;
        this.saldo = saldo;
        this.bolsilloId = bolsilloId;
        this.uid = uid;
        this.fecha =  fecha;
    }

    public String getMovimientoId() {
        return movimientoId;
    }

    public void setMovimientoId(String movimientoId) {
        this.movimientoId = movimientoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public String getBolsilloId() {
        return bolsilloId;
    }

    public void setBolsilloId(String bolsilloId) {
        this.bolsilloId = bolsilloId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
