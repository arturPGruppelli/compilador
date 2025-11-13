class Compilador{

	public static void main(String[]args)
	{	
		ArvoreSintatica arv=null;
	
		try{
            // 1. Pega o nome do arquivo
            if (args.length == 0) {
                System.out.println("Erro: Forneça o nome do arquivo de entrada.");
                return;
            }

            // 2. Executa Análise Léxica e Sintática
			AnaliseLexica al = new AnaliseLexica(args[0]);
			Parser as = new Parser(al);
		
            // 3. Constrói a árvore
			arv = as.parseProg(); // (Seu parser usa 'parseProg()', está correto)
		
			
            // --- MODIFICAÇÃO DO EXERCÍCIO 3 COMEÇA AQUI ---

			// 4. (Código antigo do Exercício 2)
			// CodeGen backend = new CodeGen();
			// String codigo = backend.geraCodigo(arv);
			// System.out.println(codigo);
			
            // 5. (Novo código do Exercício 3)
            // Cria o back-end interpretador
            Interpreter backend = new Interpreter();
            
            // Calcula o resultado
            int resultado = backend.computaResultado(arv);

            // Imprime o resultado final
            System.out.println("Resultado: " + resultado);
			
            // --- MODIFICAÇÃO DO EXERCÍCIO 3 TERMINA AQUI ---
			
		}catch(Exception e)
		{			
			System.out.println("Erro:\n" + e.getMessage());
            // e.printStackTrace(); // Descomente para ver mais detalhes do erro
		}
	}
}