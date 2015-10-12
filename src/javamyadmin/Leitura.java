/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javamyadmin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Leitura {
    
    public static String lerTexto(String rotulo){
        Scanner leitura = new Scanner(System.in);
        System.out.print(rotulo+": ");
        return leitura.nextLine();
    }
    
    public static int lerInteiro(String rotulo){
        Scanner leitura = new Scanner(System.in);
        System.out.print(rotulo+": ");
        return leitura.nextInt();
    }
    
    public static double lerNumero(String rotulo){
        Scanner leitura = new Scanner(System.in);
        System.out.print(rotulo+": ");
        return leitura.nextDouble();
    }
    
    public static boolean lerBoleano(String rotulo){
        Scanner leitura = new Scanner(System.in);
        System.out.print(rotulo+": ");
        return leitura.nextBoolean();
    }    
    
}
