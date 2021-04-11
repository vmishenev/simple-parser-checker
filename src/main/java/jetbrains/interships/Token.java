/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jetbrains.interships;

/**
 *
 * @author Admin
 */
public class Token {
        TokenType type;
        String value;

        public Token(TokenType type, String value)
        {
            this.type = type;
            this.value = value;
        }

        public TokenType getType()
        {
            return type; 
        }

        public String getValue()
        {
           return this.value; 
           // this.value = value; 
        }
        public void setValue(String value)
        {
           this.value = value; 
        }
        public Token Copy()
        {
            return new Token(type, value);
        }

        public  boolean Equals(Object obj)
        {
            if (obj ==null)
                return false;

            Token token = (Token)obj ;
            if (token == null)
                return false;

            return (this.type == token.type
                && this.value == token.value);
        }

        public  int GetHashCode()
        {
            return type.hashCode()^ value.hashCode();
        }

        public  String ToString()
        {
            return "Token type: " + type + "\n" +
                "Token value: " + value + "\n";
        }
}
