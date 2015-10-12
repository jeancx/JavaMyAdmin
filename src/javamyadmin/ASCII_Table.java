

package javamyadmin;

import com.bethecoder.ascii_table.ASCIITable;


public class ASCII_Table {
    
    public void printDatabases(String[][] data){
        String[] header = {"#","Databases"}; 
        ASCIITable.getInstance().printTable(header, data);
    }
    
    public void printTabelas(String[][] data){
        String[] header = {"#","Tabelas"}; 
        ASCIITable.getInstance().printTable(header, data);
    }
    
    public void printLinhas(String[] header, String[][] data){
        ASCIITable.getInstance().printTable(header, data);
    }
    
    public void printLinha(String[] header, String[][] data){
        ASCIITable.getInstance().printTable(header, data);
    }
    
}
