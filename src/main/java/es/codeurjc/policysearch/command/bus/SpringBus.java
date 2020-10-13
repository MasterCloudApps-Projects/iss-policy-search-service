package es.codeurjc.policysearch.command.bus;

import es.codeurjc.policysearch.command.api.Command;
import es.codeurjc.policysearch.command.api.Query;

public class SpringBus implements Bus {
    private final Registry registry;

    public SpringBus(Registry registry) {
        this.registry = registry;
    }

    @Override
    public <R, C extends Command<R>> R executeCommand(C command) {
        CommandHandler<R, C> commandHandler = (CommandHandler<R, C>) registry.getCmd(command.getClass());
        return commandHandler.handle(command);
    }

    @Override
    public <R, Q extends Query<R>> R executeQuery(Q query) {
        QueryHandler<R, Q> commandHandler = (QueryHandler<R, Q>) registry.getQuery(query.getClass());
        return commandHandler.handle(query);
    }
}
