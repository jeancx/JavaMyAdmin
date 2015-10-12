
package javamyadmin;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;



public class JavaMyAdmin {

    public static void main(String[] args) throws Exception{
        
        int opcao = 0;
        
        Principal principal = new Principal();
        
        do{
        switch(opcao){
            
            //Menu Inicial
            case 0:
                principal.limpar();
                System.out.println("##### JavaMyAdmin #####");
                System.out.println("\n Menu:");
                System.out.println("\n 1 - Configurar Host:");
                System.out.println(" 2 - Escolher Database:");
                System.out.println(" 3 - Informação de Driver:");
                System.out.println(" 4 - Sair:");
                do{
                    opcao = Leitura.lerInteiro(">> ");
                }while(opcao<1 && opcao>4);
                if(opcao==3){opcao=5;}
                if(opcao==4){opcao=-1;}
            break;
                
            // Menu Host
            case 1: 
                principal.setHost(); 
                principal.limpar();
                System.out.println("##### JavaMyAdmin #####");
                System.out.println("\n Menu:");
                System.out.println("\n 1 - Escolher Database");
                System.out.println(" 2 - Voltar");
                System.out.println(" 3 - Sair");
                do{
                    opcao = Leitura.lerInteiro(">> ");
                }while(opcao<1 && opcao>3);
                if(opcao==2){opcao=0;}
                if(opcao==3){opcao=-1;}
                if(opcao==1){opcao=2;}
            break;
                
            // Menu Database
            case 2: 
                opcao = principal.selectDB();
                principal.limpar();
            break;
                
                
            //Menu Tabela
            case 3: 
                principal.selectTB();
                principal.limpar();
            break;
            
            //Driver Information
            case 4:
                principal.getDriverInfo();
                opcao=0;
            break;
        }
        }while(opcao!=-1);

    }
    
}
