/*
*Programa que simula una taquilla de cine
*@author Magali Díaz
*@version 2.0
*/

import java.util.Scanner;

public class Maraton{


public static void main (String[] arreglo){
 int numfuncion=100;//guarda el numero de la funcion
 Funcion[] todasFunciones=new Funcion[7];
 int opcion;
 int i=0;
 String cargo;
 String tipoBoleto;
 String repetir="n";
 Scanner valorIngresado =new Scanner(System.in);
 coleccionFunciones cf;

cf=new coleccionFunciones(todasFunciones);

do{
	do{
System.out.println("******************************  Menú Auditorio ABC  ******************");
System.out.println("1)............................................Mostrar Cartelera");
System.out.println("2)............................................Compra de Boleto");
System.out.println("3)............................................Mostrar monto Recaudado por Funcion");
System.out.println("4)............................................Mostrar monto Recaudado por Boletos Generales");
System.out.println("5)............................................Mostrar monto Recaudado Total");
System.out.println("6)............................................Mostrar Lugares Disponibles");
System.out.println("7)............................................Peliculas Disponibles");
System.out.println("0)............................................Salir");
System.out.println("Elige la opción deseada (0 - 7).");
opcion =valorIngresado.nextInt();
valorIngresado.nextLine();//limpiamos buffer


switch  (opcion){
	case 1:
		System.out.println("***********************  Cartelera Auditorio ABC  ******************");
		cf.muestraArreglo();
		System.out.println("Deseas realizar otra operacion s\\ n");
			repetir=valorIngresado.nextLine();
		break;

	case 2:

		System.out.println("**********************  Compra de Boleto  **********************");
		System.out.println("Por favor indroduce la letra acorde a la opcion que deseas.");
		System.out.println("Tipo de Boleto : General  (G \\ g ), Individual  ( I \\ i ) ");
		tipoBoleto=valorIngresado.nextLine();
		//si el boleto es individual entra en el siguiente if
		if(tipoBoleto.toUpperCase().equals("I") ){
			do{
				System.out.println("**************Cartelera Auditorio ABC******************");
				cf.muestraArreglo();
				System.out.println("*************************************************************");
			    System.out.print("Por favor indroduce la funcion que deseas (1-6) o 0 para mostrar la cartelera y despues elgir función.\n F");
				//el número de la pelicula tambien se almacena en numfuncion
				numfuncion=valorIngresado.nextInt();

				valorIngresado.nextLine();//limpiamos buffer
				//si se elige una funcion valida :
				if(numfuncion>=1 && numfuncion<=cf.getFunciones().length){
					break;
				}
				else	{
					System.out.println("Opcion no valida.......");
					numfuncion=100;
				}
			}while(numfuncion==0);
		}

		//se elige un boleto general
     	else if(tipoBoleto.toUpperCase().equals("G") ){
     		  numfuncion=0;
     		 }

       else {
       	System.out.println("Opcion no valida.......");
     		  numfuncion=100;
   	       }

     	if (numfuncion!=100){
     		System.out.println("Proceder al pago? s\\n");
			cargo=valorIngresado.nextLine();

			if (cargo.toUpperCase().equals("S")){
					cf.getFuncionI(numfuncion).venderBoleto(cf.getFuncionI(numfuncion).getPelicula(),tipoBoleto.toUpperCase());//creamos el boleto
					cf.getFuncionI(numfuncion).sumaPorFuncion(cf.getFuncionI(numfuncion).getBoleto());
					cf.getFuncionI(numfuncion).sumaTotal(cf.getFuncionI(numfuncion).getBoleto());
					cf.getFuncionI(numfuncion).disminuirLugares();
					System.out.println("Su boleto es: ");
					System.out.println(cf.getFuncionI(numfuncion).getBoleto().toString());
		 	}
		 }

		System.out.println("Deseas realizar otra operacion s\\ n");
		repetir=valorIngresado.nextLine();
		break;

		case 3:
				System.out.println("Monto Recaudado por Función: "+ Funcion.getRecaudFuncion());
					System.out.println("Deseas realizar otra operacion s\\ n");
					repetir=valorIngresado.nextLine();
			break;

		case 4:
				System.out.println("Monto Recaudado por Boletos Generales: "+ Funcion.getRecaudGeneral());
					System.out.println("Deseas realizar otra operacion s\\ n");
					repetir=valorIngresado.nextLine();
			break;

		case 5:
				System.out.println("Monto Total Recaudado: "+ Funcion.getRecaudTotal());
					System.out.println("Deseas realizar otra operacion s\\ n");
					repetir=valorIngresado.nextLine();
			break;

		case 6:
				System.out.println("Lugares Disponibles: "+ Funcion.getLugarDisponible());
					System.out.println("Deseas realizar otra operacion s\\ n");
				repetir=valorIngresado.nextLine();
			break;

		case 7:
			System.out.println("Horario a consultar");
			cargo=valorIngresado.nextLine();
			System.out.println("*************************************************");
			System.out.println("Funciones disponibles depues de las: "+cargo);
			System.out.println("*************************************************");
			Funcion[] sobrantes=cf.peliculasSobrantes(cargo);
			 for(i=0;i<sobrantes.length;i++){
				System.out.println(sobrantes[i].toString());
				}
				if(i==0)
				System.out.println("No hay pelicuas disponibles de pues de las " +cargo);

			System.out.println("\n Deseas realizar otra operacion s\\ n");
			repetir=valorIngresado.nextLine();

		break;
		case 0:
		break;
		default :
			System.out.println("Opcion no valida.......");
			break;

		}
}while (opcion>7);

	}while	(repetir.toUpperCase().equals("S"));

	System.out.println("+++++++++++++BYE BYE+++++++++++++++");
    }

    /** Metodo para validar si el asiento en una funcion esta disponible
*/
private static String validaBoleto(coleccionFunciones cf,int numfuncion){
	String asiento="";
	Scanner valorIngresado=new Scanner(System.in);
	int c=0;
	String f="";
	do{
		System.out.print("\n Elige el asiento deseado para la funcion "+numfuncion+":  ");//solicitamos el asiento
		asiento=valorIngresado.nextLine();
		try{
			f=asiento.substring(0,1);
			c=Integer.parseInt(asiento.substring(1,2));
		}
		catch(Exception e) {
			System.out.println("\n--->El asiento no existe<----");
			continue;
		}
		System.out.println(f+c);
		//el metodo elegirAsiento regresa la cadena correspondiente al asiento si estuvo disponible
		if(cf.getFuncionI(numfuncion).elegirAsiento(f,c).equals(f+c)){
			cf.getFuncionI(0).elegirAsiento(f,c);//se debe tambien asignar en maraton, ya que no  estara disponible ese asiento
			break;
		}
		else if(cf.getFuncionI(numfuncion).elegirAsiento(f,c).equals("no"))
			System.out.println("\n--->El asiento no existe<----");
		else{
			System.out.println("\n--->El asiento "+ f+c+" no esta disponible<----");
		}
	}while(!cf.getFuncionI(numfuncion).elegirAsiento(f,c).equals(f+c));
	return asiento;//es la cadena con el asiento
}
}
