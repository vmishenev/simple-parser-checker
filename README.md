# simple-parser-checker

Парсер реализует синтаксический анализ методом рекурсивного спуска.
Для анализа арифмический выражений используется следующая грамматика, учитывающая приоритет операций:

```
expression ::= term (('+' | '-') term)
term ::= factor (('*'  | '/' )  factor)
factor ::= variable
          | number
          | '(' expression ')'
          | '-' factor
```

Для дальнейшего анализа Ast все его элементы реализуют интерфейс jetbrains.interships.dat.IVisitor.
## Задание
Напишите парсер (на любом языке программирования, но желательно на Kotlin, Java или С++) для языка, заданного следующей грамматикой:

```
program ::= statement_list
statement_list ::= statement | statement_list statement
statement ::= variable '=' expression | 'if' expression statement_list 'end' | 'while' expression statement_list 'end'   
expression ::= variable | constant | '(' expression ')' | expression operator expression
operator ::= '+' | '-' | '*' | '/' | '<' | '>' 
```

Приоритеты операций - стандартные. variable - буква от ‘a’ до ‘z’, constant - десятичное целое число.
Реализуйте анализ неиспользуемых присваиваний (присваивание в переменную считается неиспользуемым, если ни при каком дальнейшем пути выполнения программы эта переменная не будет прочитана до следующего присваивания в неё).
Например, для следующего примера:

a = 1
b = a
x = 3
y = 4

while (b < 5)
  z = x
  b = b + 1
  x = 9
  y = 10
end

ваш DFA должен выдать следующие неиспользуемые присваивания:

y = 4
z = x
y = 10
