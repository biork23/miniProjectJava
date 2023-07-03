package Proyect1;

import java.util.Scanner;

public class MainProject {
	
	  public static void main(String[] args) {
	        Empresa empresa = new Empresa();
	        Scanner scanner = new Scanner(System.in);
	        String opcion;

	        do {
	            System.out.println("1. Contratar empleado");
	            System.out.println("2. Despedir empleado");
	            System.out.println("3. Aumentar salario");
	            System.out.println("4. Listar empleados");
	            System.out.println("5. Salir");
	            System.out.print("Ingrese una opción: ");
	            opcion = scanner.nextLine();

	            switch (opcion) {
	                case "1":
	                    System.out.print("Nombre: ");
	                    String nombre = scanner.nextLine();
	                    System.out.print("Edad: ");
	                    int edad = Integer.parseInt(scanner.nextLine());
	                    System.out.print("Salario: ");
	                    double salario = Double.parseDouble(scanner.nextLine());
	                    empresa.contratarEmpleado(nombre, edad, salario);
	                    break;
	                case "2":
	                    System.out.print("Nombre del empleado a despedir: ");
	                    String nombreDespedir = scanner.nextLine();
	                    empresa.despedirEmpleado(nombreDespedir);
	                    break;
	                case "3":
	                    System.out.print("Nombre del empleado: ");
	                    String nombreAumentar = scanner.nextLine();
	                    System.out.print("Porcentaje de aumento: ");
	                    double porcentajeAumento = Double.parseDouble(scanner.nextLine());
	                    empresa.aumentarSalario(nombreAumentar, porcentajeAumento);
	                    break;
	                case "4":
	                    empresa.listarEmpleados();
	                    break;
	                case "5":
	                    System.out.println("Programa finalizado.");
	                    break;
	                default:
	                    System.out.println("Opción inválida. Intente nuevamente.");
	                    break;
	            }

	            System.out.println();
	        } while (!opcion.equals("5"));

	        scanner.close();
	    }
	}


