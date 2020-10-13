package es.codeurjc.policysearch.command.bus;

import es.codeurjc.policysearch.command.api.Command;
import es.codeurjc.policysearch.command.api.Query;

public interface Bus {
    <R,C extends Command<R>> R executeCommand(C command);
    <R,Q extends Query<R>> R executeQuery(Q query);
}
