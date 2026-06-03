public class InboxMail {

    public void enviarNotificacionPorMail(CuentaBancaria persona, String mensaje){
        System.out.println("Enviando correo a " +persona.getTitular()+ ": " +mensaje);
    }
}