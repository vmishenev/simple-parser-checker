package jetbrains.interships;



import jetbrains.interships.data.StatementList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

class Parser {

    public static void main(String[] args) {

        String buff = "";
        do
        {
            BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
            try {
                buff = buffer.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
            }
            StatementList tree;
            try {
                SyntacticalAnalyzator parser = new SyntacticalAnalyzator(buff);
                tree = parser.parse() ;
            } catch (Exception ex) {
                Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
        while (false); //(!buff.equals(""));
    }
}
