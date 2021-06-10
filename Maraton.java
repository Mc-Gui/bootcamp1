//**
*Programa que simula una taquilla de cine
*@author Magali Díaz
*@version 3.0
*/

import java.util.Scanner;

public class Maraton{
	public static void main (String[] arreglo){
		int numfuncion=100;//guarda el numero de la funcion
		Funcion[] todasFunciones=new Funcion[7];
		int opcion;
		int i=0;
		boolean disponible=false;
		String cargo="",tipoBoleto="",asiento="", repetir="n";
		Scanner valorIngresado =new Scanner(System.in);
		coleccionFunciones cf;
		todasFunciones[0]=new Funcion("Maraton", "10:00-02:00");
		todasFunciones[1]=new Funcion(" El Seños de los Anillos;La comunidad del Anillo..........(F1)", "10:00-13:00");
		todasFunciones[2]=new Funcion("El Seños de los Anillos;Las dos Torres..........(F2)", "13:15-16:15");
		todasFunciones[3]=new Funcion("El Seños de los Anillos;EL Retorno del Rey..........(F3)", "16:30-20:00");
		todasFunciones[4]=new Funcion("La Noche del Demonio..........(F4)", "20:15-21:50");
		todasFunciones[5]=new Funcion("La Noche del Demonio II..........(F5)", "22:00-23:40");
		todasFunciones[6]=new Funcion("La Noche del Demonio III..........(F6)", "24:00-02:00");
		cf=new coleccionFunciones(todasFunciones);

		do{
			do{
				System.out.println("******************************  Menu Auditorio ABC  ******************");
				System.out.println("1)............................................Mostrar Cartelera");
				System.out.println("2)............................................Compra de Boleto");
				System.out.println("3)............................................Mostrar monto Recaudado por Funcion");
				System.out.println("4)............................................Mostrar monto Recaudado por Boletos Generales");
				System.out.println("5)............................................Mostrar monto Recaudado Total");
				System.out.println("6)............................................Mostrar Lugares Disponibles");
				System.out.println("7)............................................Peliculas Disponibles");
				System.out.println("0)............................................Salir");
				System.out.print("\n Elige la opcion deseada (0 - 7).-> ");
				opcion =valorIngresado.nextInt();
				valorIngresado.nextLine();//limpiamos buffer


				switch(opcion){
					case 1:
					System.out.println("\n ***********************  Cartelera Auditorio ABC  ******************");
					cf.muestraArreglo();
					System.out.print("\n Deseas realizar otra operacion s\\ n:  ");
					repetir=valorIngresado.nextLine();
					break;

					case 2:
					System.out.println("\n **********************  Compra de Boleto  **********************");
					System.out.println("\n Por favor indroduce la letra acorde a la opcion que deseas.");
					System.out.print("\n Tipo de Boleto : General  (G \\ g ), Individual  ( I \\ i ) ->");
					tipoBoleto=valorIngresado.nextLine();
					//si el boleto es individual entra en el siguiente if
					if(tipoBoleto.equalsIgnoreCase("I") ){
						do{
							System.out.println("\n**************Cartelera Auditorio ABC******************");
							cf.muestraArreglo();
							System.out.println("\n*************************************************************");
							System.out.print("Por favor indroduce la funcion que deseas (1-6). -> F");
							//el número de la pelicula tambien se almacena en numfuncion
							try{
								numfuncion=valorIngresado.nextInt();
								valorIngresado.nextLine();//limpiamos buffer
							}
							catch(Exception e){
								System.out.println("\n ++++++Opcion no valida+++++++");
								valorIngresado.nextLine();
								continue;
							}
							//si se elige una funcion valida :
							if(numfuncion>=1 && numfuncion<=cf.getFunciones().length){
								cf.getFuncionI(numfuncion).imprimirMatriz();
								asiento=validaBoleto(cf,numfuncion);
								break;
							}
							else{
								System.out.println("\n ++++++Opcion no valida+++++++");
								numfuncion=100;
							}
						}while(numfuncion==100);
					}

					//se elige un boleto general
					else if(tipoBoleto.equalsIgnoreCase("G")){
						numfuncion=0;
						System.out.print("\n Deseas tener el mismo boleto durante todas las peliculas s/n?  ");
						cargo=valorIngresado.nextLine();
						if(cargo.equalsIgnoreCase("s") || cargo.equalsIgnoreCase("n")){
							if(cargo.equalsIgnoreCase("S")){//se desea tener el mismo asiento en todos las funciones
								do{
									cf.getFuncionI(numfuncion).imprimirMatriz();
									System.out.print("Elige el asiento deseado:  ");//solicitamos el asiento
									asiento=valorIngresado.nextLine();
									disponible=validaBoleto(cf,asiento);
								}while(!disponible);
							}
							else{ //se elige un asiento diferente por funcion
								for(i=1;i<cf.getFunciones().length;i++){
									cf.getFuncionI(i).imprimirMatriz();
									asiento+="F"+i+" : "+validaBoleto(cf,i)+"\n";
								}
							}
						}
						else{
							System.out.println("\n ++++++Opcion no valida+++++++");
							System.out.print("\n Deseas realizar otra operacion s\\ n:  ");
							repetir=valorIngresado.nextLine();
							break;
						}
					}
					else{
						System.out.println("\n ++++++Opcion no valida+++++++");
						System.out.print("\n Deseas realizar otra operacion s\\ n:  ");
						repetir=valorIngresado.nextLine();
						break;
						}
					//si la opcion de funcion es correcta se procede a generar el boleto
					if (tipoBoleto.equalsIgnoreCase("G") || tipoBoleto.equalsIgnoreCase("I")){
						System.out.print("\n Proceder al pago? s\\n :  ");
						cargo=valorIngresado.nextLine();

						if (cargo.equalsIgnoreCase("S")){
							cf.getFuncionI(numfuncion).venderBoleto(cf.getFuncionI(numfuncion).getPelicula(),tipoBoleto.toUpperCase(),asiento);//creamos el boleto
							cf.getFuncionI(numfuncion).sumaPorFuncion(cf.getFuncionI(numfuncion).getBoleto());
							cf.getFuncionI(numfuncion).sumaTotal(cf.getFuncionI(numfuncion).getBoleto());
							//si la funcion es maraton deben disminuirse los lugares de todas las funciones
							if(numfuncion==0){
								for(i=0;i<cf.getFunciones().length;i++){
								cf.getFuncionI(i).disminuirLugares();
								}
							}
							else{cf.getFuncionI(numfuncion).disminuirLugares();
								cf.getFuncionI(0).disminuirLugares();//para cualquier funcion tambien se debe eliminar un lugar de maraton
							}
						System.out.println("\n Su boleto es: ");
						System.out.println(cf.getFuncionI(numfuncion).getBoleto().toString());
						}
					}
					System.out.println("\n Deseas realizar otra operacion s\\ n");
					repetir=valorIngresado.nextLine();
					break;

					case 3:
					System.out.println("\n Monto Recaudado por Función: "+ Funcion.getRecaudFuncion());
					System.out.print("\n Deseas realizar otra operacion s\\ n:  ");
					repetir=valorIngresado.nextLine();
					break;

					case 4:
					System.out.println("\nMonto Recaudado por Boletos Generales: "+ Funcion.getRecaudGeneral());
					System.out.print("\n Deseas realizar otra operacion s\\ n: ");
					repetir=valorIngresado.nextLine();
					break;

					case 5:
					System.out.println("\n Monto Total Recaudado: "+ Funcion.getRecaudTotal());
					System.out.print("\n Deseas realizar otra operacion s\\ n:  ");
					repetir=valorIngresado.nextLine();
					break;

					case 6:
					System.out.print("\n De cual funcion deseas conocer los lugares disponibles:  ");
					try{opcion=valorIngresado.nextInt();}
					catch(Exception e){
						System.out.println(" \n++++++Opcion no valida+++++++\n");
						break;
					}
					if(numfuncion<0 || numfuncion>6){
						System.out.println(" \n++++++Opcion no valida+++++++\n");
						break;
					}
					else
						valorIngresado.nextLine();
					System.out.print("\n Lugares Disponibles en la funcion "+opcion+": "+ cf.getFuncionI(opcion).getLugarDisponible());
					System.out.print("\n Deseas realizar otra operacion s\\ n:  ");
					repetir=valorIngresado.nextLine();
					break;

					case 7:
					System.out.println("Horario a consultar");
					cargo=valorIngresado.nextLine();
					System.out.println("*************************************************");
					System.out.println("\nFunciones disponibles depues de las: "+cargo);
					System.out.println("*************************************************");
					Funcion[] sobrantes=cf.peliculasSobrantes(cargo);
					for(i=0;i<sobrantes.length;i++){
						System.out.println(sobrantes[i].toString());
					}
					if(i==0)
						System.out.println("\nNo hay pelicuas disponibles de pues de las " +cargo);

					System.out.print("\n Deseas realizar otra operacion s\\ n:  ");
					repetir=valorIngresado.nextLine();
					break;

					case 0:
					break;
					default :
						System.out.println("\n++++++Opcion no valida+++++++");
					break;
				}
			}while (opcion>7);
		}while	(repetir.equalsIgnoreCase("S"));
	System.out.println("\n+++++++++++++BYE BYE+++++++++++++++");
   }
 }
