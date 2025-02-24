package khalil.mysticforging;

import net.fabricmc.api.ClientModInitializer;

public class MysticForgingClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModClientEvents.register();
	}
}