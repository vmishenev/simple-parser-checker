/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jetbrains.interships.data;

import jetbrains.interships.Token;

import java.util.LinkedList;
import java.util.List;

public class ExprNodeTree {
    
        private Token token;
        private ExprNodeTree parent;
        private List<ExprNodeTree> childs;

        public ExprNodeTree(Token token)
        {
            this.token = token;
            this.parent = null;
            this.childs = new LinkedList<ExprNodeTree>();
        }

        public Token getToken()
        {
            return token; 
           //  token = value; 
        }
        public void setToken(Token value)
        {
           // return token; 
           token = value; 
        }
        public ExprNodeTree parent()
        {
            return parent; 
        }

        public List<ExprNodeTree> childs()
        {
            return childs; 
        }

        public void add(ExprNodeTree node)
        {
            node.parent = this;
            this.childs.add(node);
        }
        public ExprNodeTree copy()
        {
            ExprNodeTree copyNode = new ExprNodeTree(token.Copy());
            copyNode.parent = parent;

            for (ExprNodeTree child : childs)
                copyNode.add(child.copy());

            return copyNode;
        }



        public boolean isLeaf()
        {
            return (childs.isEmpty());
        }

        private void getLeafs(ExprNodeTree node, List<ExprNodeTree> leafs)
        {
            if (node.isLeaf())
                leafs.add(node);
            for (ExprNodeTree child : node.childs)
                getLeafs(child, leafs);
        }

        public List<ExprNodeTree> getLeafs()
        {
            List<ExprNodeTree> leafs = new LinkedList<ExprNodeTree>();
            getLeafs(this, leafs);
            return leafs;
        }

        private void getTokens(ExprNodeTree node, List<Token> tokens)
        {
            tokens.add(node.getToken());

            if (node.childs() != null)
            {
                for (ExprNodeTree child : node.childs())
                    getTokens(child, tokens);
            }
        }

        public List<Token> getTokens()
        {
            List<Token> tokens = new LinkedList<Token>();
            getTokens(this, tokens);
            return tokens;
        }






        public boolean equals(ExprNodeTree top)
        {
            if (top == null)
                return false;

            List<Token> firstTokens = this.getTokens();
            List<Token> secondTokens = top.getTokens();

            if (firstTokens.size() != secondTokens.size())
                return false;

            for (int i = 0; i < firstTokens.size(); i++)
                if (firstTokens.get(i).getType() != secondTokens.get(i).getType() ||
                    firstTokens.get(i).getValue() != secondTokens.get(i).getValue())
                    return false;
            return true;
        }


 }
