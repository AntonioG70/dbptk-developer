package com.databasepreservation.integration.roundtrip.differences;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * MySQL specific implementation to convert the source database dump to an expected version of the database dump
 * @author Bruno Ferreira <bferreira@keep.pt>
 */
public class MySqlDumpDiffExpectations extends DumpDiffExpectations {
        /**
         * List of regular expressions to match and replacements to apply to the source database dump
         */
        private static final ArrayList<Pair<Pattern, String>> directReplacements;

        static {
                directReplacements = new ArrayList<Pair<Pattern, String>>();

                directReplacements.add(
                  new ImmutablePair<Pattern, String>(Pattern.compile("(?<=\\W)tinyint\\(\\d+\\)(?=\\W)"),
                    "smallint(6)"));

                directReplacements.add(
                  new ImmutablePair<Pattern, String>(Pattern.compile("(?<=\\W)mediumint\\(\\d+\\)(?=\\W)"), "int(11)"));
        }

        @Override protected String expectedTargetDatabaseDump(String source) {
                String expectedTarget = source;
                for (Pair<Pattern, String> directReplacement : directReplacements) {
                        Pattern regex = directReplacement.getLeft();
                        String replacement = directReplacement.getRight();

                        expectedTarget = regex.matcher(expectedTarget).replaceAll(replacement);
                }
                return expectedTarget;
        }
}
