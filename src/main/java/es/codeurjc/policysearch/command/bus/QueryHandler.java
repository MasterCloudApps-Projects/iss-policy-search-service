package es.codeurjc.policysearch.command.bus;

import es.codeurjc.policysearch.command.api.Query;

public interface QueryHandler<R, C extends Query<R>> {
    R handle(C query);
}
