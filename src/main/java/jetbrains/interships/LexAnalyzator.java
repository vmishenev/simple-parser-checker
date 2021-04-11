package jetbrains.interships;

class LexAnalyzator {
    private String input;
    private int position;
    private  char currentSymbol;
    private  Boolean isInputEnd;

        public LexAnalyzator(String input)
        {
            this.input = input;
            this.position = 0;
            this.currentSymbol = input.charAt(position);
            this.isInputEnd = false;
        }

        private void readSymbol()
        {
            position++;

            if (position > input.length() - 1)
                isInputEnd = true;
            else
            {
                currentSymbol = input.charAt(position);
                isInputEnd = false;
            }
        }

        private Token readNumber()
        {
            String number = "";
            int sta = 1; // state
            while (Character.isDigit(currentSymbol) && !isInputEnd ) {

                switch (sta) {
                    case 1:

                        while (!isInputEnd && Character.isDigit(currentSymbol)) {
                            number += currentSymbol;
                            readSymbol();
                        }
                        if (currentSymbol == '.') {
                            number += currentSymbol;
                            readSymbol();
                            sta = 2;
                        }
                        if (currentSymbol == 'e' || currentSymbol == 'E') {
                            number += currentSymbol;
                            readSymbol();
                            if (currentSymbol == '+' || currentSymbol == '-') {
                                number += currentSymbol;
                                readSymbol();
                            }
                            sta = 3;
                        }


                        break;
                    case 2:
                        while (!isInputEnd && Character.isDigit(currentSymbol)) {
                            number += currentSymbol;
                            readSymbol();
                        }
                        if (currentSymbol == 'e' || currentSymbol == 'E') {
                            number += currentSymbol;
                            readSymbol();
                            if (currentSymbol == '+' || currentSymbol == '-') {
                                number += currentSymbol;
                                readSymbol();
                            }

                            sta = 3;
                        }
                        break;
                    case 3:
                        while (!isInputEnd && Character.isDigit(currentSymbol)) {
                            number += currentSymbol;
                            readSymbol();
                        }
                        break;
                }
            }

            return new Token(TokenType.NUMBER, number);
        }

        private Token readIdentificator()
        {
            String variable = "";
            while (!isInputEnd && (Character.isLetterOrDigit(currentSymbol) || currentSymbol == '_'))
            {
                variable += currentSymbol;
                readSymbol();
            }

            if( variable.equals("if"))
                return new Token(TokenType.IF, "if");
            else if( variable.equals("if"))
                return new Token(TokenType.WHILE, "while");
            else if( variable.equals("end"))
                return new Token(TokenType.END, "end");
            return new Token(TokenType.IDENT, variable);
        }

        private void skipWhiteSpaces()
        {
            while (!isInputEnd && Character.isSpaceChar(currentSymbol))
                readSymbol();
        }

        public Token getNextToken() throws Exception
        {
            if (isInputEnd)
                return null;

            if (Character.isSpaceChar(currentSymbol))
                skipWhiteSpaces();

            if (Character.isLetter(currentSymbol) || currentSymbol == '_')
                return readIdentificator();

            if (Character.isDigit(currentSymbol))
                return readNumber();

            switch (currentSymbol)
            {
                case '+':
                    readSymbol();
                    return new Token(TokenType.PLUS, "+");
                case '-':
                    readSymbol();
                    return new Token(TokenType.MINUS, "-");
                case '*':
                    readSymbol();
                    return new Token(TokenType.MULTIPLY, "*");
                case '/':
                    readSymbol();
                    return new Token(TokenType.DIV, "*");
                case '(':
                    readSymbol();
                    return new Token(TokenType.LEFT_ROUND_BRACKET, "(");
                case ')':
                    readSymbol();
                    return new Token(TokenType.RIGHT_ROUND_BRACKET, ")");
                case '<':
                    readSymbol();
                    return new Token(TokenType.LESS, "<X>");
                case '>':
                    readSymbol();
                    return new Token(TokenType.GREATER, ">");
                case '=':
                    readSymbol();
                    return new Token(TokenType.ASSIGN, "=");
                default: break;
            }
            throw new Exception("Недопустимый символ!");
        }
    }

