import java.io.File;
import java.io.FileReader;
import java.util.concurrent.Executors;

import com.google.gson.Gson;
import com.jambit.jambel.config.JambelConfiguration;
import com.jambit.jambel.config.SignalLightConfiguration;
import com.jambit.jambel.light.cmdctrl.CommandControlledSignalLight;
import com.jambit.jambel.light.cmdctrl.lan.LanCommandSender;

// Place your Spring DSL code here
beans = {
	
	Gson gson = new Gson();
	config = gson.fromJson(new FileReader(new File("jambel.json")), JambelConfiguration.class).getSignalLightConfiguration();
	
	commandSender(LanCommandSender, config)
	
	executor = Executors.newSingleThreadScheduledExecutor()
	
	signalLight(CommandControlledSignalLight, config, commandSender, executor)
	
}
