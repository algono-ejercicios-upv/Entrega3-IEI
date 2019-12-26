package bd;

import java.util.Properties;

import org.camunda.bpm.engine.delegate.DelegateTask;

public class EnviarCorreoMock extends EnviarCorreo {
	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("Inicio Mock envio de correo");
		
		String destinatario = getEmail();
		String asunto = getAsunto();
		String cuerpo = getCuerpo();
		
		Properties props = initProperties();

		System.out.println("Fin Mock envio de correo");
		System.out.println("----------------");
		System.out.println("Datos obtenidos:");
		System.out.println("-----------------------------------");
		System.out.println("Destinatario: " + destinatario);
		System.out.println("----------------");
		System.out.println("Asunto: " + asunto);
		System.out.println("------------------------");
		System.out.println("Cuerpo: " + cuerpo);
		System.out.println("------------------------");
		System.out.println("Properties: " + props.toString());
		System.out.println("-----------------------------------");
	}
}
