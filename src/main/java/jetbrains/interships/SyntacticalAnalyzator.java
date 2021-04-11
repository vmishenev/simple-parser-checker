package jetbrains.interships;


import jetbrains.interships.data.*;



public class SyntacticalAnalyzator {
    private final LexAnalyzator lexer;
    private Token currentToken;

    public SyntacticalAnalyzator(String input) throws Exception {
        this.lexer = new LexAnalyzator(input);
        currentToken = lexer.getNextToken();
    }

    protected void readToken(TokenType tokenType) throws Exception {
        if (currentToken.getType() != tokenType)
            throw new Exception("Недопустимая конструкция!");

        currentToken = lexer.getNextToken();
    }

    public StatementList parse() throws Exception {
        return getStatements();
    }


    protected ExprNodeTree GetVariable() throws Exception {
        ExprNodeTree node = new ExprNodeTree(currentToken);
        readToken(TokenType.IDENT);


        return node;
    }

    // statement_list :  statement | statement_list statement
    protected StatementList getStatements() throws Exception {
        StatementList block = new StatementList();
        for (IStatement st = getStatement(); st!= null; st = getStatement())
            block.getL().add(st);
        return block;
    }

    /* statemnt :  variable ASSIGN expression
                  | IF expression statement_list  END
                  | WHILE expression statement_list END
    */
    protected IStatement getStatement() throws Exception {
        if(currentToken == null )
            return null;

        switch (currentToken.getType()) {
            case IDENT -> {
                String name = currentToken.getValue();
                readToken(TokenType.IDENT);
                readToken(TokenType.ASSIGN);
                ExprNodeTree val = getExpression();
                return new AssignStatement(name, val);
            }
            case IF -> {
                readToken(TokenType.IF);
                ExprNodeTree cond = getExpression();
                StatementList body = getStatements();
                readToken(TokenType.END);
                return new IfStatement(cond, body);
            }
            case WHILE -> {
                readToken(TokenType.WHILE);
                ExprNodeTree whileCond = getExpression();
                StatementList whileBody = getStatements();
                readToken(TokenType.END);
                return new WhileStatement(whileCond, whileBody);
            }
            default -> throw new Exception("Неожидаемый токен!");
        }

    }

        // expression : term ((PLUS | MINUS) term)*
        protected ExprNodeTree getExpression() throws Exception
        {
            ExprNodeTree node = getTerm();

            while (currentToken != null && (currentToken.getType() == TokenType.PLUS || currentToken.getType() == TokenType.MINUS))
            {
                ExprNodeTree supNode = node.copy();
                Token token = currentToken;
                if (currentToken.getType() == TokenType.PLUS)
                {
                    readToken(TokenType.PLUS);

                    node = new ExprNodeTree(token);
                    //node.add(new List<Node> { supNode, getTerm() });
                    node.add(supNode);
                    node.add(getTerm());
                }
                else if (currentToken.getType() == TokenType.MINUS)
                {
                    readToken(TokenType.MINUS);
                    
                    node = new ExprNodeTree(token);
                    //node.add(new List<Node> { supNode, getTerm() });
                     node.add(supNode);
                     node.add(getTerm());
                }
            }

            return node;
        }

        // term : factor ((MILT | DIV)  factor)*
        protected ExprNodeTree getTerm() throws Exception
        {
            ExprNodeTree node = getFactor();


            while (currentToken != null && (currentToken.getType() == TokenType.MULTIPLY || currentToken.getType() == TokenType.DIV)) {
                ExprNodeTree supNode = node.copy();
                Token token = currentToken;
                if (currentToken.getType() == TokenType.MULTIPLY) {
                    readToken(TokenType.MULTIPLY);

                    node = new ExprNodeTree(token);

                    node.add(supNode);
                    node.add(getFactor());
                } else if (currentToken.getType() == TokenType.DIV) {
                    readToken(TokenType.DIV);

                    node = new ExprNodeTree(token);
                    //node.add(new List<Node> { supNode, getTerm() });
                    node.add(supNode);
                    node.add(getFactor());
                }
            }

            return node;
        }



        /* factor : variable
                    | number
                    | left_round_bracket expression right_round_bracket
                    | minus factor
        */
        protected ExprNodeTree getFactor() throws Exception
        {
            ExprNodeTree node = null;

            switch (currentToken.getType()) {
                case IDENT -> node = GetVariable();
                case NUMBER -> node = getNumber();
                case MINUS -> {
                    node = new ExprNodeTree(currentToken);
                    readToken(TokenType.MINUS);
                    node.add(getFactor());
                }
                case LEFT_ROUND_BRACKET -> {
                    readToken(TokenType.LEFT_ROUND_BRACKET);
                    node = getExpression();
                    readToken(TokenType.RIGHT_ROUND_BRACKET);
                }
            }

            return node;
        }



        // number : number
        protected ExprNodeTree getNumber() throws Exception
        {
            ExprNodeTree node = new ExprNodeTree(currentToken);
            readToken(TokenType.NUMBER);
            return node;
        }
    }

