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
package org.jboss.hal.client.runtime.subsystem.batch;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import org.jboss.hal.core.finder.Finder;
import org.jboss.hal.core.finder.FinderPath;
import org.jboss.hal.core.finder.FinderPathFactory;
import org.jboss.hal.core.mvp.ApplicationFinderPresenter;
import org.jboss.hal.core.mvp.HalView;
import org.jboss.hal.core.mvp.HasPresenter;
import org.jboss.hal.dmr.Operation;
import org.jboss.hal.dmr.ResourceAddress;
import org.jboss.hal.dmr.dispatch.Dispatcher;
import org.jboss.hal.meta.StatementContext;
import org.jboss.hal.resources.Ids;
import org.jboss.hal.resources.Names;
import org.jboss.hal.resources.Resources;
import org.jboss.hal.spi.Requires;

import static elemental2.dom.DomGlobal.alert;
import static org.jboss.hal.client.runtime.subsystem.batch.AddressTemplates.BATCH_DEPLOYMENT_JOB_ADDRESS;
import static org.jboss.hal.client.runtime.subsystem.batch.AddressTemplates.BATCH_DEPLOYMENT_JOB_TEMPLATE;
import static org.jboss.hal.client.runtime.subsystem.batch.AddressTemplates.BATCH_SUBDEPLOYMENT_JOB_TEMPLATE;
import static org.jboss.hal.dmr.ModelDescriptionConstants.*;
import static org.jboss.hal.meta.token.NameTokens.JOB;

public class JobPresenter extends ApplicationFinderPresenter<JobPresenter.MyView, JobPresenter.MyProxy> {

    // @formatter:off
    @ProxyCodeSplit
    @NameToken(JOB)
    @Requires(BATCH_DEPLOYMENT_JOB_ADDRESS)
    public interface MyProxy extends ProxyPlace<JobPresenter> {}

    public interface MyView extends HalView, HasPresenter<JobPresenter> {
        void update(JobNode job);
    }
    // @formatter:on


    private final FinderPathFactory finderPathFactory;
    private final Dispatcher dispatcher;
    private final StatementContext statementContext;
    private final Resources resources;
    private String deployment;
    private String subdeployment;
    private String job;

    @Inject
    public JobPresenter(final EventBus eventBus,
            final MyView view,
            final MyProxy myProxy,
            final Finder finder,
            final FinderPathFactory finderPathFactory,
            final Dispatcher dispatcher,
            final StatementContext statementContext,
            final Resources resources) {
        super(eventBus, view, myProxy, finder);
        this.finderPathFactory = finderPathFactory;
        this.dispatcher = dispatcher;
        this.statementContext = statementContext;
        this.resources = resources;
    }

    @Override
    protected void onBind() {
        super.onBind();
        getView().setPresenter(this);
    }

    @Override
    public void prepareFromRequest(final PlaceRequest request) {
        super.prepareFromRequest(request);
        deployment = request.getParameter(DEPLOYMENT, null);
        subdeployment = request.getParameter(SUBDEPLOYMENT, null);
        job = request.getParameter(NAME, null);
    }

    @Override
    public FinderPath finderPath() {
        return finderPathFactory.runtimeServerPath()
                .append(Ids.SERVER_MONITOR, Ids.asId(Names.BATCH), resources.constants().monitor(), Names.BATCH)
                .append(Ids.JOB, Ids.job(deployment, subdeployment, job), Names.JOB, job);
    }

    @Override
    protected void reload() {
        ResourceAddress address;
        if (subdeployment == null) {
            address = BATCH_DEPLOYMENT_JOB_TEMPLATE.resolve(statementContext, deployment, job);
        } else {
            address = BATCH_SUBDEPLOYMENT_JOB_TEMPLATE.resolve(statementContext, deployment, subdeployment, job);
        }
        Operation operation = new Operation.Builder(address, READ_RESOURCE_OPERATION)
                .param(INCLUDE_RUNTIME, true)
                .param(RECURSIVE, true)
                .build();
        dispatcher.execute(operation, result -> getView().update(new JobNode(address, result)));
    }

    void restartExecution(ExecutionNode execution) {
        alert(Names.NYI);
    }

    void stopExecution(ExecutionNode execution) {
        alert(Names.NYI);
    }

    void pollRunningExecutions() {

    }
}
