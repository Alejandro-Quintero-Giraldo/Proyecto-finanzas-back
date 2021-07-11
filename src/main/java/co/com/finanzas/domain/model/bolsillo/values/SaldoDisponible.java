package co.com.finanzas.domain.model.bolsillo.values;

import co.com.sofka.domain.generic.ValueObject;

public class SaldoDisponible implements ValueObject<Integer> {
    public Integer value;

    public SaldoDisponible(Integer value) {
        this.value = value;
    }

    public static SaldoDisponible inicializarSaldo(){
        return new SaldoDisponible(0);
    }

    public SaldoDisponible AumentarSaldo(Integer value) {
        return new SaldoDisponible(this.value + value);
    }

    public SaldoDisponible DisminuirSaldo(Integer value) {
        if (value > this.value){
            throw  new IllegalArgumentException("El valor ingresado es mayor que el valor disponible en el bolsillo. Por favor rectifique la información");
        }
        return new SaldoDisponible(this.value - value);
    }

    @Override
    public Integer value() {
        return this.value;
    }
}
