package Proyect1;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private List<Empleado> empleados;

    public Empresa() {
        empleados = new ArrayList<>();
    }

    public void contratarEmpleado(String nombre, int edad, double salario) {
        Empleado empleado = new Empleado(nombre, edad, salario);
        empleados.add(empleado);
        System.out.println("Empleado contratado: " + nombre);
    }

    public void despedirEmpleado(String nombre) {
        for (Empleado empleado : empleados) {
            if (empleado.getNombre().equalsIgnoreCase(nombre)) {
                empleados.remove(empleado);
                System.out.println("Empleado despedido: " + nombre);
                return;
            }
        }
        System.out.println("No se encontró al empleado: " + nombre);
    }

    public void aumentarSalario(String nombre, double porcentaje) {
        for (Empleado empleado : empleados) {
            if (empleado.getNombre().equalsIgnoreCase(nombre)) {
                empleado.aumentarSalario(porcentaje);
                System.out.println("Salario aumentado para " + nombre + " en un " + porcentaje + "%");
                return;
            }
        }
        System.out.println("No se encontró al empleado: " + nombre);
    }

    public void listarEmpleados() {
        System.out.println("Lista de empleados:");
        for (Empleado empleado : empleados) {
            System.out.println("Nombre: " + empleado.getNombre() + ", Edad: " + empleado.getEdad() + ", Salario: " + empleado.getSalario());
        }
    }
    
}