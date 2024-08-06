package stone.modular.core;

import net.minecraftforge.eventbus.api.IEventBus;

public interface Proxy {
    public void init(IEventBus bus);
    public class Client extends Server {

    }

    public class Server implements Proxy {

        @Override
        public void init(IEventBus bus) {

        }

    }
}
