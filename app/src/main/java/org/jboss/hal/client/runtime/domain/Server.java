/*
 * Copyright 2015-2016 Red Hat, Inc, and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.hal.client.runtime.domain;

import org.jboss.hal.dmr.ModelNode;
import org.jboss.hal.dmr.model.NamedNode;

import static org.jboss.hal.dmr.ModelDescriptionConstants.HOST;
import static org.jboss.hal.dmr.ModelDescriptionConstants.NAME;
import static org.jboss.hal.dmr.ModelDescriptionConstants.SERVER_GROUP;

/**
 * Combination of the two resources {@code server-config} and {@code server}. Make sure to check the status before
 * reading server related attributes.
 *
 * @author Harald Pehl
 */
public class Server extends NamedNode {

    /**
     * Status as defined by {@code server-config.status}
     */
    public enum Status {
        STARTED, STOPPED, UNDEFINED
    }


    /**
     * State as defined by {@code server.server-state}
     */
    public enum State {
        STARTING, RUNNING, RESTART_REQUIRED, RELOAD_REQUIRED, UNDEFINED
    }


    public Server(final ModelNode node) {
        super(node.get(NAME).asString(), node);
    }

    @SuppressWarnings({"HardCodedStringLiteral", "DuplicateStringLiteralInspection"})
    public String getServerGroup() {
        if (hasDefined("group")) { // first try to read from server-config
            return get("group").asString();
        } else if (hasDefined(SERVER_GROUP)) { // then from server
            return get(SERVER_GROUP).asString();
        }
        return null;
    }

    public String getHost() {
        return hasDefined(HOST) ? get(HOST).asString() : null;
    }

    /**
     * @return the status as defined by {@code server-config.status}
     */
    @SuppressWarnings({"HardCodedStringLiteral", "DuplicateStringLiteralInspection"})
    public Status getStatus() {
        if (hasDefined("status")) {
            return Status.valueOf(get("status").asString().toUpperCase());
        }
        return Status.UNDEFINED;
    }

    /**
     * If this method returns {@code true} it's safe to read the server related attributes like "host", "server-state"
     * or "suspend-state".
     */
    public boolean isStarted() {
        return getStatus() == Status.STARTED;
    }

    /**
     * @return the state as defined by {@code server.server-status}
     */
    @SuppressWarnings({"HardCodedStringLiteral", "DuplicateStringLiteralInspection"})
    public State getState() {
        if (hasDefined("server-state")) {
            return State.valueOf(get("server-state").asString());
        }
        return State.UNDEFINED;
    }
}
