package org.jboss.set.aphrodite.issue.trackers.jira;

import org.jboss.set.aphrodite.config.TrackerType;
import org.jboss.set.aphrodite.domain.Issue;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Romain Pelisse <belaran@redhat.com> on 20/04/16.
 */
public class JiraIssue extends Issue {

    private List<URL> pullRequests = new ArrayList<URL>();

    public JiraIssue(URL url, TrackerType type) {
        super(url, type);
        if ( ! type.equals(TrackerType.JIRA) )
            throw new IllegalStateException("Can't instantiate if issue is not of JIRA type");
    }

    public List<URL> getPullRequests() {
        return pullRequests;
    }

    public void setPullRequests(List<URL> pullRequests) {
        this.pullRequests = pullRequests;
    }

}
