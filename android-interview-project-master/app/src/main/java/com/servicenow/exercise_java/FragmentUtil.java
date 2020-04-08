/**
 * FragmentUtil - A utility to reuse a fragment instead of creating a new fragment.
 * Reuse of a fragment is via looking up tagname of a fragment in fragment manager.
 *
 * @author Henry Song
 */
package com.servicenow.exercise_java;

import android.app.Fragment;
import android.app.FragmentManager;

import java.util.List;

public class FragmentUtil {
    public static Fragment getFragmentByTagName(FragmentManager manager, String name) {
        List<Fragment> fragments = manager.getFragments();
        if (fragments != null) {
            int size = fragments.size();
            for (Fragment f : fragments) {
                if (f != null ) {
                    String tag = f.getTag();
                    if (tag.equals(name))
                        return f;
                }
            }
        }
        return null;
    }
}
