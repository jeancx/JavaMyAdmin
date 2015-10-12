/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javamyadmin;

import java.sql.SQLException;

/**
 *
 * @author HighLander
 */
public class Principal {
    
    public MyQuery myq = new MyQuery();
    public ASCII_Table ascii = new ASCII_Table();
    
    public void limpar() {  
        for (int i = 0; i < 10; i++) {  
            System.out.println("\n");  
        }
    }
    
    public void getDriverInfo() throws SQLException{
        myq.getDriverInfo();
    }
    
    public void setHost(){
        myq.setHost(
            Leitura.lerTexto("Deixe em branco para default: 'localhost' \nServidor: "), 
            Leitura.lerTexto("Deixe em branco para d1"
                    + "efault: '3306' \nPorta: "), 
            Leitura.lerTexto("Deixe em branco para default: 'root' \nUsuario: "), 
            Leitura.lerTexto("Deixe em branco para default: 'null' \nSenha: ")
        );
    }
    
    public int selectDB() throws SQLException{
        String[][] databases = myq.getDatabase();
        ascii.printDatabases(databases);
        System.out.println("Digite 0 para Voltar!");
        int pos = Leitura.lerInteiro("Digite o #Numero da Database: \n > ");
        if(pos == 0){return 0;}else{pos -= 1;}
        myq.setDatabase(databases[pos][1]);
        //System.out.println(databases[pos][1]);
        return selectTB();
    }
    
    public int selectTB() throws SQLException{
        String[][] tabelas = myq.getTables();
        ascii.printDatabases(tabelas);
        System.out.println("Digite 0 para Voltar!");
        int pos = Leitura.lerInteiro("Digite o #Numero da Tabela: \n > ");
        if(pos == 0){return 0;}else{pos -= 1;}
        return selectROW(tabelas[pos][1]);
    }
    
    public int selectROW(String tb) throws SQLException{
        String[] tbHeader = myq.getRowsHeader(tb);
        String[][] tbData = myq.getRowsData(tb);
        ascii.printLinhas(tbHeader, tbData);
        System.out.println("Digite 0 para Voltar!");
        int pos = Leitura.lerInteiro(">> ");
        //int pos = Leitura.lerInteiro("Digite o #Numero da Linha: \n > ");
        //if(pos == 0){return 0;}else{pos -= 1;}
        //return selectROW(tabelas[pos][1]);
        return 0;
    }
    
}
