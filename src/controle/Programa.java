package controle;

import java.util.Arrays;
import java.util.Scanner;


public class Programa {

	private int primeiro, fimN, fimP, fimG, total;	
	private String[] lista, aux;

	
	public Programa() {
		lista = new String[50];
		aux = new String[50];
		total = 0;
		primeiro = 0;
		fimN = 0;
		fimP = 0;
		fimG= 0;
	}
	
	public boolean cheia() {
		return total==lista.length;
		
	}
	public boolean vazio() {
		return total==0;
		
	}
	
	public void inserir(String nome, char prioridade) {

		//Verifica se j� atendeu 50 clientes 
		if(cheia()) {
			System.out.print("\n A clinica atendeu o limite m�ximo de pacientes de hoje! \n");
			return;
		}
				
		if(prioridade == 'g') {
						
			//Usa a array auxiliar para guardar valores que ser�o passados a frente caso um paciente de prioridade G entre
			System.arraycopy(lista, 0, aux, 0, lista.length);
			for(int i = (fimG); i <= (total-1); i++) {
				
				if((i + 1) <= 50) {
				
			lista[i+1] = aux[i];	
			
				}
				
			}
			
			lista[fimG] = nome;
			

			fimN++;
			fimP++;
			fimG++;
						

						
		}else if(prioridade == 'p') {
					  
			//Usa a array auxiliar para guardar valores que ser�o passados a frente caso um paciente de prioridade P entre		
			System.arraycopy(lista, 0, aux, 0, lista.length);
			for(int i = (fimP); i <= (total-1); i++) {
			
				if((i + 1) <= 50) {
			lista[i+1] = aux[i];	
					}
				
			}
			
			lista[fimP] = nome;
			

			fimN++;
			fimP++;


						
		}else if(prioridade == 'n') {
			
			lista[fimN] = nome;
			

			fimN++;
			

		}
		//Adiciona +1 ao total de pacientes j� atendidos
		total++;
	}
	
	
	public void atender() {
		if(vazio()) {
			System.out.print("\n Clinica vazia, ninguem para ser atendido! \n");
			return;
		}
		
		//Verifica se todos que EST�O na fila foram atendidos se sim retorna que n�o h� mais pacientes para atender
		if(primeiro == fimN) {
			System.out.print("\n N�o tem pacientes para atender no momento! \n");
			return;
		}

		System.out.print("\n O paciente "+lista[primeiro]+" foi atendido \n");
		String atendido = ""+lista[primeiro]+" {atendido(a)}";
		lista[primeiro] = atendido;

		primeiro++;
		
		//Atualiza a posi��o do fimG caso seja menor que o primeiro
		if(primeiro > fimG) {
			fimG = fimN;
		}
		
		//Atualiza a posi��o do fimP caso seja menor que o primeiro
		if(primeiro > fimP) {
			fimP = fimN;
		}
	
	}

//Programa
 public static void main(String[] args) {
	
Scanner leia = new Scanner(System.in);
Programa clinica = new Programa();		
		
int rodar = 1;
int opt;
char prioridade = 'q';

while(rodar == 1) { 	
	
	
System.out.print("\n Menu: \n (1) Adicionar um paciente \n (2) Exibir lista de pacientes \n (3) Atender pr�ximo da fila \n (4) Encerrar atendimento \n Digite uma op��o do menu: ");
	
//Caso digite uma op��o inv�lida volta ao menu
	while(!leia.hasNextInt()) {
		leia.next();
		System.out.print(" \n  Menu: \n (1) Adicionar um paciente \n (2) Exibir lista de pacientes \n (3) Atender pr�ximo da fila \n (4) Encerrar atendimento \n Digite uma op��o v�lida do menu: ");
	}
	
opt = leia.nextInt();
//Estava com um problema pois por algum motivo o scanner estava skipando o nextLine, e dando uma pesquisada no stackoverflow resolvi seguindo o coment�rio:
/* The problem occurs as you hit the enter key, which is a newline \n character. nextInt() consumes only the integer, but it skips the newline \n. 
To get around this problem, you may need to add an additional input.nextLine() after you read the int, which can consume the \n. */
leia.nextLine();
	
	
//Inserir um novo paciente atrav�s do m�todo inserir
if(opt == 1) { 
System.out.print("\n Digite o nome completo do paciente: ") ;	
String nome = leia.nextLine();

do {
System.out.print("\nDigite a prioridade do paciente: (G - Grave) (P - Priorit�rio) (N - Normal): ");
prioridade = leia.next().charAt(0);
}while(Character.toLowerCase(prioridade) != 'p' && Character.toLowerCase(prioridade) != 'g' && Character.toLowerCase(prioridade) != 'n');

clinica.inserir(nome, prioridade);


//Listar pacientes na fila
}else if(opt == 2) {
	
//Verifica se existe paciente na fila no momento
if(clinica.primeiro == clinica.fimN) {
System.out.print("\n N�o tem pacientes na fila no momento! \n");

}else {

System.out.println("\n Lista de pacientes na espera:\n");

System.out.print("(");

for(int i = clinica.primeiro; i <= (clinica.total-1); i++) {
	
System.out.print("["+clinica.lista[i]+"] ");

}
System.out.print(")\n");
		
}
		
//Chamar o m�todo de atender clientes	
}else if(opt == 3) {

	clinica.atender();
	
}else if(opt == 4) {	
	
rodar = 0;
System.out.print(" \n Atendimento encerrado! \n");
	
//Op��o inv�lida	
}else {
	System.out.print(" \n Op��o inv�lida! \n");
	leia.next();
}
	
	
	}
 }
}