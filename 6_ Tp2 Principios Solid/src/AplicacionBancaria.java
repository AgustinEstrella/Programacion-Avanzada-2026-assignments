import java.util.Scanner;

public class AplicacionBancaria {
    public static void main(String[] args){

        //Responsabilidad unica
        InboxMail inbox = new InboxMail();
        ImpresoraCuenta impresora = new ImpresoraCuenta();

        Scanner sc = new Scanner(System.in);

        System.out.println("ingrese su nombre");
        String nombre = sc.nextLine();
        System.out.println("Ingrese su sueldo");
        double sueldo = sc.nextDouble();
        System.out.println("Ingrese su id");
        int id = sc.nextInt();
        System.out.println("Ingrese 1 para cuenta corriente, 2 para caja de ahorro"); //Abierto/cerrado
        int tipo = sc.nextInt();

        if (tipo == 1){
            CuentaBancaria persona = new CuentaCorriente(nombre, sueldo, id, (sueldo*1.5));
            System.out.println("Seleccione cuanto desea depositar");
            double deposito = sc.nextDouble();
            persona.depositar(deposito);
            inbox.enviarNotificacionPorMail(persona, "Recibimos un deposito de $" +deposito+ " tu saldo ahora es de: $" +persona.getSaldo());

            System.out.println("Ingrese cuanto desea retirar");
            double retiro = sc.nextDouble();
            persona.retirar(retiro);
            inbox.enviarNotificacionPorMail(persona, "Recibimos un retiro de $" +retiro+ " tu saldo ahora es de: $" +persona.getSaldo());


        } else if (tipo == 2){
            CuentaBancaria persona = new CajaAhorro(nombre, sueldo, id);
            System.out.println("Seleccione cuanto desea depositar");
            double deposito = sc.nextDouble();
            persona.depositar(deposito);
            inbox.enviarNotificacionPorMail(persona, "Recibimos un deposito de $" +deposito+ " tu saldo ahora es de: $" +persona.getSaldo());


            System.out.println("Ingrese cuanto desea retirar");
            double retiro = sc.nextDouble();
            persona.retirar(retiro);
            inbox.enviarNotificacionPorMail(persona, "Recibimos un retiro de $" +retiro+ " tu saldo ahora es de: $" +persona.getSaldo());

        }

    }
}