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
package org.jboss.hal.meta.token;

import java.util.Set;

@SuppressWarnings("DuplicateStringLiteralInspection")
public interface NameTokens {

    String ACCESS_CONTROL = "/access-control";
    String BATCH = "/batch-jberet/{profile}";
    String CONFIGURATION = "/configuration";
    String DATA_SOURCE = "/data-source/{profile}/{name}";
    String DEPLOYMENT_SCANNERS = "/deployment-scanners/{profile}";
    String DEPLOYMENTS = "/deployments";
    String EE = "/ee/{profile}";
    String EJB3 = "/ejb3/{profile}";
    String GENERIC_SUBSYSTEM = "/generic";
    String HOMEPAGE = "/home";
    String IIOP = "/iiop/{profile}";
    String INTERFACE = "/interface/{name}";
    String IO = "/io/{profile}";
    String JCA = "/jca/{profile}";
    String JMX = "/jmx/{profile}";
    String JPA = "/jpa/{profile}";
    String LOGGING = "/logging/{profile}";
    String MACRO_EDITOR = "/macro-editor";
    String MODEL_BROWSER = "/model-browser";
    String PATCHING = "/patching";
    String PATH = "/path";
    String REMOTING = "/remoting/{profile}";
    String REQUEST_CONTROLLER = "/request-controller/{profile}";
    String RUNTIME = "/runtime";
    String SOCKET_BINDING = "/socket-binding/{name}";
    String SYSTEM_PROPERTIES = "/system-properties";
    String TRANSACTIONS = "/transactions/{profile}";
    String UNDER_THE_BRIDGE = "/utb";
    String WEBSERVICES = "/webservices/{profile}";
    String XA_DATA_SOURCE = "/xa-data-source/{profile}/{name}";

    Set<String> getTokens();
}