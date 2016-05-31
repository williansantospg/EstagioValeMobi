package utilitarios;
import java.sql.*;
import javax.swing.*;


public class conexao
{
 final private String driver = "org.postgresql.Driver"; // variavel driver
   final private String url = "jdbc:postgresql://localhost:5432/EstagioValeMobi"; // variavel endereço 
   final private String usuario = "postgres"; // do banco de dados
   final private String senha = "wllnsnts";  // do banco de dados
     
   //final private String driver = "sun.jdbc.odbc.JdbcOdbcDriver";//"org.postgresql.Driver"; // variavel driver
   //final private String url = "jdbc:odbc:MestreDaObra";//"jdbc:postgresql://localhost:5432/ControleDeEstoque"; // variavel endereço 
   //final private String usuario = "";//postgres"; // do banco de dados
   //final private String senha = "";//wllnsnts";  // do banco de dados
   public Connection conexao; // resposavel por fazer a conexão //
   public Statement statement; // responsavel por abrir caminho até o banco (Statement stmt = con.createStatement();)
   public ResultSet resultset; // responsavel por comandos sql
   
    
   public boolean conecta() 
   {
      boolean result = true;
      //JOptionPane.showMessageDialog(null,result);
      try
      {
          Class.forName(driver);
          conexao = DriverManager.getConnection(url,usuario,senha);
           //JOptionPane.showMessageDialog(null,"conectou");
      }
      catch(ClassNotFoundException Driver)
      {
          JOptionPane.showMessageDialog(null,"Driver não localizado: \n"+Driver);
          result = false;
      }
              
      catch(SQLException Fonte)
      {
          JOptionPane.showMessageDialog(null,"Deu erro na conexão "
                  +"com a fonte de dados: "+Fonte);
          result = false;
      }

      return result;
   }  
   
   
   public void desconecta()
   {
       boolean result = true;
       try
       {
        conexao.close();   
        //JOptionPane.showMessageDialog(null,"banco fechado");   
       }
        catch(SQLException fecha)
        {
        JOptionPane.showMessageDialog(null,"Não foi possivel " +  
                "fechar o banco de dados: "+fecha);
        result = false;  
       }
   }
   
   public void executeSQL(String sqls)
   {
       try
       {
       statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
       // o codigo entre parenteses acima diz que vc pode navegar nos registros do banco
       resultset = statement.executeQuery(sqls); 
       }
       catch(SQLException sqlex)
       {
       JOptionPane.showMessageDialog(null,"nao foi posssivel executar o SQL \n"+sqlex+" o SQL passado foi: "+ sqls);
       }
       
   }

   }
    
