package Proyect1;

public class Empleado {
    private String nombre;
    private int edad;
    private double salario;

    public Empleado(String nombre, int edad, double salario) {
        this.nombre = nombre;
        this.edad = edad;
        this.salario = salario;
    }
    
    public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    public String getNombre() {
        return nombre;
    }
    
    public void setEdad(int edad) {
    	this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }
    
    public void setSalario(double salario) {
    	this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void aumentarSalario(double porcentaje) {
        salario += salario * porcentaje / 100;
    }
}

