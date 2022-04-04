package com.futark.cros2;

import com.futark.cros2.entity.Control;
import com.futark.cros2.repository.BdRepo;
import com.futark.cros2.service.ControlService;
import com.futark.cros2.service.UtilisateurService;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Cros2Application {


	@Autowired
	UtilisateurService utilisateurService;

	@Autowired
	BdRepo bdRepo;

	@Autowired
	ControlService controlService;


	public static void main(String[] args) {
		SpringApplication.run(Cros2Application.class, args);
	}



	@Bean
	public CommandLineRunner run() {
		return (args) -> {

//			String publisherId = UUID.randomUUID().toString();
			String publisherId = "je sais pas trop ce que c est";

//			IMqttClient publisher = new MqttClient("tcp://iot.eclipse.org:1883",publisherId);
//			IMqttClient publisher = new MqttClient("tcp://localhost:1883",publisherId);
//			IMqttClient publisher = new MqttClient("ws://localhost:1883",publisherId);
			IMqttClient publisher = new MqttClient("tcp://localhost:9001",publisherId);

			MqttConnectOptions options = new MqttConnectOptions();
			options.setAutomaticReconnect(true);
			options.setCleanSession(true);
			options.setConnectionTimeout(10);

			publisher.connect(options);

			publisher.subscribe("test", (topic,msg)->{
				System.out.println("topic : "+topic);
				System.out.println("message : "+msg);
				Gson g = new Gson();
				Control control = g.fromJson(msg.toString(), Control.class);
//				System.out.println(control);
				controlService.saveControl(control);
			});

			publisher.subscribe("todo", (topic,msg)->{
				System.out.println("topic : "+topic);
				System.out.println("message : "+msg);
//				Gson g = new Gson();
//				Control control = g.fromJson(msg.toString(), Control.class);
//				controlService.saveControl(control);
			});

//			String topic = "test";
//			byte[] payload = "ceci est un test".getBytes();
//			MqttMessage mqttMessage = new MqttMessage(payload);
//			publisher.publish(topic,mqttMessage);
//			System.out.println("envoi message : "+mqttMessage);



//			for(int i =0; i<250;i++){
//				Bd bd  = new Bd();
//				bd.setTitre("titre_"+i);
//				bd.setTome("tome_"+(250-i));
//				bdRepo.save(bd);
//			}


//			controlService.saveControl(new Control(null,"LumSdbRdc",false,"lumiere salle de bain rez de chaussé","lumiere","rez de chausse","salle de bain"));
//			controlService.saveControl(new Control(null,"LumBuRdc",false,"lumiere buanderie rez de chaussé","lumiere","rez de chausse","buanderie"));
//			controlService.saveControl(new Control(null,"LumWcRdc",false,"lumiere WC rez de chaussé","lumiere","rez de chausse","wc"));





		};
	}

}
