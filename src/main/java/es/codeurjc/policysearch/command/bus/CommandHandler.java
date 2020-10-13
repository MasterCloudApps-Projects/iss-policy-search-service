package es.codeurjc.policysearch.command.bus;

import es.codeurjc.policysearch.command.api.Command;

public interface CommandHandler<R, C extends  Command<R>> {
    R handle(C command);
}
