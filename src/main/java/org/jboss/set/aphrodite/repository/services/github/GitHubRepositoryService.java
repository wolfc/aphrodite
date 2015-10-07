/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2015, Red Hat, Inc., and individual contributors
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

package org.jboss.set.aphrodite.repository.services.github;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.UserService;
import org.jboss.set.aphrodite.common.Utils;
import org.jboss.set.aphrodite.config.RepositoryConfig;
import org.jboss.set.aphrodite.domain.Repository;
import org.jboss.set.aphrodite.repository.services.common.AbstractRepositoryService;
import org.jboss.set.aphrodite.spi.NotFoundException;
import org.jboss.set.aphrodite.spi.RepositoryService;

import java.io.IOException;
import java.net.URL;

/**
 * @author Ryan Emerson
 */
public class GitHubRepositoryService extends AbstractRepositoryService {

    private static final Log LOG = LogFactory.getLog(RepositoryService.class);
    private GitHubClient gitHubClient;

    public GitHubRepositoryService() {
        super("github");
    }

    @Override
    protected Log getLog() {
        return LOG;
    }

    @Override
    public boolean init(RepositoryConfig config) {
        boolean parentInitiated = super.init(config);
        if (!parentInitiated)
            return false;

        try {
            gitHubClient = GitHubClient.createClient(baseUrl.toString());
            gitHubClient.setCredentials(config.getUsername(), config.getPassword());
            new UserService(gitHubClient).getUser();
        } catch (IOException e) {
            Utils.logException(LOG, "Authentication failed for RepositoryService: " + this.getClass().getName(), e);
            return false;
        }
        return true;
    }

    @Override
    public Repository getRepository(URL url) throws NotFoundException {
        return super.getRepository(url);
    }
}