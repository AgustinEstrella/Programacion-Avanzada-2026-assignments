public class AplicacionBancaria {
    public static void main(String[] args){

        //Expandimos CuentaBancaria ya que corrompía el principio de abierto/cerrado
        //abrimos la función retirar para que se pueda ampliar, pero no modificar,
        //dividiendo la clase en CuentaCorriente y CajaAhorro, teniendo cada uno su manera de retirar
        //la clase CuentaBancaria no se cambia, sino que se puede cambiar la lógica de cada tipo de cuenta
        CuentaBancaria cuenta1 = new CuentaCorriente("Pepe", 20000, 1, 10000);
        cuenta1.depositar(500);
        cuenta1.retirar(200);

        CuentaBancaria cuenta2 = new CajaAhorro("Juan", 20000, 2);
        cuenta2.depositar(500);
        cuenta2.retirar(200);

        //Creamos la clase ImpresoraCuenta e InboxMail porque se rompía el principio de
        //responsabilidad unica, a la cuenta bancaria no se le debe
        //atribuir el funcionamiento de un sistema de impresion ni de envío de e-mails
        ImpresoraCuenta impresora = new ImpresoraCuenta();
        impresora.mostrarDatosCuenta(cuenta1);
        impresora.mostrarDatosCuenta(cuenta2);

        InboxMail inbox = new InboxMail();
        inbox.enviarNotificacionPorMail(cuenta1, "Notificación enviada!");
        inbox.enviarNotificacionPorMail(cuenta2, "Notificación enviada!");

    }
}