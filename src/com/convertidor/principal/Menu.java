package com.convertidor.principal;
import java.util.Scanner;

public class Menu {
	
    private Scanner scanner;
    private ExchangeRateAPI exchangeRateAPI;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.exchangeRateAPI = new ExchangeRateAPI();
    }

    public void displayMenu() {
        int option;
        do {
            System.out.println("Seleccione una opción de conversión:");
            System.out.println("1. Pesos a Dólares");
            System.out.println("2. Pesos a Euros");
            System.out.println("3. Dólares a Euros");
            System.out.println("0. Salir");

            option = scanner.nextInt();
            switch (option) {
                case 1:
                    convertPesosToDollars();
                    break;
                case 2:
                    convertPesosToEuros();
                    break;
                case 3:
                    convertDollarsToEuros();
                    break;
                case 0:
                    System.out.println("Finalizo proceso de conversion");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 0);
    }

    private void convertPesosToDollars() {
        System.out.print("Ingrese la cantidad en pesos: ");
        double pesos = scanner.nextDouble();
        double result = exchangeRateAPI.convert("COP", "USD", pesos);
        System.out.printf("%.2f Pesos = %.2f Dólares%n", pesos, result);
    }

    private void convertPesosToEuros() {
        System.out.print("Ingrese la cantidad en pesos: ");
        double pesos = scanner.nextDouble();
        double result = exchangeRateAPI.convert("COP", "EUR", pesos);
        System.out.printf("%.2f Pesos = %.2f Euros%n", pesos, result);
    }

    private void convertDollarsToEuros() {
        System.out.print("Ingrese la cantidad en dólares: ");
        double dollars = scanner.nextDouble();
        double result = exchangeRateAPI.convert("USD", "EUR", dollars);
        System.out.printf("%.2f Dólares = %.2f Euros%n", dollars, result);
    }

}
