package javamyadmin;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MyQuery {

    private String host = "localhost";
    private String port = ":3306";
    private String database = "";
    private final String url = "jdbc:mysql://"+host+port+"/"+database;
    private String user = "root";
    private String pass = "usbw";
    
    public void setHost(String host, String port, String user, String pass) {
        this.host = (host == null) ? "localhost" : host;
        this.port = (port == null) ? ":3306" : port;
        this.user = (user == null) ? "root" : user;
        this.pass = (pass == null) ? "" : pass;
    }
    
    public void getDriverInfo() throws SQLException{
        Connection conn = DriverManager.getConnection(url, user, pass);
        // Get the MetaData
        DatabaseMetaData metaData = conn.getMetaData();
        // Get driver information
        System.out.println("Driver Information");
        System.out.println(metaData.getDriverName());
        System.out.println(metaData.getDriverVersion());
        conn.close();
    }
    
    public void setDatabase(String db) {
        this.database = db;
        System.out.println(database);
    }

    public String[][] getDatabase() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, pass);
        ResultSet resultSet = conn.getMetaData().getCatalogs();
        int rowcount = 0;
        if (resultSet.last()) {
            rowcount = resultSet.getRow();
            resultSet.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
        }
        String[][] arrayTCat = new String[rowcount][2];
        int x = 0;
        while (resultSet.next()) {
            arrayTCat[x][0] = Integer.toString(x+1);
            arrayTCat[x][1] = resultSet.getString("TABLE_CAT");
            x++;
        }
        resultSet.close();        
        return arrayTCat;
    }
    
    public String[][] getTables() throws SQLException
    {
        Connection conn = DriverManager.getConnection(url, user, pass);
        ResultSet resultSet = conn.getMetaData().getCatalogs();
        String[] types = { "TABLE" };
        resultSet = conn.getMetaData().getTables(database, null, "%", types);
        
        int rowcount = 0;
        if (resultSet.last()) {
            rowcount = resultSet.getRow();
            resultSet.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
        }
        
        String[][] tabelas = new String[rowcount][2];;
        
        int x=0;
        while (resultSet.next()) {
          tabelas[x][0] = Integer.toString(x+1);
          tabelas[x][1] = resultSet.getString(3);
          x++;
        }
        resultSet.close();
        
        return tabelas;
    }
    
    public String[] getRowsHeader(String tb) throws SQLException
    {
        Connection conn = DriverManager.getConnection(url, user, pass);
        DatabaseMetaData dbMeta = conn.getMetaData();
        
        Statement st = conn.createStatement();
        String SQL = "SELECT * FROM " + database + "." + tb;
        ResultSet rs = st.executeQuery(SQL);
        ResultSetMetaData rsMeta = rs.getMetaData();
        int colunas = rsMeta.getColumnCount();
        
        String[] header = new String[colunas+1];
        for(int i = 0; i <= colunas; i++){
            if(i!=0){
                String nomeColuna = rsMeta.getColumnName(i);
                String tipoColuna = rsMeta.getColumnTypeName(i);
                header[i] = nomeColuna + "->" + tipoColuna;
            }else{
                header[i] = "#";
            }
            System.out.println(header[i]);
        }
        return header;
    }
    
    public String[][] getRowsData(String tb) throws SQLException
    {
        Connection conn = DriverManager.getConnection(url, user, pass);
        DatabaseMetaData dbMeta = conn.getMetaData();
        
        Statement st = conn.createStatement();
        String SQL = "SELECT * FROM " + database + "." + tb;
        ResultSet rs = st.executeQuery(SQL);
        ResultSetMetaData rsMeta = rs.getMetaData();
        int colunas = rsMeta.getColumnCount() + 1;
        
        int linhas = 0;
        if (rs.last()) {
            linhas = rs.getRow();
            rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
        }
        
        String[] header = getRowsHeader(tb);
        String[][] data = new String[linhas][colunas];
        Object[] arr = new Object[colunas-1];
        
        // iterate through the java resultset
        int x=0;
        while (rs.next())
        {
            for(int i=0; i < colunas; i++){
                if(i>0){
                    arr[i-1] = rs.getObject(i);
                    if(arr[i-1]!=null){
                        data[x][i] = arr[i-1].toString(); //.substring(0, 35)
                        if(data[x][i].length() > 40){
                           data[x][i] = data[x][i].substring(0,40) + "...";
                        }
                    }
                    else
                        data[x][i]= "null";
                }
                if(i==0){
                    data[x][0] = Integer.toString(x+1);
                }
                //System.out.println(data[x][i]);
            }
            x++;
        }
        return data;
    }
    
    public String[] selectRow(String tb, String[] row) throws SQLException{
        
        Connection conn = DriverManager.getConnection(url, user, pass);
        DatabaseMetaData dbMeta = conn.getMetaData();
        
        Statement st = conn.createStatement();
        String SQL = "SELECT * FROM " + database + "." + tb;
        ResultSet rs = st.executeQuery(SQL);
        ResultSetMetaData rsMeta = rs.getMetaData();
        int colunas = rsMeta.getColumnCount() + 1;
        
        int linhas = 0;
        if (rs.last()) {
            linhas = rs.getRow();
            rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
        }
        
        String[] header = getRowsHeader(tb);
        String[] data = new String[colunas];
        Object[] arr = new Object[colunas-1];
        String iString;
        // iterate through the java resultset
        int x=0;
        while (rs.next())
        {
            for(int i=0; i < colunas; i++){
                iString = Integer.toString(x+1);
                if(iString.equals(row)){
                    if(i>0){
                        arr[i-1] = rs.getObject(i);
                        if(arr[i-1]!=null)
                            data[i] = arr[i-1].toString().substring(0, 40);
                        else
                            data[i] = "null";
                    }
                    if(i==0){
                        data[0] = Integer.toString(x+1);
                    }
                    //System.out.println(data[x][i]);
                }
            }
            x++;
        }
        return data;
    
    }

}
