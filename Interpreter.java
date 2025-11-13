/**
 * Exercício 3: Back-end Interpretador.
 * Esta classe substitui o CodeGen e calcula o resultado da árvore.
 */
public class Interpreter {

    /**
     * Método público principal.
     * Ele recebe a raiz da árvore e inicia a interpretação.
     */
    public int computaResultado(ArvoreSintatica arv) throws Exception {
        // A raiz da árvore é um 'Exp', então podemos começar a 
        // interpretação recursiva a partir dela.
        return interpreta((Exp) arv);
    }

    /**
     * Método recursivo privado que "caminha" pela árvore e calcula o valor.
     * Ele usa 'instanceof' para descobrir o tipo de cada nó.
     */
    private int interpreta(Exp no) throws Exception {
        
        // --- CASO BASE: Se o nó é um número ---
        // Se a classe do nó for 'Num', retorne o valor 'num' dele.
        if (no instanceof Num) {
            // (Seu Parser.java já converte o lexema para int,
            //  por isso podemos acessar 'num' diretamente)
            return ((Num) no).num;
        }

        // --- CASOS RECURSOS: Se o nó é uma operação ---
        
        // Se a classe do nó for 'Soma'
        if (no instanceof Soma) {
            Soma somaNo = (Soma) no;
            // Calcula recursivamente os filhos 'arg1' e 'arg2'
            int valorEsq = interpreta(somaNo.arg1);
            int valorDir = interpreta(somaNo.arg2);
            return valorEsq + valorDir;
        }

        // Se a classe do nó for 'Sub'
        if (no instanceof Sub) {
            Sub subNo = (Sub) no;
            int valorEsq = interpreta(subNo.arg1);
            int valorDir = interpreta(subNo.arg2);
            return valorEsq - valorDir;
        }

        // Se a classe do nó for 'Mult'
        if (no instanceof Mult) {
            Mult multNo = (Mult) no;
            int valorEsq = interpreta(multNo.arg1);
            int valorDir = interpreta(multNo.arg2);
            return valorEsq * valorDir;
        }

        // Se a classe do nó for 'Div'
        if (no instanceof Div) {
            Div divNo = (Div) no;
            int valorEsq = interpreta(divNo.arg1);
            int valorDir = interpreta(divNo.arg2);
            
            // Tratamento de erro para divisão por zero
            if (valorDir == 0) {
                throw new ArithmeticException("Erro de execução: Divisão por zero!");
            }
            return valorEsq / valorDir;
        }
        
        // Se a árvore não for nem Num nem uma Operação conhecida, algo está errado.
        throw new Exception("Erro: Nó da árvore inválido.");
    }
}