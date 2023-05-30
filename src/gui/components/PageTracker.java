package gui.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.prefs.Preferences;
import javax.swing.JPanel;

public class PageTracker {
    private static final String VISIT_COUNT_PREF_KEY_PREFIX = "visitCountForPage_";
    private final Preferences prefs;

    // This is the constructor for the `PageTracker` class. It initializes a `Preferences` object that
    // is used to store and retrieve data related to page visits. The `userNodeForPackage` method
    // returns a `Preferences` object that is specific to the package of the `PageTracker` class. This
    // ensures that the preferences are stored in a location that is unique to this application and not
    // shared with other applications.
    public PageTracker() {
        prefs = Preferences.userNodeForPackage(PageTracker.class);
    }

    /**
     * The function increments the visit count of a JPanel page and saves it in preferences.
     * 
     * @param page A JPanel object representing the page that has been visited.
     */
    public void pageVisited(JPanel page) {
        String pageName = page.getName();
        int visitCount = prefs.getInt(VISIT_COUNT_PREF_KEY_PREFIX + pageName, 0);
        prefs.putInt(VISIT_COUNT_PREF_KEY_PREFIX + pageName, visitCount + 1);
    }

    /**
     * This Java function retrieves the top 5 visited pages from a list of page visit counts stored in
     * preferences.
     * 
     * @return A list of the top 5 visited pages, sorted in descending order by visit count. If there
     * are less than 5 pages, it will return all the pages sorted in descending order. If there is an
     * exception, it will return an empty list.
     */
    public List<String> getTop5VisitedPages() {
        try {
            String[] keys = prefs.keys();
            List<PageVisitCount> visitCounts = new ArrayList<>();
            for (String key : keys) {
                if (key.startsWith(VISIT_COUNT_PREF_KEY_PREFIX)) {
                    String pageName = key.substring(VISIT_COUNT_PREF_KEY_PREFIX.length());
                    int visitCount = prefs.getInt(key, 0);
                    visitCounts.add(new PageVisitCount(pageName, visitCount));
                }
            }

            // Sort the visit counts in descending order and get the top 5
            visitCounts.sort(Collections.reverseOrder());
            List<String> top5Pages = new ArrayList<>();
            for (int i = 0; i < Math.min(5, visitCounts.size()); i++) {
                top5Pages.add(visitCounts.get(i).getPageName());
            }

            return top5Pages;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * The class represents a page visit count with a page name and visit count, and implements the
     * Comparable interface to compare visit counts.
     */
    private static class PageVisitCount implements Comparable<PageVisitCount> {
        private final String pageName;
        private final int visitCount;

        public PageVisitCount(String pageName, int visitCount) {
            this.pageName = pageName;
            this.visitCount = visitCount;
        }

        public String getPageName() {
            return pageName;
        }

        public int getVisitCount() {
            return visitCount;
        }

        @Override
        public int compareTo(PageVisitCount other) {
            return Integer.compare(this.visitCount, other.visitCount);
        }
    }
}
