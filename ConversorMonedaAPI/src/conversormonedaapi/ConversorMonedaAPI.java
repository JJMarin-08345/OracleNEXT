/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversormonedaapi;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author USUARIO
 */
public class ConversorMonedaAPI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        InfoMonedaJSON objMoneda = new InfoMonedaJSON();

        boolean showMenu = true;
        int optionFirstMenu; //Para el inicio de opcion en el menu
        String valorConvertir = "0";

        do {
            System.out.println("\n\n**BIENVENIDO AL CONVERSOR DE MONEDAS**");
            System.out.println("\n\n**Escriba el numero de la opcion deseada**");
            System.out.println("**1. Pesos Colombianos (COP) --> Dolar (USD)**");
            System.out.println("**2. Dolar (USD) --> Pesos Colombianos (COP)**");
            System.out.println("**3. Pesos Mexicanos (MXN) --> Dolar (USD)**");
            System.out.println("**4. Dolar (USD) --> Pesos Mexicanos (MXN)**");
            System.out.println("**5. Euro (EUR) --> Dolar (USD)**");
            System.out.println("**6. Dolar (USD) --> Euro (EUR)**");
            System.out.println("**7. Pesos Colombianos (COP) --> Euro (EUR)**");
            System.out.println("**8. Euro (EUR) --> Pesos Colombianos (COP)**");
            System.out.println("**9. Pesos Mexicanos (MXN) --> Euro (EUR)**");
            System.out.println("**10. Euro (EUR) --> Pesos Mexicanos (MXN)**");
            System.out.println("**11. Salir**");

            optionFirstMenu = sc.nextInt();
            System.out.print("\n\n");

            System.out.println("Ingrese cantidad a convertir: ");
            valorConvertir = sc.next();
            switch (optionFirstMenu) {
                case 1:
                    objMoneda.conversionMoneda("COP", "USD", valorConvertir);
                    break;
                case 2:
                    objMoneda.conversionMoneda("USD", "COP", valorConvertir);
                    break;
                case 3:
                    objMoneda.conversionMoneda("MXN", "USD", valorConvertir);
                    break;
                case 4:
                    objMoneda.conversionMoneda("USD", "MXN", valorConvertir);
                    break;
                case 5:
                    objMoneda.conversionMoneda("EUR", "USD", valorConvertir);
                    break;
                case 6:
                    objMoneda.conversionMoneda("USD", "EUR", valorConvertir);
                    break;
                case 7:
                    objMoneda.conversionMoneda("COP", "EUR", valorConvertir);
                    break;
                case 8:
                    objMoneda.conversionMoneda("EUR", "COP", valorConvertir);
                    break;
                case 9:
                    objMoneda.conversionMoneda("MXN", "EUR", valorConvertir);
                    break;
                case 10:
                    objMoneda.conversionMoneda("EUR", "MXN", valorConvertir);
                    break;
                case 11:
                    System.out.println("HASTA LA PROXIMA");
                    showMenu = false;
                    break;
                default:
                    break;
            }
        } while (showMenu);
    }

}
