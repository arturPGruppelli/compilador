import java.io.*;

enum TokenType{ NUM,SOMA, MULT,APar,FPar, EOF, SUB, DIV}

class Token{
  String lexema;
  TokenType token;

 Token (String l, TokenType t)
 	{ lexema=l;token = t;}	

}

class AnaliseLexica {

	BufferedReader arquivo;

	AnaliseLexica(String a) throws Exception
	{
		
	 	this.arquivo = new BufferedReader(new FileReader(a));
		
	}

	Token getNextToken() throws Exception
	{	
		Token token;
		int eof = -1;
		char currchar;
		int currchar1;

			do{ // elimina espaços em branco
				currchar1 =  arquivo.read();
				currchar = (char) currchar1;
			} while (currchar == '\n' || currchar == ' ' || currchar =='\t' || currchar == '\r');
			
			if(currchar1 != eof && currchar1 !=10)
			{
								
	
				if (currchar >= '0' && currchar <= '9'){
//----------------------------------- 1 ---------------------------------------
                    
                    // 1. Inicia um StringBuilder para construir o número
                    StringBuilder numero = new StringBuilder();
                    numero.append(currchar); // Adiciona o primeiro dígito já lido
                    
                    // 2. Loop para ler os próximos caracteres
                    while (true) {
                        arquivo.mark(1); // Salva a posição atual do arquivo
                        currchar1 = arquivo.read();
                        currchar = (char) currchar1;
                        
                        if (currchar >= '0' && currchar <= '9') {
                            // Se for um dígito, adiciona ao nosso número
                            numero.append(currchar);
                        } else {
                            // Se NÃO for um dígito, "devolve" o caractere lido
                            arquivo.reset(); 
                            break; // Para o loop de leitura de dígitos
                        }
                    }
                    
                    // 3. Converte a string (ex: "123") para um inteiro
                    int valorNumerico = Integer.parseInt(numero.toString());
                    
                    // 4. Retorna o Token NUM com o valor inteiro completo
					 return (new Token (numero.toString(), TokenType.NUM));
//----------------------------------- Fim 1 -----------------------------------
                
				}
					//return (new Token (currchar, TokenType.NUM));
				else
					switch (currchar){
						case '(':
							return new Token (String.valueOf(currchar),TokenType.APar);
						case ')':
							return new Token (String.valueOf(currchar),TokenType.FPar);
						case '+':
							return new Token (String.valueOf(currchar),TokenType.SOMA);
						case '*':
							return new Token (String.valueOf(currchar),TokenType.MULT);
						case '-':
							return new Token (String.valueOf(currchar),TokenType.SUB);// --2--
						case '/':
							return new Token (String.valueOf(currchar),TokenType.DIV);// --2--
            default: throw (new Exception("Caractere inválido: " + ((int) currchar)));
					}
			}

			arquivo.close();
			
		return (new Token(String.valueOf(currchar),TokenType.EOF));
		
	}
}
