package gui.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.prefs.Preferences;
import javax.swing.JPanel;

public class PageTracker {
    private static final String VISIT_COUNT_PREF_KEY_PREFIX = "visitCountForPage_";
    private final Preferences prefs;

    public PageTracker() {
        prefs = Preferences.userNodeForPackage(PageTracker.class);
    }

    public void pageVisited(JPanel page) {
        String pageName = page.getName();
        int visitCount = prefs.getInt(VISIT_COUNT_PREF_KEY_PREFIX + pageName, 0);
        prefs.putInt(VISIT_COUNT_PREF_KEY_PREFIX + pageName, visitCount + 1);
    }

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
