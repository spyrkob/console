/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.hal.meta.description;

import com.google.gwt.resources.client.TextResource;
import org.jboss.hal.dmr.ModelNode;

/**
 * @author Harald Pehl
 */
public final class StaticResourceDescription {

    private StaticResourceDescription() {}

    public static ResourceDescription from(TextResource resource) {
        try {
            return new ResourceDescription(ModelNode.fromBase64(resource.getText()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unable to read static resource description from " + resource.getName());
        }
    }
}