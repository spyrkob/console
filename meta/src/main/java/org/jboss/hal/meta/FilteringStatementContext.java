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
package org.jboss.hal.meta;

/**
 * Intercepts the resolution and allows to filter/replace certain statement values.
 *
 * @author Heiko Braun
 */
public class FilteringStatementContext implements StatementContext {

    /**
     * Allows to modify keys and tuples. Methods should return {@code null} if no modification is necessary.
     */
    public interface Filter {

        String filter(String key);

        String[] filterTuple(String tuple);
    }


    private Filter filter;
    private StatementContext delegate;

    public FilteringStatementContext(StatementContext delegate, Filter filter) {
        this.delegate = delegate;
        this.filter = filter;
    }

    @Override
    public String resolve(String key) {
        String filtered = filter.filter(key);
        return filtered != null ? filtered : delegate.resolve(key);
    }

    @Override
    public String[] resolveTuple(String tuple) {
        String[] filtered = filter.filterTuple(tuple);
        return filtered != null ? filtered : delegate.resolveTuple(tuple);
    }

    @Override
    public String selectedProfile() {
        return delegate.selectedProfile();
    }

    @Override
    public String selectedServerGroup() {
        return delegate.selectedServerGroup();
    }

    @Override
    public String selectedHost() {
        return delegate.selectedHost();
    }

    @Override
    public String selectedServer() {
        return delegate.selectedServer();
    }
}
